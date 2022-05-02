package com.hackerthon.service;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.ConnectionDb;
import com.hackerthon.common.UtilQuery;
import com.hackerthon.common.UtilTransform;
import com.hackerthon.common.UtilCommon;
import com.hackerthon.model.Employee;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;


 //  employee related database operations implemented here 
 

public class GetEmployeeService extends UtilCommon {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private final Logger logger = Logger.getLogger(ConnectionDb.class.getName());

	/**
	 * This get employees data from xml file, display them
	 * and add employees to the ArrayList.
	 * 
	 * @return void
	 * 
	 * @see #xmlxPaths()
	 */
	public void employeesFromXML() {
		try {
			
			for (Map<String, String> employeesMap : UtilTransform.xmlxPaths()) {
				
				Employee employee = new Employee();
				
				employee.setEmployeeId(employeesMap
						.get(CommonConstants.XPATH_EMPLOYEE_ID_KEY));
				employee.setFullName(employeesMap
						.get(CommonConstants.XPATH_EMPLOYEE_NAME_KEY));
				employee.setAddress(employeesMap
						.get(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY));
				employee.setFacultyName(employeesMap
						.get(CommonConstants.XPATH_EMPLOYEE_FACULTY_NAME_KEY));
				employee.setDepartment(employeesMap
						.get(CommonConstants.XPATH_EMPLOYEE_DEPARTMENT_KEY));
				employee.setDesignation(employeesMap
						.get(CommonConstants.XPATH_EMPLOYEE_DESIGNATION_KEY));
				
				employeeList.add(employee);
				
			//Employee details
				//logger.info("\n" + employee.toString() + "\n");
			}
			
		} 
		catch (IndexOutOfBoundsException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		catch (ClassCastException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		catch (NullPointerException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *  Creates employee table in database. If table already exist
	 * drop it and create new one.
	 * 
	 * @return void
	 * 
	 * @see #getDBConnection()
	 * @see #query(String)
	 * @see Connection
	 * @see Statement
	 */
	public void createEmployeesTable() {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ConnectionDb.getDBConnection();	
			statement = connection.createStatement();
			statement.executeUpdate(UtilQuery.query(CommonConstants.EMPLOYEES_DROP_TABLE_QUERY));
			statement.executeUpdate(UtilQuery.query(CommonConstants.EMPLOYEES_CREATE_TABLE_QUERY));	
		} 
		catch (SQLTimeoutException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * This insert employee data to the database table.
	 * 
	 * @return void
	 * 
	 * @see #getDBConnection()
	 * @see #query(String)
	 * @see Connection
	 * @see PreparedStatement
	 */
	public void addEmployee() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionDb.getDBConnection();			
			preparedStatement = connection.prepareStatement(UtilQuery.query(CommonConstants.EMPLOYEES_INSERT_DATA_QUERY));
			
			connection.setAutoCommit(false);
			
			for (Employee employee : employeeList) {
				
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employee.getEmployeeId());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, employee.getFullName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, employee.getAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, employee.getFacultyName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, employee.getDepartment());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, employee.getDesignation());
				
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
			connection.commit();
		} 
		catch (BatchUpdateException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (IndexOutOfBoundsException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLTimeoutException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	/**
	 * Retrieve  employee's ID from the database employee table.
	 * 
	 * @param employeeId
	 * 				ID of the employee need to be retrieved
	 * 
	 * @return void
	 * 
	 * @see #getDBConnection()
	 * @see #query(String)
	 * @see Connection
	 * @see PreparedStatement
	 */
	public void getEmployeeById(String employeeId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Employee employee = new Employee();
		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {
			connection = ConnectionDb.getDBConnection();
			preparedStatement = connection.prepareStatement(UtilQuery
					.query(CommonConstants.EMPLOYEES_RETRIEVE_EMPLOYEE_QUERY));
			
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				employee.setEmployeeId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				employee.setFullName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				employee.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				employee.setFacultyName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				employee.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				employee.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
			}
			
			employees.add(employee);
			printEmployee(employees);
		} 
		catch (SQLTimeoutException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Delete  employee's ID from the database employee table.
	 * 
	 * @param employeeId
	 * 				ID of the employee need to be retrieved
	 * 
	 * @return void
	 * 
	 * @see #getDBConnection()
	 * @see #query(String)
	 * @see Connection
	 * @see PreparedStatement
	 */
	public void deleteEmployee(String employeeId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionDb.getDBConnection();
			preparedStatement = connection.prepareStatement(UtilQuery
					.query(CommonConstants.EMPLOYEES_RETRIEVE_DELETE_EMPLOYEE_QUERY));
			
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLTimeoutException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	/**
	 * All the employees in the employees table in database.
	 * 
	 * @return void
	 * 
	 * @see #getDBConnection()
	 * @see #query(String)
	 * @see #printEmployee(ArrayList<Employee>)
	 * @see Connection
	 * @see PreparedStatement
	 * @see ResultSet
	 */
	public void displayEmployee() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		try {
			connection = ConnectionDb.getDBConnection();
			preparedStatement = connection.prepareStatement(UtilQuery
					.query(CommonConstants.EMPLOYEES_RETRIEVE_ALL_EMPLOYEES_QUERY));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Employee employee = new Employee();
				
				employee.setEmployeeId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				employee.setFullName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				employee.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				employee.setFacultyName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				employee.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				employee.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				
				employees.add(employee);
			}
		} 
		catch (SQLTimeoutException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		printEmployee(employees);
	}
	
	/**
	 * Template method design pattern implemetend here.
	 * 
	 * @return void
	 * 
	 * @see #employeesFromXML()
	 * @see #createEmployeesTable()
	 * @see #addEmployee()
	 * @see #displayEmployee()
	 */
	public final void employeeTableTransactions() {
		employeesFromXML();
		createEmployeesTable();
		addEmployee();
		displayEmployee();
	}
	
	/**
	 * Retrieve all the employees in the database table.
	 * 
	 * @param employeeList
	 * 				List of employees to be displayed
	 * 
	 * @return void
	 */
	public void printEmployee(ArrayList<Employee> employeeList){

		System.out.printf(CommonConstants.EMPLOYEE_TABLE_COLUMNS);
		System.out.println(CommonConstants.TABLE_COLUMNS_BREAK_LINE);
				
		for (Employee employee : employeeList) {
			System.out.printf(CommonConstants.EMPLOYEE_TABLE_COLUMN_ORDER_IN_ROW,
					employee.getEmployeeId(), 
					employee.getFullName(), 
					employee.getAddress(), 
					employee.getFacultyName(), 
					employee.getDepartment(), 
					employee.getDesignation());

			System.out.println(CommonConstants.TABLE_ROW_BREAK_LINE);
		}
	}
}