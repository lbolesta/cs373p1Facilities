package test.model.use;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import main.model.use.Inspection;

public class InspectionTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	final LocalDateTime defaultDate = LocalDateTime.parse("2016-03-01T09:00:00");
	private Inspection i;

	@Before
	public void setUp() throws Exception {
		i = (Inspection) context.getBean("inspection");
	}

	@After
	public void tearDown() throws Exception {
		i = null;
	}

	@Test
	public void testGetAndSetDescription() {
		assertNotEquals(i.getDescription(), "gas");
		i.setDescription("gas");
		assertEquals(i.getDescription(), "gas");
	}

	
	@Test
	public void testGetAndSetDate() {
		assertNotEquals(i.getDate(), defaultDate);
		i.setDate(defaultDate);
		assertEquals(i.getDate(), defaultDate);
	}


}
