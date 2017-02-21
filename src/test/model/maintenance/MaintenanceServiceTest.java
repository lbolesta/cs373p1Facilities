package test.model.maintenance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.MaintRequest;
import main.model.maintenance.MaintenanceService;

public class MaintenanceServiceTest {
	
	MaintenanceService defaultMaintenanceService = new MaintenanceService();
	MaintRequest defaultRequest = new MaintRequest("replace lightbulb");

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
		maint.makeFacilityMaintRequest("replace lightbulb");
		assertFalse(maint.listMaintRequests().isEmpty());
	}

	@Test
	public void testScheduleMaintenance() {
		MaintenanceService maint = defaultMaintenanceService;
		maint.makeFacilityMaintRequest("replace lightbulb");
		
	}

	@Test
	public void testCalcMaintenanceCostForFacility() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcProblemRateForFacility() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcDownTimeForFacility() {
		fail("Not yet implemented");
	}

	@Test
	public void testListMaintenance() {
		fail("Not yet implemented");
	}

	@Test
	public void testListFacilityProblems() {
		fail("Not yet implemented");
	}

}
