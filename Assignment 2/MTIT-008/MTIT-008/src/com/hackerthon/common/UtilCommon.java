package com.hackerthon.common;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

public class UtilCommon {

	//Constant for cofigure properties 
	
	public static final Properties properties = new Properties();

	private static final Logger logger = Logger.getLogger(ConnectionDb.class.getName());

	static {
		/**
		 *  this static method used for run-time exceptions
		 */
		try {
			properties.load(UtilCommon.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
		} 
		catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		catch (NullPointerException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
}
