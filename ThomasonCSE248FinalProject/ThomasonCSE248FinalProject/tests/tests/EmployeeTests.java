package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Employee;

class EmployeeTests {

	@Test
	void employeeGenerateTest() {
		Employee employee = new Employee("Test", "123", "John", "Doe");
		assertTrue(employee.toString().equals("Username: Test, Password: 123,"
				+ " First Name: John, Last Name: Doe"));
	}

}
