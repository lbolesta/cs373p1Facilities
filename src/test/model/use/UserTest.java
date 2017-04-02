package test.model.use;

import static org.junit.Assert.*;

import main.model.use.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
	
	final ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	private User user;

	final String defaultName = "Mary";
	final int defaultId = 123;
	
	@Before
	public void setUp() throws Exception {
		user = (User) context.getBean("user");
	}

	@After
	public void tearDown() throws Exception {
		user = null;
	}

	@Test
	public void testGetNameAndSetName() {
		assertEquals(user.getName(), null);
		user.setName(defaultName);
		assertEquals(user.getName(), defaultName);
	}

	@Test
	public void testGetIdNumber() {
		assertEquals(user.getIdNumber(), 0);
		user.setIdNumber(defaultId);
		assertEquals(user.getIdNumber(), defaultId);
	}

	
}
