package test_funzionali;

import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.Film;
import cinema.Sistema;

public class UC8 {
	static Sistema sistema;
	static Admin a; 
	static Film f,f1;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] act,prod,genre,tag,dir;
		HashMap<String, Film> cin = new HashMap<String, Film>();
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
		cin.put(f.getIdFilm(), f);
		cin.put(f1.getIdFilm(), f1);
		sistema = new Sistema(a);
		sistema.setMovie(cin);
	}
	
	// Scenario principale: successo visualizzazione informazioni film.
	@Test
	public void UC8_scenarioPrincipale() {
		// 1 - L'attore richiede di visualizzare la scheda film
		// 2- Il sistema restituisce la scheda
		Film test = sistema.searchMovieById(f1.getIdFilm());
		assertFalse(test.printAllInfo().isEmpty());
		// 3- L'attore chiude la scheda
	}
		
	// Scenario alternativo 3b: L'attore richiede d effetuare la prenotazione di uno
	// spettacolo.
	// Il testing di esso è presente nel test_funzionale di UC15
		@Test
		public void UC8_scenarioAlternativo_3B() {
			// 1 - L'attore richiede di visualizzare la scheda film
			// 2- Il sistema restituisce la scheda
			Film test = sistema.searchMovieById(f1.getIdFilm());
			assertFalse(test.printAllInfo().isEmpty());
			// 3b - L'attore richiede di effettuare la prenotazione di uno spettacolo
			// Esecuzione UC15 - Prenotazione
		}	

}