package com.cfs.formbean;

import java.sql.Date;

import com.cfs.databean.Fund;
import com.cfs.databean.FundTransaction;
import com.cfs.utility.CommonUtilities;

public class TransactionVO {

	private long transactionId;
	private long customerId;
	private long fundId;
	private String fundName;
	private String fundTicker;
	private Date executeDate;
	private String shares;
	private String transactionType;
	private String amount;
	private String availableShares;
	private String price;
	
	//for deposit & withdraw
	public TransactionVO(FundTransaction transaction) {
		this.transactionId = transaction.getTransactionId();
		this.customerId = transaction.getCustomerId();
		this.executeDate = transaction.getExecuteDate();
		this.transactionType = transaction.getTransactionType();
		this.amount = CommonUtilities.convertToMoney((transaction.getAmount()));
	}
	
	//for buy & sell
	public TransactionVO(FundTransaction transaction, Fund fund, long price) {
		this.transactionId = transaction.getTransactionId();
		this.customerId = transaction.getCustomerId();
		this.fundId = transaction.getFundId();
		this.executeDate = transaction.getExecuteDate();
		this.shares = CommonUtilities.convertToShare(transaction.getShares());
		this.transactionType = transaction.getTransactionType();
		this.amount = CommonUtilities.convertToMoney(transaction.getAmount());
		
		this.fundName = fund.getFundName();
		this.fundTicker = fund.getSymbol();
		
		this.price = CommonUtilities.convertToMoney(transaction.getAmount());
	}
	
	public String getFundTicker() {
		return fundTicker;
	}
	public void setFundTicker(String fundTicker) {
		this.fundTicker = fundTicker;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getAmount() {
		return amount;
	}
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAvailableShares() {
		return availableShares;
	}
	public void setAvailableShares(String availableShares) {
		this.availableShares = availableShares;
	}
	
	
}
