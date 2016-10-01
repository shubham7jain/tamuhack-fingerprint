package com.tamu.hackday.core.constants;

public class LoginConstants {
	
	public static class TableNames {
		public static final String account = "Account";
	}
	
	public static class DatabaseConfig {
		public static final String JdbcUrl = "jdbcURL";
		public static final String User = "user_name";
		public static final String Password = "password";
		public static final String MinPoolSize = "initial_pool_size";
		public static final String MaxPoolSize = "max_pool_size";
		public static final String TestConnectionOnCheckout = "testConnectionOnCheckout";
		public static final String IdleConnectionTestPeriod = "idleConnectionTestPeriod";
		public static final String CheckoutTimeout = "checkOutTimeOut";
		public static final String MaxIdleTime = "maxIdleTime";
		public static final String NumHelperThreads = "numHelperThreads";
		public static final String DatabaseName = "database_name";
	}

}
