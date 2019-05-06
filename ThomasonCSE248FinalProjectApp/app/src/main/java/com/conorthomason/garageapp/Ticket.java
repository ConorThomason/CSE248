package com.conorthomason.garageapp;

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
	
	public Ticket (Vehicle vehicle, Attendant attendant, PaymentScheme paymentScheme) {
		setLicensePlate(vehicle.getLicensePlate());
		this.vehicleType = vehicle.getVehicleType();
		this.attendantName = attendant.getFullName();
		setDateAndTime();
		this.paymentScheme = paymentScheme;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		licensePlate = licensePlate.replaceAll("\\s", "");
		licensePlate = licensePlate.toUpperCase();
		this.licensePlate = licensePlate;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleCategory) {
		this.vehicleType = vehicleCategory;
	}

	public String getAttendantName() {
		return attendantName;
	}

	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime() {
		this.dateAndTime = Utilities.zoneStringFormat(TimeControl.getCurrentTime());
	}

	public PaymentScheme getPaymentScheme() {
		return paymentScheme;
	}

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
