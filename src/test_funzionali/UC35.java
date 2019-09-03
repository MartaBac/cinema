package test_funzionali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cassiere;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.ManagerCinema;
import cinema.Sistema;

public class UC35 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	static Cinema c;
	static Cassiere cassiere;
	static ManagerCinema manager;

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
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		s = new Sistema(admin);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
		s.addNewUser(manager);
		s.addNewUser(cassiere);	
	}

	// Scenario principale UC35: Il sistema restituisce le informazioni del dipendente
	@Test
	public void UC35_scenarioPrincipale() {
		// 1 - L'attore richiede la visualizzazione della scheda di un dipendente
		// 2 - Il sistema restituisce la scheda
		assertTrue(s.printEmployeeProfile(cassiere.getNickname()));
		// 3 - L'attore chiude la scheda
		// 4 - Torna al caso Visualizzazione elenco dipendenti
	}
	
	// Scenario alternativo 3a: L'attore chiede di cambiare lo stato del dipendente
	// Questo scenario porta allo scenario UC31: Cambio stato dipendente
	@Test
	public void UC35_scenarioAlternativo_3A() {
		// 1 - L'attore richiede la visualizzazione della scheda di un dipendente
		// 2 - Il sistema restituisce la scheda
		assertTrue(s.printEmployeeProfile(cassiere.getNickname()));	
		// 3a - L'attore chiede di cambiare lo stato del dipendente
		// Va al caso UC31 Cambio stato dipendente
	}
	

}
