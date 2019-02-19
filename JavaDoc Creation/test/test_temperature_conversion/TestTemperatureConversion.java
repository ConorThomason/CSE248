package test_temperature_conversion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import temperature_conversion.TemperatureConversion;

class TestTemperatureConversion {

	@Test
	void testCelsiusToFahrenheit() {
		assertThrows(IllegalArgumentException.class, () -> {TemperatureConversion.celsiusToFahrenheit(-274);});
		assertEquals(89.6, TemperatureConversion.celsiusToFahrenheit(32));
	}
	
	@Test
	void testFahrenheitToCelsius() {
		assertThrows(IllegalArgumentException.class, () -> {TemperatureConversion.fahrenheitToCelsius(-460);});
		assertEquals(0, TemperatureConversion.fahrenheitToCelsius(32));
	}

}
