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
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.CustomerFundVO;
import com.cfs.formbean.SellFundForm;
import com.cfs.utility.CommonUtilities;
import com.cfs.databean.Position;;

public class SellFundAction extends Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);

	public SellFundAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
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
			
			//get funds
			List<CustomerFundVO> fundVOList = new ArrayList<CustomerFundVO>();
			Position[] positions = positionDAO.getCustomerFunds(customer
					.getCustomerId());

			for (int i = 0; i < positions.length; i++) {

				CustomerFundVO fundVO = new CustomerFundVO();
				int fundId = positions[i].getFundId();

				Fund fund = fundDAO.read(fundId);

				FundPriceData fundData = fundPriceHistoryDAO
						.fetchLatestPrice(fundId);

				fundVO.setFundId(fundId);
				fundVO.setFundName(fund.getFundName());
				fundVO.setTicker(fund.getSymbol());
				fundVO.setCurrentPrice(CommonUtilities.convertToMoney(fundData
						.getPrice()));
				fundVO.setShares(CommonUtilities.convertToShare(positions[i]
						.getShares()));
				fundVO.setAvailableShares(CommonUtilities.convertToShare(positions[i]
						.getAvailableShares()));
				fundVO.setPositionValue(CommonUtilities.calculatePosition(
						fundData.getPrice(), positions[i].getShares()));

				fundVOList.add(fundVO);
			}
			
			request.setAttribute("fundVOList", fundVOList);
			
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
			Fund fundSell = fundDAO.getFund(form.getTicker());
			request.setAttribute("fund", fundSell);
			if(fundSell == null) {
				errors.add("Fund not found");
			}
			if(errors.size() != 0) {
				return "sellfund.jsp";
			}
			
			//check position
			Position position = positionDAO.getPosition(userID, fundSell.getFundId());
			if(position == null || amount > CommonUtilities.longToShares(position.getAvailableShares())) {
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
			transaction.setFundId(fundSell.getFundId());
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
