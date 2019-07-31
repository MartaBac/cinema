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
import cinema.Utente;

public class UC31 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	static Sala sala;
	static Spettacolo2D spettacolo;
	static Cinema c;
	static Cassiere cassiere;
	static ManagerCinema manager, manager2;
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
		manager2 = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","1");
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		sala= new Sala("0", "salaBLU", "011111", 6, 3, 2);
		spettacolo = new Spettacolo2D("spett1", "03/12/1999", "20:30", "22:30","0", 
				true,"film0", sala);
		sala.addSpettacolo(spettacolo);
		c.addSala(sala);
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo "
				+ "inseguono.", "Spagna", act,genre, tag,prod,dir);
		
		s = new Sistema(admin);
		admin.setLoggedIn(true);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
		s.addNewUser(manager);
		s.addNewUser(manager2);
		s.addNewUser(cassiere);
		ArrayList<Seat> free = new ArrayList<Seat>();
		free = spettacolo.getFreeSeat();
		pren.add(free.get(1));
		pren.add(free.get(2));
		spettacolo.prenote(pren, cr.getNickname());
	}

	// Scenario principale UC31: Lo stato del dipendente è cambiato
	@Test
	public void UC31_scenarioPrincipale() {
		manager.setLoggedIn(true);
		admin.setLoggedIn(true);
		// 1 - L'attore richiede di cambiare lo stato dell'account di un dipendente
		// 2 - Il sistema verifica i permessi dell'attore
		// 3 - Il sistema inverte lo stato dell'account
		
		// Successo - Admin cambia stato Manager
		assertTrue(s.changeUserStatus(admin, manager));
		
		// Successo - Manager cambia stato cassiere
		assertTrue(s.changeUserStatus(manager, cassiere));
				
		// 4 - Torna al caso d'uso Visualizzazione scheda dipendente
	}
	
	// Scenario alternativo 2a: Il sistema individua permessi non adeguati
		@Test
		public void UC9_scenarioAlternativo_2A() {
			manager.setLoggedIn(true);
			admin.setLoggedIn(true);
			// 1 - L'attore richiede di cambiare lo stato dell'account di un dipendente
			// 2a - Il sistema individua permessi non adeguati
			
			assertFalse("Manager tenta di cambiare stato admin", 
					s.changeUserStatus(manager, admin));	
			
			assertFalse("Admin tenta di cambiare stato a cliente registrato", 
					s.changeUserStatus(admin, cr));
			
			assertFalse("Admin tenta di cambiare il proprio status", 
					s.changeUserStatus(admin, admin));
			
			assertFalse("Manager tenta di cambiare status di manager",
					s.changeUserStatus(manager, manager2));
			
			assertFalse("Cassiere tenta di cambiare status cassiere", 
					s.changeUserStatus(cassiere, cassiere));
			
			// Il caso d'uso termina
			
		}
}	
