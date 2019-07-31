package test_funzionali;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import cinema.Admin;
import cinema.ClienteRegistrato;
import cinema.Sistema;

public class UC12 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		cr = new ClienteRegistrato("user9","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		s = new Sistema(admin);
		s.addNewUser(cr);
		
	}


	// Scenario principale UC12: L'attore visualizza il proprio profilo
	@Test
	public void UC12_scenarioPrincipale() {
		// 1 - L'attore richiede al sistema la visualizzazione del proprio profilo
		// 2 - Il sistema restituisce le informazioni del profilo
		assertNotNull(cr.showProfile());
		// 3 - L'attore chiude la scheda
	}

	// Scenario alternativo 3a: L'attore richiede di visualizzare le proprie prenotazioni
	//	Questo porta all'esecuzione dell' UC17: Visualizzazione prenotazioni
	@Test
	public void UC12_scenarioAlternativo_3A() {
		// 1 - L'attore richiede al sistema la visualizzazione del proprio profilo
		// 2 - Il sistema restituisce le informazioni del profilo
		assertNotNull(cr.showProfile());
		// 3a - L'attore chede di visualizzare le proprie prenotazioni
		// Va al caso d'uso UC17: Visualizzazone prenotazioni
	}
	
}
