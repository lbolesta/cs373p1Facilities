package test.model.facility;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.facility.Room;
import main.model.facility.ScheduleManager;
import main.model.maintenance.MaintTicket;
import main.model.use.User;
import test.model.maintenance.*;
import test.model.use.*;

public class ScheduleManagerTest {
	
	final Room defaultRoom = new Room("711", 40);
	final ScheduleManager defaultSchedule = defaultRoom.getScheduleManager();
	final MaintenanceServiceTest maintTest = new MaintenanceServiceTest();
	final UsageServiceTest useTest = new UsageServiceTest();
	final LocalDateTime defaultStartTime = LocalDateTime.of(2016, 3, 1, 7, 0, 0, 0);
	final LocalDateTime defaultEndTime = LocalDateTime.of(2016, 3, 1, 9, 0, 0, 0);
	final User defaultUser = new User("Anna");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMaint() {
		assertNotNull(defaultRoom.getScheduleManager().getMaint());
	}

	@Test
	public void testGetUsage() {
		assertNotNull(defaultRoom.getScheduleManager().getUsage());
	}

	@Test
	public void testMakeFacilityMaintRequest() {
		maintTest.testMakeFacilityMaintRequest();
	}

	@Test
	public void testScheduleMaintenance() {
		assertTrue(defaultSchedule.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser)); //7-9
		assertFalse(defaultSchedule.listActualUsage().isEmpty());
		assertEquals(defaultSchedule.listActualUsage().size(), 1);
		defaultSchedule.makeFacilityMaintRequest("replace lightbulb", defaultStartTime);
		MaintTicket ticket = defaultSchedule.getMaint().getMaintTicket("replace lightbulb"); //7
		defaultSchedule.scheduleMaintenance(ticket, defaultStartTime.plusHours(1), defaultEndTime.plusHours(1)); //8-10
		assertEquals(defaultSchedule.listActualUsage().size(), 1);
		assertFalse(defaultSchedule.isInUseDuringInterval(defaultStartTime, defaultStartTime.plusHours(1)));
		defaultSchedule.makeFacilityMaintRequest("repair wall", defaultStartTime);
		MaintTicket ticket2 = defaultSchedule.getMaint().getMaintTicket("repair wall");
		defaultSchedule.scheduleMaintenance(ticket2, defaultStartTime, defaultEndTime);
	}

	@Test
	public void testCalcMaintenanceCostForFacility() {
		maintTest.testCalcMaintenanceCostForFacility();
	}

	@Test
	public void testCalcProblemRateForFacility() {
		maintTest.testCalcProblemRateForFacility();
	}

	@Test
	public void testCalcDownTimeForFacility() {
		maintTest.testCalcDownTimeForFacility();
	}

	@Test
	public void testListMaintRequests() {
		maintTest.testListMaintRequests();
	}

	@Test
	public void testListMaintenance() {
		maintTest.testListMaintenance();
	}

	@Test
	public void testListFacilityProblems() {
		maintTest.testListFacilityProblems();
	}

	@Test
	public void testIsInUseDuringInterval() {
		useTest.testIsInUseDuringInterval();
	}

	@Test
	public void testAssignFacilityToUse() {
		useTest.testAssignFacilityToUse();
	}

	@Test
	public void testVacateFacility() {
		useTest.testVacateFacility();
	}

	@Test
	public void testListAndAddInspections() {
		useTest.testListAndAddInspections();
	}

	@Test
	public void testListActualUsage() {
		useTest.testListActualUsage();
	}

	@Test
	public void testCalcUsageRate() {
		useTest.testCalcUsageRate();
	}

}
