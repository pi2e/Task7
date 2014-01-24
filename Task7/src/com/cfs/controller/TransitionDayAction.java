package com.cfs.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.databean.Position;
import com.cfs.formbean.*;
import com.cfs.utility.CommonUtilities;

public class TransitionDayAction extends Action{
	
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
			
			request.setAttribute("inputdate", request.getParameter("inputdate"));
			
			Fund[] funds = fundDAO.getFunds();
			request.setAttribute("funds", funds);
			
			String[] inputprice = new String[funds.length];
			for(int i = 0; i < funds.length; i++) {
				inputprice[i] = request.getParameter(Long.toString(funds[i].getFundId()));
				
			}
			request.setAttribute("inputprice", inputprice);
			
			
			FundPriceData[] oldprices = fundpriceDAO.match();
			Date lastdate = null;
			if(oldprices.length > 0) {
			    lastdate = oldprices[oldprices.length - 1].getPriceDate();
			    request.setAttribute("lastdate", formatter.format(lastdate));
			}
			
			
			TransitionDayForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {;
				return "transitionday.jsp";
			}
			
			// Get the date of the transition day
			
			java.util.Date date = formatter.parse(form.getInputdate());
			Date newdate = new Date(date.getTime());
			
			if(lastdate != null && newdate.compareTo(lastdate) < 1) {
				errors.add("Inpute date must be after last date");
			}
			
			
			
			LinkedList<FundPriceData> price = new LinkedList<FundPriceData>();
			for(Fund fund : funds) {
				FundPriceData fundprice = new FundPriceData();
				fundprice.setFundId(fund.getFundId());
				String input = request.getParameter(Long.toString(fund.getFundId()));
				if(input == null || input.length() == 0)  {
					errors.add("Must input price of every fund");
					break;
				}
				double tmp = Double.parseDouble(input);
				fundprice.setPrice(CommonUtilities.moneyToLong(tmp));
				fundprice.setPriceDate(newdate);
				price.add(fundprice);
			}
			
			if(errors.size() > 0) return "transitionday.jsp";
			
			for(FundPriceData data : price) {
				fundpriceDAO.create(data);
			}
			
			FundTransaction[] transctions = transactionDAO.getPendingTransactions();
			
			for(FundTransaction tran : transctions) {
				tran.setExecuteDate(newdate);
				
				int customerid = tran.getCustomerId();
				double amount = CommonUtilities.longToMoney(tran.getAmount());
				int fundid = tran.getFundId();
				double shares = CommonUtilities.longToShares(tran.getShares());
			
				
				if(tran.getTransactionType().equals("sell")) {
					double fundprice = CommonUtilities.longToMoney(fundpriceDAO.fetchLatestPrice(fundid).getPrice());
					Position bean = positionDAO.getPosition(customerid, fundid);
					bean.setShares(bean.getShares() - tran.getShares());
					positionDAO.update(bean);
					
					Customer cbean = customerDAO.match(MatchArg.equals("customerId", customerid))[0];
					cbean.setCash(cbean.getCash() + CommonUtilities.moneyToLong(shares*fundprice));
					cbean.setBalance(cbean.getBalance() + CommonUtilities.moneyToLong(shares*fundprice));
					customerDAO.update(cbean);
					tran.setAmount(CommonUtilities.moneyToLong(shares*fundprice));
				}
				else if(tran.getTransactionType().equals("buy")) {
					double fundprice = CommonUtilities.longToMoney(fundpriceDAO.fetchLatestPrice(fundid).getPrice());
					long share = CommonUtilities.shareToLong(amount / fundprice);
					Position bean = positionDAO.getPosition(customerid, fundid);
					if(bean == null) {
						bean = new Position();
						bean.setCustomerId(customerid);
						bean.setAvailableShares(share);
						bean.setFundId(fundid);
						bean.setShares(share);
						positionDAO.create(bean);
					} else {
						bean.setShares(bean.getShares() + share);
						bean.setAvailableShares(bean.getAvailableShares() + share);
						positionDAO.update(bean);
					}
					
					Customer cbean = customerDAO.match(MatchArg.equals("customerId", customerid))[0];
					cbean.setCash(cbean.getCash() - tran.getAmount());
					customerDAO.update(cbean);
					tran.setShares(share);
				}
				else if (tran.getTransactionType().equals("check")) {
					Customer cbean = customerDAO.match(MatchArg.equals("customerId", customerid))[0];
					cbean.setCash(cbean.getCash() - tran.getAmount());
					customerDAO.update(cbean);
				}
				else {
					Customer cbean = customerDAO.match(MatchArg.equals("customerId", customerid))[0];
					cbean.setCash(cbean.getCash() + tran.getAmount());
					cbean.setBalance(cbean.getBalance() + tran.getAmount());
					customerDAO.update(cbean);
				}
				transactionDAO.update(tran);
			}
			
			
			request.setAttribute("lastdate", newdate);
			request.setAttribute("inputdate", null);
			request.setAttribute("inputprice", null);
			errors.add("Prices have been updated!");
			
			return "transitionday.jsp";
			
		} catch (RollbackException e) {
			return "transitionday.jsp";
		} catch (FormBeanException e) {
			
			e.printStackTrace();
			return "transitionday.jsp";
		} catch (NumberFormatException e) {
			errors.add("Inpute price must be valid integer");
			e.printStackTrace();
			return "transitionday.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "transitionday.jsp";
		} catch (ParseException e) {
			errors.add("Inpute date is not a valid date format");
			e.printStackTrace();
			return "transitionday.jsp";
		}
		
		
		
	}

}
