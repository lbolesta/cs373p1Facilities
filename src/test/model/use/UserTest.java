package test.model.use;

import static org.junit.Assert.*;


import main.model.use.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	String name1 = "Mary";
	String name2 = "John";
	int defaultId = 10; 
	int defaultId2 = 11; 
	User defaultUser = new User();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNameAndSetName() {
		User a = defaultUser; 
		assertEquals(a.getName(), null);
		a.setName(name1);
		assertEquals(a.getName(), name1);
		a.setName(name2);
		assertEquals(a.getName(), name2);
	}

	@Test
	public void testGetIdNumberAndSetIdNumber() {
		User b = new User();
		assertEquals(b.getIdNumber(), null);
		b.setIdNumber(defaultId);
		assertEquals(b.getIdNumber(), defaultId);
		b.setIdNumber(defaultId2);
		assertEquals(b.getIdNumber(), defaultId2);
	}

	
}
