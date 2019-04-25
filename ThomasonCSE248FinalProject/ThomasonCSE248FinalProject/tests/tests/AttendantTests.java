package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Attendant;
import model.Garage;
import model.PaymentScheme;
import model.Vehicle;
import model.VehicleType;

class AttendantTests {
	
	ArrayList<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
	
	@Test
	void instantiationTest() {
		Attendant attendant = new Attendant("Test", "123", "John", "Doe");
		System.out.println(attendant.toString());
		assertTrue(attendant.toString().equals("Username: Test, Password: 123, First Name: John, Last Name: Doe"));
	}
	
	@Test
	void setParkedTest() {
		Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Attendant attendant = new Attendant("Test", "123", "John", "Doe");
		Vehicle vehicle = new Vehicle(VehicleType.CAR, attendant, "AA12 345");
		attendant.setParked(vehicle.getVehicleType(), vehicle.getLicensePlate());
		Vehicle foundVehicle = Garage.getVehicle(vehicle.getLicensePlate());
		assertTrue(vehicle.toString().equals(foundVehicle.toString()));
	}
}
