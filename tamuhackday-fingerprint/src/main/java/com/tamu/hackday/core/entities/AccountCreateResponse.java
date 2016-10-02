package com.tamu.hackday.core.entities;

public class AccountCreateResponse {

	String uin;
	
	String token;

	
	public AccountCreateResponse(String uin, String token) {
		super();
		this.uin = uin;
		this.token = token;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
