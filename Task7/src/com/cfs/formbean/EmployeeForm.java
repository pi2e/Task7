package com.cfs.formbean;

import java.util.ArrayList;
import org.mybeans.form.FormBean;

public class EmployeeForm extends FormBean{
	private String username;
	private String firstName;
	private String lastName;
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
	
	 public ArrayList<String> getValidationErrors() {
	        ArrayList<String> errors = new ArrayList<String>();

	        if (username == null || username.length() == 0) {
	        	errors.add("Username is required");
	        }
	        
	        if (username.length() > 15) {
				errors.add("Username can only be up to 15 characters");
			}
	        
	        if (firstName == null || firstName.length() == 0) {
	        	errors.add("First name is required");
	        }
	        
	        if (firstName.length() > 15) {
				errors.add("First name can only be up to 15 characters");
			}
	        
	        if (lastName == null || lastName.length() == 0) {
	        	errors.add("Last name is required");
	        }
	        
	        if (lastName.length() > 15) {
				errors.add("Last name can only be up to 15 characters");
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
	        
	        if (password1.length() < 6) {
	        	errors.add("Password should contain at least 6 characters");
	        }
	        
	        //end of field size check
	        if(errors.size() > 0) {
	        	return errors;
	        }
	        
	        if (!username.matches("[a-zA-Z0-9]+")) {
	        	errors.add("Username should be alphanumeric");
	        }
	        
	        if (!password1.matches("[a-zA-Z0-9]+")) {
				errors.add("Password must be alphanumeric");
			}
	        
	        if (!password1.equals(password2)) {
	        	errors.add("Passwords do not match");
	        }
	        
	        return errors;
	    }
}
