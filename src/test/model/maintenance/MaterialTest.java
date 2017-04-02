package test.model.maintenance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.maintenance.Material;

public class MaterialTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	private Material material;

	@Before
	public void setUp() throws Exception {
		material = (Material) context.getBean("material");
	}

	@After
	public void tearDown() throws Exception {
		material = null;
	}

	@Test
	public void testGetAndSetName() {
		assertNull(material.getName());
		material.setName("rock");
		assertEquals(material.getName(),"rock");
	}

	@Test
	public void testGetAndSetCost() {
		assertEquals(material.getCost(), 0, 0);
		material.setCost(0.99);
		assertEquals(material.getCost(), 0.99, 0);
		material.setCost(.49);
		assertEquals(material.getCost(), 0.49, 0);
	}

}
