package test_strutturali;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import cinema.Cinema;
import cinema.Sala;
import cinema.Spettacolo;
import cinema.Spettacolo2D;
import cinema.Spettacolo3D;

public class CinemaTest {
	Cinema c;
	@Before
	public void setUp() throws Exception {
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
	}

	@Test
	public void testCinema() {
		assertEquals("0",c.getIdCinema());
		assertEquals("Ariston",c.getNome());
		assertEquals("Sanremo",c.getCitta());
		assertEquals("Via Puggia 2",c.getIndirizzo());
		assertEquals(new HashMap<String, Sala>(),c.getSaleMap());
	}
	
	@Test
	public void testPrintAllInfo() {
		String t = "Nome\nAriston\nIndirizzo:\nVia Puggia 2\nNumero sale:\n0";
		assertEquals(t,c.printAllInfo());
	}
	@Test
	public void getNumeroSale(){
		assertEquals(0,c.getNumeroSale());
	}

	@Test
	public void testAddSala() {
		Sala s = new Sala("cinema0", "saala0", "0", 1, 1, 1);	
		// Aggiunta prima sala avvenuta con successo
		assertTrue(c.addSala(s));
		// Controllo la presenza della sala
		assertTrue(c.getSaleMap().containsValue(s));
		// Sala già presente, inserimento fallito
		assertFalse(c.addSala(s));
		Sala s1 = new Sala("cinema0", "sala1", "0", 1, 1, 1);
		// Aggiunta seconda sala
		assertTrue(c.addSala(s1));
	}

	@Test
	public void testPrintBaseInfo() {
		String s = "Nome: Ariston; Città: Sanremo";	
		assertEquals(s,c.printBaseInfo());
	}

	@Test
	public void testCompareTag() {
		String[] s = new String[]{"cane","Ariston","Sanremo"};
		String[] s1 = new String[]{"cane"};
		String[] s2 = new String[]{"Ariston"};
		String[] s3 = new String[]{"Sanremo"};
		String[] s4 = new String[]{null};
		assertNotNull(c.compareTag(s));
		// Mi aspetto che la chiave di ricerca migliore sia Ariston, poi sanremo e cane, null uguali
		assertEquals(0,(int) c.compareTag(s1));
		assertTrue(c.compareTag(s1)==c.compareTag(s4)&&c.compareTag(s2)>c.compareTag(s1)&&c.compareTag(s2)>c.compareTag(s3) );
		assertEquals(150,(int) c.compareTag(s));
	}

	@Test
	public void testToString() {
		assertEquals("0 - Ariston",c.toString());
	}

	@Test
	public void testSetNome() {
		c.setNome("prova");
		assertEquals("prova",c.getNome());
	}

	@Test
	public void testSetIndirizzo() {
		c.setIndirizzo("Via serra");
		assertEquals("Via serra",c.getIndirizzo());
	}

	@Test
	public void testSetCitta() {
		c.setCitta("Vado");
		assertEquals("Vado",c.getCitta());
	}
	
	@Test
	public void testModifyCinema(){
		HashMap<String, Spettacolo> map = new HashMap<String, Spettacolo>();
		Sala sala = new Sala("cinema0", "sala1", "011111", 6, 3, 2);
		Spettacolo s1 = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30","0",
				"mov", sala);
		map.put(s1.getIdSpettacolo(), s1);
		assertTrue(c.addSala(sala));
		Sala sala1 = new Sala("cinema0", "sala2", "011111", 6, 3, 2);
		assertTrue(c.addSala(sala1));
		assertTrue(c.addSpettacoli(map));
		// Inserisco nuovamente gli spettacoli precedenti 
		assertFalse(c.addSpettacoli(map));
		// Stessa procedura ma inserendo uno spettacolo con stesso id ma campi diversi
		map.get(s1.getIdSpettacolo()).setEndingTime("wronggg");
		assertFalse(c.addSpettacoli(map));
		map = new HashMap<String, Spettacolo>();
		// Inserisco uno spettacolo mappato su una sala inesistente
		Sala sala2 = new Sala("cinema0", "sala5", "011111", 6, 3, 2);
		Spettacolo s3 = new Spettacolo3D("spett0", "02/12/1999", "21:30", "23:30","0",
				"mav", sala2);
		map.put(s3.getIdSpettacolo(), s3);
		assertFalse(c.addSpettacoli(map));
	}

}
