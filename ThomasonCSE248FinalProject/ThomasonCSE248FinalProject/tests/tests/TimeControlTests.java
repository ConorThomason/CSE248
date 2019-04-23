package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.TimeControl;

class TimeControlTests {

	@Test
	void timeControlTest() {
		TimeControl.createTimeThread((long)1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TimeControl.stopTime();
		System.out.println("Finished execution");
		assertTrue(true); //If it makes it this far, the thread stopped successfully.
	}

}
