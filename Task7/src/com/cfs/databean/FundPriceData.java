package com.cfs.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,priceDate")
public class FundPriceData implements Comparable<FundPriceData>{

	private int fundId;
	private Date priceDate;
	private long price;
	
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
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
	@Override
	public int compareTo(FundPriceData data) {
		
		return data.getPriceDate().compareTo(priceDate);
	}
	
	
}
