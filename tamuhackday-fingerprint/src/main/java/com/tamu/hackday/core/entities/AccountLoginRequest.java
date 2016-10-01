package com.tamu.hackday.core.entities;

public class AccountLoginRequest {

	String uin;
	
	String password;

	public AccountLoginRequest() {
		
	}
	
	public AccountLoginRequest(String uin, String password) {
		super();
		this.uin = uin;
		this.password = password;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
