package test.model.use;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.use.Reservation;
import main.model.use.User;

public class ReservationTest {
	
	final LocalDateTime defaultStartTime = LocalDateTime.of(2016, 3, 1, 7, 0, 0, 0);
	final LocalDateTime defaultEndTime = LocalDateTime.of(2016, 3, 1, 10, 0, 0, 0);
	final User defaultUser = new User("Anna");
	final Reservation defaultReservation = new Reservation(defaultStartTime, defaultEndTime, defaultUser);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testReservation(){
		Reservation r = defaultReservation;
		Reservation s = new Reservation(defaultEndTime, defaultStartTime, defaultUser);
		assertEquals(r, defaultReservation);
		assertEquals(s.getStartTime(), defaultEndTime);
		assertNotEquals(s.getEndTime(), defaultStartTime);
		assertNull(s.getEndTime());
	}

	@Test
	public void testGetAndSetStartTime() {
		Reservation r = defaultReservation;
		assertEquals(r.getStartTime(), defaultStartTime);
		assertTrue(r.setStartTime(defaultStartTime.plusHours(1)));
		assertEquals(r.getStartTime(), defaultStartTime.plusHours(1));
		assertFalse(r.setStartTime(defaultStartTime.plusHours(4))); //try to set start time after end time 
		assertEquals(r.getStartTime(), defaultStartTime.plusHours(1));
	}

	@Test
	public void testGetAndSetEndTime() {
		Reservation r = defaultReservation;
		assertEquals(r.getEndTime(), defaultEndTime);
		assertTrue(r.setEndTime(defaultEndTime.plusHours(1)));
		assertEquals(r.getEndTime(), defaultEndTime.plusHours(1));
		assertFalse(r.setEndTime(defaultEndTime.minusHours(4))); //try to set end time before start time 
		assertEquals(r.getEndTime(), defaultEndTime.plusHours(1));
	}

	@Test
	public void testGetAndSetUser() {
		Reservation r = defaultReservation;
		assertEquals(r.getUser(), defaultUser);
		assertEquals(r.getUser().getName(), "Anna");
		User u = new User("Brock");
		r.setUser(u);
		assertEquals(r.getUser(), u);
		assertEquals(r.getUser().getName(), "Brock");
	}

}
