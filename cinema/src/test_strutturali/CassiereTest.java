package test_strutturali;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import cinema.Cassiere;
import cinema.Permesso;

public class CassiereTest {	
	Cassiere c;
	
	@Before
	public void setUp() throws Exception {
		c = new Cassiere("cassiere0","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","cinema0");
	}
	
	@Test
	public void testCassiere() {
		assertNotNull(c);
		assertEquals("cassiere0",c.getNickname());
		assertEquals("Alda",c.getName());
		assertEquals("Bosi",c.getSurname());
		assertEquals("mail@mail.com",c.getEmail());
		assertEquals("13/07/1977",c.getBirth());
		assertFalse(c.isLoggedIn());
		assertEquals("psw1234",c.getPassword());
		assertEquals("+3334556678",c.getPhone());
		assertEquals("cinema0",c.getCinema());
		assertEquals(Permesso.CASSIERE,c.getRole());
	}

	@Test
	public void testShowProfile(){
		String test = "nickname: " + c.getNickname() + "\nnome: " + c.getName() + "\n"
		 + "cognome: " + c.getSurname()+ "\nemail: " + c.getEmail()+"\ndata di nascita: "
		 + c.getBirth()+"\nphone number: " + c.getPhone() + "\ncinema: " + c.getCinema() 
		 + "\npermesso: " + c.getRole();
     assertEquals(test, c.showProfile()); 	
	}

	@Test
	public void testSetStatus() {
		c.setStatus(true);
		assertTrue(c.getStatus());
		c.setStatus(false);
		assertFalse(c.getStatus());
	}

	@Test
	public void testSetPhone() {
		String s = "+334567890";
		c.setPhone(s);
		assertEquals(s,c.getPhone());
	}

	@Test
	public void testSetCinema() {
		String s = "cincin";
		c.setCinemaId(s);
		assertEquals(s,c.getCinema());
	}
	
	@Test
	public void testGetPermesso(){
		assertEquals(Permesso.CASSIERE.getPermission(),c.getPermesso());
	}
}
