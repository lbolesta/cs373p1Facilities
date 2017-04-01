package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.maintenance.MaintTicket;
import main.model.maintenance.MaintenanceService;
import main.model.maintenance.Material;
import main.model.maintenance.Worker;

public class MaintenanceServiceTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	
	final MaintenanceService defaultMaintenanceService = new MaintenanceService();
	final LocalDateTime defaultSinceTime = LocalDateTime.of(2016, 3, 1, 6, 0, 0, 0);
	final LocalDateTime defaultRequestTime = LocalDateTime.of(2016, 3, 1, 7, 0, 0, 0);
	final LocalDateTime defaultOrderTime = LocalDateTime.of(2016, 3, 1, 8, 0, 0, 0);
	final LocalDateTime defaultResolveTime = LocalDateTime.of(2016, 3, 1, 10, 0, 0, 0);
	final LocalDateTime defaultTilTime = LocalDateTime.of(2016, 3, 1, 12, 0, 0, 0);
	final Material brick = (Material) context.getBean("brick");
	final Material bulb = (Material) context.getBean("bulb");
	final Worker Bill = (Worker) context.getBean("Bill");
	final Worker Bob = (Worker) context.getBean("Bob");
	
	private MaintenanceService maint;

	@Before
	public void setUp() throws Exception {
		maint = defaultMaintenanceService;
	}

	@After
	public void tearDown() throws Exception {
		maint = null;
	}

	@Test
	public void testMakeFacilityMaintRequest() {
		assertTrue(maint.listMaintRequests().isEmpty());
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		assertFalse(maint.listMaintRequests().isEmpty());
	}
	
	@Test
	public void testGetFacilityMaintRequest() {
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		assertNotNull(maint.getMaintTicket("replace lightbulb"));
		assertNull(maint.getMaintTicket("fix pipes"));
	}

	@Test
	public void testScheduleMaintenance() {
		assertTrue(maint.listMaintenance().isEmpty());
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, defaultOrderTime, defaultResolveTime);
		assertFalse(maint.listMaintenance().isEmpty());
	}

	@Test
	public void testCalcMaintenanceCostForFacility() {
		
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, defaultOrderTime, defaultResolveTime);
		ticket.addWorker(Bill);
		ticket.addWorker(Bob);
		ticket.addMaterial(brick);
		ticket.addMaterial(brick);
		ticket.addMaterial(bulb);
		assertEquals(maint.calcMaintenanceCostForFacility(), 42.75, 0);
	}

	@Test
	public void testCalcProblemRateForFacility() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testCalcDownTimeForFacility() {
		maint.makeFacilityMaintRequest("replace lightbulb", defaultRequestTime);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, defaultOrderTime, defaultResolveTime);
		assertEquals(maint.calcDownTimeForFacility(defaultSinceTime, defaultTilTime), .5 ,0);
	}
	
	@Test
	public void testListMaintRequests() {
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
