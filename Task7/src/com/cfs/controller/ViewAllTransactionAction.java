package com.cfs.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.TransactionVO;

public class ViewAllTransactionAction extends Action {

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewAllTransactionAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
	}

	@Override
	public String getName() {
		return "viewAllTransactions.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			FundTransaction[] pendingTransactionsAll = transactionDAO
					.getPendingTransactions();
			FundTransaction[] executedTransactionsAll = transactionDAO
					.getExecutedTransactions();

			List<TransactionVO> pendingTransactions = new ArrayList<TransactionVO>();
			List<TransactionVO> executedTransactions = new ArrayList<TransactionVO>();
			List<Customer> customersPending = new ArrayList<Customer>();
			List<Customer> customersExecuted = new ArrayList<Customer>();

			// pending transactions
			for (int i = pendingTransactionsAll.length-1; i>=0 ; i--) {

				FundTransaction transaction = pendingTransactionsAll[i];

				// deposits and withdrawals
				if (transaction.getTransactionType().equals("deposit")
						|| transaction.getTransactionType().equals("withdraw")) {
					TransactionVO t = new TransactionVO(transaction);
					pendingTransactions.add(t);
				}

				if (transaction.getTransactionType().equals("buy")
						|| transaction.getTransactionType().equals("sell")) {
					Fund fund = fundDAO.read(transaction.getFundId());

					TransactionVO t = new TransactionVO(transaction, fund);
					pendingTransactions.add(t);
				}

				customersPending.add(customerDAO.read(transaction
						.getCustomerId()));
			}

			// executed transactions
			for (int i = executedTransactionsAll.length-1; i>=0 ; i--) {

				FundTransaction transaction = executedTransactionsAll[i];

				if (transaction.getTransactionType().equals("deposit")
						|| transaction.getTransactionType().equals("withdraw")) {

					TransactionVO t = new TransactionVO(transaction);
					executedTransactions.add(t);

				}

				if (transaction.getTransactionType().equals("buy")
						|| transaction.getTransactionType().equals("sell")
						|| transaction.getTransactionType().equals(
								"sell cancelled")) {

					Fund fund = fundDAO.read(transaction.getFundId());

					Date date = transaction.getExecuteDate();
					FundPriceData fundPrice = fundPriceHistoryDAO.read(
							fund.getFundId(), date);

					TransactionVO t = new TransactionVO(transaction, fund,
							fundPrice.getPrice());
					executedTransactions.add(t);

				}

				customersExecuted.add(customerDAO.read(transaction
						.getCustomerId()));

			}

			request.setAttribute("pendingTransactions", pendingTransactions);
			request.setAttribute("executedTransactions", executedTransactions);
			request.setAttribute("customersPending", customersPending);
			request.setAttribute("customersExecuted", customersExecuted);

			return "adminTransHistory.jsp";

		} catch (Exception e) {
			errors.add(e.getMessage());
			return "adminTransHistory.jsp";
		}

	}
}
