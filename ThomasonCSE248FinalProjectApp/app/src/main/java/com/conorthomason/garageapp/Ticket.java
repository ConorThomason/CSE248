package com.conorthomason.garageapp;

/**
 * The Ticket class is used to construct a String output when a vehicle is entered
 *
 * @see <A href="../src/model/Vehicle.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class Ticket {
	String licensePlate;
	VehicleType vehicleType;
	String attendantName;
	String dateAndTime;
	PaymentScheme paymentScheme;


	
	//This constructor exists to force a date/time using Clock
	public Ticket(String licensePlate, VehicleType vehicleCategory, 
			String attendantName, PaymentScheme paymentScheme) {
		setLicensePlate(licensePlate);
		this.vehicleType = vehicleCategory;
		this.attendantName = attendantName;
		setDateAndTime();
		this.paymentScheme = paymentScheme;
		
	}

    /**
     * Ticket constructor, sets the date and time using the TimeControl static class.
     * @param vehicle
     * @param attendant
     * @param paymentScheme
     */
	public Ticket (Vehicle vehicle, String attendant, PaymentScheme paymentScheme) {
		setLicensePlate(vehicle.getLicensePlate());
		this.vehicleType = vehicle.getVehicleType();
		this.attendantName = attendant;
		setDateAndTime();
		this.paymentScheme = paymentScheme;
	}

    /**
     * Returns licensePlate string
     * @return String licensePlate
     */
	public String getLicensePlate() {
		return licensePlate;
	}

    /**
     * Using regex to replace all the spaces, and to convert all letters to uppercase,
     * this method provides uniformity in the usage of license plates. Helps with searching.
     * @param licensePlate
     */
	public void setLicensePlate(String licensePlate) {
		licensePlate = licensePlate.replaceAll("\\s", "");
		licensePlate = licensePlate.toUpperCase();
		this.licensePlate = licensePlate;
	}

    /**
     * Returns VehicleType enum
     * @return VehicleType type
     */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

    /**
     * Sets VehicleType for ticket (helps with rate calculation later)
     * @param vehicleCategory
     */
	public void setVehicleType(VehicleType vehicleCategory) {
		this.vehicleType = vehicleCategory;
	}

    /**
     * getter for attendantName
     * @return String attendantName
     */
	public String getAttendantName() {
		return attendantName;
	}

    /**
     * Sets the attendant name using String provided
     * @param attendantName
     */
	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}

    /**
     * Returns the simplified format of the Instant upon the ticket creation.
     * @return String dateAndTime
     */
	public String getDateAndTime() {
		return dateAndTime;
	}

    /**
     * Using the utilities class, dateAndTime are set using a simplified form of an Instant output
     */
	public void setDateAndTime() {
		this.dateAndTime = Utilities.zoneStringFormat(TimeControl.getCurrentTime());
	}

    /**
     * Returns the paymentscheme established
     * @return PaymentScheme paymentScheme
     */
	public PaymentScheme getPaymentScheme() {
		return paymentScheme;
	}

    /**
     * Set the payment scheme established by the user upon ticket creation.
     * @param paymentScheme
     */
	public void setPaymentScheme(PaymentScheme paymentScheme) {
		this.paymentScheme = paymentScheme;
	}
	@Override
	public String toString() {
		return "License Plate: " + licensePlate + "\nVehicle Type: " + vehicleType.toString() + 
				"\nAttendant Name: " + attendantName + "\nDate/Time: " + dateAndTime + "\n"
						+ "Payment Scheme: " + paymentScheme;
	}
	
}
