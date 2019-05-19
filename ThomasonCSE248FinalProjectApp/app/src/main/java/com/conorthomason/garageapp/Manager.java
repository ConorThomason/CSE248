package com.conorthomason.garageapp;

import java.io.Serializable;

/**
 * The Manager class is used largely as a way to differentiate employee instances for the controller
 *
 * @see <A href="../src/model/Vehicle.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class Manager extends Employee implements Serializable {
    private EmployeeManagement employees = null;
    public Manager(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }
}
