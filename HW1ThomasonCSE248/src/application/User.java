package application;

public class User {
	private String firstName;
	private String lastName;
	private String gender;
	private String id;
	private String userName;
	private String password;
	private double gpa;
	
	public User(String firstName, String lastName, String gender, String id, String userName, String password, double gpa) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.gpa = gpa;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String isGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", id=" + id
				+ ", userName=" + userName + ", password=" + password + ", gpa=" + gpa + "]";
	}
	
	
}
