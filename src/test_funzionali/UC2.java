package test_funzionali;


import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cinema;
import cinema.Sistema;


public class UC2 {
	static Sistema sistema;
	static Admin a; 
	static Cinema c,c1;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		a = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		c1 = new Cinema( "1","Odeon", "Genova", "Via Como 2");
	}
	
	@Before
	public void setUp() throws Exception {
		HashMap<String, Cinema> cin = new HashMap<String, Cinema>();
		cin.put(c.getIdCinema(), c);
		cin.put(c1.getIdCinema(), c1);
		sistema = new Sistema(a);
		sistema.setCinema(cin);
	}
	
	// Scenario principale: successo visualizzazione elenco cinema del circuito.
	@Test
	public void UC2_scenarioPrincipale() {
		// 1 - L'attore richiede di visualizzare l'elenco cinema
		// 2- Il sistema restituisce l'elenco di cinema nel circuito
		assertTrue(sistema.printCinemaList());
		// 3- Il sistema richiede all'attore come intende proseguire
		// 4- L'attore chiude la lista
	}
	
	// Scenario alternativo 2a: Il sistema non trova alcun cinema
		@Test
		public void UC2_scenarioAlternativo_2A() {
			// Per eseguirlo setto la lista cinema del sistema ad una lista vuota
			sistema.setCinema(new HashMap<String, Cinema>());
			// 2a - il sistema trova un elenco cinema vuoto
			// Il sistema comunica all'attore l'errore
			assertFalse(sistema.printCinemaList());
		}
		
	// Scenario alternativo 4a: L'attore richiede di fare una ricerca
	// Questo scenario fa eseguire l'use case UC5: Ricerca Cinema
	// Il testing di esso è presente nel test_funzionale di UC5
		@Test
		public void UC2_scenarioAlternativo_4A() {
			// 1 - L'attore richiede di visualizzare l'elenco cinema
			// 2- Il sistema restituisce l'elenco di cinema nel circuito
			assertTrue(sistema.printCinemaList());
			// 3- Il sistema richiede all'attore come intende proseguire
			// 4a- L'attore richiede di effettuare una ricerca			
			// Inizio UC5
		}

	// Scenario alternativo 4b: L'attore richiede di visualizzare una scheda cinema
	// Questo scenario fa eseguire l'use case UC6: Visualizzazione Scheda Cinema
	// Il testing di esso è presente nel test_funzionale di UC6
		@Test
		public void UC2_scenarioAlternativo_4B() {
			// 1 - L'attore richiede di visualizzare l'elenco cinema
			// 2- Il sistema restituisce l'elenco di cinema nel circuito
			assertTrue(sistema.printCinemaList());
			// 3- Il sistema richiede all'attore come intende proseguire
			// 4a- L'attore richiede di visualizzare una scheda cinema		
			// Inizio UC6
		}				
}
