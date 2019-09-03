package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
import cinema.Spettacolo2D;

public class UC20 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
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
		cr = new ClienteRegistrato("userGin","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		sala= new Sala("0", "salaBLU", "011111", 6, 3, 2);
		spettacolo = new Spettacolo2D("spett1", "03/12/1999", "20:30", "22:30","0", true,"film0",
				sala);
		sala.addSpettacolo(spettacolo);
		c.addSala(sala);
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo inseguono.",
				"Spagna", act,genre, tag,prod,dir);	
		s = new Sistema(admin);
		admin.setLoggedIn(true);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
		ArrayList<Seat> free = new ArrayList<Seat>();
		free = spettacolo.getFreeSeat();
		pren.add(free.get(1));
		pren.add(free.get(2));
	}
	
	@Before
	public void setUp() throws Exception {
		spettacolo.prenote(pren, cr.getNickname());
	}

	// Scenario principale UC20: L'attore ha annullato una sua prenotazione
	@Test
	public void UC20_scenarioPrincipale() {
		int i = spettacolo.getPrenotazioni().size();
		// 1 - L'attore richiede al sistema di annullare una prenotazione
		// 2 - Il sistema chiede conferma
		// 3 - L'attore conferma
		// 4 - Il sistema annulla la prenotazione
		assertTrue(spettacolo.deletePrenotazione("row1column1"));
		assertEquals(i,spettacolo.getPrenotazioni().size()+1);
	}
	
	// Scenario alternativo 3a:L'attore annulla l'eliminazione
		@Test
		public void UC20_scenarioAlternativo_3A() {
			// 1 - L'attore richiede al sistema di annullare una prenotazione
			// 2 - Il sistema chiede conferma
			// 3a - L'attore non conferma
			// L'use case termina
			}
}
