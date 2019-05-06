package com.conorthomason.garageapp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Space {
	private String vehicleLicense;
	private VehicleType vehicleType;
	private Instant timeDateParked;
	private ArrayList<PaymentScheme> acceptedPaymentSchemes;
	
	public Space(VehicleType vehicleType, ArrayList<PaymentScheme> acceptedPaymentSchemes) {
		this.vehicleType = vehicleType;
		this.timeDateParked = TimeControl.getCurrentTime();
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	public String getVehicleLicense() {
		return vehicleLicense;
		
	}
	public void setVehicleLicense(String vehicleRegistration) {
		this.vehicleLicense = vehicleRegistration;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Instant getTimeDateParked() {
		return timeDateParked;
	}

	public void setTimeDateParked(Instant timeDateParked) {
		this.timeDateParked = timeDateParked;
	}

	public ArrayList<PaymentScheme> getAcceptedPaymentSchemes() {
		return acceptedPaymentSchemes;
	}

	public void setAcceptedPaymentSchemes(ArrayList<PaymentScheme> acceptedPaymentSchemes) {
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	
	
}
