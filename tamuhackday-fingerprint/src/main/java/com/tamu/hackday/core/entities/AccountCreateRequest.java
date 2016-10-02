package com.tamu.hackday.core.entities;

import java.io.Serializable;

public class AccountCreateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String uin;
	
	String email;
	
	String password;
	
	public AccountCreateRequest() {
		
	}
	
	public AccountCreateRequest(String uin, String email, String password) {
		this.uin = uin;
		this.email = email;
		this.password = password;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
