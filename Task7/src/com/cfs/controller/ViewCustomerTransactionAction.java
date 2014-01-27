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

public class ViewCustomerTransactionAction extends Action {

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewCustomerTransactionAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
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

			if (request.getSession().getAttribute("user") instanceof Customer) {

				customer = (Customer) request.getSession().getAttribute("user");
			} else if (request.getParameter("custId") != null) {

				String userID = (String) request.getParameter("custId");
				customer = customerDAO.read(Integer.parseInt(userID));
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
					Fund fund = fundDAO.read(transaction.getFundId());
					
					TransactionVO t = new TransactionVO(transaction,fund);
					pendingTransactions.add(t);
				}

			}

			// executed transactions
			for (int i = 0; i < executedTransactionsAll.length; i++) {

				FundTransaction transaction = executedTransactionsAll[i];
				System.out.println(transaction.getTransactionType());
				
				
				if (transaction.getTransactionType().equals("deposit")
						|| transaction.getTransactionType()
								.equals("withdraw")) {

					if (transaction.getExecuteDate() != null) {

						TransactionVO t = new TransactionVO(transaction);
						executedTransactions.add(t);

					}
				}
				
				if (transaction.getTransactionType().equals("buy")
						|| transaction.getTransactionType().equals("sell")) {
					
					Fund fund = fundDAO.read(transaction.getFundId());
					
					if (transaction.getExecuteDate() != null) {
					
						Date date = transaction.getExecuteDate();
						System.out.println(date);
						FundPriceData fundPrice = fundPriceHistoryDAO.read(fund.getFundId(), date);
						
						TransactionVO t = new TransactionVO(transaction, fund, fundPrice.getPrice());
						executedTransactions.add(t);
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
