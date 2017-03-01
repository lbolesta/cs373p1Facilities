package test.model.facility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.facility.Building;
import main.model.facility.Campus;
import main.model.facility.Room;

public class CampusTest {
	
	Room defaultRoom = new Room("711", 40);
	Room defaultRoom2 = new Room("712", 50);
	Building defaultBuilding = new Building("Corboy Law Center");
	Campus defaultCampus = new Campus("Loyola University");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetScheduleManager() {
		assertNotNull(defaultCampus.getScheduleManager());
	}

	@Test
	public void testFacilities() {
		assertTrue(defaultCampus.listFacilities().isEmpty());
		defaultBuilding.addNewFacility(defaultRoom);
		defaultBuilding.addNewFacility(defaultRoom2);
		defaultCampus.addNewFacility(defaultBuilding);
		assertTrue(defaultCampus.listFacilities().contains(defaultBuilding));
		assertEquals(defaultCampus.listFacilities().size(),1);
		defaultCampus.addNewFacility(defaultBuilding);
		assertEquals(defaultCampus.listFacilities().size(),1);
		defaultCampus.removeFacility(defaultBuilding);
		assertEquals(defaultCampus.listFacilities().size(),0);
	}

	@Test
	public void testRequestAvailableCapacity() {
		assertEquals(defaultCampus.requestAvailableCapacity(), 0);
		defaultBuilding.addNewFacility(defaultRoom);
		defaultBuilding.addNewFacility(defaultRoom2);
		defaultCampus.addNewFacility(defaultBuilding);
		assertEquals(defaultCampus.requestAvailableCapacity(), 90);
	}

	@Test
	public void testGetFacilityInformation() {
		assertNotNull(defaultBuilding.getFacilityInformation());
	}

	@Test
	public void testAddNewFacilityDetail() {
		assertTrue(defaultCampus.getFacilityInformation().getDetails().isEmpty());
		defaultCampus.addNewFacilityDetail("Jesuit university");
		assertFalse(defaultCampus.getFacilityInformation().getDetails().isEmpty());
		assertEquals(defaultCampus.getFacilityInformation().getDetails().size(), 1);
	}

}
