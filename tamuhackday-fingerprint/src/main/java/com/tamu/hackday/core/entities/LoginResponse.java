package com.tamu.hackday.core.entities;

public class LoginResponse {
	
	String uin;

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public LoginResponse(String uin) {
		super();
		this.uin = uin;
	}
}
