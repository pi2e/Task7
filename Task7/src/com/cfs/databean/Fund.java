package com.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class Fund implements Comparable<Fund>{

	private int fundId;
	private String fundName;
	private String symbol;
	
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public int compareTo(Fund fund) {
		int retval = symbol.compareTo(fund.getSymbol());
		return retval;
	}
	
}
