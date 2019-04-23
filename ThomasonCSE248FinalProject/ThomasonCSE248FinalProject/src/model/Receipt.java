package model;

import java.time.Duration;

public class Receipt extends Ticket {
	
	private double chargedAndPaid;
	
	public Receipt (Vehicle vehicle, Attendant attendant, PaymentScheme paymentScheme) {
		super(vehicle, attendant, paymentScheme);
		setCalculatedRate(vehicle);
	}
	
	public void setCalculatedRate(Vehicle vehicle) {
		int parkingSpot = Garage.getVehicle(vehicle.getLicensePlate()).getParkingSpot();
		Space vehicleSpace = Garage.getSpace(parkingSpot);
		long hours = Duration.between(vehicleSpace.getTimeDateParked(), TimeControl.getCurrentTime()).toHours();
		this.chargedAndPaid = hours * vehicle.getVehicleType().getHourlyRate();
		//TODO Add early bird pricing
	}
	public String toString() {
		return super.toString() + ", Charged and Paid: $" + chargedAndPaid; 
	}

}