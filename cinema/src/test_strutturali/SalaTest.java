package test_strutturali;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.InvalidParameterException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import cinema.Sala;
import cinema.Spettacolo;
import cinema.Spettacolo2D;

public class SalaTest {
	Sala s;
	@Before
	public void setUp() {
		s= new Sala("cinema0", "salaBLU", "011111", 6, 3, 2);
	}
	
	@Test
	public void testSala() {
		assertEquals("cinema0",s.getCinemaId());
		assertEquals("salaBLU",s.getName());
		assertEquals("011111",s.getUsableSeats());
		assertEquals("cinema0salaBLU",s.getSalaId());
		assertEquals((Integer)6, s.getCapacity());
		assertEquals((Integer) 3, s.getRows());
		assertEquals((Integer) 2, s.getColumns());		
	}

	@Test
	public void testSetUsableSeats() {
		s.setUsableSeats("001001");
		assertEquals("001001",s.getUsableSeats());
		// The set will not perform given an invalid parameter
		s.setUsableSeats("0");
		assertEquals("001001",s.getUsableSeats());
		s.setUsableSeats(null);
		assertEquals("001001",s.getUsableSeats());
		s.setUsableSeats("fallisci");
		assertEquals("001001",s.getUsableSeats());
		// String di caratteri ma della lunghezza giusta
		s.setUsableSeats("fallis");
		assertEquals("001001",s.getUsableSeats());
		// String di numeri non solo 01
		s.setUsableSeats("001007");
		assertEquals("001001",s.getUsableSeats());
	}

	@Test
	public void testSetName() {
		s.setName("provaNome");
		assertEquals("provaNome",s.getName());
	}
	@Test
	public void testPrintInfo() {
		String test = "cinema0salaBLU//cinema0//salaBLU//011111//6//3//2\r\n";
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     s.printInfo();
	    assertEquals(test, outContent.toString()); 	
	}


	@Test
	public void testSetListaSpettacoli() {
		HashMap<String, Spettacolo> spett = new HashMap<String, Spettacolo>();
		Spettacolo s1 = new Spettacolo2D("s1", "12/03/1928", "22:30", "23:30", 
				"0","Film1", s);
		assertFalse(s.getListaSpettacoli().containsKey(s1.getIdSpettacolo()));
		spett.put(s1.getIdSpettacolo(), s1);
		s.setListaSpettacoli(spett);
		assertTrue(s.getListaSpettacoli().containsKey(s1.getIdSpettacolo()));
	}
	
	@Test
	public void testAddSpettacolo(){
		Spettacolo s1 = new Spettacolo2D("s4", "12/03/1928", "22:30", "23:30", 
				"0","Film1", s);
		assertTrue(s.addSpettacolo(s1));
		assertFalse(s.addSpettacolo(s1));
		assertTrue(s.getListaSpettacoli().containsKey(s1.getIdSpettacolo()));
	}
	
	@Test 
	public void testRemoveSpettacolo(){
		Spettacolo s1 = new Spettacolo2D("s4", "12/03/1928", "22:30", "23:30", 
				"0","Film1", s);
		assertTrue(s.addSpettacolo(s1));
		assertTrue(s.getListaSpettacoli().containsKey(s1.getIdSpettacolo()));
		assertTrue(s.removeSpettacolo(s1.getIdSpettacolo()));
		assertFalse(s.getListaSpettacoli().containsKey(s1.getIdSpettacolo()));
		assertFalse(s.removeSpettacolo(s1.getSpettacoloId()));
	}

	@Test(expected = InvalidParameterException.class) 
	public void setUpExc() throws Exception {
		/*This must throw an exception because the third parameter has one value
		 * in excess
		*/
		new Sala("cinema0", "salaBLU", "0111111", 6, 3, 2);
		
	}
	
	@Test(expected = InvalidParameterException.class) 
	public void setUpExc2() throws Exception {
		/*This must throw an exception because 3*1!=6
		*/
		new Sala("cinema0", "salaBLU", "011111", 6, 3, 1);
		
	}
	
	@Test(expected = InvalidParameterException.class) 
	public void setUpExc3() throws Exception {
		/*This must throw an exception because length("011") != capacity(6)
		*/
		new Sala("cinema0", "salaBLU", "011", 6, 3, 1);
		
	}



}
