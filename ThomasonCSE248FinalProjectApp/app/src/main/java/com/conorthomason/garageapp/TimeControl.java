package com.conorthomason.garageapp;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * TimeControl is intended to be used primarily for the receipt/ticket classes. By using multithreading,
 * a multiplier can be enforced, creating significantly larger time increments. These are then used
 * for cost calculations.
 *
 * @see <A href="../src/model/Vehicle.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public class TimeControl extends Thread {


	private static Thread timeThread;
	private static Instant whenStarted;
	private static final ZoneId defaultZone = ZoneId.systemDefault();

	private TimeControl() {}

    /**
     * Creates a time thread, applying the time scale multiplier
     * @param scale
     */
	public static void createTimeThread(long scale) {
		whenStarted = Instant.now();
		Time scaledTime = new Time(scale);
		timeThread = new Thread(scaledTime, "time");
		timeThread.start();

	}

    /**
     * Create a time thread using a certain point in time. Primarily used for testing.
     * Can also be used for changing the time multiplier.
     * @param scale
     * @param instant
     */
	public static void createSpecificTimeThread(long scale, Instant instant) {
		whenStarted = instant;
		Time scaledTime = new Time(scale);
		timeThread = new Thread(scaledTime, "time");
		timeThread.start();
	}

    /**
     * Allows the time multiplier to be changed, by stopping the thread, changing the multiplier,
     * and restarting the thread.
     * @param multiplier
     */
	public static void changeMultiplier(long multiplier) {
		Time.stop();
		createSpecificTimeThread(multiplier, whenStarted);
		
	}

    /**
     *
     * @return Instant instant - gets the current time stored in the Thread
     */
	public static Instant getCurrentTime() {
		return whenStarted;
	}

    /**
     *
     * @return ZoneId zone - gets the default time zone being used
     */
	public static ZoneId getDefaultZone() {
		return defaultZone;
	}

    /**
     * Time is essentially the backend for TimeControl - it handles the thread operations.
     */
    static class Time implements Runnable{
	
	private static volatile boolean exit = false;
	private long multiplier;


	public Time(long multiplier) {
		this.multiplier = multiplier;
	}

        /**
         * Runnable for TimeControl. Time is incremented in seconds, seconds are multiplied by the
         * provided scale.
         */
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

        /**
         * Stop time, when exit is true, the thread catches the change and cancels.
         */
	public static void stop() {
		exit = true;
	}
	
}

    /**
     * Get the default zone clock based off when the time started. Good for time differences.
     * @return Clock
     */
	public static Clock clockDefaultZone() {
		return Clock.fixed(whenStarted, defaultZone);
	}

    /**
     * Increment the instance by 1 second multiplied by the requested scale.
     * @param timeScale
     * @return String
     */
	private static String nextInstant(long timeScale) {
		whenStarted = whenStarted.plusSeconds(1 * timeScale);
		return whenStarted.toString();
	}

    /**
     * Largely for abstraction, this stops the time thread.
     */
	public static void stopTime() {
		Time.stop();
	}

    /**
     * Starts the time thread using the provided multiplier.
     * @param multiplier
     */
	public static void startTime(long multiplier) {
		if (multiplier != 1) {
			TimeControl.createTimeThread(multiplier);
		}
		else {
			TimeControl.createTimeThread(1);
		}
	}
}
