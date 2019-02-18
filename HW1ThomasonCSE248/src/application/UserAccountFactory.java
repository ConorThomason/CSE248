package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class UserAccountFactory {
	private static int id = 1;
	private NameWarehouse warehouse;
	public UserAccountFactory() {
		this.warehouse = new NameWarehouse();
	}
	public int getId() {
		return id;
	}
	public String emitLastName(int specificName) {
		return warehouse.getLastName(specificName);
	}
	public String emitLastName() {
		return warehouse.getLastName(generateBoundedRandom("Last Name"));
	}
	public int generateBoundedRandom(String type) {
		int randomRange = warehouse.getWarehouseSize(type);
		return new Random().nextInt(randomRange);
	}
	public String emitFirstName(String gender, int specificName) {
		if (gender.equals("Female"))
			return warehouse.getFemaleName(specificName);
		else
			return warehouse.getMaleName(specificName);
	}
	public String emitFirstName(String gender) {
		if (gender.equals("Female"))
			return warehouse.getFemaleName(generateBoundedRandom("Female"));
		else
			return warehouse.getMaleName(generateBoundedRandom("Male"));
	}
	public String emitGender() {
		Random rand = new Random();
		int result = rand.nextInt(2);
		return (result == 0) ? "Male" : "Female";
	}
	public String emitGender(int type) {
		return (type == 0) ? "Male" : "Female";
	}
	public String emitId() {
		return Integer.toString(id++);
	}
	public String emitUserName(String firstName, String lastName, String id) {
		if (lastName.length() < 4) {
			return firstName.charAt(0) + lastName + id.charAt(id.length() - 1);
		}
		else {
			return firstName.charAt(0) + lastName.substring(0, 4) + id.charAt(id.length() - 1);
		}
	}
	public String generateEmptyString(int length) {
		String returned = "";
		for (int i = 0; i < length; i++) {
			returned += " ";
		}
		return returned;
	}
	public String emitPassword() {
		return generatePassword();
		
	}
	public String generatePassword() {
		String symbolSet = " !\"#$%&'()*+,-.//:;<=>?@[\\]^_`{|}~";
		String upperCaseSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseSet = "abcdefghijklmnopqrstuvwxyz";
		String numberSet = "1234567890";
		int length = new Random().nextInt(30-8) + 8; //Upperbound = 30, lower = 8. Add 8 to guarantee 8 or more.
		//Decide on initial placement of required char from spec. From then onwards, characters can be random (fillPasswordSpaces).
		String password = generateEmptyString(length);
		password = passwordRandomPosition(password, symbolSet);
		password = passwordRandomPosition(password, upperCaseSet);
		password = passwordRandomPosition(password, lowerCaseSet);
		password = passwordRandomPosition(password, numberSet);
		return fillPasswordSpaces(password);
	}
	public String passwordRandomPosition(String password, String charSet) {
		StringBuilder editedPass = new StringBuilder(password);
		int randomPosition = new Random().nextInt(password.length() - 1);
		while (password.charAt(randomPosition) != ' ') {
			randomPosition = new Random().nextInt(password.length() - 1);
		}
		editedPass.setCharAt(randomPosition, charSet.charAt(new Random().nextInt(charSet.length() - 1)));
		return editedPass.toString();
	}
	public String fillPasswordSpaces(String partialFill) {
		StringBuilder finalPass = new StringBuilder(partialFill);
		String allSet = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "abcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < partialFill.length(); i++) {
			if (finalPass.charAt(i) == ' ') {
				finalPass.setCharAt(i, allSet.charAt(new Random().nextInt(allSet.length() - 1)));
			}
			else {
				continue;
			}
		}
		return finalPass.toString();
	}
	public double emitGpa() {
		BigDecimal gpa = new BigDecimal(Double.toString(new Random().nextDouble() * 4));
		return gpa.setScale(3, RoundingMode.HALF_EVEN).doubleValue();
		//Next double returns a value [0.0, 1.0]. Multiplying by 4 brings it to the upper bound desired.
	}
	public User emitUserAccount(String firstName, String lastName, String gender, double gpa, String password) {
		String id = this.emitId();
		String userName = this.emitUserName(firstName, lastName, id);
		User newUser = new User(firstName, lastName, gender, id, userName, password, gpa);
		return newUser;
	}
	public User emitUserAccount() {
		String gender = this.emitGender();
		String firstName;
		if (gender.equals("Male"))
			firstName = this.emitFirstName("Male");
		else
			firstName = this.emitFirstName("Female");
		String lastName = this.emitLastName();
		String id = this.emitId();
		String userName = this.emitUserName(firstName, lastName, id);
		String password = this.emitPassword();
		double gpa = this.emitGpa();
		User newUser = new User(firstName, lastName, gender, id, userName, password, gpa);
		return newUser;
	}
}
