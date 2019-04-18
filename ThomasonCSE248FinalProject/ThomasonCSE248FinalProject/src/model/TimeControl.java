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
		whenStarted = Instant.now();
		timeThread = new Thread(incrementTime(scale));
		timeThread.run();

	}

	public static Runnable incrementTime(long multiplier) {
		return () -> {
			try {
				while(!Thread.currentThread().isInterrupted()) {
					System.out.println(clockDefaultZone());
					Thread.sleep(1000);
					nextInstant(multiplier);
				}
			} catch (InterruptedException e) {
				//nop
			}
			nextInstant(multiplier);
		};
	}

	public static Clock clockDefaultZone() {
		return Clock.fixed(whenStarted, defaultZone);
	}

	private static String nextInstant(long timeScale) {
		whenStarted = whenStarted.plusSeconds(1 * timeScale);
		return whenStarted.toString();
	}

	public static void stopTime() {
		timeThread.interrupt();
	}
}
