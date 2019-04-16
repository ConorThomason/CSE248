package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Garage {
	
	private static HashMap<String, Vehicle> vehicles;
	private static EmployeeManagement employees;
	private static ArrayList<Space> spaces;
	private static int carSpaces;
	private static int truckSpaces;
	private static int motorcycleSpaces;
	private static Garage _garage = new Garage();

	private Garage() {}
	
	public static Garage createGarage(ArrayList<PaymentScheme> carPaymentSchemes, ArrayList<PaymentScheme> motorcyclePaymentSchemes, ArrayList<PaymentScheme> truckPaymentSchemes, 
			int carSpaces, int truckSpaces, int motorcycleSpaces) {
		Garage.carSpaces = carSpaces;
		Garage.truckSpaces = truckSpaces;
		Garage.motorcycleSpaces = motorcycleSpaces;
		Garage.spaces = new ArrayList<Space>();
		Garage.spaceSetup(VehicleType.CAR, Garage.carSpaces, carPaymentSchemes);
		Garage.spaceSetup(VehicleType.TRUCK, Garage.truckSpaces, truckPaymentSchemes);
		Garage.spaceSetup(VehicleType.MOTORCYCLE, Garage.motorcycleSpaces, motorcyclePaymentSchemes);
		return _garage;
	}
	
	public static void spaceSetup(VehicleType vehicleType, int numberOfSpaces, ArrayList<PaymentScheme> paymentScheme) {
		for (int i = 0; i < numberOfSpaces; i++) {
			Space newSpace = new Space(vehicleType, paymentScheme);
			spaces.add(newSpace);
		}
	}
	
	//This will only ever be called upon the creation of a space by an attendant
	public static boolean hasVehicleSpace(VehicleType vehicleType) {
		boolean returnValue;
		switch(vehicleType) {
			case CAR: returnValue = (Garage.carSpaces != 0) ? true : false;
			case MOTORCYCLE: returnValue = (Garage.motorcycleSpaces != 0) ? true : false;
			case TRUCK: returnValue = (Garage.truckSpaces != 0) ? true : false;
			default: returnValue = false;
		}
		return returnValue;
	}
	
	public static HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}
	@Override
	public String toString() {
		return "Car Spaces: " + Garage.carSpaces + ", Motorcycle Spaces: " + Garage.motorcycleSpaces + ", Truck Spaces: " +
				Garage.truckSpaces;
	}
}
