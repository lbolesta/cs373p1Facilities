package test.model.use;

import static org.junit.Assert.*;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.use.Inspection;
import main.model.use.UsageService;
import main.model.use.User;

public class UsageServiceTest {
	
	final LocalDateTime defaultSinceTime = LocalDateTime.of(2016, 3, 1, 6, 0, 0, 0);
	final LocalDateTime defaultStartTime = LocalDateTime.of(2016, 3, 1, 7, 0, 0, 0);
	final LocalDateTime defaultEndTime = LocalDateTime.of(2016, 3, 1, 9, 0, 0, 0);
	final LocalDateTime defaultTilTime = LocalDateTime.of(2016, 3, 1, 11, 0, 0, 0);
	final UsageService defaultUsageService = new UsageService();
	final User defaultUser = new User("Annie");
	final Inspection defaultInspection = new Inspection("Water Inspection", LocalDateTime.of(2015, 3, 1, 7, 0, 0, 0));
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsInUseDuringInterval() {
		UsageService use = defaultUsageService;
		assertFalse(use.isInUseDuringInterval(defaultStartTime, defaultEndTime)); //7-9
		use.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser);
		assertTrue(use.isInUseDuringInterval(defaultStartTime, defaultEndTime)); //7-9
		assertFalse(use.isInUseDuringInterval(defaultStartTime.minusHours(1), defaultStartTime)); //6-7
		assertFalse(use.isInUseDuringInterval(defaultEndTime, defaultEndTime.plusHours(1))); //9-10
		assertTrue(use.isInUseDuringInterval(defaultStartTime.minusHours(1), defaultEndTime.minusHours(1))); //6-8
		assertTrue(use.isInUseDuringInterval(defaultStartTime.plusHours(1), defaultEndTime.plusHours(1))); //8-10
		assertTrue(use.isInUseDuringInterval(defaultStartTime.plusMinutes(30), defaultEndTime.minusMinutes(30))); //7:30-8:30
	}

	@Test
	public void testAssignFacilityToUse() {
		UsageService use = defaultUsageService;
		assertTrue(use.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser)); //7-9
		assertFalse(use.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser)); //7-9
		assertFalse(use.assignFacilityToUse(defaultStartTime.plusHours(1), defaultEndTime.plusHours(1), defaultUser)); //8-10
		assertFalse(use.isInUseDuringInterval(defaultEndTime, defaultEndTime.plusHours(2))); //9-11
		assertTrue(use.assignFacilityToUse(defaultStartTime.plusHours(2), defaultEndTime.plusHours(2), defaultUser)); //9-11
	}

	@Test
	public void testVacateFacility() {
		UsageService use = defaultUsageService;
		use.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser);
		assertTrue(use.isInUseDuringInterval(defaultStartTime, defaultEndTime));
		use.vacateFacility(defaultStartTime.minusHours(1), defaultEndTime.minusHours(1));
		assertFalse(use.isInUseDuringInterval(defaultStartTime, defaultEndTime));
	}

	@Test
	public void testListAndAddInspections() {
		UsageService use = defaultUsageService;
		Inspection i = defaultInspection;
		Inspection j = new Inspection(i.getDescription(), i.getDate().minusYears(1));
		Inspection k = new Inspection(j.getDescription(), j.getDate().minusYears(1));
		List<Inspection> ins = new ArrayList<Inspection>();
		ins.add(i);
		use.addInspections(ins);
		assertEquals(use.listInspections(), ins);
		ins.add(j);
		ins.add(k);
		use.addInspections(ins);
		assertEquals(use.listInspections(), ins);
	}

	@Test
	public void testListActualUsage() {
		UsageService use = defaultUsageService;
		assertTrue(use.listActualUsage().isEmpty());
		use.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser);
		assertFalse(use.listActualUsage().isEmpty());
	}

	@Test
	public void testCalcUsageRate() {
		UsageService use = defaultUsageService;
		assertEquals(use.calcUsageRate(defaultSinceTime, defaultTilTime), 0, 0);
		use.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser);
		assertEquals(use.calcUsageRate(defaultSinceTime, defaultTilTime), .4, 0.01);
	}

}
