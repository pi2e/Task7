package com.cfs.formbean;

import java.util.ArrayList;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean{
	private String username;
	private String password;
	private String accountType;
	private String action;
	
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setUsername(String e) { 
		username = trimAndConvert(e, "<>\""); 
	}
    
	public void setPassword(String p) { 
		password = trimAndConvert(p, "<>\""); 
	}
    
	public void setAccountType(String a) {
		accountType = a;
	}
	
	public void setAction(String a) { 
		action   = a;        
	}
	
    public ArrayList<String> getValidationErrors() {
        ArrayList<String> errors = new ArrayList<String>();

        if (username == null || username.length() == 0) {
        	errors.add("Username is required");
        }
        
        if (username.length() > 15) {
			errors.add("Username can only be up to 15 characters");
		}
        
        if (password == null || password.length() == 0) {
        	errors.add("Password is required");
        }

        return errors;
    }
}

