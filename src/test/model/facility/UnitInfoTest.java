package test.model.facility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.facility.UnitInfo;

public class UnitInfoTest {

	UnitInfo defaultUnitInfo = new UnitInfo();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	@Test
	public void testGetCapacityAndSetCapacity() {
		UnitInfo a = defaultUnitInfo;
		assertEquals(a.getCapacity(), null);
		a.setCapacity(10);
		assertEquals(a.getCapacity(), 10);
		a.setCapacity(20);
		assertEquals(a.getCapacity(), 20);
	}



	@Test
	public void testGetNameAndSetName() {
		UnitInfo b = defaultUnitInfo;
		assertEquals(b.getName(), null);
		b.setName("Lewis Towers");
		assertEquals(b.getName(), "Lewis Towers");
		b.setName("IC");
		assertEquals(b.getName(), "IC");
	}

	
	@Test
	public void testGetIdNumberAndSetIdNumber() {
		UnitInfo c = defaultUnitInfo;
		assertEquals(c.getIdNumber(), null);
		c.setIdNumber(1);
		assertEquals(c.getIdNumber(), 1);
		c.setIdNumber(2);
		assertEquals(c.getIdNumber(), 2);
	}

	

	@Test
	public void testGetDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveDetail() {
		fail("Not yet implemented");
	}

}
