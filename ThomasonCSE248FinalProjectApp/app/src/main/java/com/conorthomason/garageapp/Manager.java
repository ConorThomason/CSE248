package com.conorthomason.garageapp;

public class Manager extends Employee {
    public Manager(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
        EmployeeManagement.addEmployee(this);
    }

    public boolean setParked(VehicleType vehicleType, String licensePlate) {
        Vehicle vehicle = new Vehicle(vehicleType, this.getFullName(), licensePlate);
        Garage.parkVehicle(vehicle);
        return false;
    }

    public static boolean addAttendant(Attendant attendant){
        boolean success = EmployeeManagement.addEmployee(attendant);
        if (success){
            return true;
        }
        return false;
    }

    public static boolean removeAttendant(Attendant attendant){
        return EmployeeManagement.removeEmployee(attendant.getUsername());
    }
}
