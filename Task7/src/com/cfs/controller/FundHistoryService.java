package com.cfs.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cfs.databean.FundPriceData;
import com.cfs.utility.CommonUtilities;

public class FundHistoryService {

	Calendar calendar = Calendar.getInstance();

	public List<FundPriceData> getFundListForPeriod(FundPriceData[] fundList,
			Long year) {
		List<FundPriceData> newFundList = new ArrayList<FundPriceData>();
		for (FundPriceData fundPriceData : fundList) {
			calendar.setTime(fundPriceData.getPriceDate());
			if (calendar.get(Calendar.YEAR) == year) {
				newFundList.add(fundPriceData);
			}
		}

		return newFundList;

	}

	public long findMaxFundValue(FundPriceData[] fundList, long year) {

		List<FundPriceData> newFundList = getFundListForPeriod(fundList, year);
		long maxValue = -1;
		if (newFundList != null) {
			if (newFundList.size() != 0) {
				if (!(newFundList.isEmpty())) {
					maxValue = newFundList.get(0).getPrice();
					for (FundPriceData newFundPriceData : newFundList) {
						if (newFundPriceData.getPrice() > maxValue) {
							maxValue = newFundPriceData.getPrice();
						}
					}
				}
			}
		}
		return maxValue;

	}

	public long findMinFundValue(FundPriceData[] fundList, long year) {
		List<FundPriceData> newFundList = getFundListForPeriod(fundList, year);
		long minValue = -1;
		if (newFundList != null) {
			if (!newFundList.isEmpty()) {
				minValue = newFundList.get(0).getPrice();
				for (FundPriceData newFundPriceData : newFundList) {

					if (newFundPriceData.getPrice() < minValue) {
						minValue = newFundPriceData.getPrice();
					}
				}
			}
		}

		return minValue;

	}

	public List<Object[]> populateGraph(FundPriceData[] latestPrices,
			String period) {
		List<Object[]> array = new ArrayList<Object[]>();
		calendar.setTime(latestPrices[0].getPriceDate());
		int year = calendar.get(Calendar.YEAR);
		if (period.equals("1")) {
			array.add(new Object[] { "Date for Year " + year, "Fund Value" });
			for (int i = latestPrices.length -1; i >= 0; i--) {
				System.out.println(latestPrices[i].getPriceDate());
				if(i >= 10){
					continue;
				}
				calendar.setTime(latestPrices[i].getPriceDate());
				int yearRecord = calendar.get(Calendar.YEAR);
				if (yearRecord == year) {
					array.add(new Object[] {
							(Date) latestPrices[i].getPriceDate(),
							CommonUtilities.convertToMoney(latestPrices[i]
									.getPrice()) });
				}
			}

		}

		return array;

	}
}
