package test.model.use;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import main.model.use.Inspection;

public class InspectionTest {
	
	final ZonedDateTime defaultDate = ZonedDateTime.of(2017, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
	final String defaultDescription = "Water Inspection";
	final String defaultDescription2 = "Gas inspection";
	final Inspection defaultInspection = new Inspection(defaultDescription, defaultDate);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetDescription() {
		Inspection a = defaultInspection;
		assertEquals(a.getDescription(), defaultDescription);
		a.setDescription(defaultDescription2);
		assertEquals(a.getDescription(), defaultDescription2);
	}

	
	@Test
	public void testGetAndSetDate() {
		Inspection b = defaultInspection;
		assertEquals(b.getDate(), defaultDate);
		b.setDate(b.getDate().plusDays(1));
		assertEquals(b.getDate(), defaultDate.plusDays(1));
	}


}
