package p2;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Thread thread2 = new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("Hello ");
					}
				});
				thread2.run();
				System.out.println("Goodbye ");
			}
		});
		for (int i = 0; i < 10; i++) {
			thread1.run();
		}
		
		

	}

}
