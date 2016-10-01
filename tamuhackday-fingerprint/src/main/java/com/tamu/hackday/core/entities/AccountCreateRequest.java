package com.tamu.hackday.core.entities;

import java.io.Serializable;

public class AccountCreateRequest implements Serializable {

	String uin;
	
	String email;
	
	String password;
	
	String token;
	
	public AccountCreateRequest(String uin, String email, String password, String token) {
		this.uin = uin;
		this.email = email;
		this.password = password;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
