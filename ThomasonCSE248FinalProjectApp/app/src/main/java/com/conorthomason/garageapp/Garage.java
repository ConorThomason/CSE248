package com.conorthomason.garageapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Garage {

    public static void setVehicles(HashMap<String, Vehicle> vehicles) {
        Garage.vehicles = vehicles;
    }

    public static void setSpaces(ArrayList<Space> spaces) {
        Garage.spaces = spaces;
    }

    public static void setCarSpaces(int carSpaces) {
        Garage.carSpaces = carSpaces;
    }

    public static void setCurrentCars(int currentCars) {
        Garage.currentCars = currentCars;
    }

    public static void setTruckSpaces(int truckSpaces) {
        Garage.truckSpaces = truckSpaces;
    }

    public static void setCurrentTrucks(int currentTrucks) {
        Garage.currentTrucks = currentTrucks;
    }

    public static void setMotorcycleSpaces(int motorcycleSpaces) {
        Garage.motorcycleSpaces = motorcycleSpaces;
    }

    public static void setCurrentMotorcycles(int currentMotorcycles) {
        Garage.currentMotorcycles = currentMotorcycles;
    }

    private static HashMap<String, Vehicle> vehicles;
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
		Garage.spaceSetup(VehicleType.MOTORCYCLE, Garage.motorcycleSpaces, motorcyclePaymentSchemes);
		Garage.spaceSetup(VehicleType.TRUCK, Garage.truckSpaces, truckPaymentSchemes);
		vehicles = new HashMap<String, Vehicle>((carSpaces + truckSpaces + motorcycleSpaces) * 2);
		return _garage;
	}

	public static int getCarSpaces(){
	    return carSpaces;
    }
    public static int getCurrentCars(){
	    return currentCars;
    }
    public static int getTruckSpaces(){
	    return truckSpaces;
    }
    public static int getCurrentTrucks(){
	    return currentTrucks;
    }
    public static int getMotorcycleSpaces(){
	    return motorcycleSpaces;
    }
    public static int getCurrentMotorcycles(){
	    return currentMotorcycles;
    }

	public static ArrayList<Space> getSpaces(){
	    return spaces;
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
	
	public static boolean spaceAvailable(VehicleType type) {
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
	
	public static Space getSpace(int spaceIndex) {
		return spaces.get(spaceIndex);
	}
	
	public static boolean parkVehicle(Vehicle vehicle) {
		int carOffset = carSpaces - currentCars;
		int motorcycleOffset = carSpaces + (motorcycleSpaces - currentMotorcycles);
		int truckOffset = carSpaces + motorcycleSpaces + (truckSpaces - currentTrucks);
		if (spaceAvailable(vehicle.getVehicleType())) {
			switch(vehicle.getVehicleType()) {
			case CAR:
				vehicle.setParkingSpot(carOffset);
				spaces.get(carSpaces - currentCars).setVehicleLicense(vehicle.getLicensePlate());
				break;
			case MOTORCYCLE:
				vehicle.setParkingSpot(motorcycleOffset);
				spaces.get(motorcycleSpaces - currentMotorcycles).setVehicleLicense(vehicle.getLicensePlate());
				break;
			case TRUCK:
				vehicle.setParkingSpot(truckOffset);
				spaces.get(truckSpaces - currentTrucks).setVehicleLicense(vehicle.getLicensePlate());
				break;
			default:
				break;
			}
			addVehicle(vehicle);
			return true;
		}
		return false;
	}
	public static void printVehiclesKeySet() {
		System.out.println("Printing Vehicles");
		for (String vehicleLicense : vehicles.keySet()) {
			System.out.println(vehicleLicense);
		}
		System.out.println("Done printing vehicles \n");
	}
	public static Vehicle getVehicle(String vehicleLicense) {
		if (findVehicle(vehicleLicense)) {
			return vehicles.get(vehicleLicense);
		}
		return null;
	}
	
	public static boolean findVehicle(String vehicleLicense) {
		if (vehicles.get(vehicleLicense) == null) {
			return false;
		}
		return true;
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
	
	public static String garageDetails() {
		return "Car Spaces: " + Garage.carSpaces + ", Motorcycle Spaces: " + Garage.motorcycleSpaces + ", Truck Spaces: " +
				Garage.truckSpaces;
	}
}
