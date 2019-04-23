package model;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public final class Utilities {
	
	public static String zoneStringFormat(Instant currentInstant) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
				.withLocale(Locale.US).withZone(TimeControl.getDefaultZone());
		String output = formatter.format(currentInstant);
		return output;
	}
}
