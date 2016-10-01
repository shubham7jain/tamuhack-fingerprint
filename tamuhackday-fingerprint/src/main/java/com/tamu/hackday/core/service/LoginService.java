package com.tamu.hackday.core.service;

import java.sql.SQLException;

import com.tamu.hackday.core.db.api.LoginDbManager;
import com.tamu.hackday.core.entities.AccountCreateRequest;
import com.tamu.hackday.core.entities.AccountDo;

public class LoginService {

	private static final LoginService instance = new LoginService();
	
	private LoginDbManager loginDbManager;
	
	public static LoginService getInstance() {
		return instance;
	}
	
	public void initialize(LoginDbManager loginDbManager) {

		if (loginDbManager == null) {

			throw new IllegalArgumentException("Null Database API");
		}
		this.loginDbManager = loginDbManager;
	}
	
	public void createAccount(AccountCreateRequest request) throws ClassNotFoundException, SQLException {
		AccountDo accountDo = new AccountDo(request);
		
		
		this.loginDbManager.createAccount(accountDo);
	}
}
