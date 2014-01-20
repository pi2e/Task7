package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class FundForm extends FormBean{
	private String fundname;
	private String tickername;

	public String getFundname() {
		return fundname;
	}

	public String getTickername() {
		return tickername;
	}

	public void setFundname(String f) {
		fundname = trimAndConvert(f ,"<>\"");
	}

	public void setTickername(String t) {
		tickername = trimAndConvert(t ,"<>\"");
	}
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (fundname == null || fundname.length() == 0) {
			errors.add("Fundname is required");
		}
		if (tickername == null || tickername.length() == 0) {
			errors.add("Tickername is required");
		}
		if (tickername.length() > 5) {
			errors.add("Tickername must be less 5 character");
		}
		if (!tickername.matches("[a-zA-Z]+")) {
			errors.add("Tickername must be letter only");
		}
		
		return errors;
	}
}
