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
		Time scaledTime = new Time(scale);
		timeThread = new Thread(scaledTime, "time");
		timeThread.start();

	}
	
	public static void createSpecificTimeThread(long scale, Instant instant) {
		whenStarted = instant;
		Time scaledTime = new Time(scale);
		timeThread = new Thread(scaledTime, "time");
		timeThread.start();
	}
	
	public static void changeMultiplier(long multiplier) {
		Time.stop();
		createSpecificTimeThread(multiplier, whenStarted);
		
	}
	public static Instant getCurrentTime() {
		return whenStarted;
	}
	
	public static ZoneId getDefaultZone() {
		return defaultZone;
	}
static class Time implements Runnable{
	
	private static volatile boolean exit = false;
	private long multiplier;
	public Time(long multiplier) {
		this.multiplier = multiplier;
	}
	
	@Override
	public void run() {
			try {
				while(!exit) {
					Thread.sleep(1000);
					nextInstant(multiplier);
				}
			} catch (InterruptedException e) {
				//nop
			}
			nextInstant(multiplier);
		
	}
	
	public static void stop() {
		exit = true;
	}
	
}

	public static Clock clockDefaultZone() {
		return Clock.fixed(whenStarted, defaultZone);
	}

	private static String nextInstant(long timeScale) {
		whenStarted = whenStarted.plusSeconds(1 * timeScale);
		return whenStarted.toString();
	}

	public static void stopTime() {
		Time.stop();
	}
	
	public static void startTime(long multiplier) {
		if (multiplier != 1) {
			TimeControl.createTimeThread(multiplier);
		}
		else {
			TimeControl.createTimeThread(1);
		}
	}
}
