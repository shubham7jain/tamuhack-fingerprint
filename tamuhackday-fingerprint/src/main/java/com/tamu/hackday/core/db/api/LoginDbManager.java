package com.tamu.hackday.core.db.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import com.tamu.hackday.container.exception.ExceptionCodes;
import com.tamu.hackday.container.exception.LoginException;
import com.tamu.hackday.core.constants.LoginConstants.TableNames;
import com.tamu.hackday.core.entities.AccountDo;
import com.tamu.hackday.core.entities.LoginResponse;
import com.tamu.hackday.core.utils.Helper;

public class LoginDbManager implements ILoginDbManager {

	public void createAccount(AccountDo accountDo) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = null;
		
		DataSource dataSource = Helper.getDataSource("master");

		conn = dataSource.getConnection();
		// Create sql to add to database
		String sql = "INSERT INTO " + TableNames.account + " (uin, email,"
				+ "pass, token, "
				+ "creation_tome, last_updated) VALUES (?, ?, ?, ?, ?, ?)";

		// Create parameters and data types arrays
		  PreparedStatement preparedStmt = conn.prepareStatement(sql);
	      preparedStmt.setString (1, accountDo.getUin());
	      preparedStmt.setString (2, accountDo.getEmail());
	      preparedStmt.setString (3, accountDo.getPassword());
	      preparedStmt.setString (4, accountDo.getToken());
	      preparedStmt.setTimestamp (5, new Timestamp(accountDo.getCreationTime().getMillis()));
	      preparedStmt.setTimestamp (6, new Timestamp(accountDo.getLastUpdated().getMillis()));

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      conn.close();
	}
	
	public LoginResponse findByUinAndPassword(String uin, String password) throws ClassNotFoundException, SQLException, LoginException {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = null;
		
		DataSource dataSource = Helper.getDataSource("master");

		conn = dataSource.getConnection();
		
		String sql = "SELECT uin from " + TableNames.account + " where uin = ? and pass = ?;"; 
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uin);
		preparedStatement.setString(2, password);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		
		if(rs.next()) {
			return new LoginResponse(uin);
		} else {
			throw new LoginException("Uin or Password invalid", ExceptionCodes.ITEM_NOT_FOUND);
		}
	}
	
	public LoginResponse findByUin(String uin) throws ClassNotFoundException, SQLException, LoginException {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = null;
		
		DataSource dataSource = Helper.getDataSource("master");

		conn = dataSource.getConnection();
		
		String sql = "SELECT uin from " + TableNames.account + " where uin = ?;"; 
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, uin);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		
		if(rs.next()) {
			return new LoginResponse(uin);
		} else {
			return null;
		}
	}
	
	public LoginResponse authenticateByToken(String tokenId) throws SQLException, ClassNotFoundException, LoginException {
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection conn = null;
        
        DataSource dataSource = Helper.getDataSource("master");

        conn = dataSource.getConnection();
        // Create sql to retrieve from database
        String sql = "SELECT uin FROM " + TableNames.account + " where token = ? ";

        // Create parameters and data types arrays
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString (1, tokenId);
         
        // execute the preparedstatement
        ResultSet rs = preparedStmt.executeQuery();
        
        LoginResponse response = null;
        if(rs.next()){
            response = new LoginResponse(rs.getString("uin"));
            conn.close();
        } else {
            conn.close();
        	throw new LoginException("Token not found", ExceptionCodes.ITEM_NOT_FOUND);
        }
        return response;
    }
}
