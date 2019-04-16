package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Garage {
	private HashMap<String, Vehicle> vehicles;
	private EmployeeManagement employees;
	private ArrayList<Space> spaces;
	private int carSpaces;
	private int truckSpaces;
	private int motorcycleSpaces;
	
	
	public Garage(List<PaymentScheme> carPaymentSchemes, List<PaymentScheme> motorcyclePaymentSchemes, List<PaymentScheme> truckPaymentSchemes, 
			int carSpaces, int truckSpaces, int motorcycleSpaces) {
		//Keep the payment scheme associated to the spaces, not the enum.
		spaces = new ArrayList<Space>();
		this.carSpaces = carSpaces;
		this.truckSpaces = truckSpaces;
		this.motorcycleSpaces = motorcycleSpaces;
		this.spaceSetup(VehicleType.CAR, carSpaces, carPaymentSchemes);
		this.spaceSetup(VehicleType.TRUCK, truckSpaces, carPaymentSchemes);
		this.spaceSetup(VehicleType.MOTORCYCLE, motorcycleSpaces, carPaymentSchemes);
	}
	
	public void spaceSetup(VehicleType vehicleType, int numberOfSpaces, List<PaymentScheme> paymentScheme) {
		for (int i = 0; i < numberOfSpaces; i++) {
			spaces.add(new Space(vehicleType, paymentScheme));
		}
	}
	
	//This will only ever be called upon the creation of a space by an attendant
	public boolean hasVehicleSpace(VehicleType vehicleType) {
		boolean returnValue;
		switch(vehicleType) {
			case CAR: returnValue = (this.carSpaces != 0) ? true : false;
			case MOTORCYCLE: returnValue = (this.motorcycleSpaces != 0) ? true : false;
			case TRUCK: returnValue = (this.truckSpaces != 0) ? true : false;
			default: returnValue = false;
		}
		return returnValue;
	}
	public abstract boolean addVehicle(Vehicle vehicle);
	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}
	@Override
	public String toString() {
		return "Car Spaces: " + carSpaces + ", Motorcycle Spaces: " + motorcycleSpaces + ", Truck Spaces: " +
				truckSpaces;
	}
}
