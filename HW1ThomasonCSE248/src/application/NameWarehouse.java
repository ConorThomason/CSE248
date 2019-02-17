package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameWarehouse {
	
	private ArrayList<String> maleNames;
	private ArrayList<String> femaleNames;
	private ArrayList<String> lastNames;
	//ArrayList is used due to unknown size until import is complete, yet utilizes an
	//index system that can be used in conjunction with bounded random numbers.
	
	public NameWarehouse() {
		this.maleNames = new ArrayList<String>();
		this.femaleNames = new ArrayList<String>();
		this.lastNames = new ArrayList<String>();
		this.importMaleNames();
		this.importFemaleNames();
		this.importLastNames();
	}
	public int getWarehouseSize(String type) {
		switch(type) {
		case "Male": return this.maleNames.size();
		case "Female": return this.femaleNames.size();
		case "Last Name": return this.lastNames.size();
		default:
			return -1;
		}
	}
	public String getFirstName(int name, boolean female) {
		if (female)
			return getFemaleName(name);
		else
			return getMaleName(name);
	}
	public String getMaleName(int name) {
		return maleNames.get(name);
	}
	public String getFemaleName(int name) {
		return femaleNames.get(name);
	}
	public String getLastName(int name) {
		return lastNames.get(name);
	}
	private void importMaleNames() {
		importNames("boys_names.txt", "Male");
	}
	private void importFemaleNames() {
		importNames("girls_names.txt", "Female");
	}
	private void importLastNames() {
		importNames("Last Names.txt", "Last Name");
	}
	private void importNames(String fileName, String type) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				patternCheck(scanner.next(), type);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void patternCheck(String next, String type) {
		String pattern = "([A-Z])\\w+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(next);
		if (m.find()) {
			switch(type) {
			case "Male": maleNames.add(m.group(0));
				break;
			case "Female": femaleNames.add(m.group(0));
				break;
			case "Last Name": lastNames.add(m.group(0));
				break;
			default:
				break;
			}
		}
	}
}
