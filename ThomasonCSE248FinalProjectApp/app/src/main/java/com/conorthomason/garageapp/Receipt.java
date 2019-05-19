package com.conorthomason.garageapp;

import java.time.Duration;

/**
 * Receipt class extends ticket, meant to be used when taking into account costs and time
 *
 * @see <A href="../src/model/Receipt.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class Receipt extends Ticket {
	
	private double chargedAndPaid;
	private Garage garage = null;

    /**
     * Creates the receipt, taking into account all of the display and cost-affecting factors
     * @param vehicle
     * @param attendant
     * @param space
     * @param paymentScheme
     * @param earlyBird
     */
	public Receipt (Vehicle vehicle, String attendant, Space space,  PaymentScheme paymentScheme, boolean earlyBird) {
		super(vehicle, attendant, paymentScheme);
		setCalculatedRate(vehicle, space, earlyBird);
	}

    /**
     * Stores the calculated rate, found through getCalculatedRate
     * @param vehicle
     * @param space
     * @param earlyBird
     */
	public void setCalculatedRate(Vehicle vehicle, Space space, boolean earlyBird) {
		this.chargedAndPaid = getCalculatedRate(vehicle, space, earlyBird);
	}

    /**
     * Returns the calculated rate, taking into account the settings input by the garage manager
     * @param vehicle
     * @param space
     * @param earlyBird
     * @return double calculatedRate
     */
	public double getCalculatedRate(Vehicle vehicle, Space space, boolean earlyBird) {
		int parkingSpot = vehicle.getParkingSpot();
		long hours = Duration.between(space.getTimeDateParked(), TimeControl.getCurrentTime()).toHours() + 1;
		if (earlyBird && hours < 24){
		    return vehicle.getVehicleType().getEarlyBirdPrice();
        }
        else if (earlyBird && hours >= 24){
            return vehicle.getVehicleType().getEarlyBirdPrice() + (hours * vehicle.getVehicleType().getHourlyRate());
        }
		return hours * vehicle.getVehicleType().getHourlyRate();
	}

    /**
     * Returns the price paid
     * @return double chargedAndPaid
     */
	public double getChargedAndPaid() {
		return chargedAndPaid;
	}

    /**
     * Superclass handles most of the toString, only difference is the charge.
     * @return
     */
	public String toString() {
		return super.toString() + "\nCharged and Paid: $" + chargedAndPaid; 
	}

}