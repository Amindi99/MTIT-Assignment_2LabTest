package com.hackerthon.model;

import com.hackerthon.common.CommonConstants;


//All the getters and setters display here

public class Employee {

	public String employeeId;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	//return the object as a string in the tostring() method
	
	@Override
	public String toString() {
		return String.format(CommonConstants.EMPLOYEE_TO_STRING_FORMAT,
				employeeId, fullName, address, facultyName, department, designation);
	}
}
