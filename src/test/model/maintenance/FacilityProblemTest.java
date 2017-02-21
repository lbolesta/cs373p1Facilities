package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.FacilityProblem;
import main.model.maintenance.MaintOrder;

public class FacilityProblemTest {
	
	ZonedDateTime defaultStartTime = ZonedDateTime.of(2016, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
	ZonedDateTime defaultEndTime = ZonedDateTime.of(2016, 3, 1, 10, 0, 0, 0, ZoneId.systemDefault());
	FacilityProblem defaultProblem = new FacilityProblem("ceiling leaks", defaultStartTime);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetDescription(){
		FacilityProblem problem = defaultProblem;
		final String description = "replace lightbulb";
		assertNotEquals(problem.getDescription(), description);
		problem.setDescription(description);
		assertEquals(problem.getDescription(), description);
	}

	@Test
	public void testGetAndSetStartTime() {
		FacilityProblem problem = defaultProblem;
		assertEquals(problem.getStartTime(),defaultStartTime);
		assertTrue(problem.setStartTime(ZonedDateTime.now()));
		problem.setEndTime(ZonedDateTime.now());
		assertFalse(problem.setStartTime(ZonedDateTime.now().plusHours(1)));
		assertTrue(problem.setStartTime(defaultStartTime.plusHours(1)));
		assertEquals(problem.getStartTime(),defaultStartTime.plusHours(1));
	}

	@Test
	public void testGetAndSetEndTime() {
		FacilityProblem problem = defaultProblem;
		ZonedDateTime time = ZonedDateTime.now();
		assertEquals(problem.getEndTime(), time);
		assertFalse(problem.setEndTime(defaultStartTime.minusHours(1)));
		assertEquals(problem.getEndTime(), time);
		assertTrue(problem.setEndTime(defaultStartTime.plusHours(1)));
		assertEquals(problem.getEndTime(),defaultStartTime.plusHours(1));
	}

	@Test
	public void testIsAndSetAssigned() {
		FacilityProblem problem = defaultProblem;
		assertFalse(problem.isAssigned());
		MaintOrder order = new MaintOrder(problem, defaultStartTime.plusHours(1), defaultEndTime.plusHours(1));
		assertTrue(order.getFacilityProblem().isAssigned());
		assertTrue(problem.isAssigned());
		assertEquals(order.getFacilityProblem().getEndTime(), defaultEndTime.plusHours(1));
	}

}
