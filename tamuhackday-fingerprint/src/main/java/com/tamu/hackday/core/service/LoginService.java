package com.tamu.hackday.core.service;

import java.sql.SQLException;

import com.tamu.hackday.container.exception.ExceptionCodes;
import com.tamu.hackday.container.exception.LoginException;
import com.tamu.hackday.core.db.api.LoginDbManager;
import com.tamu.hackday.core.entities.AccountCreateRequest;
import com.tamu.hackday.core.entities.AccountCreateResponse;
import com.tamu.hackday.core.entities.AccountDo;
import com.tamu.hackday.core.entities.AccountLoginRequest;
import com.tamu.hackday.core.entities.LoginResponse;

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
	
	public AccountCreateResponse createAccount(AccountCreateRequest request)
			throws ClassNotFoundException, SQLException, LoginException {
		LoginResponse response = loginDbManager.findByUin(request.getUin());

		if (response == null) {

			AccountDo accountDo = new AccountDo(request);

			this.loginDbManager.createAccount(accountDo);

			return new AccountCreateResponse(accountDo.getUin(), accountDo.getToken());
		} else {
			throw new LoginException("UIN already exists", ExceptionCodes.DUPLICATE);
		}
	}

	public LoginResponse authenticateByUin(AccountLoginRequest request) throws LoginException, ClassNotFoundException, SQLException {
		return this.loginDbManager.findByUinAndPassword(request.getUin(), request.getPassword());
	}
	
	public LoginResponse authenticateByToken(String tokenId) throws ClassNotFoundException, SQLException, LoginException {
	    //    AccountDo accountDo = new AccountDo(request);
	        
	        
	    return this.loginDbManager.authenticateByToken(tokenId);
	    }
}
