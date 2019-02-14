package stocks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StockTest {

	@Test //Turns methods into pseudo main methods, runs each upon running. Don't need a main method/Demo class for every test.
	void test() {
		double actual1 = 0;
		Stock stock1 = new Stock("IBM", 1000.00, 25.00);
		actual1 = stock1.getTotal();
		
		double actual2 = 0;
		Stock stock2 = new Stock("GE", 400.00, 100.00);
		actual2 = stock2.getTotal();
		
		assertEquals(25000, actual1);
		assertEquals(40000, actual2);
	}

}
