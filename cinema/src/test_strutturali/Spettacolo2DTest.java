package test_strutturali;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import cinema.Sala;
import cinema.Seat;
import cinema.Spettacolo;
import cinema.Spettacolo2D;

public class Spettacolo2DTest {

	Spettacolo2D s,s1;
	Sala sala;
	@Before
	public void setUp() throws Exception {
		sala = new Sala("cinema0", "salaBLU", "011011", 6, 3, 2);	
		s1 = new Spettacolo2D("spett1", "03/12/1999", "20:30", "22:30","0", true,"mov",
				sala);
		s = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","0","mov", sala);
	}
	
	@Test
	public void testSpettacolo() {
		assertEquals("spett0",s.getSpettacoloId());
		assertEquals("mov",s.getIdMovie());
		assertEquals("02/12/1999",s.getDateShow());
		assertEquals("21:30",s.getStartingTime());
		assertEquals("23:30",s.getEndingTime());
		assertFalse(s.getVietato());
		BigDecimal bd1= new BigDecimal("8.50");
		BigDecimal bd2= new BigDecimal("6");
		assertEquals(bd1,s.getTicketCost());
		assertEquals(bd2,s.getReducedTicketCost());
	}

	@Test
	public void testSpettacoloVietato() {
		assertEquals("spett1",s1.getSpettacoloId());
		assertEquals("mov",s.getIdMovie());
		assertEquals("03/12/1999",s1.getDateShow());
		assertEquals("20:30",s1.getStartingTime());
		assertEquals("22:30",s1.getEndingTime());
		assertTrue(s1.getVietato());
	}


	@Test
	public void testSetVietato() {
		s.setVietato(true);
		assertTrue(s.getVietato());
		s.setVietato(false);
		assertFalse(s.getVietato());
	}


	@Test
	public void testSetEndingTime() {
		String a = "21:12";
		s.setEndingTime(a);
		assertEquals(a,s.getEndingTime());
	}


	@Test
	public void testSetDateShow() {
		String a = "12/03/2000";
		s.setDateShow(a);
		assertEquals(a,s.getDateShow());
	}


	@Test
	public void testSetTicketCost() {
		BigDecimal bd = new BigDecimal("1.50");
		Spettacolo.setTicketCost(bd);
		assertEquals(bd,s.getTicketCost());
		BigDecimal bd1 = new BigDecimal("8.50");
		Spettacolo.setTicketCost(bd1 );
	}
	
	@Test
	public void testSetReducedTicketCost() {
		BigDecimal bd = new BigDecimal("2.50");
		Spettacolo.setReducedTicketCost(bd);
		assertEquals(bd,s.getReducedTicketCost());
		BigDecimal bd1 = new BigDecimal("6");
		Spettacolo.setReducedTicketCost(bd1);
	}


	@Test
	public void testSetStartingTime() {
		String t ="12:00";
		s.setStartingTime(t);
		assertEquals(t,s.getStartingTime());
	}
	
	@Test 
	public void testSetIdSala(){
		s.setIdSala("provaSet");
		assertEquals("provaSet",s.getIdSala());
	}

	@Test
	public void testSetSalaId() {
		String id = "prova1";
		s.setSalaId(id);
		assertEquals(id,s.getSalaId());
	}
	

	
	@Test
	public void testSetMovieId(){
		s.setIdMovie("hula");
		assertEquals("hula",s.getIdMovie());
	}

	@Test
	public void testPrenote(){
		ArrayList<Seat> se = new ArrayList<Seat>();
		Seat s1 = new Seat(1,1,true);
		se.add(s1);
		// Posto non usabile
		assertFalse(s.prenote(se,"id1"));
		se.remove(s1);
		s1 = new Seat(1,2,true);
		se.add(s1);
		// Posto usabile e libero
		assertTrue(s.prenote(se,"id1"));
		s1 = new Seat(1,2,true);
		// Posto usabile ma occupato
		assertFalse(s.prenote(se,"id2"));
		// Prenotazione presente
		assertNotNull(s.getPrenotazioni().get("row1column2"));
		assertEquals("id1",s.getPrenotazioni().get("row1column2").getClientId());
		// Prenotazione assente
		assertNull(s.getPrenotazioni().get("row1column3"));
	}
	
	@Test
	public void testDeletePrenotazione(){
		assertFalse("La cancellazione deve fallire perché non esiste tale prenotazione",
				s.deletePrenotazione("notpresent"));
		ArrayList<Seat> se = new ArrayList<Seat>();
		Seat s1 = new Seat(1,2,true);
		se.add(s1);
		s.prenote(se,"id1");
		assertTrue("Cancellazione della prenotazione appena avvenuta",
				s.deletePrenotazione(s1.getId()));
	}
	
	@Test
	public void testGetFreeSeat(){
		// Devono coincidere in quanto nessuna prenotazione è avvenuta
		String k = sala.getUsableSeats();
		int t = 0;
		for(char c : k.toCharArray()) {
		    if(c == '1') {
		        t++;
		    }
		}
		assertEquals(t,s.getFreeSeat().size());
		ArrayList<Seat> se = new ArrayList<Seat>();
		Seat s1 = new Seat(1,2,true);
		se.add(s1);
		s.prenote(se,"id1");
		assertEquals(t-1,s.getFreeSeat().size());
	}
	
	@Test
	public void testGetPrenotazione(){
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		assertFalse(s.getPrenotazione("nulla"));
		ArrayList<Seat> se = new ArrayList<Seat>();
		Seat s1 = new Seat(1,2,true);
		String st;
		se.add(s1);
		s.prenote(se,"Client");
	    st = new String(new char[60]).replace("\0", "-") + "\r\n";
	    st += s1.toString().substring(0, s1.toString().length()) +"\r\n";
	    st += new String(new char[60]).replace("\0", "-") + "\r\n";
	    System.setOut(new PrintStream(outContent));
	    assertTrue(s.getPrenotazione(s1.getId()));
	    assertEquals(st, outContent.toString()); 
	}
	   	
	
	@Test
	public void testSetIdCinema(){
		s.setIdCinema("2");
		assertEquals("2",s.getIdCinema());
	}
	
	@Test
	public void testToString(){
		assertEquals(s.getIdSpettacolo() + " " + s.getDateShow() + " " + 
				s.getStartingTime() + " " + s.getIdSala(),  s.toString());
	}
	
	
}
