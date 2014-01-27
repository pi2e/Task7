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
			int userId = customer.getCustomerId();
			// get latest customer from database
			customer = customerDAO.read(userId);

			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			String balance = CommonUtilities.convertToMoney((customer
					.getBalance()));
			request.setAttribute("availablebalance", balance);

			request.setAttribute("ledgerBalance",
					CommonUtilities.convertToMoney((customer.getCash())));

			request.setAttribute("customer", customer);

			if (!form.isPresent()) {
				return "requestCheck.jsp";
			}

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "requestCheck.jsp";
			}

			double amount = Double.parseDouble(form.getWithdrawAmount());
			// Check if the withdraw amount is more than the balance
			/*
			if (balance != null) {
				String balanceStr = CommonUtilities.removeCommas(balance);

				if (Double.parseDouble(balanceStr) < amount) {
					errors.add("You do not have sufficient balance");
					return "requestCheck.jsp";
				}
			}
			*/

			// update balance
			if (!customerDAO.update(customer.getCustomerId(),
					-CommonUtilities.moneyToLong(amount), 0)) {
				errors.add("You do not have sufficient balance");
				return "requestCheck.jsp";
			}
			//	customer.setBalance(customer.getBalance()
			//			- CommonUtilities.moneyToLong(amount));
			//customerDAO.update(customer);

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
