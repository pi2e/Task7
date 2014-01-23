package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean{
	
	private String userId;
	private String amount;
	
	
	
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public ArrayList<String> getValidationErrors() {
        ArrayList<String> errors = new ArrayList<String>();
        
        if (amount.length() == 0 ) {
        	errors.add("Amount is required");
        }
        
        try {
        	
        	if(Double.parseDouble(amount) < 0) {
        		errors.add("Amount must be positive");
        	}
        	
		} catch (NumberFormatException e) { 
			errors.add("Amount must be numeric");
		}
        
        return errors;
    }

}
