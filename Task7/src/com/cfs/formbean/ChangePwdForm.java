package com.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePwdForm extends FormBean {
	private String confirmPassword;
	private String newPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setConfirmPassword(String s) {
		confirmPassword = trimAndConvert(s, "<>\"");
	}

	public void setNewPassword(String s) {
		newPassword = trimAndConvert(s, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("Password is required");
		}

		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm password is required");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (newPassword.length() < 6) {
			errors.add("Password should contain at least 6 characters");
		}

		if (!newPassword.matches("[a-zA-Z0-9]+")) {
			errors.add("Password must be alphanumeric");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords are not the same");
		}

		return errors;
	}
}
