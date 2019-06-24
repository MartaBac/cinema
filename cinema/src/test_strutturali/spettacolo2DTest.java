package test_strutturali;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import cinema.Sala;
import cinema.Spettacolo;
import cinema.Spettacolo2D;

public class spettacolo2DTest {

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
	public void equals(){

		Spettacolo s1 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","0",
				"mov",this.sala);
		assertTrue(s1.equalsSpett(s1));
		Spettacolo s2 = new Spettacolo2D("spett1", "02/12/1999", "21:30", "23:30","0",
				"mov",this.sala);
		// Diverso id spettacolo
		assertFalse(s1.equalsSpett(s2));
		Spettacolo s3 = new Spettacolo2D("spett0", "02/12/1998", "21:30", "23:30","0",
				"mov",this.sala);
		// Diversa data
		assertFalse(s1.equalsSpett(s3));
		Spettacolo s4 = new Spettacolo2D("spett0", "02/12/1999", "22:31", "23:30","0",
				"mov",this.sala);
		// Diverso StartingTime
		assertFalse(s1.equalsSpett(s4));
		Spettacolo s5 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:31","0",
				"mov",this.sala);
		// Diverso endingTime
		assertFalse(s1.equalsSpett(s5));
		Spettacolo s6 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","1",
				"mov", this.sala);
		// Different idCinema
		assertFalse(s1.equalsSpett(s6));
		Spettacolo s7 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","0",
				"mov2", this.sala);
		// Different idMovie
		assertFalse(s1.equalsSpett(s7));
		// Diverso - vietato minori
		Spettacolo s10 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","0",
				"mov",this.sala);
		s10.setVietato(true);
		assertFalse(s1.equalsSpett(s10));	
		// Diversa salaId
		Sala sass = new Sala("uh", "salaROSA", "011111", 6, 3, 2);
		Spettacolo s9 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","0",
				"mov",sass);
		assertFalse(s1.equalsSpett(s9));	
	}
	
	@Test
	public void testSetMovieId(){
		s.setIdMovie("hula");
		assertEquals("hula",s.getIdMovie());
	}

	@Test
	public void testPrenote(){
		// Posto non usabile
		assertFalse(s.prenote(1,1,"id1"));
		// Posto usabile e libero
		assertTrue(s.prenote(1,2,"id1"));
		// Posto usabile ma occupato
		assertFalse(s.prenote(1,2,"id2"));
		// Prenotazione presente
		assertNotNull(s.getPrenotazioni().get("row1column2"));
		assertEquals("id1",s.getPrenotazioni().get("row1column2").getClientId());
		// Prenotazione assente
		assertNull(s.getPrenotazioni().get("row1column3"));
	}
	
}
