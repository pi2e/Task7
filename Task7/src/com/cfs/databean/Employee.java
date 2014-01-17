package com.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("username")
public class Employee {
	
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean checkPassword(String pwd) {
		return password.equals(pwd);
	}
}
