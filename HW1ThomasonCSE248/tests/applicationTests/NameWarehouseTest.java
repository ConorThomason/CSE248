package applicationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.NameWarehouse;

class NameWarehouseTest {
	NameWarehouse warehouse = new NameWarehouse();
	@Test
	void maleFirstNameTest() {
		assertEquals("James", warehouse.getFirstName(3, false));
	}
	
	@Test
	void femaleFirstNameTest() {
		assertEquals("Sophia", warehouse.getFirstName(4, true));
	}
	
	@Test
	void lastNameTest() {
		assertEquals("Sweeney", warehouse.getLastName(7));
	}

}
