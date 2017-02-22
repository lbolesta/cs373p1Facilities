package test.model.use;

import static org.junit.Assert.*;

import java.time.ZoneId;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import main.model.use.Inspection;

public class InspectionTest {
	
	ZonedDateTime defaultDate = ZonedDateTime.of(2017, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
	String defaultDescription = "Water Inspection";
	String defaultDescription2 = "Gas inspection";
	Inspection defaultInspection = new Inspection();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDateAndSetDescription() {
		Inspection a = defaultInspection;
		assertEquals(a.getDescription(), null);
		a.setDescription(defaultDescription);
		assertEquals(a.getDescription(), defaultDescription);
		a.setDescription(defaultDescription2);
		assertEquals(a.getDescription(), defaultDescription2);
	}

	
	@Test
	public void testGetDateAndSetDate() {
		Inspection b = defaultInspection; 
		assertEquals(b.getDate(), null);
		b.setDate(defaultDate);
		assertNotEquals(b.getDate(), ZonedDateTime.now());
		assertEquals(b.getDate(), defaultDate);
	}


}
