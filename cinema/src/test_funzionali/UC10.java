package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.ClienteRegistrato;
import cinema.Sistema;

public class UC10 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		cr = new ClienteRegistrato("user3","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");	
		s = new Sistema(admin);
		s.addNewUser(cr);
	}
	
	@Before
	public void setUp() throws Exception {
		s.login(cr.getNickname(), cr.getPassword());
	}

	// Scenario principale UC10: L'utente viene deautenticato
	@Test
	public void UC10_scenarioPrincipale() {
		// 1 - L'attore comunica di volersi deautenticare
		// 2 - Il sistema richiede un comando di conferma dell'operazione
		// 3 - L'attore conferma
		// 4 - Il sistema deautentica l'attore
		assertTrue(s.logout(cr.getNickname()));
	}

	// Scenario alternativo 3a: L'attore non conferma
	@Test
	public void UC10_scenarioAlternativo_3A() {
		// 1 - L'attore comunica di volersi deautenticare
		// 2 - Il sistema richiede un comando di conferma dell'operazione
		// 3a - L'attore non conferma
		// Il caso d'uso termina
		}
}