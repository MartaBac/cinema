package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.Film;
import cinema.Sala;
import cinema.Seat;
import cinema.Sistema;
import cinema.Spettacolo2D;

public class UC17 {
	static Admin admin;
	static ClienteRegistrato crUC17;
	static Sistema system;
	static Sala sala;
	static Spettacolo2D spettacolo;
	static Cinema c;
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
		system = new Sistema(admin);
		crUC17 = new ClienteRegistrato("userUC17","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		c = new Cinema( "2","Ariston", "Sanremo", "Via Puggia 2");
		sala= new Sala("2", "salaGIALLA", "011111", 6, 3, 2);
		spettacolo = new Spettacolo2D("spett1", "03/12/1999", "20:30", "22:30","2", true,"film0",
				sala);
		f = new Film("film1", "la fuga", "01/03/2013", "Un tizio fugge e altri lo inseguono.",
				"Spagna", act,genre, tag,prod,dir);
		sala.addSpettacolo(spettacolo);
		c.addSala(sala);	
		admin.setLoggedIn(true);
		system.addNewCinema(c, admin);
		system.addNewUser(crUC17);
		ArrayList<Seat> free = new ArrayList<Seat>();
		free = spettacolo.getFreeSeat();
		pren.add(free.get(1));
		pren.add(free.get(2));
		assertTrue(spettacolo.prenote(pren, crUC17.getNickname()));
	}

	// Scenario principale UC17: L'attore visualizza l'elenco delle sue prenotazioni
	@Test
	public void UC17_scenarioPrincipale() {
		ArrayList<String> t;
		// 1 - L'attore richiede di visualizzare le proprie prenotazioni
		// 2 - Il sistema restituisce l'elenco di prenotazioni dell'attore
		t = system.searchPrenotazioni(crUC17.getNickname());
		assertEquals(2,t.size());
		// 3 - L'attore chiude la scheda
	}

	// Scenario alternativo 3a:L'attore chiede di visualizzare una specifica prenotazione
	// Questo scenario alternativa fa eseguire l'UC20 Annullare prenotazione,
	// di cui esiste già il testing funzionale.
	@Test
	public void UC17_scenarioAlternativo_3A() {
		ArrayList<String> t;
		// 1 - L'attore richiede di visualizzare le proprie prenotazioni
		// 2 - Il sistema restituisce l'elenco di prenotazioni dell'attore
		t = system.searchPrenotazioni(crUC17.getNickname());
		assertEquals(2,t.size());
		// 3a - L'attore richiede di visualizzare una prenotazione specifica
		// I - Il sistema restituisce le informazioni specifiche di quella prenotazione
		assertTrue(t.get(0).length()!=0);
		// II - L'attore torna indietro
		// III - Va al punto due dello scenario principale
		
		// IIa - L'attore richiede di annullare la prenotazione
		// Va al caso d'uso UC20 Annullare prenotazione		
		}
		
}
	