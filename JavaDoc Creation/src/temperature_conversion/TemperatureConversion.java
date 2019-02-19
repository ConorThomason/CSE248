package temperature_conversion;
/**
 * The TemperatureConversion Java application prints a table converting Celsius to Fahrenheit degrees
 * 
 * @see <A href="../src/temperature_conversion/TemperatureConversion.java">Java Source Code</A>
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 *
 *@version V1.0, 2/19/2019
 */
public class TemperatureConversion {
	/**
	 * The main method prints a Celsius to Fahrenheit conversion table
	 * 
	 * The bounds of the table range from -50C to +50C in 10 degree-increments
	 * 
	 * @param args not used in this implementation
	 * @author Conor Thomason
	 */
	public static void main(String[] args) {
		final double TABLE_BEGIN = -50.0;
		final double TABLE_END = 50.0;
		final double TABLE_STEP = 10.0;
		double celsius;
		double fahrenheit;
		System.out.println("TEMPERATURE CONVERSION");
		System.out.println("--------------------------------");
		System.out.println("Celsius        Fahrenheit");
		for(celsius = TABLE_BEGIN; celsius <= TABLE_END; celsius += TABLE_STEP) {
			fahrenheit = celsiusToFahrenheit(celsius);
			System.out.printf("%6.2fC", celsius);
			System.out.printf("%14.2fF\n", fahrenheit);
		}
		System.out.println("-------------------------------");
	}
	
/**
 * Convert a temperature from Celsius degrees to Fahrenheit degrees
 * 
 * @param celsius: temperature in Celsius degrees
 * @return fahrenheit: temperature converted to Fahrenheit degrees
 * @throws java.lang.IllegalArgumentException indicates that Celsius is less than 
 * 			the lowest Celsius temperature (-271.16)
 * @author Conor Thomason
 * @conor.precondition celsius lower or equal than -273.16
 * @conor.postcondition the temperature in Fahrenheit
 */
	
	public static double celsiusToFahrenheit(double celsius) {
		final double MINIMUM_CELSIUS = -273.16;
		if (celsius < MINIMUM_CELSIUS) {
			throw new IllegalArgumentException("Argument " + celsius + " too low");
		}
			return (1.8*celsius)+32;
	}
	public static double fahrenheitToCelsius(double fahrenheit) {
		final double MINIMUM_FAHRENHEIT = -459.67;
		if (fahrenheit < MINIMUM_FAHRENHEIT) {
			throw new IllegalArgumentException("Argument " + fahrenheit + " too low");
		}
		return (fahrenheit-32)/1.8;
	}
}
