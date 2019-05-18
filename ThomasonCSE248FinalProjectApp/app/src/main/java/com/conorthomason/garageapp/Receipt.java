package com.conorthomason.garageapp;

import java.time.Duration;

public class Receipt extends Ticket {
	
	private double chargedAndPaid;
	private Garage garage = null;
	
	public Receipt (Vehicle vehicle, String attendant, Space space,  PaymentScheme paymentScheme) {
		super(vehicle, attendant, paymentScheme);
		setCalculatedRate(vehicle, space);
	}
	public void setCalculatedRate(Vehicle vehicle, Space space) {
		this.chargedAndPaid = getCalculatedRate(vehicle, space);
		//TODO Add early bird pricing
	}
	public double getCalculatedRate(Vehicle vehicle, Space space) {
		int parkingSpot = vehicle.getParkingSpot();
		long hours = Duration.between(space.getTimeDateParked(), TimeControl.getCurrentTime()).toHours() + 1;
		return hours * vehicle.getVehicleType().getHourlyRate();
	}
	
	public double getChargedAndPaid() {
		return chargedAndPaid;
	}
	public String toString() {
		return super.toString() + "\nCharged and Paid: $" + chargedAndPaid; 
	}

}