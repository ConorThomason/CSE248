package model;

import java.time.Duration;

public class Receipt extends Ticket {
	
	private double chargedAndPaid;
	
	public Receipt (Vehicle vehicle, Attendant attendant, PaymentScheme paymentScheme) {
		super(vehicle, attendant, paymentScheme);
		setCalculatedRate(vehicle);
	}
	public void setCalculatedRate(Vehicle vehicle) {
		this.chargedAndPaid = getCalculatedRate(vehicle);
		//TODO Add early bird pricing
	}
	public double getCalculatedRate(Vehicle vehicle) {
		int parkingSpot = Garage.getVehicle(vehicle.getLicensePlate()).getParkingSpot();
		Space vehicleSpace = Garage.getSpace(parkingSpot);
		long hours = Duration.between(vehicleSpace.getTimeDateParked(), TimeControl.getCurrentTime()).toHours();
		return hours * vehicle.getVehicleType().getHourlyRate();
	}
	
	public double getChargedAndPaid() {
		return chargedAndPaid;
	}
	public String toString() {
		return super.toString() + "\nCharged and Paid: $" + chargedAndPaid; 
	}

}