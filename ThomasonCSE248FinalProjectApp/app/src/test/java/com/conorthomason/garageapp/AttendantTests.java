package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class AttendantTests {
	
	ArrayList<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
    EmployeeManagement employees = new EmployeeManagement();
    Garage garage = new Garage();

    @Test
	void instantiationTest() {
	    employees.createEmployeeManagement();
		Attendant attendant = new Attendant("Test", "123", "John", "Doe");
		System.out.println(attendant.toString());
		assertTrue(attendant.toString().equals("Username: Test, Password: 123, First Name: John, Last Name: Doe"));
	}

	//deprecated
//	@Test
//	void setParkedTest() {
//        employees.createEmployeeManagement();
//		garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
//		Attendant attendant = new Attendant("Test", "123", "John", "Doe");
//		Vehicle vehicle = new Vehicle(VehicleType.CAR, attendant, "AA12 345");
//		attendant.setParked(vehicle.getVehicleType(), vehicle.getLicensePlate());
//		Vehicle foundVehicle = garage.getVehicle(vehicle.getLicensePlate());
//		assertTrue(vehicle.toString().equals(foundVehicle.toString()));
//	}
}
