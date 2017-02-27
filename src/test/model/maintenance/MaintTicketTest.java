package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.MaintTicket;
import main.model.maintenance.Material;
import main.model.maintenance.Worker;

public class MaintTicketTest {
	
	final ZonedDateTime defaultRequestTime = ZonedDateTime.of(2016, 3, 1, 7, 0, 0, 0, ZoneId.systemDefault());
	final MaintTicket defaultTicket = new MaintTicket("replace lightbulb",defaultRequestTime);
	final ZonedDateTime defaultOrderTime = ZonedDateTime.of(2016, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
	final ZonedDateTime defaultResolveTime = ZonedDateTime.of(2016, 3, 1, 10, 0, 0, 0, ZoneId.systemDefault());
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
	public void testGetState() {
		MaintTicket ticket = defaultTicket;
		ZonedDateTime time = ZonedDateTime.of(2016, 3, 1, 7, 30, 0, 0, ZoneId.systemDefault());
		assertEquals(ticket.getState(time), "REQUEST");
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		time = time.plusHours(1); //8:30
		assertEquals(ticket.getState(time), "ORDER");
		time = time.plusHours(2); //10:30
		assertEquals(ticket.getState(time), "RESOLVED");
	}

	@Test
	public void testCalcCost() {
		MaintTicket ticket = defaultTicket;
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertEquals(ticket.calcCost(), 0.0, 0);
		ticket.addMaterial(brick);
		ticket.addMaterial(brick);
		assertEquals(ticket.calcCost(), 5, 0);
		ticket.addWorker(Bob);
		assertEquals(ticket.calcCost(), 35, 0);
	}

	@Test
	public void testGetAndSetDescription(){
		MaintTicket ticket = defaultTicket;
		final String description = "repaint walls";
		assertNotEquals(ticket.getDescription(), description);
		assertEquals(ticket.getDescription(), "replace lightbulb");
		ticket.setDescription(description);
		assertEquals(ticket.getDescription(), description);
	}

	@Test
	public void testGetAndSetRequestTime() {
		MaintTicket ticket = defaultTicket;
		assertEquals(ticket.getRequestTime(),defaultRequestTime);
		assertTrue(ticket.setRequestTime(defaultRequestTime.plusHours(1)));
		assertEquals(ticket.getRequestTime(),defaultRequestTime.plusHours(1));
	}

	@Test
	public void testGetAndSetOrderTime(){
		MaintTicket ticket = defaultTicket;
		assertFalse(ticket.setOrderTime(defaultOrderTime.minusHours(2))); //try to set to time less than request
		assertTrue(ticket.setOrderTime(defaultOrderTime));
		assertEquals(ticket.getOrderTime(),defaultOrderTime);
	}

	@Test
	public void testGetAndSetResolveTime() {
		MaintTicket ticket = defaultTicket;
		assertFalse(ticket.setResolveTime(defaultResolveTime)); //try to set resolve time without order time
		assertTrue(ticket.setOrderTime(defaultOrderTime));
		assertTrue(ticket.setResolveTime(defaultResolveTime));
		assertEquals(ticket.getResolveTime(), defaultResolveTime);
		assertFalse(ticket.setResolveTime(defaultResolveTime.minusHours(3))); //try to set to time less than order
		assertTrue(ticket.setResolveTime(defaultResolveTime.minusHours(1)));
		assertEquals(ticket.getResolveTime(),defaultResolveTime.minusHours(1));
	}

	@Test
	public void testMaterials() {
		MaintTicket ticket = defaultTicket;
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertTrue(ticket.getMaterials().isEmpty());
		ticket.addMaterial(brick);
		ticket.addMaterial(mortar);
		ticket.addMaterial(brick);
		assertEquals(ticket.calcCost(), 10.75, 0);
		ticket.removeMaterial(mortar);
		assertEquals(ticket.calcCost(), 5, 0);
		ticket.removeMaterial(mortar);
		assertEquals(ticket.calcCost(), 5, 0);
	}

	@Test
	public void testWorkers() {
		MaintTicket ticket = defaultTicket;
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertTrue(ticket.getWorkers().isEmpty());
		ticket.addWorker(Bill);
		ticket.addWorker(Bob);
		ticket.addWorker(Bill);
		assertEquals(ticket.calcCost(), 34, 0);
		ticket.removeWorker(Bob);
		assertEquals(ticket.calcCost(), 4, 0);
		ticket.removeWorker(Bob);
		assertEquals(ticket.calcCost(), 4, 0);
		ticket.removeWorker(Bill);
		assertEquals(ticket.calcCost(), 2, 0);
	}

}
