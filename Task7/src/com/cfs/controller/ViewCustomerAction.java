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
//import com.cfs.formbean.TransactionVO;
import com.cfs.utility.CommonUtilities;

public class ViewCustomerAction extends Action {

	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewCustomerAction(Model model) {

		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
	}

	@Override
	public String getName() {

		return "viewcustomer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		Customer customer = null;

		try {
			
			if (request.getSession().getAttribute("user") instanceof Customer) {
				
				customer = (Customer) request.getSession().getAttribute("user");
				System.out.println(customer);
				
			} else if (request.getParameter("custId") != null) {

				String userID = (String) request.getParameter("custId");
				customer = customerDAO.read(Integer.parseInt(userID));
			}
			if (customer == null) {
				errors.add("Unexpected error occured.");
				return "home.jsp";
			}
			request.setAttribute("customer", customer);
			request.setAttribute("balance",
					CommonUtilities.convertToMoney((customer.getBalance())));
			request.setAttribute("ledgerBalance",
					CommonUtilities.convertToMoney((customer.getCash())));

			// get customer's funds
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
				System.out.println(positions[i].getShares());
				System.out.println(CommonUtilities.convertToShare(positions[i]
						.getShares()));
				fundVO.setPositionValue(CommonUtilities.calculatePosition(
						fundData.getPrice(), positions[i].getShares()));

				fundVOList.add(fundVO);
			}

			request.setAttribute("fundVOList", fundVOList);

		} catch (DAOException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "customerinfo.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "customerinfo.jsp";
		}

		return "customerinfo.jsp";
	}

}
