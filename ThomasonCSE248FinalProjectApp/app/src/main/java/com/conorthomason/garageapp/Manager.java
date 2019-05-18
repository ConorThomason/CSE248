package com.conorthomason.garageapp;

import java.io.Serializable;

public class Manager extends Employee implements Serializable {
    private EmployeeManagement employees = null;
    public Manager(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }
}
