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
        employees.addEmployee(test);
        assertTrue(employees.findEmployee("Test"));
    }

    //Deprecated
//    @Test
//    void addAttendantTest(){
//        Manager test = new Manager("Test", "test", "TestFirst", "TestLast");
//        Attendant attendant = new Attendant("TestAttendant", "123", "John", "Doe");
//        test.addAttendant(attendant);
//        assertTrue(employees.findEmployee("TestAttendant"));
//    }

    //Deprecated

//    @Test
//    void removeAttendantTest(){
//        Manager test = new Manager("Test", "test", "TestFirst", "TestLast");
//        Attendant attendant = new Attendant("TestAttendant", "123", "John", "Doe");
//        test.addAttendant(attendant);
//        test.removeAttendant(attendant);
//        assertFalse(EmployeeManagement.findEmployee("TestAttendant"));
//    }
}
