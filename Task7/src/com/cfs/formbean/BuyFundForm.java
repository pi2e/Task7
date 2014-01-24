package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String ticker;
	private String amount;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = trimAndConvert(ticker, "<>\"");
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount, "<>\"");
	}

	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (ticker.length() == 0) {
			errors.add("Fund ticker required");
		}

		if (amount.length() == 0) {
			errors.add("Dollar amount required");
		}

		if (errors.size() != 0) {
			return errors;
		}

		try {
			double amt = Double.parseDouble(amount);

			if (amt < 0) {
				errors.add("Amount must be positive");
			}
			
			int decimal = amount.lastIndexOf('.');
			if (decimal != -1 && amount.length() - decimal > 3) {
				errors.add("You can not specify more the two decimal");
			}

		} catch (NumberFormatException e) {
			errors.add("Amount must be numeric (up to 2 decimal places");
		}
		return errors;
	}

}
