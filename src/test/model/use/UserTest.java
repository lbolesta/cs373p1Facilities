package test.model.use;

import static org.junit.Assert.*;


import main.model.use.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	final String name1 = "Mary";
	final String name2 = "John";
	User defaultUser = new User(name1);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNameAndSetName() {
		User a = defaultUser; 
		assertEquals(a.getName(), name1);
		a.setName(name2);
		assertEquals(a.getName(), name2);
	}

	@Test
	public void testGetIdNumber() {
		User b = defaultUser;
		assertEquals(b.getIdNumber(), -1);
	}

	
}
