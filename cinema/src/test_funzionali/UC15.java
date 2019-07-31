package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.Film;
import cinema.Sala;
import cinema.Seat;
import cinema.Sistema;
import cinema.Spettacolo;
import cinema.Spettacolo2D;

public class UC15 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	static Sala sala;
	static Spettacolo2D spettacolo;
	static Cinema c;
	static Film f;

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
		cr = new ClienteRegistrato("user8","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		sala= new Sala("0", "salaBLU", "011111", 6, 3, 2);
		spettacolo = new Spettacolo2D("spett1", "03/12/1999", "20:30", "22:30","0", true,"film0",
				sala);
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo inseguono.",
				"Spagna", act,genre, tag,prod,dir);
		
		sala.addSpettacolo(spettacolo);
		c.addSala(sala);
	
	
	} 
	@Before
	public void setUp() throws Exception {

		s = new Sistema(admin);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
	}

	// Scenario principale UC15: L'attore effettua una prenotazione
	@Test
	public void UC15_scenarioPrincipale() {
		ArrayList<Seat> pren = new ArrayList<Seat>();
		ArrayList<Seat> free = new ArrayList<Seat>();
		// 1 - L'attore richiede al sistema di effettuare una prenotazione
		// 2 - Il sistema restituisce l'elenco di posti disponibili per quello spettacolo
		free = spettacolo.getFreeSeat();
		// 3 - L'attore sceglie quali posti prenotare
		pren.add(free.get(1));
		pren.add(free.get(2));
		// 4 - Il sistema chiede conferma
		// 5 - L'attore conferma
		// 6 - Il sistema effettua la prenotazione
		assertTrue(spettacolo.prenote(pren, cr.getNickname()));
	}
	
	// Scenario alternativo 3a: L'attore richiede al sistema di visualizzare l'intera
	// programmazione di quel film in quel cinema.
	// Questo scenario riporta ad una nuova selezione di spettacolo per poi ritornare
	// al punto due dello scenario principale
		@Test
		public void UC15_scenarioAlternativo_3A() {
			ArrayList<Seat> free = new ArrayList<Seat>();
			// 1 - L'attore richiede al sistema di effettuare una prenotazione
			// 2 - Il sistema fornisce la lista di posti liberi per quello spettacolo
			free = spettacolo.getFreeSeat();
			// 3a - L'attore chiede di vedere la programmazione del film in quel cinema		
			// Il sistema restituisce il calendario di programmazione del film
			HashMap<String, Spettacolo> h = c.searchSpettacolo(f.getIdFilm());
			assertNotNull(h);
			assertEquals(1,h.size()); // Vi è un solo spettacolo per il cinema/film
			// L'attore seleziona uno spettacolo
			// Rimanda al punto 2 dello scenario principale
		}

	// Scenario alternativo 5a: La richiesta viene annullata
	@Test
	public void UC15_scenarioAlternativo_5A() {
		ArrayList<Seat> pren = new ArrayList<Seat>();
		ArrayList<Seat> free = new ArrayList<Seat>();
		// 1 - L'attore richiede al sistema di effettuare una prenotazione
		// 2 - Il sistema restituisce l'elenco di posti disponibili per quello spettacolo
		free = spettacolo.getFreeSeat();
		// 3 - L'attore sceglie quali posti prenotare
		pren.add(free.get(1));
		pren.add(free.get(2));
		// 4 - Il sistema chiede conferma
		// 5a - L'attore annulla
		// Il caso d'uso termina
	}
}