package p3;

public interface Observable {
	void register(Observer subjectObserver);
	void unregister(Observer observer);
	void notifyObservers();
}