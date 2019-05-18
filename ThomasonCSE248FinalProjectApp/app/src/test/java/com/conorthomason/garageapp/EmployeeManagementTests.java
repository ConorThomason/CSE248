package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class EmployeeManagementTests {
	EmployeeManagement employees = new EmployeeManagement();
	
	@Test
	void addEmployeeTest() {
		employees.createEmployeeManagement();
		Employee employee = new Employee("Test", "123", "John", "Doe");
		employees.addEmployee(employee);
		assertTrue(employees.findEmployee(employee.getUsername()));
	}
	
	@Test
	void removeEmployeeTest() {
		employees.createEmployeeManagement();
		Employee employee = new Employee("Test", "123", "John", "Doe");
		employees.addEmployee(employee);
		employees.removeEmployee(employee.getUsername());
		assertFalse(employees.findEmployee(employee.getUsername()));
	}
	
	@Test
	void duplicateEmployeeTest() {
		employees.createEmployeeManagement();
		Employee employee = new Employee("Test", "123", "John", "Doe");
		Employee employee2 = new Employee("Test", "456", "Jane", "Doe");
		employees.addEmployee(employee);
		assertFalse(employees.addEmployee(employee2));
	}
}
