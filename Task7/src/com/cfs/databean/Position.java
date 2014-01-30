package com.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId,fundId")
public class Position {

	private int customerId;
	private int fundId;
	private long shares;
	private long availableShares;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public long getAvailableShares() {
		return availableShares;
	}

	public void setAvailableShares(long availableShares) {
		this.availableShares = availableShares;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}
}
