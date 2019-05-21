package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class GarageTests {
	ArrayList<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
    Garage garage = new Garage();
	
	@Test
	void instantiationTest() {

		garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		carPayment.add(PaymentScheme.CASH);
		motorcyclePayment.add(PaymentScheme.DEBIT);
		truckPayment.add(PaymentScheme.CREDIT);
		assertTrue(garage.garageDetails().equals("Car Spaces: 5, Motorcycle Spaces: 5, Truck Spaces: 5"));
	}
	
	@Test
	void parkVehicleTest() {
		garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
		assertTrue(garage.parkVehicle(testVehicle, PaymentScheme.CASH));
	}
	
	@Test
	void findVehicleTest() {
		garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
		assertTrue(garage.parkVehicle(testVehicle, PaymentScheme.CASH));
		assertTrue(garage.findVehicle(testVehicle.getLicensePlate()));
	}
	@Test
	void getVehicleTest() {
		garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
		assertTrue(garage.parkVehicle(testVehicle, PaymentScheme.CASH));
		assertTrue(garage.findVehicle(testVehicle.getLicensePlate()));
		assertEquals(testVehicle, garage.getVehicle("TESTPLA"));
	}
	@Test
    void parkMultipleTest(){
        garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
        Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
        Vehicle testVehicle2 = new Vehicle(VehicleType.CAR, "Test2", "TEST PLA2");
        garage.parkVehicle(testVehicle, PaymentScheme.CASH);
        garage.parkVehicle(testVehicle2, PaymentScheme.CASH);
        assertTrue(garage.getVehicle("TESTPLA").equals(testVehicle));
        assertTrue(garage.getVehicle("TESTPLA2").equals(testVehicle2));
    }

}
