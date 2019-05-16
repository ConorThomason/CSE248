package p3;

public class SubjectObserver implements Observer {
	private int value;
	private int observerId;
	
	@SuppressWarnings("unused")
	private Observable subject;
	
	private static int id = 0;
	
	public SubjectObserver(Observable subject) {
		this.subject = subject;
		this.value = ((Subject)subject).getValue();
		observerId = ++id;
		subject.register(this);
	}
	
	public void update(int value) {
		this.value = value;
	}
	
	public void print() {
		System.out.println(observerId + ": " + value);
	}

}
