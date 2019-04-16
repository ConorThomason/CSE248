package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Garage;
import model.PaymentScheme;
import model.Vehicle;
import model.VehicleType;

class GarageTests {
	Garage garage;
	List<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	List<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	List<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
	
	@Test
	void instantiationTest() {
		garage = new Garage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		System.out.println(garage.toString());
		assertTrue(garage.toString().equals("Car Spaces: 5, Motorcycle Spaces: 5, Truck Spaces: 5"));
	}
	
	@Test
	void addVehicleTest() {
		assertTrue(garage.addVehicle(new Vehicle(VehicleType.CAR, "Test", "TEST PLA")));
	}

}
