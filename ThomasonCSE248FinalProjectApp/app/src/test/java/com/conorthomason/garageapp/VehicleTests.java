package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class VehicleTests {
	
	@Test
	void vehicleGenerateTest() {
		Vehicle vehicle = new Vehicle(VehicleType.CAR, "John", "AA12 345");
		assertEquals(vehicle.getLicensePlate(), "AA12345");
		assertTrue(vehicle.getParkedBy().equals("John"));
		assertTrue(vehicle.getVehicleType().equals(VehicleType.CAR));
	}
	
	@Test
	void vehicleTypeTest() {
		VehicleType vehicle = VehicleType.CAR;
		assertTrue(vehicle.toString().equals("CAR"));
		assertTrue(vehicle.getEarlyBirdPrice() == 20.00);
		assertTrue(vehicle.getHourlyRate() == 2.50);
		
		vehicle = VehicleType.TRUCK;
		assertTrue(vehicle.toString().equals("TRUCK"));
		assertTrue(vehicle.getEarlyBirdPrice() == 40.00);
		assertTrue(vehicle.getHourlyRate() == 5.00);
		
		vehicle = VehicleType.MOTORCYCLE;
		assertTrue(vehicle.toString().equals("MOTORCYCLE"));
		assertTrue(vehicle.getEarlyBirdPrice() == 10.00);
		assertTrue(vehicle.getHourlyRate() == 1.00);
	}
}
