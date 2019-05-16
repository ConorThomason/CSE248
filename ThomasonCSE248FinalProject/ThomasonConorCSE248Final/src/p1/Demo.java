package p1;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Thread thread2 = new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("Hello ");
					}
				});
				thread2.run();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Goodbye ");
			}
		});
		for (int i = 0; i < 10; i++) {
			thread1.run();
		}
		
		

	}

}
