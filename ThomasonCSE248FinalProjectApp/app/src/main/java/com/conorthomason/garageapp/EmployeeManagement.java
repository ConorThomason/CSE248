package com.conorthomason.garageapp;

import android.app.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * The EmployeeManagement class is the main data structure for storing the information of employees.
 * This will be instantiated in the Garage class.
 * 
 * @see <A href="../src/model/EmployeeManagement.java">Java source code</A>
 * 
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 * 
 * @version V1.0, 4/8/2019
 *
 */

public class EmployeeManagement extends Activity implements Serializable {
	
	private static TreeMap<String, Employee> tree;
	private static boolean exists = false;
	private static EmployeeManagement _employees = new EmployeeManagement();
	private static Employee activeUser = null;
	
	public EmployeeManagement() {}
	
	public static EmployeeManagement createEmployeeManagement() {
		tree = new TreeMap<String, Employee>();
		exists = true;
		return _employees;
	}
	/**
	 * 
	 * @param Employee - Employee object passed
	 * @return boolean, if true added successfully. If false added unsuccessfully.
	 */

	public static boolean exists(){
	    return exists;
    }
    private void loadEmployees(){
	    try{
	        File internalStorageDir = getFilesDir();
	        File users = new File (internalStorageDir, "users.bin");
            FileInputStream fis = new FileInputStream(users);
            ObjectInputStream objectIn = new ObjectInputStream(fis);
            try {
                EmployeeManagement.tree = (TreeMap<String, Employee>)objectIn.readObject();
            } catch (ClassNotFoundException f){
                f.printStackTrace();
            }

        } catch (IOException e){
            //nop

        }
    }
    private void saveEmployees(){
        try {
            File internalStorageDir = getFilesDir();
            File users = new File(internalStorageDir, "users.bin");
            FileOutputStream fos = new FileOutputStream(users);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(EmployeeManagement.tree);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static boolean addEmployee(Employee employee) {
		if (EmployeeManagement.findEmployee(employee.getUsername()))
			return false;
		tree.put(employee.getUsername(), employee);
		return true;
	}

	public static void setActiveEmployee(Employee employee){
	    activeUser = employee;
    }

    public static Employee getActiveEmployee(){
	    return activeUser;
    }

	public static Employee getEmployee(Employee employee){
	    return getEmployee(employee.getUsername());
    }

    public static Employee getEmployee(String username){
	    return tree.get(username);
    }
	/**
	 * 
	 * @param username - key value to search for and remove
	 * @return If removed successfully, return true. If false, removed unsuccessfully.
	 */
	public static boolean removeEmployee(String username) {
		Employee removedEmployee = tree.remove(username);
		if (removedEmployee == null) {
			return false;
		}
		return true;
	}
	public static boolean removeEmployee(Attendant attendant){
		return removeEmployee(attendant.getUsername());
	}
	/**
	 * 
	 * @param username - key value to search for
	 * @return If the employee is found, returns true. If false, not found in the TreeMap.
	 */
	public static boolean findEmployee(String username) {
		if (tree.containsKey(username))
			return true;
		return false;
	}
	
	/**
	 * Prints the employees by iterating through the entrySet of the tree.
	 */
	public static void printEmployees() {
		for(Map.Entry<String, Employee> entry: tree.entrySet()) {
			System.out.println(tree.get(entry.getKey()));
		}
		System.out.println("\n");
	}
}
