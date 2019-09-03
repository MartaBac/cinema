package test_funzionali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.Cassiere;
import cinema.ClienteRegistrato;
import cinema.ManagerCinema;
import cinema.Sistema;

public class UC31 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	static Cassiere cassiere;
	static ManagerCinema manager, manager2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		cr = new ClienteRegistrato("user0","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		cassiere = new Cassiere("cassiere0","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","0");
		manager = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","0");
		manager2 = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","1");	
		s = new Sistema(admin);
		s.addNewUser(cr);
		s.addNewUser(manager);
		s.addNewUser(manager2);
		s.addNewUser(cassiere);
	}

	// Scenario principale UC31: Lo stato del dipendente è cambiato
	@Test
	public void UC31_scenarioPrincipale() {
		manager.setLoggedIn(true);
		admin.setLoggedIn(true);
		// 1 - L'attore richiede di cambiare lo stato dell'account di un dipendente
		// 2 - Il sistema verifica i permessi dell'attore
		// 3 - Il sistema inverte lo stato dell'account	
		assertTrue("Admin cambia stato Manager",s.changeUserStatus(admin, manager));	
		assertTrue("Manager cambia stato cassiere", 
				s.changeUserStatus(manager, cassiere));			
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
		// Torna al caso d'uso Visualizzazione scheda dipendente	
	}
}	
