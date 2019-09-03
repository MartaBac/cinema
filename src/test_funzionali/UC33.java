package test_funzionali;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cassiere;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.Film;
import cinema.ManagerCinema;
import cinema.Sala;
import cinema.Seat;
import cinema.Sistema;
import cinema.Spettacolo;
import cinema.Spettacolo2D;

public class UC33 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	static Sala sala;
	static Spettacolo2D spettacolo;
	static Cinema c;
	static Cassiere cassiere;
	static ManagerCinema manager;
	static Film f;
	static ArrayList<Seat> pren = new ArrayList<Seat>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] act,genre,tag,prod,dir ;
		act = new String[] {"Fernando Moya", "Alicia Gonzales"};
		genre = new String[] {"Drama"};
		tag = new String[] {"fuga", "inseguimento","Gonzales"};
		prod= new String[]{"prod0", "prod1", "prod2"};
		dir = new String[]{"dir1","dir2"};
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		cr = new ClienteRegistrato("user0","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		cassiere = new Cassiere("cassiere0","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","0");
		manager = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","0");
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		sala= new Sala("0", "salaBLU", "011111", 6, 3, 2);
		spettacolo = new Spettacolo2D("spett1", "03/12/1999", "20:30", "22:30","0", true,"film0",
				sala);
		sala.addSpettacolo(spettacolo);
		c.addSala(sala);
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo inseguono.",
				"Spagna", act,genre, tag,prod,dir);
		
		s = new Sistema(admin);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
		s.addNewUser(manager);
		s.addNewUser(cassiere);	
	}

	// Scenario principale UC33: Memorizzazione modifiche elenco di spettacoli
	@Test
	public void UC33_scenarioPrincipale() {		
		HashMap<String, Spettacolo> ss = new HashMap<String, Spettacolo>();
		// 1 - L'attore richiede di gestire il calendario di programmazione
		// 2 - Il sistema fornisce la lista di cinema modificabili dall'attore
		s.getMyCinema(admin);
		// 3 - L'attore seleziona un cinema
		// 4 - Il sistema restituisce l'elenco di spettacoli del cinema
		c.printSpettacoli();
		// 5 - L'attore richiede di inserire un nuovo spettacolo
		// 6 - Il sistema richiede i dati dello spettacolo
		// 7 - L'attore inserisce le informazioni
		Spettacolo2D spett = new Spettacolo2D("spettprova", "03/12/1699", "20:30", 
				"22:30","0", true,"film0",sala);
		int i = c.searchSpettacolo(spett.getIdMovie()).size();
		ss.put(spett.getIdSpettacolo(), spett);
		c.addSpettacoli(ss);	
		// 8 - Il sistema aggiunge lo spettacolo
		assertEquals(i+1,c.searchSpettacolo(spett.getIdMovie()).size());
		assertTrue(c.searchSpettacolo(spett.getIdMovie()).get(spett.getIdSpettacolo())
				!=null);
		// 9 - L'attore chiude la gestione
		// 10 - Il caso d'uso termina
	}
	
	// Scenario alternativo 3a: Il sistema individua un solo cinema modificabile
	@Test
	public void UC33_scenarioAlternativo_2A() {
		// 1 - L'attore richiede di gestire il calendario di programmazione
		// 2a - Il sistema individua un solo cinema modificabile
		s.getMyCinema(manager);
		// Vai al punto 4 dello scenario principale
	}
	
	// Scenario alternativo 9a: L'attore richiede di continuare la gestione
	@Test
	public void UC33_scenarioAlternativo_9A() {
		HashMap<String, Spettacolo> ss = new HashMap<String, Spettacolo>();
		// 1 - L'attore richiede di gestire il calendario di programmazione
		// 2 - Il sistema fornisce la lista di cinema modificabili dall'attore
		s.getMyCinema(admin);
		// 3 - L'attore seleziona un cinema
		// 4 - Il sistema restituisce l'elenco di spettacoli del cinema
		c.printSpettacoli();
		// 5 - L'attore richiede di inserire un nuovo spettacolo
		// 6 - Il sistema richiede i dati dello spettacolo
		// 7 - L'attore inserisce le informazioni
		Spettacolo2D spett = new Spettacolo2D("spettprova2", "03/12/1679", "19:30", 
				"22:30","0", true,"film0",sala);
		int i = c.searchSpettacolo(spett.getIdMovie()).size();
		ss.put(spett.getIdSpettacolo(), spett);
		c.addSpettacoli(ss);	
		// 8 - Il sistema aggiunge lo spettacolo
		assertEquals(i+1,c.searchSpettacolo(spett.getIdMovie()).size());
		assertTrue(c.searchSpettacolo(spett.getIdMovie()).get(spett.getIdSpettacolo())
				!=null);
		// 9a - L'attore richiede di continuare la gestione
		// Vai al punto 2 dello scenario principale
	}
	
}
