package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import model.Garage;
import model.PaymentScheme;

class GarageTests {
	ArrayList<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
	
	@BeforeClass
	void setup() {
		
	}
	
	@Test
	void instantiationTest() {
		carPayment.add(PaymentScheme.CASH);
		motorcyclePayment.add(PaymentScheme.DEBIT);
		truckPayment.add(PaymentScheme.CREDIT);
		Garage garage = Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		System.out.println(garage.toString());
		assertTrue(garage.toString().equals("Car Spaces: 5, Motorcycle Spaces: 5, Truck Spaces: 5"));
	}
	
//	@Test
//	void addVehicleTest() {
//		assertTrue(garage.addVehicle(new Vehicle(VehicleType.CAR, "Test", "TEST PLA")));
//	}

}
