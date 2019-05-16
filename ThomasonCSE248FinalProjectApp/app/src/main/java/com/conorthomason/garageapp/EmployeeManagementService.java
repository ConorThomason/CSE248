package com.conorthomason.garageapp;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EmployeeManagementService extends Application implements Serializable {
    private static EmployeeManagementService application;
    private EmployeeManagement singleton;

    public EmployeeManagementService getInstance(){
        return application;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
        application.initialize();
    }
    private void initialize(){
        singleton = new EmployeeManagement();
    }

    public EmployeeManagement getSingleton(){
        return singleton;
    }

    public boolean saveData(){
        try {
            File users = new File(getFilesDir(), "users.bin");
            FileOutputStream fos = new FileOutputStream(users);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(singleton);
            objectOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean loadData(){
        try{
            File users = new File (getFilesDir(), "users.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);

            try {
                EmployeeManagement employees = (EmployeeManagement) objectIn.readObject();
                this.singleton = employees;
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }
            System.out.println("Loaded data");
            return true;
        } catch (IOException e){
            e.printStackTrace();
            initialize();
            singleton.createEmployeeManagement();
            return false;
        }

    }
}
