package test.model.use;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.use.Inspection;
import main.model.use.Reservation;
import main.model.use.UsageService;

public class UsageServiceTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	final LocalDateTime since = LocalDateTime.parse("2016-03-01T06:00:00");
	final LocalDateTime start = LocalDateTime.parse("2016-03-01T07:00:00");
	final LocalDateTime end = LocalDateTime.parse("2016-03-01T09:00:00");
	final LocalDateTime til = LocalDateTime.parse("2016-03-01T11:00:00");
	
	private UsageService use;
	private Reservation r;
	private Inspection i;
	
	@Before
	public void setUp() throws Exception {
		use = (UsageService) context.getBean("usageService");
		r = (Reservation) context.getBean("defaultReservation");
		i = (Inspection) context.getBean("defaultInspection");
	}

	@After
	public void tearDown() throws Exception {
		use = null;
		r = null;
		i = null;
	}

	@Test
	public void testIsInUseDuringInterval() {
		assertFalse(use.isInUseDuringInterval(start, end)); //7-9
		use.assignFacilityToUse(r);
		assertTrue(use.isInUseDuringInterval(start, end)); //7-9
		assertFalse(use.isInUseDuringInterval(start.minusHours(1), start)); //6-7
		assertFalse(use.isInUseDuringInterval(end, end.plusHours(1))); //9-10
		assertTrue(use.isInUseDuringInterval(start.minusHours(1), end.minusHours(1))); //6-8
		assertTrue(use.isInUseDuringInterval(start.plusHours(1), end.plusHours(1))); //8-10
		assertTrue(use.isInUseDuringInterval(start.plusMinutes(30), end.minusMinutes(30))); //7:30-8:30
	}

	@Test
	public void testAssignFacilityToUse() {
		use.assignFacilityToUse(r); //7-9
		use.assignFacilityToUse(r); //7-9
		Reservation s = r;
		s.setStartTime(r.getStartTime().plusHours(1));
		s.setEndTime(r.getEndTime().plusHours(1));
		use.assignFacilityToUse(s); //8-10
		s.setStartTime(r.getStartTime().plusHours(2));
		s.setEndTime(r.getEndTime().plusHours(2));
		use.assignFacilityToUse(s); //9-11
	}

	@Test
	public void testVacateFacility() {
		use.assignFacilityToUse(r);
		assertTrue(use.isInUseDuringInterval(start, end));
		use.vacateFacility(start.minusHours(1), end.minusHours(1));
		assertFalse(use.isInUseDuringInterval(start, end));
	}

	@Test
	public void testListAndAddInspections() {
		Inspection j = (Inspection) context.getBean("inspection");
		j.setDescription(i.getDescription());
		j.setDate(i.getDate().minusYears(1));
		Inspection k = (Inspection) context.getBean("inspection");
		k.setDescription(j.getDescription());
		k.setDate(j.getDate().minusYears(1));
		use.addInspection(i);
		assertTrue(use.listInspections().contains(i));
		use.addInspection(j);
		use.addInspection(k);
		assertTrue(use.listInspections().contains(j));
	}

	@Test
	public void testListActualUsage() {
		assertTrue(use.listActualUsage().isEmpty());
		use.assignFacilityToUse(r);
		assertFalse(use.listActualUsage().isEmpty());
	}

	@Test
	public void testCalcUsageRate() {
		assertEquals(use.calcUsageRate(since, til), 0, 0);
		use.assignFacilityToUse(r);
		assertEquals(use.calcUsageRate(since, til), .4, 0.01);
	}

}
