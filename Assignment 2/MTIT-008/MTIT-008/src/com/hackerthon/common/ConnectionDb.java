package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;


public class ConnectionDb extends UtilCommon {

	private static Connection connection = null;
	private static final Logger logger = Logger.getLogger(ConnectionDb.class.getName());
	
	private ConnectionDb() {
		
	}


	public static Connection getDBConnection(){
		try {
			if (connection == null || connection.isClosed()) {

				Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));

				connection = DriverManager.getConnection(
								properties.getProperty(CommonConstants.URL), 
								properties.getProperty(CommonConstants.USERNAME),
								properties.getProperty(CommonConstants.PASSWORD));
			}
		} 
		catch (Exception exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}
		
		return connection;
	}
}