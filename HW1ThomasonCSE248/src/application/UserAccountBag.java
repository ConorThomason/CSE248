package application;

import java.util.HashMap;

public class UserAccountBag {
	private UserAccountFactory userFactory;
	private HashMap<String, User> userTable = new HashMap<String, User>(10000); //10000 as the spec requires 5000 accounts. Double the amount to keep the
																		//load factor below or at 0.5
	private User currentUser = null;
	public UserAccountBag() {
		userFactory = new UserAccountFactory();
	}
	public void padUsers() {
		for (int i = 0; i < 3000; i++) {
			boolean result = this.insertUser(userFactory.emitUserAccount());
			if (!result)
				i--; //If a username duplicate is found, false is returned and the user won't be entered. The loop decrements by 1 to try again.
		}
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User updatedUser) {
		currentUser = updatedUser;
	}
	public User createUser(String firstName, String lastName, String gender, double gpa, String password) {
		return userFactory.emitUserAccount(firstName, lastName, gender, gpa, password);
	}
	public boolean insertUser(User newUser) {
		if (!searchAccount(newUser.getUserName())) {
			userTable.put(newUser.getUserName(), newUser);
			return true;
		}
		else
			return false;
	}
	public boolean userSignIn(String user, String password) {
		if (searchAccount(user, password) == true) {
			currentUser = userTable.get(user);
			return true;
		}
		else
			return false;
	}
	public boolean searchAccount(String user) {
		if (userTable.containsKey(user))
			return true;
		else
			return false;
	}
	public int getId() {
		return userFactory.getId();
	}
	/*
	 * This method should allow for the addition of users with the same first and last name, with the same final digit of their id
	 * (by adding 1 to the id). Given enough clashes, this will result in multiple numbers at the end of the username.
	 */
	public String emitUserName(String firstName, String lastName, String id) {
		String userName = userFactory.emitUserName(firstName, lastName, id);
		while (searchAccount(userName)) {
			int newId = Integer.parseInt(id) + 1;
			userName = userFactory.emitUserName(firstName, lastName, Integer.toString(newId));
		}
		return userName;
			
	}
	public boolean searchAccount(String userName, String password) {
		User foundUser = this.findUser(userName);
		if (foundUser != null) {
			return foundUser.getPassword().equals(password);
		}
		return false;
	}
	public User findUser(String userName) {
		return userTable.get(userName);
	}
	public int getUserTableSize() {
		return userTable.size();
	}
}
