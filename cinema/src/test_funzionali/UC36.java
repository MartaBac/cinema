package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import cinema.Spettacolo2D;

public class UC36 {
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
		cassiere.setStatus(true);
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
		admin.setLoggedIn(true);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
		s.addNewUser(manager);
		s.addNewUser(cassiere);
		ArrayList<Seat> free = new ArrayList<Seat>();
		free = spettacolo.getFreeSeat();
		pren.add(free.get(1));
		pren.add(free.get(2));
		spettacolo.prenote(pren, cr.getNickname());
		
	}

	// Scenario principale UC36: Il sistema memorizza le nuove informazioni del cinema
	@Test
	public void UC36_scenarioPrincipale() {
		Cinema c1 = new Cinema( "0","Ariston", "Lucca", "Via Puggia 8");
		// 1 - L'attore richiede di modificare la scheda del cinema
		// 2 - Il sistema restituisce la scheda modificabile del cinema
		// 3 - L'attore immette tutte le modifiche e conferma il salvataggio
		// 4 - Il sistema salva le modifiche alla scheda
		assertTrue(s.modifyCinema(c1));
		// 5 - Torna al caso d'uso Visualizzazione scheda cinema

	}
	
	// Scenario alternativo 3a: L'attore annulla le modifiche
	// Questo scenario porta allo scenario UC6:Visualizzazione scheda cinema
	@Test
	public void UC36_scenarioAlternativo_3A() {
		// 1 - L'attore richiede di modificare la scheda del cinema
		// 2 - Il sistema restituisce la scheda modificabile del cinema
		// 3a - L'attore annulla
		// Torna al caso d'uso Visualizzazione scheda cinema
	}
	

}
