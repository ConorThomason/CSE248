package applicationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.User;
import application.UserAccountBag;

class UserAccountBagTest {
	UserAccountBag bag = new UserAccountBag();
	@Test
	void bagInsertionTest() {
		User testUser = new User("Test", "Test", "Male", "1", "TTest1", "TestPass1!", 3.5);
		bag.insertUser(testUser);
		assertEquals(testUser, bag.findUser(testUser.getUserName()));
	}
	
	@Test
	void padUsersTest() {
		bag.padUsers();
		assertEquals(3000, bag.getUserTableSize());
	}
	
	@Test
	void multipleUsersFindTest() {
		User test3User = new User ("Test3", "Test", "Male", "1", "TTest3", "TestPass1!", 3.5);
		bag.insertUser(new User ("Test", "Test", "Male", "1", "TTest1", "TestPass1!", 3.5));
		bag.insertUser(new User ("Test2", "Test", "Male", "1", "TTest2", "TestPass1!", 3.5));
		bag.insertUser(test3User);
		bag.insertUser(new User ("Test4", "Test", "Male", "1", "TTest4", "TestPass1!", 3.5));
		bag.insertUser(new User ("Test5", "Test", "Male", "1", "TTest5", "TestPass1!", 3.5));
		assertEquals(test3User, bag.findUser(test3User.getUserName()));
	}
	
	/*
	 * Hashmaps overwrite any duplicate keys. Ideally, upon running this test,
	 * the original user should still exist because the measures taken to prevent duplicates
	 * come into play.
	 */
	@Test
	void hashmapDuplicateTest() { 
		User testUser = new User ("Test", "Test", "Male", "1", "TTest", "TestPass1!", 3.5);
		User testUser1 = new User ("Test2", "Test", "Male", "1", "TTest", "TestPass1!", 3.5);
		bag.insertUser(testUser);
		bag.insertUser(testUser1);
		//Same keys (userName), searching should produce a user equal to testUser, not testUser1.
		assertEquals(testUser, bag.findUser("TTest"));
		assertTrue(bag.duplicateCheck(testUser)); //Double checking the duplicateCheck method.
	}
}
