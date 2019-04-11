package model;

/**
 * The Employee class is superclass for both the Manager and Attendant classes.
 * Any password/username restrictions are handled by the Garage class.
 * 
 * @see <A href="../src/model/Employee.java">Java source code</A>
 * 
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 * 
 * @version V1.0, 4/8/2019
 *
 */

public class Employee {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	/**
	 * Constructs Employee, this will rarely (if ever) happen on its own. Will usually be called as a superclass.
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 */
	public Employee(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Username: " + this.username + ", Password: " + this.password
				+ ", First Name: " + this.firstName + ", Last Name: " + this.lastName;
	}
}
