package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import model.Garage;
import model.PaymentScheme;
import model.Vehicle;
import model.VehicleType;

class GarageTests {
	ArrayList<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
	
	@Test
	void instantiationTest() {
		Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		carPayment.add(PaymentScheme.CASH);
		motorcyclePayment.add(PaymentScheme.DEBIT);
		truckPayment.add(PaymentScheme.CREDIT);
		assertTrue(Garage.garageDetails().equals("Car Spaces: 5, Motorcycle Spaces: 5, Truck Spaces: 5"));
	}
	
	@Test
	void parkVehicleTest() {
		Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
		assertTrue(Garage.parkVehicle(testVehicle));
	}
	
	@Test
	void findVehicleTest() {
		Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
		assertTrue(Garage.parkVehicle(testVehicle));
		assertTrue(Garage.findVehicle(testVehicle.getLicensePlate()));
	}
	@Test
	void getVehicleTest() {
		Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle testVehicle = new Vehicle(VehicleType.CAR, "Test", "TEST PLA");
		assertTrue(Garage.parkVehicle(testVehicle));
		assertTrue(Garage.findVehicle(testVehicle.getLicensePlate()));
		assertEquals(testVehicle, Garage.getVehicle("TESTPLA"));
		
	}

}
