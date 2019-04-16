package model;

public class Attendant extends Employee {

	public Attendant(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}
	
	public boolean setParked(VehicleType vehicleType, String licensePlate) {
		Vehicle vehicle = new Vehicle(vehicleType, this.getFirstName(), licensePlate);
		if (Garage.hasVehicleSpace(vehicleType)) {
			return addVehicle(vehicle);
		}
		return false;
	}
	
	public boolean addVehicle(Vehicle vehicle) {
		
		return false;
	}
}
