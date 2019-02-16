package applicationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NameWarehouseTest {
	NameWarehouse warehouse = new NameWarehouse();
	@Test
	void maleFirstNameTest() {
		assertEquals("James", warehouse.getMaleName(3));
	}
	
	@Test
	void femaleFirstNameTest() {
		assertEquals("Sophia", warehouse.getFemaleName(4));
	}
	
	@Test
	void lastNameTest() {
		assertEquals("Sweeney", warehouse.getLastName(7));
	}

}
