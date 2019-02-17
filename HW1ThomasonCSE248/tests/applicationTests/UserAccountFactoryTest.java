package applicationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import application.UserAccountFactory;


class UserAccountFactoryTest {
	UserAccountFactory userFactory = new UserAccountFactory();
	String symbolSet = " !\"#$%&'()*+,-.//:;<=>?@[\\]^_`{|}~";
	String upperCaseSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String lowerCaseSet = "abcdefghijklmnopqrstuvwxyz";
	String numberSet = "1234567890";
	
	@Test
	void emitLastNameTest() {
		assertEquals("Zhang", userFactory.emitLastName(2));
	}
	
	@Test
	void emitFemaleFirstNameTest() {
		assertEquals("Ava", userFactory.emitFirstName("Female", 2));
	}
	
	@Test
	void emitMaleFirstNameTest() {
		assertEquals("Noah", userFactory.emitFirstName("Male", 1));
	}
	
	@Test
	void emitGenderTest() {
		assertEquals("Female", userFactory.emitGender(1));
		assertEquals("Male", userFactory.emitGender(0));
	}
	
	@Test
	void emitUserName() {
		assertEquals("LKirk1", userFactory.emitUserName("Liam", "Kirkpatrick", "1"));
		assertEquals("AFry1", userFactory.emitUserName("Ava", "Fry", "1"));
	}
	
	@Test
	void emitPassword() {
		boolean passwordPasses = false;
		String password = userFactory.emitPassword();
		if (passwordContains(password, upperCaseSet) && passwordContains(password, symbolSet) &&
				passwordContains(password, lowerCaseSet) && passwordContains(password, numberSet)
				&& password.length() >= 8) {
			passwordPasses = true;
		}
		assertEquals(true, passwordPasses);
	}
	boolean passwordContains(String password, String charSet) {
		for (int i = 0; i < password.length(); i++) {
			for (int j = 0; j < charSet.length(); j++) {
				if (charSet.charAt(j) == password.charAt(i))
					return true;
			}
			
		}
		return false;
	}
	
	@Test
	void emitGpaTest() {
		assertTrue(userFactory.emitGpa() >= 0.0 && userFactory.emitGpa() <= 4.0);
	}

}
