package com.conorthomason.garageapp;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Space class is used to help in the creation of a List in the Garage class.
 * Allows for the Array-based implementation as a distance calculation.
 *
 * @see <A href="../src/model/Vehicle.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class Space implements Serializable {
	private String vehicleLicense;
	private VehicleType vehicleType;
	private Instant timeDateParked;
	private ArrayList<PaymentScheme> acceptedPaymentSchemes;


	public Space(VehicleType vehicleType, ArrayList<PaymentScheme> acceptedPaymentSchemes) {
		this.vehicleType = vehicleType;
		this.timeDateParked = TimeControl.getCurrentTime();
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}

    /**
     * Return the vehicleLicense String
     * @return String vehicleLicense
     */
	public String getVehicleLicense() {
		return vehicleLicense;
		
	}

    /**
     * Set the vehicleLicense
     * @param vehicleRegistration
     */
	public void setVehicleLicense(String vehicleRegistration) {
		this.vehicleLicense = vehicleRegistration;
	}

    /**
     * Get the vehicleType assigned to the space (Usually preformed at Garage creation)
     * @return VehicleType vehicleType
     */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

    /**
     * Set the vehicleType assigned to the space (Usually performed in a loop at Garage creation)
     * @param vehicleType
     */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

    /**
     * Return the instant the vehicle assigned to this space.
     * @return Instant timeDateParked
     */
	public Instant getTimeDateParked() {
		return timeDateParked;
	}

    /**
     * Set the Instant a vehicle was assigned to this space
     * @param timeDateParked
     */
	public void setTimeDateParked(Instant timeDateParked) {
		this.timeDateParked = timeDateParked;
	}

    /**
     * Return the acceptable paymentScheme for this space
     * @return ArrayList acceptedPaymentSchemes
     */
	public ArrayList<PaymentScheme> getAcceptedPaymentSchemes() {
		return acceptedPaymentSchemes;
	}

    /**
     * Set the arraylist of accepted payment schemes
     * @param acceptedPaymentSchemes
     */
	public void setAcceptedPaymentSchemes(ArrayList<PaymentScheme> acceptedPaymentSchemes) {
		this.acceptedPaymentSchemes = acceptedPaymentSchemes;
	}
	
	
	
}
