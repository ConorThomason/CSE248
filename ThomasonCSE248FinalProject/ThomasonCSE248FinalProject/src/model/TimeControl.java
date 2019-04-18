package model;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TimeControl extends Thread {

	private static Thread timeThread;
	private static Instant whenStarted;
	private static final ZoneId defaultZone = ZoneId.systemDefault();

	private TimeControl() {}

	public static void createTimeThread(long scale) {
		final long timeScale = scale;
		whenStarted = Instant.now();
		timeThread = new Thread() {
			public void run() {
				long multiplier = timeScale;
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(1000);
						System.out.println(nextInstant(multiplier));
						
						nextInstant(multiplier);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		};
		timeThread.run();
	}
	
	public static Clock clockDefaultZone() {
		return Clock.fixed(whenStarted, defaultZone);
	}
	
	private static String nextInstant(long timeScale) {
		whenStarted = whenStarted.plusMillis(500 * timeScale);
		return whenStarted.toString();
	}
	
	public static void stopTime() {
		timeThread.interrupt();
	}
}
