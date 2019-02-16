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
	
	public NameWarehouse() {
		this.maleNames = new ArrayList<String>();
		this.femaleNames = new ArrayList<String>();
		this.lastNames = new ArrayList<String>();
		this.importMaleNames();
		this.importFemaleNames();
		this.importLastNames();
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
		importNames("boys_names.txt", 0);
	}
	private void importFemaleNames() {
		importNames("girls_names.txt", 1);
	}
	private void importLastNames() {
		importNames("Last Names.txt", 2);
	}
	private void importNames(String fileName, int type) {
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
	private void patternCheck(String next, int type) {
		//type == 0, male.
		//type == 1, female.
		//type == 2, last name.
		String pattern = "([A-Z])\\w+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(next);
		if (m.find()) {
			switch(type) {
			case 0: maleNames.add(m.group(0));
				break;
			case 1: femaleNames.add(m.group(0));
				break;
			case 2: lastNames.add(m.group(0));
				break;
			default:
				break;
			}
		}
	}
}
