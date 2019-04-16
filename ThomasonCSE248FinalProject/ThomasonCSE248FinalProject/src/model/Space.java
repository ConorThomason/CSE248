package model;

import java.util.ArrayList;
import java.util.Date;

public class Space {
	private String vehicleRegistration;
	private VehicleType vehicleType;
	private Date timeDateParked;
	private ArrayList<PaymentScheme> acceptedPaymentSchemes;
	
	public Space(VehicleType vehicleType, ArrayList<PaymentScheme> acceptedPaymentSchemes) {
		this.vehicleType = vehicleType;
		this.timeDateParked = new Date();
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	//This class exists for the purpose of testing only. Would never be used live.
	public Space(VehicleType vehicleType, ArrayList<PaymentScheme> acceptedPaymentSchemes, Date forcedDate) {
		this.vehicleType = vehicleType;
		this.timeDateParked = forcedDate;
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	public String getVehicleRegistration() {
		return vehicleRegistration;
		
	}
	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Date getTimeDateParked() {
		return timeDateParked;
	}

	public void setTimeDateParked(Date timeDateParked) {
		this.timeDateParked = timeDateParked;
	}

	public ArrayList<PaymentScheme> getAcceptedPaymentSchemes() {
		return acceptedPaymentSchemes;
	}

	public void setAcceptedPaymentSchemes(ArrayList<PaymentScheme> acceptedPaymentSchemes) {
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	
	
}
