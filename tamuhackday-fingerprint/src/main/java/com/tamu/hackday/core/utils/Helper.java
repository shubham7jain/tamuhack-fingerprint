package com.tamu.hackday.core.utils;

import javax.sql.DataSource;

import com.tamu.hackday.core.db.api.DatabaseManager;


public class Helper {

	public static boolean isNullOrEmpty(String str) {

		return (str == null || str == "null" || str.trim().length() == 0) ? true : false;
	}
	
	public static DataSource getDataSource(String dbPoolName) {
		return DatabaseManager.getInstance().getDataSource(dbPoolName);
	}
}
