package com.conorthomason.garageapp;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GarageService extends Application implements Serializable {
    private static GarageService application;
    private Garage singleton;

    public GarageService getInstance(){
        return application;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
    }

    public Garage getSingleton(){
        return singleton;
    }

    public boolean saveData(){
        try {
            File users = new File(getFilesDir(), "garage.bin");
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
            File users = new File (getFilesDir(), "garage.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);

            try {
                Garage garage = (Garage) objectIn.readObject();
                this.singleton = garage;
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }
            System.out.println("Loaded data");
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}
