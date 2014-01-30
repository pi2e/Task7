package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.BuyFundForm;
import com.cfs.utility.CommonUtilities;

public class BuyFundAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyFundForm.class);

	public BuyFundAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
	}

	@Override
	public String getName() {
		return "buyFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		Customer customer = null;

		try {

			// fund list
			Fund[] funds = fundDAO.getFunds();
			List<String> fundPrices = new ArrayList<String>();
			List<String> priceDifference = new ArrayList<String>();

			for (int i = 0; i < funds.length; i++) {
				FundPriceData fundData = fundPriceHistoryDAO
						.fetchLatestPrice(funds[i].getFundId());

				if (fundData == null) {
					fundPrices.add("");
					priceDifference.add("");
				} else {

					fundPrices.add(CommonUtilities.convertToMoney((fundData
							.getPrice())));

					FundPriceData[] latestPrices = fundPriceHistoryDAO
							.fetchLatestPrices(funds[i].getFundId());

					// get price difference
					if (latestPrices.length > 1) {
						String price = CommonUtilities
								.convertToMoney(latestPrices[0].getPrice()
										- latestPrices[1].getPrice());

						String percentage = CommonUtilities
								.formatPrice((double) (((latestPrices[0]
										.getPrice() - latestPrices[1]
										.getPrice()) * 100 / latestPrices[1]
										.getPrice())));

						priceDifference.add(price + "  (" + percentage + "%) ");
					} else {
						priceDifference.add("");
					}

				}

			}

			request.setAttribute("funds", funds);
			request.setAttribute("fundPrices", fundPrices);
			request.setAttribute("priceDifference", priceDifference);

			HttpSession session = request.getSession();
			customer = (Customer) session.getAttribute("user");

			int userID = customer.getCustomerId();
			customer = customerDAO.read(userID);

			String balance = CommonUtilities.convertToMoney(customer
					.getBalance());
			request.setAttribute("balance", balance);

			BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (request.getParameter("buyFund") != null) {
				form.setTicker(request.getParameter("buyFund").toString());
				form.setAmount("");
				form.setPresent(true);

				return "buyfund.jsp";
			}

			if (!form.isPresent()) {
				return "buyfund.jsp";
			}

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "buyfund.jsp";
			}

			// check balance

			double amount = Double.parseDouble(form.getAmount());
			// String balanceStr = CommonUtilities.removeCommas(balance);
			/*
			 * if(amount > Double.parseDouble(balanceStr)) {
			 * errors.add("You do not have sufficient balance"); }
			 * if(errors.size() != 0) { return "buyfund.jsp"; }
			 */

			// find fund
			Fund fund = fundDAO.getFund(form.getTicker());
			request.setAttribute("fund", fund);
			if (fund == null) {
				errors.add("Fund not found");
			}
			if (errors.size() != 0) {
				return "buyfund.jsp";
			}

			// update balance
			// customer.setBalance(customer.getBalance() -
			// CommonUtilities.moneyToLong(amount));
			// customerDAO.update(customer);
			if (!customerDAO.update(customer.getCustomerId(),
					-CommonUtilities.moneyToLong(amount), 0)) {

				errors.add("You do not have sufficient balance");
				return "buyfund.jsp";
			}

			// create transaction
			FundTransaction transaction = new FundTransaction();
			transaction.setTransactionType("buy");
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setAmount(CommonUtilities.moneyToLong(amount));
			transaction.setFundId(fund.getFundId());
			transactionDAO.create(transaction);

			request.setAttribute("successMessage",
					"Buy order queued successfully");
			return "viewCustomerTransaction.do";

		} catch (DAOException e) {
			errors.add(e.toString());
			return "buyfund.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "buyfund.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "buyfund.jsp";
		}
	}
}
