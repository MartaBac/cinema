package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cinema.Admin;
import cinema.Permesso;

public class AdminTest {	
	Admin a;

	@Before
	public void setUp() throws Exception {
		a = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
			"abc0123", "+393339087654", null);
	}
	
	@Test
	public void testManager() {
		assertNotNull(a); 
		assertEquals("Admin0",a.getNickname());
		assertEquals("Bob",a.getName());
		assertEquals("Brown",a.getSurname());
		assertEquals("lurbin@em.net",a.getEmail());
		assertFalse(a.isLoggedIn());
		assertEquals("abc0123",a.getPassword());
		assertEquals("+393339087654", a.getPhone());
		assertEquals(null, a.getCinema());
		assertTrue(a.getStatus());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSetCinema(){
		// Non passa perché fallisce e manda l'eccezione
		a.setCinemaId("cinema");
	}
	
	@Test
	public void testSetStatus(){
		a.setStatus(true);
		boolean t = a.getStatus();
		a.setStatus(false);
		// Il cambio stato NON deve avvenire
		assertEquals(t,a.getStatus());
	}
	
	@Test
	public void testGetPermesso(){
		assertEquals(Permesso.ADMIN.getPermission(),a.getPermesso());
	}
}

