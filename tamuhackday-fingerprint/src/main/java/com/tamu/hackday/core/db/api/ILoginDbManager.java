package com.tamu.hackday.core.db.api;

import java.sql.SQLException;

import com.tamu.hackday.container.exception.LoginException;
import com.tamu.hackday.core.entities.AccountDo;
import com.tamu.hackday.core.entities.LoginResponse;

public interface ILoginDbManager {

	public void createAccount(AccountDo accountDo) throws SQLException, ClassNotFoundException;
	
	public LoginResponse findByUinAndPassword(String uin, String password) throws ClassNotFoundException, SQLException, LoginException ;
	
	public LoginResponse authenticateByToken(String tokenId) throws SQLException, ClassNotFoundException, LoginException;
}
