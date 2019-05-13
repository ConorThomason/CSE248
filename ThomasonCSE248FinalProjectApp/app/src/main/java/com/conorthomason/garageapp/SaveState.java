package com.conorthomason.garageapp;

import android.app.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class SaveState extends Activity implements Serializable {

    //EmployeeManagement data
    private TreeMap<String, Employee> tree;

    //Garage Data
    private HashMap<String, Vehicle> vehicles;
    private ArrayList<Space> spaces;
    private int carSpaces;
    private int currentCars = 0;
    private int truckSpaces;
    private int currentTrucks = 0;
    private int motorcycleSpaces;
    private int currentMotorcycles = 0;

    public TreeMap<String, Employee> getTree() {
        return tree;
    }

    public void setTree(TreeMap<String, Employee> tree) {
        this.tree = tree;
    }

    public HashMap<String, Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(HashMap<String, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    public int getCarSpaces() {
        return carSpaces;
    }

    public void setCarSpaces(int carSpaces) {
        this.carSpaces = carSpaces;
    }

    public int getCurrentCars() {
        return currentCars;
    }

    public void setCurrentCars(int currentCars) {
        this.currentCars = currentCars;
    }

    public int getTruckSpaces() {
        return truckSpaces;
    }

    public void setTruckSpaces(int truckSpaces) {
        this.truckSpaces = truckSpaces;
    }

    public int getCurrentTrucks() {
        return currentTrucks;
    }

    public void setCurrentTrucks(int currentTrucks) {
        this.currentTrucks = currentTrucks;
    }

    public int getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public void setMotorcycleSpaces(int motorcycleSpaces) {
        this.motorcycleSpaces = motorcycleSpaces;
    }

    public int getCurrentMotorcycles() {
        return currentMotorcycles;
    }

    public void setCurrentMotorcycles(int currentMotorcycles) {
        this.currentMotorcycles = currentMotorcycles;
    }

    public SaveState(TreeMap<String, Employee> tree, HashMap<String, Vehicle> vehicles, ArrayList<Space> spaces, int carSpaces,
                     int currentCars, int truckSpaces, int currentTrucks, int motorcycleSpaces, int currentMotorcycles){
        this.tree = tree;
        this.vehicles = vehicles;
        this.spaces = spaces;
        this.carSpaces = carSpaces;
        this.currentCars = currentCars;
        this.truckSpaces = truckSpaces;
        this.currentTrucks = currentTrucks;
        this.motorcycleSpaces = motorcycleSpaces;
        this.currentMotorcycles = currentMotorcycles;
    }

    public SaveState(){
        //nop
    }

    public void extractManagementData(){
        this.tree = EmployeeManagement.getTree();
    }

    public void extractGarageData(){
        this.vehicles = Garage.getVehicles();
        this.spaces = Garage.getSpaces();
        this.carSpaces = Garage.getCarSpaces();
        this.currentCars = Garage.getCurrentCars();
        this.truckSpaces = Garage.getTruckSpaces();
        this.currentTrucks = Garage.getCurrentTrucks();
        this.motorcycleSpaces = Garage.getMotorcycleSpaces();
        this.currentMotorcycles = Garage.getCurrentMotorcycles();
    }
    public void inputManagementData(){
        EmployeeManagement.setTree(this.getTree());
    }

    public void inputGarageData(){
        Garage.setVehicles(this.getVehicles());
        Garage.setSpaces(this.getSpaces());
        Garage.setCarSpaces(this.getCarSpaces());
        Garage.setCurrentCars(this.getCurrentCars());
        Garage.setTruckSpaces(this.getTruckSpaces());
        Garage.setCurrentTrucks(this.getCurrentTrucks());
        Garage.setMotorcycleSpaces(this.getMotorcycleSpaces());
        Garage.setCurrentMotorcycles(this.getCurrentMotorcycles());
    }
    public boolean loadData(File internalStorageDir){
        try{
            File users = new File (internalStorageDir, "users.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);

            try {
                SaveState state = (SaveState)objectIn.readObject();
                this.vehicles = state.getVehicles();
                this.spaces = state.getSpaces();
                this.carSpaces = state.getCarSpaces();
                this.currentCars = state.getCurrentCars();
                this.truckSpaces = state.getTruckSpaces();
                this.currentTrucks = state.getCurrentTrucks();
                this.motorcycleSpaces = state.getMotorcycleSpaces();
                this.currentMotorcycles = state.getCurrentMotorcycles();
                this.inputManagementData();
                this.inputGarageData();
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }
            return true;
        } catch (IOException e){
            return false;
        }

    }
    public boolean saveData(){
        try {
            File internalStorageDir = getFilesDir();
            File users = new File(internalStorageDir, "users.bin");
            FileOutputStream fos = new FileOutputStream(users);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(this);
            objectOut.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
