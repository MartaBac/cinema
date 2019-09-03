package test_funzionali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Film;
import cinema.Sistema;

public class UC40 {
	static Admin admin;
	static Sistema s;
	static String[] act,genre,tag,prod,dir ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		act = new String[] {"Fernando Moya", "Alicia Gonzales"};
		genre = new String[] {"Drama"};
		tag = new String[] {"fuga", "inseguimento","Gonzales"};
		prod= new String[]{"prod0", "prod1", "prod2"};
		dir = new String[]{"dir1","dir2"};
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);		
		s = new Sistema(admin);	
	}

	// Scenario principale UC40: Il sistema memorizza le nuove informazioni del film
	@Test
	public void UC40_scenarioPrincipale() {
		// 1 - L'attore richiede di inserire un nuovo film
		// 2 - Il sistema richiede i dati del nuovo film
		// 3 - L'attore immette tutte le informazioni e conferma le modifiche
		Film f1 = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri "
				+ "lo inseguono.", "Spagna", act,genre, tag,prod,dir);
		// 4 - Il sistema salva la nuova scheda film
		s.addNewFilm(f1, admin);
		// 5 - Il caso d'uso termina
		assertEquals(f1.toString(),s.searchMovieById(f1.getIdFilm()).toString());
	}
	
	// Scenario alternativo 3a: L'attore annulla l'operazione
	@Test
	public void UC40_scenarioAlternativo_3A() {
		// 1 - L'attore richiede di inserire un nuovo film
		// 2 - Il sistema richiede i dati del nuovo film
		// 3a - L'attore annulla
		// Il caso d'uso termina
	}
	
}
