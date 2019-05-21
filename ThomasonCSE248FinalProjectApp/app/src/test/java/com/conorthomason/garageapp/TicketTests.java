package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class TicketTests {

	@Test
	void instatiationTest() {
		TimeControl.createTimeThread(1);
		Ticket ticket = new Ticket("TEST PLA", VehicleType.CAR, "Test", PaymentScheme.CASH);
		System.out.println(ticket.toString());
		assertTrue(ticket.getLicensePlate().equals("TESTPLA"));
		assertTrue(ticket.getVehicleType() == VehicleType.CAR);
		assertTrue(ticket.getAttendantName().equals("Test"));
		assertTrue(ticket.getDateAndTime().equals(Utilities.zoneStringFormat(TimeControl.getCurrentTime())));
		assertTrue(ticket.getPaymentScheme() == PaymentScheme.CASH);
	}
		
	
	@Test
	void overloadConstructorTest() {
		TimeControl.createTimeThread(1);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA"); //Actual use-case
		Attendant testAttendant = new Attendant("Test", "Test", "FirstTest", "LastTest");
		Ticket ticket = new Ticket(testVehicle, testAttendant.getFullName(), PaymentScheme.CASH);
		assertTrue(ticket.getLicensePlate().equals("TESTPLA"));
		assertTrue(ticket.getVehicleType() == VehicleType.CAR);
		assertTrue(ticket.getAttendantName().equals("FirstTest LastTest"));
		assertTrue(ticket.getDateAndTime().equals(Utilities.zoneStringFormat(TimeControl.getCurrentTime())));
		assertTrue(ticket.getPaymentScheme() == PaymentScheme.CASH);
	}

}
