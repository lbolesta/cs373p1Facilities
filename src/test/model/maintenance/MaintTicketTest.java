package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.maintenance.MaintTicket;
import main.model.maintenance.Material;
import main.model.maintenance.Worker;

public class MaintTicketTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	
	final MaintTicket defaultTicket = (MaintTicket) context.getBean("defaultMaintTicket");
	final LocalDateTime defaultRequestTime = LocalDateTime.parse("2016-03-01T07:00:00");
	final LocalDateTime defaultOrderTime = LocalDateTime.parse("2016-03-01T08:00:00");
	final LocalDateTime defaultResolveTime = LocalDateTime.parse("2016-03-01T10:00:00");
	final Material brick = (Material) context.getBean("material");
	final Material lightbulb = (Material) context.getBean("material");
	final Worker Bill = (Worker) context.getBean("worker");
	final Worker Bob = (Worker) context.getBean("worker");
	
	private MaintTicket ticket;

	@Before
	public void setUp() throws Exception {
		ticket = defaultTicket;
		brick.setName("brick");
		brick.setCost(2);
		lightbulb.setName("lightbulb");
		lightbulb.setCost(5);
		Bill.setName("Bill");
		Bill.setWage(10);
		Bob.setName("Bob");
		Bob.setWage(20);
	}

	@After
	public void tearDown() throws Exception {
		ticket = null;
	}

	@Test
	public void testGetState() throws Exception {
		LocalDateTime time = LocalDateTime.of(2016, 3, 1, 7, 30, 0, 0);
		assertEquals(ticket.getState(time), "REQUEST");
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		time = time.plusHours(1); //8:30
		assertEquals(ticket.getState(time), "ORDER");
		time = time.plusHours(2); //10:30
		assertEquals(ticket.getState(time), "RESOLVED");
	}

	@Test
	public void testCalcCost() throws Exception {
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertEquals(ticket.calcCost(), 0.0, 0);
		ticket.addMaterial(brick);
		ticket.addMaterial(brick);
		assertEquals(ticket.calcCost(), 4, 0);
		ticket.addWorker(Bob);
		assertEquals(ticket.calcCost(), 44, 0);
	}

	@Test
	public void testGetAndSetDescription(){
		final String description = "repaint walls";
		assertNotEquals(ticket.getDescription(), description);
		assertEquals(ticket.getDescription(), "replace lightbulb");
		ticket.setDescription(description);
		assertEquals(ticket.getDescription(), description);
	}

	@Test
	public void testGetAndSetRequestTime() {
		assertEquals(ticket.getRequestTime(), defaultRequestTime);
		ticket.setRequestTime(defaultRequestTime.plusHours(1));
		assertEquals(ticket.getRequestTime(), defaultRequestTime.plusHours(1));
	}

	@Test
	public void testGetAndSetOrderTime() {
		ticket.setOrderTime(defaultOrderTime.minusHours(2)); //try to set to time less than request
		ticket.setOrderTime(defaultOrderTime);
		assertEquals(ticket.getOrderTime(),defaultOrderTime);
	}

	@Test
	public void testGetAndSetResolveTime() {
		ticket.setResolveTime(defaultResolveTime); //try to set resolve time without order time
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertEquals(ticket.getResolveTime(), defaultResolveTime);	
		ticket.setResolveTime(defaultResolveTime.minusHours(3)); // try to set resolve time too early
		assertEquals(ticket.getResolveTime(),defaultResolveTime);
		ticket.setResolveTime(defaultResolveTime.minusHours(1));
		assertEquals(ticket.getResolveTime(),defaultResolveTime.minusHours(1));
	}

	@Test
	public void testMaterials() {
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertTrue(ticket.getMaterials().isEmpty());
		ticket.addMaterial(brick);
		ticket.addMaterial(lightbulb);
		ticket.addMaterial(lightbulb);
		assertEquals(ticket.calcCost(), 12, 0);
		ticket.removeMaterial(lightbulb);
		assertEquals(ticket.calcCost(), 7, 0);
		ticket.removeMaterial(lightbulb);
		assertEquals(ticket.calcCost(), 2, 0);
	}

	@Test
	public void testWorkers() {
		ticket.setOrderTime(defaultOrderTime);
		ticket.setResolveTime(defaultResolveTime);
		assertTrue(ticket.getWorkers().isEmpty());
		ticket.addWorker(Bill);
		ticket.addWorker(Bob);
		ticket.addWorker(Bill);
		assertEquals(ticket.calcCost(), 60, 0); // can only add worker once
		ticket.removeWorker(Bob);
		assertEquals(ticket.calcCost(), 20, 0);
		ticket.removeWorker(Bob);
		assertEquals(ticket.calcCost(), 20, 0);
		ticket.removeWorker(Bill);
		assertEquals(ticket.calcCost(), 0, 0);
	}

}
