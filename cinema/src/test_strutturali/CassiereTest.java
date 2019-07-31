package test_strutturali;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
	public void testShowProfile() throws IOException{
		String exp, test6;
		String test = new String(new char[60]).replace("\0", " ");
		exp = "Nickname: " + test.substring("Nickname: ".length(), 40) + 
				 c.getNickname() + "\r\n" + 
				"Name: " + test.substring("Name: ".length(), 40) + 
				 c.getName() + "\r\n" + 
				"Cognome: " + test.substring("Cognome: ".length(), 40) +
				 c.getSurname() + "\r\n" + 
				"E-mail: " + test.substring("E-mail: ".length(), 40) +
				 c.getEmail() + "\r\n" + 
				"Data di nascita: " + test.substring("Data di nascita: ".length(), 40) + 
				 c.getBirth() + "\r\n" +
				"Numero di telefono: " + test.substring("Numero di telefono: ".length(),
						40) + c.getPhone() + "\r\n" +
				"Luogo di lavoro: " + test.substring("Luogo di lavoro: ".length(),
						40) + c.getCinema() + "\r\n" +
				"Permesso: " + test.substring("Permesso: ".length(), 40) + 
				 c.getPermessoObj() + "\r\n";
		test6 = new String(new char[60]).replace("\0", "-");
		exp = test6 + "\r\n" + exp + test6 + "\r\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));	
		
		
		String s = "nickname: " + c.getNickname() + "\nnome: " + c.getName() + "\n" +
					"cognome: " + c.getSurname()+ "\nemail: " + c.getEmail() + 
					"\ndata di nascita: " + c.getBirth()+"\nphone number: " + 
					c.getPhone() + "\ncinema: " + c.getCinema() + "\npermesso: " +
					c.getRole();
    	assertEquals(s, c.showProfile()); 
    	assertEquals(exp, outContent.toString());
    	outContent.close();
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
	
	@Test public void testIsEmployer(){
		assertFalse(c.getPermessoObj().isEmployer());	
	}
	
	@Test
	public void testGetPermesso(){
		assertEquals(Permesso.CASSIERE.getPermission(),c.getPermesso());
	}
}
