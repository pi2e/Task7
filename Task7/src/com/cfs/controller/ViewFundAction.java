package com.cfs.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;

import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.Model;
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
		String period = (String)request.getParameter("period");
		String action = (String)request.getParameter("subaction");
		if(action != null && action.equals("view")){
			return "viewHistory.do";
		}
		if(period == null){
			period = "1";
		}

		Fund fund = null;
		String price = null;

		try {
			int fundId = Integer.parseInt(request.getParameter("fundId")
					.toString());
			fund = fundDAO.getFund(fundId);
			request.setAttribute("fund", fund);

			if (fundPriceHistoryDAO.fetchLatestPrice(fundId) == null) {
				price = "price unavailable";
			} else {
				price = CommonUtilities.convertToMoney(fundPriceHistoryDAO
						.fetchLatestPrice(fundId).getPrice());
			}
			request.setAttribute("price", price);

			// price difference
			String priceDifference = null;
			request.setAttribute("priceDifference", priceDifference);

			FundPriceData fundData = fundPriceHistoryDAO.fetchLatestPrice(fund
					.getFundId());

			if (fundData == null) {
				priceDifference = "";
			} else {

				FundPriceData[] latestPrices = fundPriceHistoryDAO
						.fetchLatestPrices(fund.getFundId());

				if (latestPrices.length > 1) {
					String priceDif = CommonUtilities
							.convertToMoney(latestPrices[0].getPrice()
									- latestPrices[1].getPrice());

					String percentage = CommonUtilities
							.formatPrice((double) (((latestPrices[0].getPrice() - latestPrices[1]
									.getPrice()) * 100 / latestPrices[1]
									.getPrice())));

					priceDifference = (priceDif + "  (" + percentage + "%) ");

				} else {
					priceDifference = "";
				}

				// Set the Min and Max for an year
				
				request.setAttribute("period", period);
				FundHistoryService fundService = new FundHistoryService();
				if (latestPrices != null) {
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(latestPrices[0].getPriceDate());
					 int latestyear = cal.get(Calendar.YEAR);
					 
					Long maxValue = fundService.findMaxFundValue(latestPrices,
							latestyear);
					Long minValue = fundService.findMinFundValue(latestPrices,
							latestyear);
					String min = minValue != null ? CommonUtilities
							.convertToMoney(minValue)
							: "No Price Found";
					String max = maxValue != null ? CommonUtilities
							.convertToMoney(maxValue)
							: "No Price Found";
					request.setAttribute("year", latestyear);
					request.setAttribute("maxValue", max);
					request.setAttribute("minValue", min);
					request.setAttribute("priceDifference", priceDifference);
				}

			// Populate the graph
				
				List<Object[]> arrayData = fundService.populateGraph(latestPrices, period);
				request.setAttribute("arrayData", arrayData);
				
			}

		} catch (DAOException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "customerinfo.jsp";
		}

		return "fundinfo.jsp";
	}

}
