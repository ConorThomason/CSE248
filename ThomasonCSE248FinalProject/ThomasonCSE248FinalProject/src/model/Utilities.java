package model;

import java.time.Clock;

public final class Utilities {
	
	public static String timeDateZoneConversion(Clock dateAndTime) {
		String createdString = dateAndTime.toString();
		//FixedClock[1970-01-01T00:00:00Z,UTC]
		createdString = createdString.substring(11, createdString.length());
		String date = createdString.substring(0, 10);
		String time = createdString.substring(11, 19);
		String timeZone = createdString.substring(21, 24);
		return date + " " + time + " " + timeZone;
	}
}
