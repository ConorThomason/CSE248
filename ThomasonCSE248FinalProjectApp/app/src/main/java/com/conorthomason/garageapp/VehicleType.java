package com.conorthomason.garageapp;

import java.io.Serializable;

public enum VehicleType implements Serializable {
	CAR (20.00, 2.50), 
	TRUCK (40.00, 5.00), 
	MOTORCYCLE (10.00, 1.00);
	
	private double earlyBird;
	private double hourlyRate;
	
	VehicleType (double earlyBird, double hourlyRate){
		this.earlyBird = earlyBird;
		this.hourlyRate = hourlyRate;
	}
	
	public double getHourlyRate() {
		return hourlyRate;
	}
	public double getEarlyBirdPrice() {
		return earlyBird;
	}
	public void setHourlyRate(double newRate) {
		this.hourlyRate = newRate;
	}
	
	public void setEarlyBird(double newRate) {
		this.earlyBird = newRate;
	}
}