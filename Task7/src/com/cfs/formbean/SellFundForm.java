package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean {
	private String ticker;
	private String shares;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = trimAndConvert(ticker, "<>\"");
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = trimAndConvert(shares, "<>\"");
	}

	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (ticker.length() == 0) {
			errors.add("Fund ticker required");
		}

		if (shares.length() == 0) {
			errors.add("Number of shares required");
		}

		if (errors.size() != 0) {
			return errors;
		}

		try {
			double amt = Double.parseDouble(shares);

			if (amt < 0) {
				errors.add("Number of shares must be positive");
			}
			
			int decimal = shares.lastIndexOf('.');
			if (decimal != -1 && shares.length() - decimal > 4) {
				errors.add("You can not specify more than 3 decimal places");
			}

		} catch (NumberFormatException e) {
			errors.add("Number of shares must be numeric (up to 3 decimal places)");
		}
		return errors;
	}

}
