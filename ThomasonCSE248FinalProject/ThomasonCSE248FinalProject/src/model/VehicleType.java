package model;

public enum VehicleType {
	CAR (20.00, 2.50), 
	TRUCK (40.00, 5.00), 
	MOTORCYCLE (10.00, 1.00);
	
	private final double earlyBird;
	private final double hourlyRate;
	
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
}