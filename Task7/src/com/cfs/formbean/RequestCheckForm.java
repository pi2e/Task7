package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {

	private String withdrawAmount;

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = trimAndConvert(withdrawAmount, "<>\"");
	}

	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (withdrawAmount == null || withdrawAmount.length() == 0) {
			errors.add("Amount is required");
			return errors;
		}
		 try {
	        	
	        	if(Double.parseDouble(withdrawAmount) < 0) {
	        		errors.add("Amount must be positive");
	        	}
	        	
			} catch (NumberFormatException e) { 
				errors.add("Amount must be numeric");
			}
		return errors;
	}
}
