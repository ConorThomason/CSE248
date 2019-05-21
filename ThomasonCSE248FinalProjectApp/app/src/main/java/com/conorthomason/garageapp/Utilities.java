package com.conorthomason.garageapp;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Intended for the usage of methods that are used multiple times in model.
 *
 * @see <A href="../src/model/Vehicle.java">Java source code</A>
 *
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 * @version V1.0, 4/8/2019
 *
 */
public final class Utilities {



    /**
     *
     * @param currentInstant
     * @return String output Returns string after converting instant to a simpler form.
     */
	public static String zoneStringFormat(Instant currentInstant) {
	    String output;
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
				.withLocale(Locale.US).withZone(TimeControl.getDefaultZone());
		try {
		    output = formatter.format(currentInstant);
        } catch (NullPointerException e){
		    e.printStackTrace();
		    return "Time Retrieval Failed";
        }
		return output;
	}
}
