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
	public String perform(HttpServletRequest request) {
		
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
			Date lastdate = oldprices[oldprices.length - 1].getPriceDate();
			request.setAttribute("lastdate", formatter.format(lastdate));
			
			TransitionDayForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {;
				return "transitionday.jsp";
			}
			
			// Get the date of the transition day
			
			java.util.Date date = formatter.parse(form.getInputdate());
			Date newdate = new Date(date.getTime());
			
			if(newdate.compareTo(lastdate) < 1) {
				errors.add("Inpute date must be after last date");
			}
			
			
			
			LinkedList<FundPriceData> price = new LinkedList<FundPriceData>();
			for(Fund fund : funds) {
				FundPriceData fundprice = new FundPriceData();
				fundprice.setFundId(fund.getFundId());
				fundprice.setPrice(Long.parseLong(request.getParameter(Long.toString(fund.getFundId()))));
				fundprice.setPriceDate(newdate);
				price.add(fundprice);
			}
			
			System.out.println(price.size());
			
			for(FundPriceData data : price) {
				fundpriceDAO.create(data);
			}
			
			FundTransaction[] transctions = transactionDAO.getPendingTransactions();
			
			for(FundTransaction tran : transctions) {
				tran.setExecuteDate(newdate);
				transactionDAO.update(tran);
				long customerid = tran.getCustomerId();
				long amount = tran.getAmount();
				long fundid = tran.getFundId();
				long shares = tran.getShares();
				long fundprice = Long.parseLong(request.getParameter(Long.toString(fundid)));

				if(tran.getTransactionType().equals("sell")) {
					Position bean = positionDAO.match(MatchArg.equals("fundId", fundid),MatchArg.equals("customerId", customerid))[0];
					bean.setShares(bean.getShares() - shares);
					positionDAO.update(bean);
					
					Customer cbean = customerDAO.match(MatchArg.equals("customerId", customerid))[0];
					cbean.setCash(cbean.getCash() + shares*fundprice);
					cbean.setBalance(cbean.getCash());
					customerDAO.update(cbean);
				}
				else {
					long share = amount / fundprice;
					Position bean;
					Position[] beans = positionDAO.match(MatchArg.equals("fundId", fundid),MatchArg.equals("customerId", customerid));
					if(beans == null) {
						bean = new Position();
						bean.setCustomerId(customerid);
						bean.setFundId(fundid);
						bean.setShares(share);
						positionDAO.create(bean);
					} else {
						bean = beans[0];
						bean.setShares(bean.getShares() + share);
						positionDAO.update(bean);
					}
					
					Customer cbean = customerDAO.match(MatchArg.equals("customerId", customerid))[0];
					cbean.setCash(cbean.getCash() - share*fundprice);
					cbean.setBalance(cbean.getCash());
					customerDAO.update(cbean);
				}
			}
			
			System.out.println("success");
			
			
			
			
		} catch (RollbackException e) {
			return "transitionday.jsp";
		} catch (FormBeanException e) {
			
			e.printStackTrace();
		} catch (NumberFormatException e) {
			errors.add("Inpute price must be valid integer");
			e.printStackTrace();
			return "transitionday.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			errors.add("Inpute date is not a valid date format");
			e.printStackTrace();
			return "transitionday.jsp";
		}
		
		
		
		return "transitionday.jsp";
	}

}
