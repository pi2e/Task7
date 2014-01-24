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
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Fund;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.SellFundForm;
import com.cfs.utility.CommonUtilities;
import com.cfs.databean.Position;;

public class SellFundAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);

	public SellFundAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() {
		return "sellFund.do";
	}

	@Override
	public String perform(HttpServletRequest request){

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		Customer customer = null;
		
		try {
			
			HttpSession session = request.getSession();
			customer = (Customer)session.getAttribute("user");
			
			int userID = customer.getCustomerId();
			customer = customerDAO.read(userID);
			
			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return "sellfund.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return "sellfund.jsp";
			}
			
			double amount = Double.parseDouble(form.getShares());
			
			//find fund
			Fund fund = fundDAO.getFund(form.getTicker());
			request.setAttribute("fund", fund);
			if(fund == null) {
				errors.add("Fund not found");
			}
			if(errors.size() != 0) {
				return "sellfund.jsp";
			}
			
			//check position
			Position position = positionDAO.getPosition(userID, fund.getFundId());
			if(amount > CommonUtilities.longToShares(position.getAvailableShares())) {
				errors.add("You do not have sufficient shares");
			}
			if(errors.size() != 0) {
				return "sellfund.jsp";
			}
			
			//create transaction
			FundTransaction transaction = new FundTransaction();
			transaction.setTransactionType("sell");
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setShares(CommonUtilities.shareToLong(amount));
			transaction.setFundId(fund.getFundId());
			transactionDAO.create(transaction);
			
			//update share balance
			position.setAvailableShares(position.getAvailableShares() - CommonUtilities.shareToLong(amount));
			positionDAO.update(position);
			
			request.setAttribute("successMessage", "Buy order queued successfully");
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
