package com.hackerthon.main;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.GetEmployeeService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerFactoryConfigurationError;

public class ExecuteMain {

	public static void main(String[] args) {

		// Logger for logging the necessary messages
		final Logger logger = Logger.getLogger(UtilTransform.class.getName());

		GetEmployeeService employeeService = new GetEmployeeService();

		try {
			UtilTransform.requestTransform();
			employeeService.employeeTableTransactions();
		} catch (Exception exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}
	}
}
