package test.model.maintenance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.maintenance.Worker;

public class WorkerTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	private Worker worker;

	@Before
	public void setUp() throws Exception {
		worker = (Worker) context.getBean("worker");
	}

	@After
	public void tearDown() throws Exception {
		worker = null;
	}

	@Test
	public void testGetAndSetName() {
		assertEquals(worker.getName(), null);
		worker.setName("Bill");
		assertEquals(worker.getName(),"Bill");
		worker.setName("Bob");
		assertEquals(worker.getName(), "Bob");
	}

	@Test
	public void testGetAndSetWage() {
		assertEquals(worker.getWage(), 0, 0);
		worker.setWage(8.25);
		assertEquals(worker.getWage(),8.25,0);
	}

}
