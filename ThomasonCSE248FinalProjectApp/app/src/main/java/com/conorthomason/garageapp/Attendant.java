package com.conorthomason.garageapp;

import java.io.Serializable;

public class Attendant extends Employee implements Serializable {

    private Garage garage = null;
    private EmployeeManagement employees = null;
	public Attendant(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}
}
