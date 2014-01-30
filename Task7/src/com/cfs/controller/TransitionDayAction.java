package com.cfs.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.databean.Position;
import com.cfs.formbean.*;
import com.cfs.utility.CommonUtilities;

public class TransitionDayAction extends Action {

	private FormBeanFactory<TransitionDayForm> formBeanFactory = FormBeanFactory
			.getInstance(TransitionDayForm.class);

	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundpriceDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;

	public TransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundpriceDAO = model.getFundPriceDAO();
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() {
		return "transitionday.do";
	}

	@Override
	public synchronized String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			formatter.setLenient(false);

			request.setAttribute("inputdate", request.getParameter("inputdate"));

			Fund[] funds = fundDAO.getFunds();
			request.setAttribute("funds", funds);

			String[] lastprices = new String[funds.length];
			request.setAttribute("lastprices", lastprices);

			String[] inputprice = new String[funds.length];
			for (int i = 0; i < funds.length; i++) {
				inputprice[i] = request.getParameter(Long.toString(funds[i]
						.getFundId()));
				FundPriceData lastdata = fundpriceDAO.fetchLatestPrice(funds[i]
						.getFundId());
				if (lastdata == null)
					lastprices[i] = null;
				else
					lastprices[i] = CommonUtilities.convertToMoney(lastdata
							.getPrice());

			}
			request.setAttribute("inputprice", inputprice);

			FundPriceData[] oldprices = fundpriceDAO.match();
			Date lastdate = null;
			if (oldprices.length > 0) {
				lastdate = oldprices[oldprices.length - 1].getPriceDate();
				request.setAttribute("lastdate", formatter.format(lastdate));
			}

			TransitionDayForm form = formBeanFactory.create(request);

			if (!form.isPresent()) {
				;
				return "transitionday.jsp";
			}

			// Get the date of the transition day

			java.util.Date date = formatter.parse(form.getInputdate());
			Date newdate = new Date(date.getTime());

			if (lastdate != null && newdate.compareTo(lastdate) < 1) {
				errors.add("Inpute date must be after last date");
			}

			LinkedList<FundPriceData> price = new LinkedList<FundPriceData>();
			for (int i = 0; i < funds.length; i++) {
				Fund fund = funds[i];
				FundPriceData fundprice = new FundPriceData();
				fundprice.setFundId(fund.getFundId());
				String input = request
						.getParameter(Long.toString(fund.getFundId())).trim()
						.replaceAll(",", "");
				int decimal = input.lastIndexOf('.');
				if (decimal != -1 && input.length() - decimal > 3) {
					errors.add("You can not specify more the two decimal");
					break;

				}
				if (input == null || input.length() == 0) {
					if (lastprices[i] == null) {
						errors.add("You must input the price if the fund doesn't have current price");
						break;
					}
					input = lastprices[i].replace(",", "");
				}
				double tmp = Double.parseDouble(input);
				if (tmp > 10000) {
					errors.add("fund price can't be more than 10000 dollars");
					break;
				}
				if (tmp <= 0) {
					errors.add("fund price must be positive");
					break;
				}
				fundprice.setPrice(CommonUtilities.moneyToLong(tmp));
				fundprice.setPriceDate(newdate);
				price.add(fundprice);
			}

			if (errors.size() > 0)
				return "transitionday.jsp";

			for (FundPriceData data : price) {
				fundpriceDAO.create(data);
			}

			FundTransaction[] transctions = transactionDAO
					.getPendingTransactions();

			for (FundTransaction tran : transctions) {
				tran.setExecuteDate(newdate);

				int customerid = tran.getCustomerId();
				double amount = CommonUtilities.longToMoney(tran.getAmount());
				int fundid = tran.getFundId();
				double shares = CommonUtilities.longToShares(tran.getShares());

				if (tran.getTransactionType().equals("sell")) {
					double fundprice = CommonUtilities.longToMoney(fundpriceDAO
							.fetchLatestPrice(fundid).getPrice());
					Long change = CommonUtilities.moneyToLong(shares
							* fundprice);
					if (change == 0) {
						positionDAO.update(customerid, fundid,
								tran.getShares(), 0);
						tran.setTransactionType("sell cancelled");
					} else {
						positionDAO.update(customerid, fundid, 0,
								-tran.getShares());
						customerDAO.update(customerid, change, change);
						tran.setAmount(change);
					}
				} else if (tran.getTransactionType().equals("buy")) {
					double fundprice = CommonUtilities.longToMoney(fundpriceDAO
							.fetchLatestPrice(fundid).getPrice());
					long share = CommonUtilities
							.shareToLong(amount / fundprice);
					Position bean = positionDAO.getPosition(customerid, fundid);
					if (bean == null) {
						bean = new Position();
						bean.setCustomerId(customerid);
						bean.setAvailableShares(share);
						bean.setFundId(fundid);
						bean.setShares(share);
						positionDAO.create(bean);
					} else {
						positionDAO.update(customerid, fundid, share, share);
					}

					customerDAO.update(customerid, 0, -tran.getAmount());
					tran.setShares(share);
				} else if (tran.getTransactionType().equals("withdraw")) {
					customerDAO.update(customerid, 0, -tran.getAmount());
				} else {
					customerDAO.update(customerid, tran.getAmount(),
							tran.getAmount());
				}
				transactionDAO.update(tran);
			}

			for (int i = 0; i < funds.length; i++) {
				FundPriceData lastdata = fundpriceDAO.fetchLatestPrice(funds[i]
						.getFundId());
				if (lastdata == null)
					lastprices[i] = null;
				else
					lastprices[i] = CommonUtilities.convertToMoney(lastdata
							.getPrice());

			}

			request.setAttribute("lastdate", newdate);
			request.setAttribute("inputdate", null);
			request.setAttribute("inputprice", null);
			request.setAttribute("successMessage", "Prices have been updated!");

			return "transitionday.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "transitionday.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "transitionday.jsp";
		} catch (NumberFormatException e) {
			errors.add("Inpute price must be valid number");
			return "transitionday.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "transitionday.jsp";
		} catch (ParseException e) {
			errors.add("Inpute date is not a valid date format");
			return "transitionday.jsp";
		}

	}

}
