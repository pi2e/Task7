package com.cfs.formbean;

public class CustomerFundVO {

	private long fundId;
	private String fundName;
	private String ticker;
	private String shares;
	private String availableShares;
	private String currentPrice;
	private String positionValue;

	public String getAvailableShares() {
		return availableShares;
	}

	public void setAvailableShares(String availableShares) {
		this.availableShares = availableShares;
	}

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

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setPositionValue(String positionValue) {
		this.positionValue = positionValue;
	}

	public String getPositionValue() {
		return positionValue;
	}

}
