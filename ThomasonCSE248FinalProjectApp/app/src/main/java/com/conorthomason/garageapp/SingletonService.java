package com.conorthomason.garageapp;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SingletonService extends Application implements Serializable {
    private static SingletonService application;
    private Garage garageSingleton;
    private EmployeeManagement employeeManagementSingleton;

    public SingletonService getInstance(){
        return application;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
    }

    private void initializeEmployees(){
        employeeManagementSingleton = new EmployeeManagement();
        employeeManagementSingleton.createEmployeeManagement();
    }

    private void initializeGarage(){
        garageSingleton = new Garage();
    }

    public Garage getGarageSingleton(){
        return garageSingleton;
    }

    public EmployeeManagement getEmployeeManagementSingleton(){
        return employeeManagementSingleton;
    }

    public void saveData(){
        saveGarage();
        saveEmployees();
    }

    public void loadData(){
        loadGarage();
        loadEmployees();
    }
    public boolean saveGarage(){
        try {
            File users = new File(getFilesDir(), "garage.bin");
            FileOutputStream fos = new FileOutputStream(users);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(garageSingleton);
            objectOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean loadGarage(){
        try{
            File users = new File (getFilesDir(), "garage.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);

            try {
                Garage garage = (Garage) objectIn.readObject();
                this.garageSingleton = garage;
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }
            System.out.println("Loaded data");
            return true;
        } catch (IOException e){
            initializeGarage();
            e.printStackTrace();
            return false;
        }
    }
    public boolean loadEmployees(){
        try{
            File users = new File (getFilesDir(), "users.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);

            try {
                EmployeeManagement employees = (EmployeeManagement) objectIn.readObject();
                this.employeeManagementSingleton = employees;
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }
            System.out.println("Loaded data");
            return true;
        } catch (IOException e){
            e.printStackTrace();
            initializeEmployees();
            return false;
        }

    }
    public boolean saveEmployees(){
        try {
            File users = new File(getFilesDir(), "users.bin");
            FileOutputStream fos = new FileOutputStream(users);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(employeeManagementSingleton);
            objectOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
