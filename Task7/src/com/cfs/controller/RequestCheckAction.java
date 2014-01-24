package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.RequestCheckForm;
import com.cfs.utility.CommonUtilities;

public class RequestCheckAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	public RequestCheckAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();

	}

	@Override
	public String getName() {

		return "requestCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		Customer customer = null;

		try {

			customer = (Customer) request.getSession().getAttribute("user");
			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("availablebalance",
					CommonUtilities.convertToMoney((customer.getBalance())));
			System.out.println("balance"
					+ CommonUtilities.convertToMoney((customer.getBalance())));
			request.setAttribute("ledgerBalance",
					CommonUtilities.convertToMoney((customer.getCash())));
			request.setAttribute("form", form);
			request.setAttribute("customer", customer);
			if (!form.isPresent()) {
				return "requestCheck.jsp";
			}
			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "requestCheck.jsp";
			}

			//Check if the withdraw amount is more than the balance
			if (customer.getBalance() < Double.parseDouble(form
					.getWithdrawAmount())) {
				errors.add("Withdraw amount must be less than Balance amount");
				return "requestCheck.jsp";
			}

			// create transaction
			FundTransaction transaction = new FundTransaction();
			transaction.setTransactionType("withdraw");
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setAmount(CommonUtilities.moneyToLong(Double
					.parseDouble(form.getWithdrawAmount())));
			
			transactionDAO.create(transaction);
			return "viewCustomerTransaction.do?custId="
					+ customer.getCustomerId();
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "requestCheck.jsp";
		} catch (Exception e) {
			errors.add(e.toString());
			return "requestCheck.jsp";
		}
	}

}
