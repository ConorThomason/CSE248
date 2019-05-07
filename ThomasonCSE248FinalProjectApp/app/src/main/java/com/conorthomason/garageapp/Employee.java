package com.conorthomason.garageapp;

import java.io.Serializable;

/**
 * The Employee class is superclass for both the Manager and Attendant classes.
 * Any password/username restrictions are handled by the EmployeeManagement class.
 * 
 * @see <A href="../src/model/Employee.java">Java source code</A>
 * 
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 * 
 * @version V1.0, 4/11/2019
 *
 */

public class Employee implements Serializable {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	/**
	 * Constructs Employee, this will rarely (if ever) happen on its own. Will usually be called as a superclass.
	 * @param username String username, entry restrictions will be enforced upon entry to EmployeeManagement
	 * @param password String password, may be encrypted later. Low priority task currently.
	 * @param firstName String firstName, self-explanatory.
	 * @param lastName String lastName, self-explanatory.
	 */
	public Employee(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * 
	 * @return username Returns employee's username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * @param username String (restrictions on creation are placed later)
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 
	 * @return password Returns String password (Encryption will be considered)
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password String sets Employee pass. (Encryption will be considered)
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return String firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * 
	 * @param firstName String sets firstName. 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * 
	 * @return String lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * 
	 * @param lastName String sets lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
	
	@Override
	public String toString() {
		return "Username: " + this.username + ", Password: " + this.password
				+ ", First Name: " + this.firstName + ", Last Name: " + this.lastName;
	}
}
