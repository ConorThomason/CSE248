package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Garage {
	
	private static HashMap<String, Vehicle> vehicles;
	private static EmployeeManagement employees;
	private static ArrayList<Space> spaces;
	private static int carSpaces;
	private static int currentCars = 0;
	private static int truckSpaces;
	private static int currentTrucks = 0;
	private static int motorcycleSpaces;
	private static int currentMotorcycles = 0;
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
		vehicles = new HashMap<String, Vehicle>((carSpaces + truckSpaces + motorcycleSpaces) * 2);
		return _garage;
	}
	
	public static void spaceSetup(VehicleType vehicleType, int numberOfSpaces, ArrayList<PaymentScheme> paymentScheme) {
		for (int i = 0; i < numberOfSpaces; i++) {
			Space newSpace = new Space(vehicleType, paymentScheme);
			spaces.add(newSpace);
		}
	}
	
	private static boolean addVehicle(Vehicle vehicle) {
		if (!Garage.findVehicle(vehicle.getLicensePlate())) {
			vehicles.put(vehicle.getLicensePlate(), vehicle);
			return true;
		}
		return false;
	}
	
	public static boolean spotAvailable(VehicleType type) {
		boolean returnedValue;
		switch(type) {
		case CAR: returnedValue = (currentCars < carSpaces) ? true : false;
			break;
		case MOTORCYCLE: returnedValue = (currentMotorcycles < motorcycleSpaces) ? true : false;
			break;
		case TRUCK: returnedValue = (currentTrucks < truckSpaces) ? true : false;
			break;
		default: returnedValue = false;
			break;
		}
		return returnedValue;
	}
	
	public static boolean parkVehicle(Vehicle vehicle) {
		if (spotAvailable(vehicle.getVehicleType())) {
			switch(vehicle.getVehicleType()) {
			case CAR:
				vehicle.setParkingSpot(carSpaces - currentCars);
				spaces.get(carSpaces - currentCars).setVehicleLicense(vehicle.getLicensePlate());
				break;
			case MOTORCYCLE:
				vehicle.setParkingSpot(motorcycleSpaces - currentMotorcycles);
				spaces.get(motorcycleSpaces - currentMotorcycles).setVehicleLicense(vehicle.getLicensePlate());
				break;
			case TRUCK:
				vehicle.setParkingSpot(truckSpaces - currentTrucks);
				spaces.get(truckSpaces - currentTrucks).setVehicleLicense(vehicle.getLicensePlate());
				break;
			default:
				break;
			}
			addVehicle(vehicle);
		} else {
			removeVehicle(vehicle.getLicensePlate());
		}
		return false;
	}
	public static boolean findVehicle(String vehicleLicense) {
		boolean returnedValue = (vehicles.get(vehicleLicense) == null) ? false : true;
		return returnedValue;
	}
	
	public static boolean removeVehicle(String vehicleLicense) {
		if (findVehicle(vehicleLicense)) {
			vehicles.remove(vehicleLicense);
			return true;
		}
		return false;
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
