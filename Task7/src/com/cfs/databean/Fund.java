package com.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class Fund {

	private long fundId;
	private String fundName;
	private String symbol;
	
	public long getFundId() {
		return fundId;
	}
	public void setFundId(long fundId) {
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
	
}
