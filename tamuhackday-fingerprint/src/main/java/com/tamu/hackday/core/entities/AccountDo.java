package com.tamu.hackday.core.entities;

import java.util.UUID;

import org.joda.time.DateTime;

/**
 * @author shubhamjain
 *
 */
public class AccountDo {

	
	String uin;
	
	String email;
	
	String password;
	
	String token;
	
	DateTime creationTime;
	
	DateTime lastUpdated;

	public AccountDo(AccountCreateRequest request) {
		this.uin = request.uin;
		this.email = request.email;
		this.password = request.password;
		this.token = request.password;
		
		this.creationTime = new DateTime();
		this.lastUpdated = new DateTime();
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

	public DateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(DateTime creationTime) {
		this.creationTime = creationTime;
	}

	public DateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
