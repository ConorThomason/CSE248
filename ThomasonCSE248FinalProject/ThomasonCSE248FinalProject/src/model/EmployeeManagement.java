package model;

import java.util.Map;
import java.util.TreeMap;

/**
 * The EmployeeManagement class is the main data structure for storing the information of employees.
 * This will be instantiated in the Garage class.
 * 
 * @see <A href="../src/model/EmployeeManagement.java">Java source code</A>
 * 
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 * 
 * @version V1.0, 4/8/2019
 *
 */

public class EmployeeManagement {
	
	private static TreeMap<String, Employee> tree;
	private static EmployeeManagement _employees = new EmployeeManagement();
	
	public EmployeeManagement() {}
	
	public static EmployeeManagement createEmployeeManagement() {
		tree = new TreeMap<String, Employee>();
		return _employees;
	}
	/**
	 * 
	 * @param employee - Employee object passed
	 * @return boolean, if true added successfully. If false added unsuccessfully.
	 */
	public boolean addEmployee(Employee employee) {
		if (this.findEmployee(employee.getUsername()))
			return false;
		tree.put(employee.getUsername(), employee);
		return true;
	}
	
	/**
	 * 
	 * @param username - key value to search for and remove
	 * @return If removed successfully, return true. If false, removed unsuccessfully.
	 */
	public boolean removeEmployee(String username) {
		Employee removedEmployee = tree.remove(username);
		if (removedEmployee == null) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param username - key value to search for
	 * @return If the employee is found, returns true. If false, not found in the TreeMap.
	 */
	public boolean findEmployee(String username) {
		if (tree.containsKey(username))
			return true;
		return false;
	}
	
	/**
	 * Prints the employees by iterating through the entrySet of the tree.
	 */
	public void printEmployees() {
		for(Map.Entry<String, Employee> entry: tree.entrySet()) {
			System.out.println(tree.get(entry.getKey()));
		}
		System.out.println("\n");
	}
}
