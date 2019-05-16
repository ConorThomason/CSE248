package p3;

import java.util.ArrayList;

public class Subject implements Observable {
	private ArrayList<Observer> observerList;
	private int value;
	
	public Subject() {
		observerList = new ArrayList<>();
	}
	
	@Override
	public void register(Observer observer) {
		observerList.add(observer);
	}

	@Override
	public void unregister(Observer observer) {
		int index = observerList.indexOf(observer);
		observerList.remove(index);
		
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observerList) {
			observer.update(value);
		}
		
	}
	
	public void setValue(int value) {
		this.value = value;
		notifyObservers();
	}
	
	public int getValue() {
		return value;
	}
	
}
