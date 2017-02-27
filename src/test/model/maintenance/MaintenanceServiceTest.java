package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.MaintTicket;
import main.model.maintenance.MaintenanceService;
import main.model.maintenance.Material;
import main.model.maintenance.Worker;

public class MaintenanceServiceTest {
	
	final MaintenanceService defaultMaintenanceService = new MaintenanceService();
	final ZonedDateTime defaultSinceTime = ZonedDateTime.of(2016, 3, 1, 6, 0, 0, 0, ZoneId.systemDefault());
	final ZonedDateTime defaultRequestTime = ZonedDateTime.of(2016, 3, 1, 7, 0, 0, 0, ZoneId.systemDefault());
	final ZonedDateTime defaultOrderTime = ZonedDateTime.of(2016, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
	final ZonedDateTime defaultResolveTime = ZonedDateTime.of(2016, 3, 1, 10, 0, 0, 0, ZoneId.systemDefault());
	final ZonedDateTime defaultTilTime = ZonedDateTime.of(2016, 3, 1, 12, 0, 0, 0, ZoneId.systemDefault());
	Material brick = new Material("brick", 2.50);
	Material mortar = new Material("mortar", 5.75);
	Worker Bill = new Worker("Bill", 1);
	Worker Bob = new Worker("Bob", 15);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeFacilityMaintRequest() {
		MaintenanceService maint = defaultMaintenanceService;
		assertTrue(maint.listMaintRequests().isEmpty());
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		assertFalse(maint.listMaintRequests().isEmpty());
	}
	
	@Test
	public void testGetFacilityMaintRequest() {
		MaintenanceService maint = defaultMaintenanceService;
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		assertNotNull(maint.getMaintTicket("replace lightbulb"));
		assertNull(maint.getMaintTicket("fix pipes"));
	}

	@Test
	public void testScheduleMaintenance() {
		MaintenanceService maint = defaultMaintenanceService;
		assertTrue(maint.listMaintenance().isEmpty());
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, defaultOrderTime, defaultResolveTime);
		assertFalse(maint.listMaintenance().isEmpty());
	}

	@Test
	public void testCalcMaintenanceCostForFacility() {
		MaintenanceService maint = defaultMaintenanceService;
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, defaultOrderTime, defaultResolveTime);
		ticket.addWorker(Bill);
		ticket.addWorker(Bob);
		ticket.addMaterial(brick);
		ticket.addMaterial(brick);
		ticket.addMaterial(mortar);
		assertEquals(maint.calcMaintenanceCostForFacility(), 42.75, 0);
	}

	@Test
	public void testCalcProblemRateForFacility() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testCalcDownTimeForFacility() {
		MaintenanceService maint = defaultMaintenanceService;
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, defaultOrderTime, defaultResolveTime);
		assertEquals(maint.calcDownTimeForFacility(defaultSinceTime, defaultTilTime), .5 ,0);
	}

}
