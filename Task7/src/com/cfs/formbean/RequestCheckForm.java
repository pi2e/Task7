package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

import com.cfs.utility.CommonUtilities;

public class RequestCheckForm extends FormBean {

	private String withdrawAmount;

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		String amt = CommonUtilities.removeCommas(withdrawAmount);
		this.withdrawAmount = trimAndConvert(amt, "<>\"");
	}

	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (withdrawAmount == null || withdrawAmount.length() == 0) {
			errors.add("Amount is required");
			return errors;
		}
		 try {
	        	
	        	if(Double.parseDouble(withdrawAmount) <= 0) {
	        		errors.add("Amount must be positive");
	        	}
	        	
	        	int decimal = withdrawAmount.lastIndexOf('.');
				if (decimal != -1 && withdrawAmount.length() - decimal > 3) {
					errors.add("You can not specify more the two decimal places");
				}
	        	
			} catch (NumberFormatException e) { 
				errors.add("Amount must be numeric");
			}
		return errors;
	}
}
