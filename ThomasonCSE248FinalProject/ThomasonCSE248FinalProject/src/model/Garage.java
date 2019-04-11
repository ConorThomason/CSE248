package model;

import java.util.HashMap;
import java.util.List;

public class Garage {
	private HashMap<String, Vehicle> vehicles;
	private EmployeeManagement employees;
	private Space[] carSpaces;
	private Space[] truckSpaces;
	private Space[] motorcycleSpaces;
	
	public Garage(List<PaymentScheme> carPaymentSchemes, List<PaymentScheme> motorcyclePaymentSchemes, List<PaymentScheme> truckPaymentSchemes, int carSpaces, int truckSpaces, int motorcycleSpaces) {
		//Keep the payment scheme associated to the spaces, not the enum.
		this.carSpaceSetup(VehicleType.CAR, carSpaces, carPaymentSchemes);
		this.truckSpaceSetup(VehicleType.TRUCK, truckSpaces, truckPaymentSchemes);
		this.motorcycleSpaceSetup(VehicleType.MOTORCYCLE, motorcycleSpaces, motorcyclePaymentSchemes);
	}
	
	public void carSpaceSetup(VehicleType vehicleType, int numberOfSpaces, List<PaymentScheme> paymentScheme) {
		spaceSetup(vehicleType, numberOfSpaces, carSpaces, paymentScheme);
	}
	
	public void truckSpaceSetup(VehicleType vehicleType, int numberOfSpaces, List<PaymentScheme> paymentScheme) {
		spaceSetup(vehicleType, numberOfSpaces, truckSpaces, paymentScheme);
	}
	
	public void motorcycleSpaceSetup(VehicleType vehicleType, int numberOfSpaces, List<PaymentScheme> paymentScheme) {
		spaceSetup(vehicleType, numberOfSpaces, motorcycleSpaces, paymentScheme);
	}
	
	public void spaceSetup(VehicleType vehicleType, int numberOfSpaces, Space[] spaces, List<PaymentScheme> paymentScheme) {
		spaces = new Space[numberOfSpaces];
		for (int i = 0; i < spaces.length; i++) {
			spaces[i] = new Space(vehicleType, paymentScheme);
		}
	}
}
