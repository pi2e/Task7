package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.DepositCheckForm;
import com.cfs.utility.CommonUtilities;

public class DepositCheckAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositCheckForm.class);

	public DepositCheckAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "depositCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		Customer customer = null;

		try {

			String userID = (String) request.getParameter("custId");
			customer = customerDAO.read(Integer.parseInt(userID));
			request.setAttribute("customer", customer);

			DepositCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "depositcheck.jsp";
			}

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "depositcheck.jsp";
			}

			// create transaction
			FundTransaction transaction = new FundTransaction();
			transaction.setTransactionType("deposit");
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setAmount(CommonUtilities.moneyToLong(Double
					.parseDouble(form.getAmount())));
			transactionDAO.create(transaction);
			
			request.setAttribute("successMessage", "Deposit queued successfully.");
			
			return "viewCustomerTransaction.do?custId=" + customer.getCustomerId();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			errors.add(e.toString());
		}
		return "depositcheck.jsp";
	}
}
