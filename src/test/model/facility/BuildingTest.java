package test.model.facility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.model.facility.*;


public class BuildingTest {

	Room defaultRoom = new Room("711", 40);
	Room defaultRoom2 = new Room("712", 50);
	Building defaultBuilding = new Building("Corboy Law Center");
	ScheduleManager defaultSchedule = new ScheduleManager();
	String detail = "Closed on weekends";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetScheduleManager() {
		assertNotNull(defaultBuilding.getScheduleManager());
	}

	@Test
	public void testFacilities() {
		assertTrue(defaultBuilding.listFacilities().isEmpty());
		defaultBuilding.addNewFacility(defaultRoom);
		assertTrue(defaultBuilding.listFacilities().contains(defaultRoom));
		assertEquals(defaultBuilding.listFacilities().size(),1);
		defaultBuilding.addNewFacility(defaultRoom);
		assertEquals(defaultBuilding.listFacilities().size(),1);
		defaultBuilding.addNewFacility(defaultRoom2);
		assertEquals(defaultBuilding.listFacilities().size(),2);
		defaultBuilding.removeFacility(defaultRoom);
		assertEquals(defaultBuilding.listFacilities().size(),1);
		defaultBuilding.removeFacility(defaultRoom);
		assertEquals(defaultBuilding.listFacilities().size(),1);
	}

	@Test
	public void testRequestAvailableCapacity() {
		assertEquals(defaultBuilding.requestAvailableCapacity(), 0);
		defaultBuilding.addNewFacility(defaultRoom);
		assertEquals(defaultBuilding.requestAvailableCapacity(), 40);
	}

	@Test
	public void testGetFacilityInformation() {
		assertNotNull(defaultBuilding.getFacilityInformation());
		assertEquals(defaultBuilding.getFacilityInformation().getName(), "Corboy Law Center");
		assertEquals(defaultBuilding.getFacilityInformation().getCapacity(), 0);
		defaultBuilding.addNewFacility(defaultRoom);
		assertEquals(defaultBuilding.getFacilityInformation().getCapacity(), 40);
	}

	@Test
	public void testAddNewFacilityDetail() {
		assertTrue(defaultBuilding.getFacilityInformation().getDetails().isEmpty());
		defaultBuilding.addNewFacilityDetail(detail);
		assertFalse(defaultBuilding.getFacilityInformation().getDetails().isEmpty());
		assertEquals(defaultBuilding.getFacilityInformation().getDetails().size(), 1);
	}

}

