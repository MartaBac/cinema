package test_funzionali;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.ClienteRegistrato;
import cinema.Sistema;

public class UC9 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		cr = new ClienteRegistrato("userG","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		s = new Sistema(admin);
		s.addNewUser(cr);
	}

	@Test
	public void UC9_scenarioPrincipale() {
		// 1 - L'attore richiede di effettuare l'autenticazione
		// 2 - Il sistema richiede i dati di accesso
		// 3 - L'attore inserisce i dati
		// 4 - Il sistema verifica la validità dei dati immessi
		// 5 - Il sistema autentica l'attore
		assertTrue(s.login(cr.getNickname(), cr.getPassword()));
	}

	// Scenario alternativo 4a: Il sistema non valida i dati.
	// Questo scenario fa andare al passo 2 dello scenario principale
	@Test
	public void UC9_scenarioAlternativo_4A() {
		// 1 - L'attore richiede di effettuare l'autenticazione
		// 2 - Il sistema richiede i dati di accesso
		// 3 - L'attore inserisce i dati
		// 4a - Il sistema non valida i dati
		assertFalse(s.login("wrong","false"));
		// Il sistema comunica l'errore all'utente
		// Torna al passo 2 dello scenario principale
		}
	
	@AfterClass
	public static void tearDownAfterClass(){
		s = null;
	}
}
