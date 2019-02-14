package stocks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockBagTest {
	
	static StockBag theBag = new StockBag(10);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Stock stock1 = new Stock("IBM", 1000.00, 25.00);
		Stock stock2 = new Stock("GE", 400.00, 100.00);
		theBag.insert(stock1);
		theBag.insert(stock2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test1() {
		double actual = 0.0;
		actual = theBag.getGrandTotal();
		assertEquals(65000, actual);
	}
}
