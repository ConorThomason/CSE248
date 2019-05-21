package com.conorthomason.garageapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Garage class stores the spaces, vehicles, and space counts of the Garage. Allows input/removal
 * of appropriate objects.
 *
 * @see <A href="../src/model/Garage.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class Garage implements Serializable {

    public void setVehicles(HashMap<String, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Allows the Spaces array to be set differently
     * @param spaces
     */
    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    /**
     * Set the number of car spaces
     * @param carSpaces
     */
    public void setCarSpaces(int carSpaces) {
        this.carSpaces = carSpaces;
    }

    /**
     * Set the number of current cars
     * @param currentCars
     */
    public void setCurrentCars(int currentCars) {
        this.currentCars = currentCars;
    }

    /**
     * Set the number of truck spaces
     * @param truckSpaces
     */
    public void setTruckSpaces(int truckSpaces) {
        this.truckSpaces = truckSpaces;
    }

    /**
     * Set the number of current trucks
     * @param currentTrucks
     */
    public void setCurrentTrucks(int currentTrucks) {
        this.currentTrucks = currentTrucks;
    }

    /**
     * Set the number of motorcycle spaces
     * @param motorcycleSpaces
     */
    public void setMotorcycleSpaces(int motorcycleSpaces) {
        this.motorcycleSpaces = motorcycleSpaces;
    }

    /**
     * Set the number of current motorcycles
     * @param currentMotorcycles
     */
    public void setCurrentMotorcycles(int currentMotorcycles) {
        this.currentMotorcycles = currentMotorcycles;
    }

    private HashMap<String, Vehicle> vehicles;
	private ArrayList<Space> spaces;
	private int carSpaces;
	private int currentCars = 0;
	private int truckSpaces;
	private int currentTrucks = 0;
	private int motorcycleSpaces;
	private int currentMotorcycles = 0;
	private Garage _garage;

	public Garage() {
    }

    /**
     * Instantiate the garage, using the data provided.
     * @param carPaymentSchemes
     * @param motorcyclePaymentSchemes
     * @param truckPaymentSchemes
     * @param carSpaces
     * @param truckSpaces
     * @param motorcycleSpaces
     * @return Garage _garage
     */
	public Garage createGarage(ArrayList<PaymentScheme> carPaymentSchemes, ArrayList<PaymentScheme> motorcyclePaymentSchemes, ArrayList<PaymentScheme> truckPaymentSchemes,
			int carSpaces, int truckSpaces, int motorcycleSpaces) {
		this.carSpaces = carSpaces;
		this.truckSpaces = truckSpaces;
		this.motorcycleSpaces = motorcycleSpaces;
		this.spaces = new ArrayList<Space>();
		this.spaceSetup(VehicleType.CAR, this.carSpaces, carPaymentSchemes);
		this.spaceSetup(VehicleType.MOTORCYCLE, this.motorcycleSpaces, motorcyclePaymentSchemes);
		this.spaceSetup(VehicleType.TRUCK, this.truckSpaces, truckPaymentSchemes);
		vehicles = new HashMap<String, Vehicle>((carSpaces + truckSpaces + motorcycleSpaces) * 2);
		TimeControl.startTime(1);
		return _garage;
	}

    /**
     * Get the number of car spaces
     * @return int carSpaces
     */
	public int getCarSpaces(){
	    return carSpaces;
    }

    /**
     * Get the current number of cars
     * @return int currentCars
     */
    public int getCurrentCars(){
	    return currentCars;
    }

    /**
     * Get the number of truck spaces
     * @return int truckSpaces
     */
    public int getTruckSpaces(){
	    return truckSpaces;
    }

    /**
     * Get the current number of trucks
     * @return int currentTrucks
     */
    public int getCurrentTrucks(){
	    return currentTrucks;
    }

    /**
     * Get the number of motorcycle spaces
     * @return int motorcycleSpaces
     */
    public int getMotorcycleSpaces(){
	    return motorcycleSpaces;
    }

    /**
     * Get the current number of motorcycles
     * @return int currentMotorcycles
     */
    public int getCurrentMotorcycles(){
	    return currentMotorcycles;
    }

    /**
     * Get the List of spaces
     * @return ArrayList spaces
     */
	public ArrayList<Space> getSpaces(){
	    return spaces;
    }

    /**
     * Set up the required number of spaces for each type of vehicle
     * @param vehicleType
     * @param numberOfSpaces
     * @param paymentScheme
     */
	public void spaceSetup(VehicleType vehicleType, int numberOfSpaces, ArrayList<PaymentScheme> paymentScheme) {
		for (int i = 0; i < numberOfSpaces; i++) {
			Space newSpace = new Space(vehicleType, paymentScheme);
			spaces.add(newSpace);
		}
	}

    /**
     * Attempt to add a vehicle to the HashMap.
     * @param vehicle
     * @return boolean success
     */
	private boolean addVehicle(Vehicle vehicle) {
		if (!this.findVehicle(vehicle.getLicensePlate())) {
			vehicles.put(vehicle.getLicensePlate(), vehicle);
			return true;
		}
		return false;
	}

    /**
     * Checks the partitioned indexes, return true if there are more carSpaces than there are cars.
     * @param type
     * @return boolean spaceAvailable
     */
	public boolean spaceAvailable(VehicleType type) {
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

    /**
     * Return a certain space from the Spaces list. Usually the exact index is taken from a Vehicle
     * @param spaceIndex
     * @return Space space
     */
	public Space getSpace(int spaceIndex) {
		return spaces.get(spaceIndex);
	}

    /**
     * Park a vehicle. Handles both the Spaces ArrayList and the Vehicle HashMap
     * @param vehicle
     * @param payment
     * @return boolean success
     */
	public boolean parkVehicle(Vehicle vehicle, PaymentScheme payment) {
	    TimeControl.startTime(1);
	    ArrayList<PaymentScheme> paymentSchemes = new ArrayList<>();
	    paymentSchemes.add(payment);
		int carOffset = carSpaces - currentCars;
		int motorcycleOffset = carSpaces + (motorcycleSpaces - currentMotorcycles);
		int truckOffset = carSpaces + motorcycleSpaces + (truckSpaces - currentTrucks);
		if (spaceAvailable(vehicle.getVehicleType())) {
			switch(vehicle.getVehicleType()) {
			case CAR:
				vehicle.setParkingSpot(carOffset - 1);
				spaces.get(carOffset - 1).setVehicleLicense(vehicle.getLicensePlate());
                spaces.get(carOffset - 1).setTimeDateParked(TimeControl.getCurrentTime());
                spaces.get(carOffset - 1).setAcceptedPaymentSchemes(paymentSchemes);
                currentCars++;
				break;
			case MOTORCYCLE:
				vehicle.setParkingSpot(motorcycleOffset - 1);
				spaces.get(motorcycleOffset - 1).setVehicleLicense(vehicle.getLicensePlate());
                spaces.get(motorcycleOffset - 1).setTimeDateParked(TimeControl.getCurrentTime());
                spaces.get(motorcycleOffset - 1).setAcceptedPaymentSchemes(paymentSchemes);
				currentMotorcycles++;
				break;
			case TRUCK:
				vehicle.setParkingSpot(truckOffset - 1);
				spaces.get(truckOffset - 1).setVehicleLicense(vehicle.getLicensePlate());
                spaces.get(truckOffset - 1).setTimeDateParked(TimeControl.getCurrentTime());
                spaces.get(truckOffset - 1).setAcceptedPaymentSchemes(paymentSchemes);
				currentTrucks++;
				break;
			default:
				break;
			}
			addVehicle(vehicle);
			TimeControl.stopTime();
			return true;
		}
		TimeControl.stopTime();
		return false;
	}

    /**
     * Print the keyset of the vehicle hashmap. Meant for testing
     */
	public void printVehiclesKeySet() {
		System.out.println("Printing Vehicles");
		for (String vehicleLicense : vehicles.keySet()) {
			System.out.println(vehicleLicense);
		}
		System.out.println("Done printing vehicles \n");
	}

    /**
     * If the vehicle is found, returns the vehicle object from the HashMap
     * @param vehicleLicense
     * @return Vehicle vehicle
     */
	public Vehicle getVehicle(String vehicleLicense) {
		if (findVehicle(vehicleLicense)) {
			return vehicles.get(vehicleLicense);
		}
		return null;
	}

    /**
     * Searches the HashMap for the vehicle, returns true if found
     * @param vehicleLicense
     * @return boolean foundVehicle
     */
	public boolean findVehicle(String vehicleLicense) {
		if (vehicles.get(vehicleLicense) == null) {
			return false;
		}
		return true;
	}

    /**
     * Searches for the vehicle, then attempts to remove it. CurrentVehicle counters are adjusted.
     * @param vehicleLicense
     * @return boolean removedVehicle
     */
	public boolean removeVehicle(String vehicleLicense) {
	    VehicleType type = this.getVehicle(vehicleLicense).getVehicleType();
	    switch (type){
            case CAR:
                currentCars--;
                break;
            case MOTORCYCLE:
                currentMotorcycles--;
                break;
            case TRUCK:
                currentTrucks--;
                break;
        }
		if (findVehicle(vehicleLicense)) {
			vehicles.remove(vehicleLicense);
			return true;
		}
		return false;
	}

    /**
     * Provides the vehicle HashMap if direct access is necessary
     * @return HashMap vehicles
     */
	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

    /**
     * Provides a String summary of the garage
     * @return String details
     */
	public String garageDetails() {
		return "Car Spaces: " + this.carSpaces + ", Motorcycle Spaces: " + this.motorcycleSpaces + ", Truck Spaces: " +
				this.truckSpaces;
	}
}
