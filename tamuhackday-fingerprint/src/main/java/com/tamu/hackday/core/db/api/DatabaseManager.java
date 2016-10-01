package com.tamu.hackday.core.db.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tamu.hackday.core.constants.LoginConstants;
import com.tamu.hackday.core.utils.Helper;

public class DatabaseManager {
	
	// Singleton instance
		private static final DatabaseManager INSTANCE = new DatabaseManager();

		private Map<String, ComboPooledDataSource> dataSourceMap = new HashMap<String, ComboPooledDataSource>();

		public void setDataSourceMap(Map<String, ComboPooledDataSource> dataSourceMap) {
			this.dataSourceMap = dataSourceMap;
		}

		// Private constructor
		private DatabaseManager() {
		}

		// Return singleton instance
		public static DatabaseManager getInstance() {

			return INSTANCE;
		}

		public void addDatabaseConnection(String poolName, Map<String, String> poolConfig) {

			try {

				ComboPooledDataSource dataSource = new ComboPooledDataSource();
				dataSource.setDriverClass("com.mysql.jdbc.Driver");

				String jdbcUrl = poolConfig.get(LoginConstants.DatabaseConfig.JdbcUrl);
				String databaseName = (String) poolConfig.get(LoginConstants.DatabaseConfig.DatabaseName);
				jdbcUrl = jdbcUrl + "/" + databaseName;

				dataSource.setJdbcUrl(jdbcUrl);
				dataSource.setUser(poolConfig.get(LoginConstants.DatabaseConfig.User));
				dataSource.setPassword(poolConfig.get(LoginConstants.DatabaseConfig.Password));

				String minPoolSize = poolConfig.get(LoginConstants.DatabaseConfig.MinPoolSize);
				String maxPoolSize = poolConfig.get(LoginConstants.DatabaseConfig.MaxPoolSize);
				String testConnectionOnCheckout =
				        poolConfig.get(LoginConstants.DatabaseConfig.TestConnectionOnCheckout);
				String idleConnectionTestPeriod =
				        poolConfig.get(LoginConstants.DatabaseConfig.IdleConnectionTestPeriod);
				String checkoutTime = poolConfig.get(LoginConstants.DatabaseConfig.CheckoutTimeout);
				String numHelperThread = poolConfig.get(LoginConstants.DatabaseConfig.NumHelperThreads);
				String maxIdleTime = poolConfig.get(LoginConstants.DatabaseConfig.MaxIdleTime);

				if (!Helper.isNullOrEmpty(minPoolSize))
					dataSource.setMinPoolSize(Integer.parseInt(minPoolSize));
				if (!Helper.isNullOrEmpty(maxPoolSize))
					dataSource.setMaxPoolSize(Integer.parseInt(maxPoolSize));
				if (!Helper.isNullOrEmpty(testConnectionOnCheckout))
					dataSource.setTestConnectionOnCheckout(Boolean.parseBoolean(testConnectionOnCheckout));
				if (!Helper.isNullOrEmpty(idleConnectionTestPeriod))
					dataSource.setIdleConnectionTestPeriod(Integer.parseInt(idleConnectionTestPeriod));
				if (!Helper.isNullOrEmpty(checkoutTime))
					dataSource.setCheckoutTimeout(Integer.parseInt(checkoutTime));
				if (!Helper.isNullOrEmpty(maxIdleTime))
					dataSource.setMaxIdleTime(Integer.parseInt(maxIdleTime));
				if (!Helper.isNullOrEmpty(numHelperThread))
					dataSource.setNumHelperThreads(Integer.parseInt(numHelperThread));

				dataSource.getConnection();
				dataSourceMap.put(poolName, dataSource);
			}
			catch (Exception e) {

				throw new RuntimeException("An exception occurred while initializing data sources", e);
			}
		}

		public DataSource getDataSource(String poolName) {

			if (dataSourceMap.containsKey(poolName))
				return dataSourceMap.get(poolName);
			return null;
		}

		public boolean hasDataSource(String poolName) {
			return dataSourceMap.containsKey(poolName);
		}

		// Initialize the data source
		public void initialize() throws JsonParseException, JsonMappingException, IOException {

			ObjectMapper mapper = new ObjectMapper();
			
			String json = "{\"master\":{\"user_name\":\"root\",\"password\":\"\",\"database_name\":\"hackday_login\",\"jdbcURL\":\"jdbc:mysql://127.0.0.1:3306\","
					+ "\"initial_pool_size\":\"3\",\"max_pool_size\":\"10\",\"readonly\":\"false\"}}";
			Map<String, Map<String, String>> map = new HashMap<>();

			// convert JSON string to Map
			map = mapper.readValue(json, new TypeReference<Map<String, Map<String, String>>>(){});

			for (String dbPoolName : map.keySet()) {
				addDatabaseConnection(dbPoolName, map.get(dbPoolName));
			}

		}

		public void shutDown() {

			Iterator<ComboPooledDataSource> it = dataSourceMap.values().iterator();
			while (it.hasNext()) {
				it.next().close();
			}
			dataSourceMap.clear();
		}

}
