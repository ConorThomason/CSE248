package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Employee;
import model.EmployeeManagement;

class EmployeeManagementTests {
	EmployeeManagement employees = new EmployeeManagement();
	
	@Test
	void addEmployeeTest() {
		Employee employee = new Employee("Test", "123", "John", "Doe");
		employees.addEmployee(employee);
		assertTrue(employees.findEmployee(employee.getUsername()));
	}
	
	@Test
	void removeEmployeeTest() {
		Employee employee = new Employee("Test", "123", "John", "Doe");
		employees.addEmployee(employee);
		employees.removeEmployee(employee.getUsername());
		assertFalse(employees.findEmployee(employee.getUsername()));
	}
	
	@Test
	void duplicateEmployeeTest() {
		Employee employee = new Employee("Test", "123", "John", "Doe");
		Employee employee2 = new Employee("Test", "456", "Jane", "Doe");
		employees.addEmployee(employee);
		assertFalse(employees.addEmployee(employee2));
	}
}
