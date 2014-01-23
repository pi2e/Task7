package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.PositionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.Model;
import com.cfs.databean.Position;
import com.cfs.formbean.CustomerFundVO;
import com.cfs.utility.CommonUtilities;

public class ViewFundAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
	}

	@Override
	public String getName() {
		return "viewFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		Fund fund  =  null;
		
		try {
			long fundId = Long.parseLong(request.getParameter("fundId").toString());
			fund = fundDAO.getFund(fundId);
			request.setAttribute("fund", fund);
			
			String price = CommonUtilities.convertToMoney(fundPriceHistoryDAO.fetchLatestPrice(fundId).getPrice());
			request.setAttribute("price", price);
			
		} catch (DAOException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "customerinfo.jsp";
		} 

		return "fundinfo.jsp";
	}

}
