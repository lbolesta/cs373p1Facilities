package test.model.facility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.facility.UnitInfo;

public class UnitInfoTest {
	
	UnitInfo defaultUnitInfo = new UnitInfo("711", 40);
	String detail1 = "Class use only";
	String detail2 = "Keycard locked";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetCapacityAndSetCapacity() {
		UnitInfo i = defaultUnitInfo;
		assertEquals(i.getCapacity(), 40);
		i.setCapacity(10);
		assertEquals(i.getCapacity(), 10);
	}

	@Test
	public void testGetNameAndSetName() {
		UnitInfo i = defaultUnitInfo;
		assertEquals(i.getName(), "711");
		i.setName("712");
		assertEquals(i.getName(), "712");
	}

	@Test
	public void testDetails() {
		UnitInfo i = defaultUnitInfo;
		assertTrue(i.getDetails().isEmpty());
		i.addDetail(detail1);
		assertTrue(i.getDetails().contains(detail1));
		i.addDetail(detail1);
		assertEquals(i.getDetails().size(),1);
		i.addDetail(detail2);
		assertEquals(i.getDetails().size(), 2);
		i.removeDetail(detail1);
		assertEquals(i.getDetails().size(), 1);
		i.removeDetail(detail1);
		assertEquals(i.getDetails().size(), 1);
	}

}
