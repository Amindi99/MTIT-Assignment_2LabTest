package com.hackerthon.common;

public class CommonConstants {

	/** All the constants file paths including here */

	public static final String QUERY_XML = "QueryFilePath";

	public static final String PROPERTY_FILE = "../config/config.properties";

	public static final String TAG_NAME = "query";

	public static final String ATTRIBUTE_ID = "id";

	public static final String COMMA = ",";

	public static final String URL = "url";

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	public static final String DRIVER_NAME = "driverName";

	public static final int COLUMN_INDEX_ONE = 1;

	public static final int COLUMN_INDEX_TWO = 2;

	public static final int COLUMN_INDEX_THREE = 3;

	public static final int COLUMN_INDEX_FOUR = 4;

	public static final int COLUMN_INDEX_FIVE = 5;

	public static final int COLUMN_INDEX_SIX = 6;

	public static final String EMPLOYEE_TABLE_COLUMNS = "Employee ID\t\tFull Name\t\t\tAddress\t\t\t\tFaculty Name\t\tDepartment\t\t\tDesignation\n%n";

	public static final String EMPLOYEE_TO_STRING_FORMAT = "Employee ID \t=\t %s\nFullName \t=\t %s\nAddress \t=\t %s\nFaculty Name \t=\t %s\nDepartment \t=\t %s\nDesignation \t=\t %s";

	public static final String EMPLOYEES_CREATE_TABLE_QUERY = "create_employee";

	public static final String EMPLOYEES_DROP_TABLE_QUERY = "drop_employee";

	public static final String EMPLOYEES_INSERT_DATA_QUERY = "insert_employee";

	public static final String EMPLOYEES_RETRIEVE_EMPLOYEE_QUERY = "retrieve_employee";

	public static final String EMPLOYEES_RETRIEVE_ALL_EMPLOYEES_QUERY = "retrieve_all_employees";

	public static final String EMPLOYEES_RETRIEVE_DELETE_EMPLOYEE_QUERY = "delete_employee";

	public static final String EMPLOYEE_TABLE_COLUMN_ORDER_IN_ROW = "%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n%n";

	public static final String TABLE_COLUMNS_BREAK_LINE = "=====================================================================================================================================================================================";

	public static final String TABLE_ROW_BREAK_LINE = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

	public static final String XPATH_EMPLOYEE_ID_KEY = "xpath_employee_id";

	public static final String XPATH_EMPLOYEE_NAME_KEY = "xpath_employee_name";

	public static final String XPATH_EMPLOYEE_ADDRESS_KEY = "xpath_employee_address";

	public static final String XPATH_EMPLOYEE_FACULTY_NAME_KEY = "xpath_employee_faculty_name";

	public static final String XPATH_EMPLOYEE_DEPARTMENT_KEY = "xpath_employee_department";

	public static final String XPATH_EMPLOYEE_DESIGNATION_KEY = "xpath_employee_designation";

	public static final String SRC_EMPLOYEE_XML_REQUEST = "src_employee_xml_request";

	public static final String SRC_EMPLOYEE_XSL = "src_employee_xsl";

	public static final String SRC_EMPLOYEE_XML_RESPONSE = "src_employee_xml_response";

	public static final String COMPLIE_COUNT_EMPLOYEE = "count(//Employees/Employee)";

	public static final String COMPLIE_EMPLOYEES_EMPLOYEES = "//Employees/Employee[";

	public static final String COMPILE_EMOPLYEES_EMOPLYEE_ID = "]/EmployeeID/text()";

	public static final String COMPILE_EMOPLYEES_EMOPLYEE_NAME = "]/EmployeeFullName/text()";

	public static final String COMPILE_EMOPLYEES_EMOPLYEE_ADDRESS = "]/EmployeeFullAddress/text()";

	public static final String COMPILE_EMOPLYEES_EMOPLYEE_FACULTY_NAME = "]/FacultyName/text()";

	public static final String COMPILE_EMOPLYEES_EMOPLYEE_DEPARTMENT = "]/Department/text()";

	public static final String COMPILE_EMOPLYEES_EMOPLYEE_DESIGNATION = "]/Designation/text()";

	public static final String SRC_EMOPLOYEE_QUERY_XML = "src_emoployee_query_xml";
}
