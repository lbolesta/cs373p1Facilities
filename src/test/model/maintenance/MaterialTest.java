package test.model.maintenance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.MaterialImpl;

public class MaterialTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetName() {
		final MaterialImpl material = new MaterialImpl();
		assertNull(material.getName());
		material.setName("rock");
		assertEquals(material.getName(),"rock");
	}

	@Test
	public void testGetAndSetCost() {
		final MaterialImpl material = new MaterialImpl();
		assertEquals(material.getCost(), 0, 0);
		material.setCost(0.99);
		assertEquals(material.getCost(), 0.99, 0);
		material.setCost(.49);
		assertEquals(material.getCost(), 0.49, 0);
	}

}
