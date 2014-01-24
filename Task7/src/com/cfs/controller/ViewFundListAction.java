package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.databean.Fund;
import com.cfs.databean.FundPriceData;
import com.cfs.databean.Model;
import com.cfs.utility.CommonUtilities;

public class ViewFundListAction extends Action {

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewFundListAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceDAO();
	}

	@Override
	public String getName() {
		return "viewFundList.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		if (request.getAttribute("success") != null) {

			request.setAttribute("success", "success");
		}

		try {
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

					if (latestPrices.length > 1) {
						String price = CommonUtilities
								.convertToMoney(latestPrices[0].getPrice()
										- latestPrices[1].getPrice());
						
						String percentage = CommonUtilities.formatPrice((double)(((latestPrices[0].getPrice() - latestPrices[1].getPrice())*100 / 
								latestPrices[1].getPrice())));
						
						priceDifference.add(price + "  (" + percentage +"%) ");
					} else {
						priceDifference.add("");
					}

				}

			}

			request.setAttribute("funds", funds);
			request.setAttribute("fundPrices", fundPrices);
			request.setAttribute("priceDifference", priceDifference);
			return "fundlist.jsp";

		} catch (Exception e) {
			errors.add(e.getMessage());
			return "fundlist.jsp";
		}

	}

}
