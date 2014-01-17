package com.cfs.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,priceDate")
public class FundPriceData {

	private long fundId;
	private Date priceDate;
	private long price;
	
	public long getFundId() {
		return fundId;
	}
	public void setFundId(long fundId) {
		this.fundId = fundId;
	}
	public Date getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	
}
