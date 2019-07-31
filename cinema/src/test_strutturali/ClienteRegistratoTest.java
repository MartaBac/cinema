package test_strutturali;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
				"Permesso: " + test.substring("Permesso: ".length(), 40) + 
				 c.getPermessoObj() + "\r\n";
		test6 = new String(new char[60]).replace("\0", "-");
		exp = test6 + "\r\n" + exp + test6 + "\r\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));	
		
		String s = "nickname: " + c.getNickname() + "\nnome: "+c.getName() + 
			"\ncognome: " + c.getSurname() +"\nemail: " + c.getEmail() +"\n"
				+ "data di nascita: " + c.getBirth() + "\npermesso: " +
			c.getPermessoObj();	
		assertEquals(s, c.showProfile()); 
		assertEquals(exp, outContent.toString());
		outContent.close();
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
	
	@Test
	public void testGetPermessoObj(){
		assertEquals(Permesso.USER,c.getPermessoObj());
	}
	
	@Test
	public void testSetLoggedIn(){
		c.setLoggedIn(false);
		assertFalse(c.isLoggedIn());
		c.setLoggedIn(true);
		assertTrue(c.isLoggedIn());
	}
}