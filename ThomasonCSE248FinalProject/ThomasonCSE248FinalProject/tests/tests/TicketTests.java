package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import model.Attendant;
import model.PaymentScheme;
import model.Ticket;
import model.Vehicle;
import model.VehicleType;

class TicketTests {

	@Test
	void instatiationTest() {
		Ticket ticket = new Ticket("TEST PLA", VehicleType.CAR, "Test", PaymentScheme.CASH, Clock.fixed(Instant.parse( "1970-01-01T00:00:00Z"), ZoneId.of("UTC")));
		System.out.println(ticket.toString());
		assertTrue(ticket.getLicensePlate().equals("TESTPLA"));
		assertTrue(ticket.getVehicleType() == VehicleType.CAR);
		assertTrue(ticket.getAttendantName().equals("Test"));
		assertTrue(ticket.getPaymentScheme() == PaymentScheme.CASH);
		assertTrue(ticket.getDateAndTime().equals("1970-01-01 00:00:00 UTC"));
	}
		
	
	@Test
	void overloadConstructorTest() {
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA"); //Actual use-case
		Attendant testAttendant = new Attendant("Test", "Test", "FirstTest", "LastTest");
		Ticket ticket = new Ticket(testVehicle, testAttendant, PaymentScheme.CASH);
		assertTrue(ticket.getLicensePlate().equals("TESTPLA"));
		assertTrue(ticket.getVehicleType() == VehicleType.CAR);
		assertTrue(ticket.getAttendantName().equals("Test"));
		assertTrue(ticket.getPaymentScheme() == PaymentScheme.CASH);
		assertTrue(ticket.getDateAndTime().equals(Clock.systemDefaultZone().toString()));
	}

}
