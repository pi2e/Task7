package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class CustomerForm extends FormBean{
	private String username;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String password1;
	private String password2;
	private String balance;
	private String ledgerBalance;
	
	
	public String getUsername() {
		return username;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLedgerBalance() {
		return ledgerBalance;
	}

	public void setLedgerBalance(String ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress1() {
		return address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public String getPassword1() {
		return password1;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public void setPassword1(String e) { 
		password1 = trimAndConvert(e, "<>\""); 
	}
	
	public void setPassword2(String e) { 
		password2 = trimAndConvert(e, "<>\""); 
	}
	
	public void setUsername(String e) { 
		username = trimAndConvert(e, "<>\""); 
	}
	
	public void setFirstName(String e) { 
		firstName = trimAndConvert(e, "<>\""); 
	}
	
	public void setLastName(String e) { 
		lastName = trimAndConvert(e, "<>\""); 
	}
	
	public void setAddress1(String e) { 
		address1 = trimAndConvert(e, "<>\""); 
	}
	
	public void setAddress2(String e) { 
		address2 = trimAndConvert(e, "<>\""); 
	}
	
	public void setCity(String e) { 
		city = trimAndConvert(e, "<>\""); 
	}
    
	public void setState(String p) { 
		state = trimAndConvert(p, "<>\""); 
	}
    
	public void setZipcode(String e) { 
		zipcode = trimAndConvert(e, "<>\""); 
	}

	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		} 
		else if (!username.matches("[a-zA-Z0-9]+")) {
			errors.add("Username should be alphanumeric");
		} 
		else if (username.length() > 15) {
			errors.add("Username can only be up to 15 characters");
		} 
		else if (username.length() < 6) {
			errors.add("Username should contain at least 6 characters");
		}

		if (firstName == null || firstName.length() == 0) {
			errors.add("First name is required");
		} 
		else if (!firstName.matches("[a-zA-Z\\s]+")) {
			errors.add("First name should only contain alphabets");
		} 
		else if (firstName.length() > 15) {
			errors.add("First name can only be up to 15 characters");
		}
		if (lastName == null || lastName.length() == 0) {
			errors.add("Last name is required");
		} 
		else if (!lastName.matches("[a-zA-Z\\s]+")) {
			errors.add("Last name should only contain alphabets");
		}

		else if (lastName.length() > 15) {
			errors.add("Last name can only be up to 15 characters");
		}

		if (address1 == null || address1.length() == 0) {
			errors.add("Address Line 1 is required");
		}
		else if (!address1.matches("[a-zA-Z0-9\\s]+")) {
			errors.add("Address Line 1 should be alphanumeric");
		}
		else if (address1.length() > 40) {
			errors.add("Address Line 1 can only be up to 40 characters");
		}
		
		if ((address2.length() > 0) && !address2.matches("[a-zA-Z0-9\\s]+")) {
			errors.add("Address Line 2 should be alphanumeric");
		}
		else if (address2.length() > 40) {
			errors.add("Address Line 2 can only be up to 40 characters");
		}

		if (city == null || city.length() == 0) {
			errors.add("City is required");
		}
		else if (!city.matches("[a-zA-Z0-9\\s]+")) {
			errors.add("City should be alphanumeric");
		}
		else if (city.length() > 20) {
			errors.add("City name can only be up to 20 characters");
		}

		if (state == null || state.length() == 0) {
			errors.add("State is required");
		}

		if (zipcode == null || zipcode.length() == 0) {
			errors.add("Zipcode is required");
		} 
		else if (zipcode.length() != 5) {
			errors.add("Zipcode should be 5 digits");
		}

		if (password1 == null || password1.length() == 0) {
			errors.add("Password is required");
		}

		if (password2 == null || password2.length() == 0) {
			errors.add("Password confirmation is required");
		}
		
		else if (password1.length() < 6) {
			errors.add("Password should contain at least 6 characters");
		}
		// end of missing field checks
		if (errors.size() > 0) {
			return errors;
		}

		// end of field size check
		if (errors.size() > 0) {
			return errors;
		}

		try {
			Integer.parseInt(zipcode);
		} catch (NumberFormatException e) {
			errors.add("Zipcode should be an integer");
		}

		if (!password1.equals(password2)) {
			errors.add("Passwords do not match");
		}

		return errors;
	}
}


