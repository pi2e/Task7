package com.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId,fundId")
public class Position {
	
	private long customerId;
	private long fundId;
	private long shares;
	private long availableShares;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getAvailableShares() {
		return availableShares;
	}
	public void setAvailableShares(long availableShares) {
		this.availableShares = availableShares;
	}
	public long getFundId() {
		return fundId;
	}
	public void setFundId(long fundId) {
		this.fundId = fundId;
	}
	public long getShares() {
		return shares;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
}
