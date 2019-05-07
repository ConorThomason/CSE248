package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class ManagerTests {

    EmployeeManagement employees = new EmployeeManagement();
    @Test
    void instantiateTest() {
        employees.createEmployeeManagement();
        Manager test = new Manager("Test", "test", "TestFirst", "TestLast");
        assertTrue(EmployeeManagement.findEmployee("Test"));
    }

    @Test
    void addAttendantTest(){
        Manager test = new Manager("Test", "test", "TestFirst", "TestLast");
        Attendant attendant = new Attendant("TestAttendant", "123", "John", "Doe");
        test.addAttendant(attendant);
        assertTrue(EmployeeManagement.findEmployee("TestAttendant"));
    }

    @Test
    void removeAttendantTest(){
        Manager test = new Manager("Test", "test", "TestFirst", "TestLast");
        Attendant attendant = new Attendant("TestAttendant", "123", "John", "Doe");
        test.addAttendant(attendant);
        test.removeAttendant(attendant);
        assertFalse(EmployeeManagement.findEmployee("TestAttendant"));
    }
}
