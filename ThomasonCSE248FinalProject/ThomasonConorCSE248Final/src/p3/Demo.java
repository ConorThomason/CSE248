package p3;

public class Demo {

	public static void main(String[] args) {
		Subject observed = new Subject();
		observed.setValue(5);
		SubjectObserver observer1 = new SubjectObserver(observed);
		SubjectObserver observer2 = new SubjectObserver(observed);
		System.out.println("First Run, value = 5");
		observer1.print();
		observed.setValue(observed.getValue() * 2);
		observer2.print();
		
		
		System.out.println("\nSecond Run, value = 10");
		observed.setValue(10);
		observer1.print();
		observed.setValue(10*2);
		observer2.print();
	}

}
