package application;

import java.util.HashMap;

public class UserAccountBag {
	UserAccountFactory userFactory;
	HashMap<String, User> userTable = new HashMap<String, User>(10000); //10000 as the spec requires 5000 accounts. Double the amount to keep the
																		//load factor below or at 0.5
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
	public boolean insertUser(User newUser) {
		if (!duplicateCheck(newUser)) {
			userTable.put(newUser.getUserName(), newUser);
			return true;
		}
		else
			return false;
	}
	public boolean duplicateCheck(User user) {
		if (userTable.containsKey(user.getUserName()))
			return true;
		else
			return false;
	}
	public boolean passwordCheck(String userName, String password) {
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
