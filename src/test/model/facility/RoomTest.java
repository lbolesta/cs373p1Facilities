package test.model.facility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.facility.Room;
import main.model.facility.ScheduleManager;

public class RoomTest {
	
	String detail1 = "Class use only";
	String detail2 = "Keycard locked";
	Room defaultRoom = new Room("711", 40);
	ScheduleManager defaultSchedule = new ScheduleManager();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInfo() {
		assertNotNull(defaultRoom.getInfo());
		assertEquals(defaultRoom.getInfo().getName(), "711");
		assertEquals(defaultRoom.getInfo().getCapacity(), 40);
	}

	@Test
	public void testGetScheduleManager() {
		assertNotNull(defaultRoom.getScheduleManager());
	}

	@Test
	public void testListFacilities() {
		assertFalse(defaultRoom.listFacilities().isEmpty());
		assertTrue(defaultRoom.listFacilities().contains(defaultRoom));
		assertEquals(defaultRoom.listFacilities().size(),1);
	}

	@Test
	public void testRequestAvailableCapacity() {
		assertEquals(defaultRoom.requestAvailableCapacity(), 40);
	}

	@Test
	public void testGetFacilityInformation() {
		assertNotNull(defaultRoom.getFacilityInformation());
		assertEquals(defaultRoom.getFacilityInformation().getName(), "711");
		assertEquals(defaultRoom.getFacilityInformation().getCapacity(), 40);
	}

	@Test
	public void testAddNewFacilityDetail() {
		assertTrue(defaultRoom.getFacilityInformation().getDetails().isEmpty());
		defaultRoom.addNewFacilityDetail(detail1);
		assertFalse(defaultRoom.getFacilityInformation().getDetails().isEmpty());
		assertEquals(defaultRoom.getFacilityInformation().getDetails().size(), 1);
	}

}
