package test.model.use;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.use.Reservation;
import main.model.use.User;

public class ReservationTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	final private LocalDateTime start = LocalDateTime.parse("2016-03-01T07:00:00");
	final private LocalDateTime end = LocalDateTime.parse("2016-03-01T09:00:00");
	private Reservation r;
	
	@Before
	public void setUp() throws Exception {
		r = (Reservation) context.getBean("defaultReservation");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetStartTime() {
		assertEquals(r.getStartTime(), start);
		r.setStartTime(start.plusHours(1));
		assertEquals(r.getStartTime(), start.plusHours(1));
		r.setStartTime(start.plusHours(4)); //try to set start time after end time 
		assertEquals(r.getStartTime(), start.plusHours(1));
	}

	@Test
	public void testGetAndSetEndTime() {
		assertEquals(r.getEndTime(), end);
		r.setEndTime(end.plusHours(1));
		assertEquals(r.getEndTime(), end.plusHours(1));
		r.setEndTime(end.minusHours(4)); //try to set end time before start time
		assertEquals(r.getEndTime(), end.plusHours(1));
	}

	@Test
	public void testGetAndSetUser() {
		assertEquals(r.getUser().getName(), "Anna");
		User u = (User) context.getBean("user");
		u.setName("Brock");
		r.setUser(u);
		assertEquals(r.getUser(), u);
		assertEquals(r.getUser().getName(), "Brock");
	}

}
