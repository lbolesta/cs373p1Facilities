package test.model.maintenance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.Worker;

public class WorkerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetName() {
		final Worker worker = new Worker();
		assertEquals(worker.getName(), null);
		worker.setName("Bill");
		assertEquals(worker.getName(),"Bill");
		worker.setName("Bob");
		assertEquals(worker.getName(), "Bob");
	}

	@Test
	public void testGetAndSetWage() {
		final Worker worker = new Worker();
		assertEquals(worker.getWage(), 0, 0);
		worker.setWage(8.25);
		assertEquals(worker.getWage(),8.25,0);
	}

}
