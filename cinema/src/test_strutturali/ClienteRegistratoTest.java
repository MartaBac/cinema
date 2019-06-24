package test_strutturali;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import cinema.ClienteRegistrato;
import cinema.Permesso;

public class ClienteRegistratoTest {

	ClienteRegistrato c;
	
	@Before
	public void setUp() throws Exception {
		c = new ClienteRegistrato("user0","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		
	}
	
	@Test
	public void testClienteRegistrato() {
		assertNotNull(c);
		assertEquals("user0",c.getNickname());
		assertEquals("Gino",c.getName());
		assertEquals("Cozzi",c.getSurname());
		assertEquals("email@gmail.com",c.getEmail());
		assertEquals("15/02/1980",c.getBirth());
		assertFalse(c.isLoggedIn());
		assertEquals("psw123",c.getPassword());
		assertEquals(Permesso.USER,c.getRole());
	}
	
	@Test
	public void testGetStatus() {
		c.setStatus(true);
		assertTrue(c.getStatus());
		c.setStatus(false);
		assertFalse(c.getStatus());

}
	@Test
	/* Must always be null, for now is not expected to have a
	 * Phone number associated to a normal user
	 */
	public void testGetPhone(){
		assertNull(c.getPhone());
	}
	
	/*
	 * Normal users must not be associated to a specific cinema,
	 * so they have the cinema field set to null -always.
	 */
	@Test
	public void testGetCinema(){
		assertNull(c.getCinema());
	}
	
	@Test
	public void testIsEmployee(){
		assertFalse(c.isEmployee());
	}
	
	@Test
	public void testShowProfile(){
		String s = "nickname: user0\nnome: Gino\ncognome: Cozzi\nemail: email@gmail.com\n"
				+ "data di nascita: 15/02/1980\npermesso: USER";	
    assertEquals(s, c.showProfile()); 	
	}
	
	@Test
	public void testToString(){
		String s = "Gino Cozzi " + Permesso.USER;
		assertEquals(s,c.toString());
	}
	
	@Test
	public void testSetName(){
		String s = "Laila";
		c.setName(s);
		assertEquals(s,c.getName());
	}
	
	@Test
	public void testSetSurname(){
		String s = "Maila";
		c.setSurname(s);
		assertEquals(s,c.getSurname());
	}
	
	@Test
	public void testSetNickname(){
		String s = "Baila";
		c.setNickname(s);
		assertEquals(s,c.getNickname());
	}
	
	@Test
	public void testSetEmail(){
		String s = "sss@gmail.com";
		c.setEmail(s);
		assertEquals(s,c.getEmail());
	}
	
	@Test
	public void testSetPhone(){
		String s="12/04/1944";
		c.setBirth(s);
		assertEquals(s,c.getBirth());
	}
	
	@Test
	public void testGetPermesso(){
		assertEquals(Permesso.USER.getPermission(),c.getPermesso());
	}
}