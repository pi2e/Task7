package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.DepositCheckForm;
import com.cfs.utility.CommonUtilities;

public class DepositMultipleCheckAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public DepositMultipleCheckAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "depositMultipleCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			Customer[] customers = customerDAO.getAllCustomers();
			request.setAttribute("customers", customers);

			List<String> depositList = new ArrayList<String>();
			request.setAttribute("depositList", depositList);

			boolean inputError = false;
			
			List<String> cash = new ArrayList<String>();
			List<String> balance = new ArrayList<String>();
			
			request.setAttribute("cash", cash);
			request.setAttribute("balance", balance);
			
			
			//check for input errors
			for (int i = 0; i < customers.length; i++) {
				long id = customers[i].getCustomerId();
				String amount = request.getParameter(String.valueOf(id)).trim();

				if (amount.length() == 0) {
					amount = "0";
				}

				DepositCheckForm form = new DepositCheckForm();
				form.setUserId(String.valueOf(id));
				form.setAmount(amount);
				depositList.add(amount);

				if(form.getValidationErrors().size() != 0) {
					inputError = true;
				}
				
				cash.add(CommonUtilities.convertToMoney(customers[i].getCash()));
				balance.add(CommonUtilities.convertToMoney(customers[i].getBalance()));
			}

			// if any errors, return 
			if (inputError) {
				errors.add("Deposits must be positive numeric amounts with up to 2 decimal places and less than 1,000,000,000,000 dollars");
				return "customerlist.jsp";
			}
			
			// if no errors, create transaction
			for (int i = 0; i < customers.length; i++) {
				int id = customers[i].getCustomerId();
				String amount = request.getParameter(String.valueOf(id)).trim();
				
				if (!amount.equals("") && !amount.equals("0")) {
					
					FundTransaction transaction = new FundTransaction();
					transaction.setTransactionType("deposit");
					transaction.setCustomerId(id);
					transaction.setAmount(CommonUtilities.moneyToLong(Double.parseDouble(amount)));
					transactionDAO.create(transaction);
					request.setAttribute("successMessage", "Deposits queued successfully");
					
				}

			}
			
			depositList.clear();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			errors.add(e.toString());
			return "customerlist.jsp";
		}
		return "customerlist.jsp";
	}
}
