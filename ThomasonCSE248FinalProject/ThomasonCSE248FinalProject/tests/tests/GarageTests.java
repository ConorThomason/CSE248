package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import model.Garage;
import model.PaymentScheme;

class GarageTests {
	List<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	List<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	List<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
	
	@BeforeClass
	void setup() {
		carPayment.add(PaymentScheme.CASH);
		motorcyclePayment.add(PaymentScheme.DEBIT);
		truckPayment.add(PaymentScheme.CREDIT);
	}
	
	@Test
	void instantiationTest() {
		Garage garage = Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		System.out.println(garage.toString());
		assertTrue(garage.toString().equals("Car Spaces: 5, Motorcycle Spaces: 5, Truck Spaces: 5"));
	}
	
//	@Test
//	void addVehicleTest() {
//		assertTrue(garage.addVehicle(new Vehicle(VehicleType.CAR, "Test", "TEST PLA")));
//	}

}
