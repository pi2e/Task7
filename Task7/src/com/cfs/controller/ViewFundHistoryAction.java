package com.cfs.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;

import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.Model;
import com.cfs.utility.CommonUtilities;

public class ViewFundHistoryAction extends Action {

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewFundHistoryAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "viewHistory.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		Fund fund = null;
		try {
			int fundId = Integer.parseInt(request.getParameter("fundId")
					.toString());
			fund = fundDAO.getFund(fundId);
			request.setAttribute("fund", fund);

			FundPriceData[] priceHistoryData = fundPriceHistoryDAO
					.fetchLatestPrices(fundId);
			String[] priceDifference = new String[priceHistoryData.length - 1];
			String[] percentageDifference = new String[priceHistoryData.length - 1];
			String[] price = new String[priceHistoryData.length];
			int i = 0;
			if (priceHistoryData.length > 1) {
				for (i = 0; i < priceHistoryData.length - 1; i++) {
					String priceDif = CommonUtilities
							.convertToMoney(priceHistoryData[i].getPrice()
									- priceHistoryData[i + 1].getPrice());

					String percentage = CommonUtilities
							.formatPrice((double) (((priceHistoryData[i]
									.getPrice() - priceHistoryData[i + 1]
									.getPrice()) * 100 / priceHistoryData[1]
									.getPrice())));

					priceDifference[i] = priceDif;
					percentageDifference[i] = percentage + "%";
					price[i] = CommonUtilities
							.convertToMoney(priceHistoryData[i].getPrice());
				}
			}
			price[i] = CommonUtilities.convertToMoney(priceHistoryData[i]
					.getPrice());

			request.setAttribute("priceHistoryData", priceHistoryData);
			request.setAttribute("priceRecord", price);
			request.setAttribute("price", price[0]);
			if (priceDifference != null && priceDifference.length != 0) {
				request.setAttribute("todayPriceChange", priceDifference[0]);
			}

			if (percentageDifference != null
					&& percentageDifference.length != 0) {
				request.setAttribute("todayPercentChange",
						percentageDifference[0]);
			}

			if (priceDifference != null && priceDifference.length != 0) {
				request.setAttribute("priceDifference", priceDifference);
			}

			if (percentageDifference != null
					&& percentageDifference.length != 0) {
				request.setAttribute("percentageDifference",
						percentageDifference);
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}

		return "viewFundHistory.jsp";
	}
}
