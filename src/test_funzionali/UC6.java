package test_funzionali;

import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.Cinema;
import cinema.Sistema;

public class UC6 {
	static Sistema sistema;
	Admin a; 
	Cinema c,c1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	@Before
	public void setUp() throws Exception {
		HashMap<String, Cinema> cin = new HashMap<String, Cinema>();
		a = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		c1 = new Cinema( "1","Odeon", "Genova", "Via Como 2");
		cin.put(c.getIdCinema(), c);
		cin.put(c1.getIdCinema(), c1);
		sistema = new Sistema(a);
		sistema.setCinema(cin);
	}
	
	// Scenario principale: successo visualizzazione informazioni cinema.
	@Test
	public void UC6_scenarioPrincipale() {
		// 1 - L'attore richiede di visualizzare la scheda cinema
		// 2- Il sistema restituisce la scheda
		Cinema test = sistema.searchCinemaById(c1.getIdCinema());
		assertFalse(test.printAllInfo().isEmpty());
		// 3- L'attore chiude la scheda
	}
	
	// Scenario alternativo 3a: L'attore richiede di modificare la scheda cinema
	// Il testing di esso è presente nel test_funzionale di UC36
		@Test
		public void UC6_scenarioAlternativo_3A() {
			// 1 - L'attore richiede di visualizzare la scheda cinema
			// 2- Il sistema restituisce la scheda
			Cinema test = sistema.searchCinemaById(c1.getIdCinema());
			assertFalse(test.printAllInfo().isEmpty());
			// 3b - L'attore richiede di modificare la scheda cinema
			// Esecuzione UC36 - Modifica scheda cinema
		}	
		
	// Scenario alternativo 3b: L'attore richiede d effetuare la prenotazione di uno
	// spettacolo.
	// Il testing di esso è presente nel test_funzionale di UC15
		@Test
		public void UC6_scenarioAlternativo_3B() {
			// 1 - L'attore richiede di visualizzare la scheda cinema
			// 2- Il sistema restituisce la scheda
			Cinema test = sistema.searchCinemaById(c1.getIdCinema());
			assertFalse(test.printAllInfo().isEmpty());
			// 3b - L'attore richiede di effettuare la prenotazione di uno spettacolo
			// Esecuzione UC15 - Prenotazione
		}		
}

