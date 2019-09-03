package test_funzionali;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Film;
import cinema.Sistema;

public class UC7 {
	static Sistema sistema;
	static Admin a; 
	static Film f, f1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] act,genre,tag,prod,dir;
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
	
	// Scenario principale: il sistema restituisce un elenco di film che corrispondono
	// ai criteri di ricerca.
	@Test
	public void UC7_scenarioPrincipale() {
		String[] tag = new String[]{"cerca","film","fuga"};
		LinkedHashMap<String, Film> hm;
		// 1 - L'attore richiede di cercare un Film della catena
		// 2 - Il sistema richiede i paramatri per la ricerca
		// 3 - L'attore inserisce i dati richiesti
		// 4 - Il sis restituisce un elenco di film corrispondenti ai criteri di ricerca
		hm = sistema.searchMovie(tag);
		assertFalse(hm.isEmpty()); 
		// 6 - L'attore chiude la lista
	}
	
	// Scenario alternativo 3a: L'attore annulla l'operazione
	// Fa eseguire l'use case UC3: visualizzazione elenco film del circuito
		@Test
		public void UC7_scenarioAlternativo_3A() {
			// 1 - L'attore richiede di cercare un Film della catena
			// 2 - Il sistema richiede i paramatri per la ricerca
			// 3a - L'attore annulla l'operazione		
			// Torna all'UC3: visualizzazione elenco film del circuito
		}

	// Scenario alternativo 6a: L'attore richiede una nuova ricerca.
	// Questo scenario fa tornare al punto 2 dello scenario principale
		@Test
		public void UC7_scenarioAlternativo_6A() {
			String[] tag = new String[]{"cerca","film","fuga"};
			LinkedHashMap<String, Film> hm;
			// 1 - L'attore richiede di cercare un Film della catena
			// 2 - Il sistema richiede i paramatri per la ricerca
			// 3 - L'attore inserisce i dati richiesti
			// 4 - Il sis restituisce un elenco di film corrispondenti ai criteri di ricerca
			hm = sistema.searchMovie(tag);
			assertFalse(hm.isEmpty()); 
			// 6a - L'attore richiede di effettuare un'altra ricerca
			// Torna ad UC7 - punto 2 scenario principale
		}		
		
	// Scenario alternativo 6b: L'attore seleziona un film.
	// Questo scenario fa andare all'UC8 : visualizzazione scheda film
		@Test
		public void UC7_scenarioAlternativo_6B() {
			String[] tag = new String[]{"cerca","film","fuga"};
			LinkedHashMap<String, Film> hm;
			// 1 - L'attore richiede di cercare un Film della catena
			// 2 - Il sistema richiede i paramatri per la ricerca
			// 3 - L'attore inserisce i dati richiesti
			// 4 - Il sis restituisce un elenco di film corrispondenti ai criteri di ricerca
			hm = sistema.searchMovie(tag);
			assertFalse(hm.isEmpty()); 
			// 6b - L'attore seleziona un film da visualizzare
			// Va ad UC8 -  visualizzazione scheda film
		}	
}
