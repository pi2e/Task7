package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.TransactionVO;
import com.cfs.utility.CommonUtilities;

public class ViewCustomerTransactionAction extends Action {

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public ViewCustomerTransactionAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "viewCustomerTransaction.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		Customer customer = null;

		try {

			if (request.getSession().getAttribute("user").getClass()
					.equals("Customer")) {

				customer = (Customer) request.getSession().getAttribute("user");
			} else if (request.getParameter("custId") != null) {

				String userID = (String) request.getParameter("custId");
				customer = customerDAO.read(Long.parseLong(userID));
			}

			FundTransaction[] pendingTransactionsAll = transactionDAO
					.getPendingTransaction(customer.getCustomerId());
			FundTransaction[] executedTransactionsAll = transactionDAO
					.getTransactions(customer.getCustomerId());

			List<TransactionVO> pendingTransactions = new ArrayList<TransactionVO>();
			List<TransactionVO> executedTransactions = new ArrayList<TransactionVO>();

			// pending transactions
			for (int i = 0; i < pendingTransactionsAll.length; i++) {

				FundTransaction transaction = pendingTransactionsAll[i];

				// deposits and withdrawals
				if (transaction.getTransactionType().equals("deposit")
						|| transaction.getTransactionType()
								.equals("withdraw")) {
					TransactionVO t = new TransactionVO(transaction);
					pendingTransactions.add(t);
				}

				if (transaction.getTransactionType().equals("buy")
						|| transaction.getTransactionType().equals("sell")) {
					// TransactionVO t = new TransactionVO(transaction, fund,
					// price);
					// pendingTransactions.add(t);
				}

			}

			// executed transactions
			for (int i = 0; i < executedTransactionsAll.length; i++) {

				FundTransaction transaction = executedTransactionsAll[i];

				if (transaction.getTransactionType().equals("deposit")
						|| transaction.getTransactionType()
								.equals("withdrawal")) {

					if (transaction.getExecuteDate() != null) {

						TransactionVO t = new TransactionVO(transaction);
						executedTransactions.add(t);

					}

					if (transaction.getTransactionType().equals("buy")
							|| transaction.getTransactionType().equals("sell")) {
						// TransactionVO t = new TransactionVO(transaction,
						// fund, price);
						// executedTransactions.add(t);
					}
				}

			}

			request.setAttribute("pendingTransactions", pendingTransactions);
			request.setAttribute("executedTransactions", executedTransactions);
			request.setAttribute("customer", customer);
			
			return "customerTransHistory.jsp";

		} catch (Exception e) {
			errors.add(e.getMessage());
			return "customerTransHistory.jsp";
		}

	}
}
