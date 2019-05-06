package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class TimeControlTests {

	@Test
	void timeControlTest() {
		TimeControl.createTimeThread((long)1);
		System.out.println(Utilities.zoneStringFormat(TimeControl.getCurrentTime()));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TimeControl.stopTime();
		System.out.println("Finished execution");
		System.out.println(Utilities.zoneStringFormat(TimeControl.getCurrentTime()));
		assertTrue(true);
	}
}
