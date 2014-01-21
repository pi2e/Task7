package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class FundForm extends FormBean{
	private String fundName;
	private String ticker;

	public String getFundName() {
		return fundName;
	}

	public String getTicker() {
		return ticker;
	}

	public void setFundName(String f) {
		fundName = trimAndConvert(f ,"<>\"");
	}

	public void setTicker(String t) {
		ticker = trimAndConvert(t ,"<>\"");
	}
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (fundName == null || fundName.length() == 0) {
			errors.add("Fundname is required");
		}
		
		if (ticker == null || ticker.length() == 0) {
			errors.add("Tickername is required");
		}
		
		if (ticker.length() > 5) {
			errors.add("Tickername must be less 5 character");
		}
		
		if(errors.size() != 0) {
			return errors;
		}
		
		if (!ticker.matches("[a-zA-Z]+")) {
			errors.add("Tickername must be letter only");
		}
		
		return errors;
	}
}
