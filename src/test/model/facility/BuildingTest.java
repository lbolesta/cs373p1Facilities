package test.model.facility;

import static org.junit.Assert.*;
import main.model.facility.Building;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.model.facility.*;


public class BuildingTest {
	Building defaultBuilding = new Building();
	ScheduleManager defaultSchedule = new ScheduleManager();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInfo() {
		 Building b = defaultBuilding;
		 assertEquals(b.getInfo(), null);
		
	}

	@Test
	public void testGetScheduleManager() {
		Building a = defaultBuilding; 
		assertEquals(a.getScheduleManager(), null);
	}

	@Test
	public void testListFacilities() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestAvailableCapacity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFacilityInformation() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewFacility() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewFacilityDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFacility() {
		fail("Not yet implemented");
	}

}

