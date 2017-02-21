package test.model.maintenance;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.FacilityProblem;
import main.model.maintenance.MaintRequest;

public class MaintRequestTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetFacilityProblem() {
		MaintRequest request = new MaintRequest("repaint walls");
		FacilityProblem problem = new FacilityProblem("fix lightbulb", ZonedDateTime.now());
		assertNotEquals(request.getFacilityProblem(), problem);
		assertNotEquals(request.getFacilityProblem().getDescription(), problem.getDescription());
		request.setFacilityProblem(problem);
		assertEquals(request.getFacilityProblem(), problem);
	}

	@Test
	public void testGetAndSetRequestDate() {
		MaintRequest request = new MaintRequest("repaint walls");
		ZonedDateTime time = ZonedDateTime.now().minusMinutes(1);
		assertNotEquals(request.getRequestTime(), time);
		request.setRequestTime(time);
		assertEquals(request.getRequestTime(), time);
		
	}

}
