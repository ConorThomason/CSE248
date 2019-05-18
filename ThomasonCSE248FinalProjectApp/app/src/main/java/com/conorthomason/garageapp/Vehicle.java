package com.conorthomason.garageapp;

import java.io.Serializable;

/**
 * The Vehicle class is the object stored in the main data structure. Data is stored and manipulated here
 * 
 * @see <A href="../src/model/Vehicle.java">Java source code</A>
 * 
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 * 
 * @version V1.0, 4/8/2019
 *
 */

public class Vehicle implements Serializable {
	
	private VehicleType vehicleType;
	private String parkedBy;
	private int parkingSpot;
	private String licensePlate;
	
	/**
	 * Constructs the object, usually instantiated from an Attendant class.
	 * @param vehicleType: Enum VehicleType is used, CAR, TRUCK, or MOTORCYCLE. Rates/Early Bird can be pulled from Enum later.
	 * @param parkedBy: String provided should be attendant name.
	 * @param licensePlate: License plate of the vehicle. Currently doesn't differentiate on state.
	 * @author Thomason
	 */
	public Vehicle(VehicleType vehicleType, String parkedBy, String licensePlate) {
		this.vehicleType = vehicleType;
		this.parkedBy = parkedBy;
		setLicensePlate(licensePlate);
	}
	
	/**
	 * 
	 * @param vehicleType: Enum VehicleType is used, CAR, TRUCK, or MOTORCYCLE.
	 * @param attendant: Passes whole attendant object instead of just a string, forces full name usage.
	 * @param licensePlate: License plate of the vehicle. Currently doesn't differentiate on state.
	 */
	public Vehicle(VehicleType vehicleType, Attendant attendant, String licensePlate) {
		this.vehicleType = vehicleType;
		this.parkedBy = attendant.getFullName();
		setLicensePlate(licensePlate);
	}
	
	/**
	 * Sets current index position of Vehicle in the Garage spots list.
	 * Will usually be set via an Attendant
	 * @param parkingSpot - Returns pos, relevant to the List Spots in Garage class.
	 */
	public void setParkingSpot(int parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	/**
	 * Returns current index position of Vehicle in the Garage Spots List
	 * @return int Index Returns pos (Used for distance calculations later)
	 */
	public int getParkingSpot() {
		return parkingSpot;
	}
	/**
	 * Returns entire VehicleType enum, not just the String.
	 * @return Enum VehicleType  Returns entire enum, not just the string
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	/**
	 * Input requires enum declaration for input
	 * @param vehicle  The enum VehicleType
	 */
	public void setVehicleType(VehicleType vehicle) {
		this.vehicleType = vehicle;
	}
	/**
	 * Returns attendant's name
	 * @return name  Attendant's name
	 */
	public String getParkedBy() {
		return parkedBy;
	}
	/**
	 * Set attendant name via String
	 * @param parkedBy  The name of the attendant responsible for parking
	 */
	public void setParkedBy(String parkedBy) {
		this.parkedBy = parkedBy;
	}
	/**
	 * Get vehicle's license plate, currently doesn't differentiate by String
	 * @return licensePlate Returns licensePlate after conformity changes (Uppercase, no spaces)
	 */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
	 * Set License Plate, spaces removed and set to uppercase for data conformity.
	 * @param licensePlate  The license plate of the vehicle, currently doesn't differentiate by state
	 */
	public void setLicensePlate(String licensePlate) {
		licensePlate = licensePlate.replaceAll("\\s", "");
		licensePlate = licensePlate.toUpperCase();
		this.licensePlate = licensePlate;
	}
	
	@Override
	public String toString() {
		return ("Vehicle Type: " + vehicleType.toString() + "\t Parked By: " + parkedBy + "\t License Plate: " + licensePlate);
	}

	public String getMainTextFormat(){
	    return "License Plate: " + licensePlate;
    }

    public String getDetails(){
	    return "Vehicle Type: " + vehicleType.toString() + " \t Parked By: " + parkedBy + "\nDistance: " + getParkingSpot();
    }
	
}
