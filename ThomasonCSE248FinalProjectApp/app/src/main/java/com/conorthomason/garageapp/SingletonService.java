package com.conorthomason.garageapp;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * SingletonService is used to access both the Garage and EmployeeManagement singletons throughout Android.
 * Handles the saving/loading of these data structures also.
 *
 * @see <A href="../src/model/SignletonService.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class SingletonService extends Application implements Serializable {
    private static SingletonService application;
    private Garage garageSingleton;
    private EmployeeManagement employeeManagementSingleton;

    public SingletonService getInstance(){
        return application;
    }

    /**
     * Instantiates the Service when calling the application.
     */
    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
    }

    /**
     * Initialize the tree in EmployeeManagement.
     */
    private void initializeEmployees(){
        employeeManagementSingleton = new EmployeeManagement();
        employeeManagementSingleton.createEmployeeManagement();
    }

    /**
     * Initialize the Garage - nothing is actually instantiated in the Garage class,
     * but it allows the object to be formed.
     */
    private void initializeGarage(){
        garageSingleton = new Garage();
    }

    /**
     * Return the garageSingleton stored, allows calling of Garage locally in each activity.
     * @return Garage garageSingleton
     */
    public Garage getGarageSingleton(){
        return garageSingleton;
    }

    /**
     * Return the EmployeeManagement stored, allows calling of EmployeeManagement locally in each activity.
     * @return EmployeeManagement employeeManagementSingleton
     */
    public EmployeeManagement getEmployeeManagementSingleton(){
        return employeeManagementSingleton;
    }

    /**
     * Set the garageSingleton in case any changes are only made locally.
     * @param garage
     */
    public void setGarageSingleton(Garage garage){
        this.garageSingleton = garage;
    }

    /**
     * Runs both save methods for each data structure - issues were encountered, so this is rarely used.
     */
    public void saveData(){
        saveGarage();
        saveEmployees();
    }

    /**
     * Print the garage HashMap - used largely for testing.
     * @param mp
     */
    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

    /**
     * Runs btoh load methods for each data structure - issues were encountered, so this is rarely used.
     */
    public void loadData(){
        loadGarage();
        loadEmployees();
    }

    /**
     * Save the garage data structure to the local file directory.
     * @return boolean success
     */
    public boolean saveGarage(){
        try {
            File users = new File(getFilesDir(), "garage.bin");
            FileOutputStream fos = new FileOutputStream(users);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(garageSingleton);
            objectOut.close();
            System.out.println("Saving garage...");
            printMap(garageSingleton.getVehicles());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Loads the garage from the local file directory.
     * @return boolean success
     */
    public boolean loadGarage(){
        try{
            File users = new File (getFilesDir(), "garage.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);

            try {
                Garage garage = (Garage)objectIn.readObject();
                this.garageSingleton = garage;
                System.out.println("Loaded garage");
                printMap(garageSingleton.getVehicles());
                return true;
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }
        } catch (IOException e){
            initializeGarage();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Loads the EmployeeManagement from the local file directory.
     * @return boolean success
     */
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
            System.out.println("Loaded garage");
            System.out.println(garageSingleton.garageDetails());
            return true;
        } catch (IOException e){
            e.printStackTrace();
            initializeEmployees();
            return false;
        }

    }

    /**
     * Saves the EmployeeManagement to the local file directory.
     * @return boolean success
     */
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
