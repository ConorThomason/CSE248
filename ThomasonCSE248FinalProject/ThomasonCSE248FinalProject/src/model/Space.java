package model;

import java.util.Date;
import java.util.List;

public class Space {
	private String vehicleRegistration;
	private VehicleType vehicleType;
	private Date timeDateParked;
	private List<PaymentScheme> acceptedPaymentSchemes;
	
	public Space(VehicleType vehicleType, List<PaymentScheme> acceptedPaymentSchemes) {
		this.vehicleType = vehicleType;
		this.timeDateParked = new Date();
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	//This class exists for the purpose of testing only. Would never be used live.
	public Space(VehicleType vehicleType, List<PaymentScheme> acceptedPaymentSchemes, Date forcedDate) {
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

	public List<PaymentScheme> getAcceptedPaymentSchemes() {
		return acceptedPaymentSchemes;
	}

	public void setAcceptedPaymentSchemes(List<PaymentScheme> acceptedPaymentSchemes) {
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	
	
}
