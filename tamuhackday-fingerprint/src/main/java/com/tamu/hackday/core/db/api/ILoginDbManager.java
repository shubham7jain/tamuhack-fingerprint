package com.tamu.hackday.core.db.api;

import java.sql.SQLException;

import com.tamu.hackday.core.entities.AccountDo;

public interface ILoginDbManager {

	public void createAccount(AccountDo accountDo) throws SQLException, ClassNotFoundException;
}
