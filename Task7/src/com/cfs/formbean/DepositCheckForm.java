package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

import com.cfs.utility.CommonUtilities;

public class DepositCheckForm extends FormBean {

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
		String amt = CommonUtilities.removeCommas(amount);
		this.amount = trimAndConvert(amt, "<>\"");
	}

	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (amount.length() == 0) {
			errors.add("Amount is required");
		}

		try {

			if (Double.parseDouble(amount) < 0) {
				errors.add("Amount must be positive");
			}

			int decimal = amount.lastIndexOf('.');
			if (decimal != -1 && amount.length() - decimal > 3) {
				errors.add("You can not specify more the two decimal");
			}
			if ((decimal != -1 && decimal > 9)
					|| (decimal == -1 && amount.length() > 9)) {
				errors.add("You can not deposit more than 1,000,000,000 dollars");
			}

		} catch (NumberFormatException e) {
			errors.add("Amount must be numeric");
		}

		return errors;
	}

}
