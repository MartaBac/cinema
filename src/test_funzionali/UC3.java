package test_funzionali;


import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.Film;
import cinema.Sistema;


public class UC3 {
	static Sistema sistema;
	static Admin a; 
	static Film f, f1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] act,genre,tag,prod,dir;
		a = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		act = new String[] {"Fernando Moya", "Alicia Gonzales"};
		genre = new String[] {"Drama"};
		tag = new String[] {"fuga", "inseguimento","Gonzales"};
		prod= new String[]{"prod0", "prod1", "prod2"};
		dir = new String[]{"dir1","dir2"};
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo inseguono.",
				"Spagna", act,genre, tag,prod,dir);
		f1 = new Film("film1", "perdono", "01/03/2015", "Una squadra perde sempre",
				"Spagna", act,genre, tag,prod,dir);
		sistema = new Sistema(a);

	}
	
	@Before
	public void setUp() throws Exception {
		HashMap<String, Film> cin = new HashMap<String, Film>();
		cin.put(f.getIdFilm(), f);
		cin.put(f1.getIdFilm(), f1);
		sistema = new Sistema(a);
		sistema.setMovie(cin);
	}
	
	// Scenario principale: successo visualizzazione elenco film del circuito.
	@Test
	public void UC3_scenarioPrincipale() {
		// 1 - L'attore richiede di visualizzare l'elenco film
		// 2- Il sistema restituisce l'elenco di film nel circuito
		assertTrue(sistema.printMovieList());
		// 3- Il sistema richiede all'attore come intende proseguire
		// 4- L'attore chiude la lista
	}
	
	// Scenario alternativo 2a: Il sistema non trova alcun film
		@Test
		public void UC3_scenarioAlternativo_2A() {
			// Per eseguirlo setto la lista film del sistema ad una lista vuota
			sistema.setMovie(new HashMap<String, Film>());
			// 2a - il sistema trova un elenco film vuoto
			// Il sistema comunica all'attore l'errore
			assertFalse(sistema.printMovieList());
		}
		
	// Scenario alternativo 4a: L'attore richiede di fare una ricerca
	// Questo scenario fa eseguire l'use case UC7: Ricerca Film
	// Il testing di esso è presente nel test_funzionale di UC7
		@Test
		public void UC3_scenarioAlternativo_4A() {
			// 1 - L'attore richiede di visualizzare l'elenco film
			// 2- Il sistema restituisce l'elenco di film nel circuito
			assertTrue(sistema.printMovieList());
			// 3- Il sistema richiede all'attore come intende proseguire
			// 4a- L'attore richiede di effettuare una ricerca			
			// Inizio UC6
		}

	// Scenario alternativo 4b: L'attore richiede di visualizzare una scheda film
	// Questo scenario fa eseguire l'use case UC8: Visualizzazione Scheda Film
	// Il testing di esso è presente nel test_funzionale di UC8
		@Test
		public void UC3_scenarioAlternativo_4B() {
			// 1 - L'attore richiede di visualizzare l'elenco film
			// 2- Il sistema restituisce l'elenco di film nel circuito
			assertTrue(sistema.printMovieList());
			// 3- Il sistema richiede all'attore come intende proseguire
			// 4a- L'attore richiede di visualizzare una scheda film		
			// Inizio UC8
		}		
}
