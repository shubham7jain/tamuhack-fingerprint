package com.tamu.hackday.core.db.api;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.tamu.hackday.core.constants.LoginConstants.TableNames;
import com.tamu.hackday.core.entities.AccountDo;

public class LoginDbManager implements ILoginDbManager {

	public void createAccount(AccountDo accountDo) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:port/LoginDetails","root", "");
		conn.close();

		// Create sql to add to database
		String sql = "INSERT INTO " + TableNames.account + " (account_id, uin, email,"
				+ "pass, token, "
				+ "creation_tome, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?)";

		// Create parameters and data types arrays
		  PreparedStatement preparedStmt = conn.prepareStatement(sql);
	      preparedStmt.setString (1, accountDo.getAccountId());
	      preparedStmt.setString (2, accountDo.getUin());
	      preparedStmt.setString (3, accountDo.getEmail());
	      preparedStmt.setString (4, accountDo.getPassword());
	      preparedStmt.setString (5, accountDo.getToken());
	      preparedStmt.setTimestamp (6, new Timestamp(accountDo.getCreationTime().getMillis()));
	      preparedStmt.setTimestamp (7, new Timestamp(accountDo.getLastUpdated().getMillis()));

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      conn.close();
	}
}
