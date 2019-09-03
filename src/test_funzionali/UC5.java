package test_funzionali;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cinema;
import cinema.Sistema;

public class UC5 {
	static Sistema sistema;
	static Admin a; 
	String[] act,genre,tag,prod,dir;
	static HashMap<String, Cinema> cin;
	static Cinema c,c1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 cin = new HashMap<String, Cinema>();
		a = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		c1 = new Cinema( "1","Odeon", "Genova", "Via Como 2");
		cin.put(c.getIdCinema(), c);
		cin.put(c1.getIdCinema(), c1);
		sistema = new Sistema(a);
		sistema.setCinema(cin);
	}
	
	// Scenario principale: il sistema restituisce un elenco di cinema che corrispondono
	// ai criteri di ricerca.
	@Test
	public void UC5_scenarioPrincipale() {
		String[] tag = new String[]{"cerca","cinema","Ariston"};
		LinkedHashMap<String, Cinema> hm;
		// 1 - L'attore richiede di cercare un Cinema della catena
		// 2 - Il sistema richiede i paramatri per la ricerca
		// 3 - L'attore inserisce i dati richiesti
		// 4 - Il sistema effettua la ricerca utilizzando i parametri inseriti
		// 5 - Il sistema restituisce i risultati della ricerca
		hm = sistema.searchCinema(tag);
		assertFalse(hm.isEmpty());
		// 6 - L'attore chiude la lista
	}
	
	// Scenario alternativo 3a: L'attore annulla l'operazione
	// Fa eseguire l'use case UC2: visualizzazione elenco cinema del circuito
		@Test
		public void UC5_scenarioAlternativo_3A() {
			// 1 - L'attore richiede di cercare un Cinema della catena
			// 2 - Il sistema richiede i paramatri per la ricerca
			// 3a - L'attore annulla l'operazione		
			// Torna all'UC2: visualizzazione elenco cinema del circuito
		}

	// Scenario alternativo 6a: L'attore richiede una nuova ricerca.
	// Questo scenario fa tornare al punto 2 dello scenario principale
		@Test
		public void UC5_scenarioAlternativo_6A() {
			String[] tag = new String[]{"cerca","cinema","Ariston"};
			LinkedHashMap<String, Cinema> hm;
			// 1 - L'attore richiede di cercare un Cinema della catena
			// 2 - Il sistema richiede i paramatri per la ricerca
			// 3 - L'attore inserisce i dati richiesti
			// 4 - Il sistema effettua la ricerca utilizzando i parametri inseriti
			// 5 - Il sistema restituisce i risultati della ricerca
			hm = sistema.searchCinema(tag);
			assertFalse(hm.isEmpty());
			// 6a - L'attore richiede di effettuare un'altra ricerca
			// Torna ad UC5 - punto 2 scenario principale
		}		
		
	// Scenario alternativo 6b: L'attore seleziona un cinema.
	// Questo scenario fa andare all'UC6 : visualizzazione scheda cinema
		@Test
		public void UC5_scenarioAlternativo_6B() {
			String[] tag = new String[]{"cerca","cinema","Ariston"};
			LinkedHashMap<String, Cinema> hm;
			// 1 - L'attore richiede di cercare un Cinema della catena
			// 2 - Il sistema richiede i paramatri per la ricerca
			// 3 - L'attore inserisce i dati richiesti
			// 4 - Il sistema effettua la ricerca utilizzando i parametri inseriti
			// 5 - Il sistema restituisce i risultati della ricerca
			hm = sistema.searchCinema(tag);
			assertFalse(hm.isEmpty());
			// 6b - L'attore seleziona un cinema da visualizzare
			// Va ad UC6 -  visualizzazione scheda cinema
		}	
		@AfterClass
		public static void tearDownAfterClass(){
			sistema = null;
		}
}

