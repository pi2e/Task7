package com.cfs.formbean;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
	
	
	public String getUsername() {
		return username;
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
		username = trimAndConvert(e, "<>\""); 
	}
	
	public void setPassword2(String e) { 
		username = trimAndConvert(e, "<>\""); 
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
        
        if (firstName == null || firstName.length() == 0) {
        	errors.add("First name is required");
        }
        
        if (lastName == null || lastName.length() == 0) {
        	errors.add("Last name is required");
        }
        
        if (address1 == null || address1.length() == 0) {
        	errors.add("Address is required");
        }
        
        if (city == null || city.length() == 0) {
        	errors.add("City is required");
        }
        
        if (state == null || state.length() == 0) {
        	errors.add("State is required");
        }
        
        if (zipcode == null || zipcode.length() == 0) {
        	errors.add("Zipcode is required");
        }
        
        if (password1 == null || password1.length() == 0) {
        	errors.add("Password is required");
        }
        
        if (password2 == null || password2.length() == 0) {
        	errors.add("Password confirmation is required");
        }
        
        //end of missing field checks
        if(errors.size() > 0) {
        	return errors;
        }
        
        if (username.length() < 6) {
        	errors.add("Username should contain at least 6 characters");
        }
        
        if (username.length() < 6) {
        	errors.add("Password should contain at least 6 characters");
        }
        
        if (username.length() != 5) {
        	errors.add("Zipcode should be 5 digits");
        }
        
        //end of field size check
        if(errors.size() > 0) {
        	return errors;
        }
        
        if (Pattern.matches(username, "[a-zA-Z_0-9]")) {
        	errors.add("Username should be alphanumeric");
        }
        
        if (Pattern.matches(password1,".*[<>\"].*")) {
			errors.add("Password cannot contain brackets and quotes");
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

