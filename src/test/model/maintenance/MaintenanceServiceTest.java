package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.maintenance.MaintTicket;
import main.model.maintenance.MaintService;
import main.model.maintenance.Material;
import main.model.maintenance.Worker;

public class MaintenanceServiceTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	
	final LocalDateTime since = LocalDateTime.parse("2016-03-01T06:00:00");
	final LocalDateTime request = LocalDateTime.parse("2016-03-01T07:00:00");
	final LocalDateTime order = LocalDateTime.parse("2016-03-01T08:00:00");
	final LocalDateTime resolve = LocalDateTime.parse("2016-03-01T10:00:00");
	final LocalDateTime til = LocalDateTime.parse("2016-03-01T12:00:00");
	final Material brick = (Material) context.getBean("brick");
	final Material lightbulb = (Material) context.getBean("lightbulb");
	final Worker Bill = (Worker) context.getBean("Bill");
	final Worker Bob = (Worker) context.getBean("Bob");
	
	private MaintService maint;

	@Before
	public void setUp() throws Exception {
		maint = (MaintService) context.getBean("maintService");
	}

	@After
	public void tearDown() throws Exception {
		maint = null;
	}

	@Test
	public void testMakeFacilityMaintRequest() {
		assertTrue(maint.listMaintRequests().isEmpty());
		maint.makeFacilityMaintRequest("replace lightbulb", request);
		assertFalse(maint.listMaintRequests().isEmpty());
	}
	
	@Test
	public void testGetFacilityMaintRequest() {
		maint.makeFacilityMaintRequest("replace lightbulb", request);
		assertNotNull(maint.getMaintTicket("replace lightbulb"));
		assertNull(maint.getMaintTicket("fix pipes"));
	}

	@Test
	public void testScheduleMaintenance() {
		assertTrue(maint.listMaintenance().isEmpty());
		maint.makeFacilityMaintRequest("replace lightbulb", request);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, order, resolve);
		assertFalse(maint.listMaintenance().isEmpty());
	}

	@Test
	public void testCalcMaintenanceCostForFacility() {
		
		maint.makeFacilityMaintRequest("replace lightbulb", request);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, order, resolve);
		ticket.addWorker(Bill);
		ticket.addWorker(Bob);
		ticket.addMaterial(brick);
		ticket.addMaterial(brick);
		ticket.addMaterial(lightbulb);
		assertEquals(maint.calcMaintenanceCostForFacility(), 69, 0);
	}

	@Test
	public void testCalcProblemRateForFacility() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testCalcDownTimeForFacility() {
		maint.makeFacilityMaintRequest("replace lightbulb", request);
		MaintTicket ticket = maint.getMaintTicket("replace lightbulb");
		maint.scheduleMaintenance(ticket, order, resolve);
		assertEquals(maint.calcDownTimeForFacility(since, til), .5 ,0);
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
