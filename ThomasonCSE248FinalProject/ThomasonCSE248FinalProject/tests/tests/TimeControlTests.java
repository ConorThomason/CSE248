package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.TimeControl;

class TimeControlTests {

	@Test
	void test() {
		TimeControl.createTimeThread(1);
		TimeControl.stopTime();
		System.out.println(TimeControl.clockDefaultZone());
		assertTrue(true);
	}

}
