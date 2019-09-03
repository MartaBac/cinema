package test_funzionali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cassiere;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.ManagerCinema;
import cinema.Sala;
import cinema.Sistema;
import cinema.Spettacolo2D;

public class UC28 {
	static Admin admin;
	static ClienteRegistrato cr;
	static Sistema s;
	static Sala sala;
	static Spettacolo2D spettacolo;
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
		cassiere.setStatus(true);
		manager = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","0");
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		
		s = new Sistema(admin);
		admin.setLoggedIn(true);
		s.addNewCinema(c, admin);
		s.addNewUser(cr);
		s.addNewUser(manager);
		s.addNewUser(cassiere);
		
	}

	// Scenario principale UC28: L'attore visualizza l'elenco dei suoi dipendenti
	@Test
	public void UC28_scenarioPrincipale() {
		// 1 - L'attore richiede la visualizzazione dipendenti
		// 2 - Il sistema restituisce l'elenco di dipendenti dell'attore
		manager.setLoggedIn(true);
		admin.setLoggedIn(true);
		assertTrue(s.printMyEmployeeList(manager).length==1);
		assertTrue(s.printMyEmployeeList(admin).length==2);
		// 3 - L'attore chiude l'elenco
	}
	
	// Scenario alternativo 3a: L'attore richiede la visualizzazione di una scheda
	// Questo scenario porta allo scenario UC35: Visualizzazione scheda dipendente
	// che viene testato separatamente.
	@Test
	public void UC20_scenarioAlternativo_3A() {
		// 1 - L'attore richiede la visualizzazione dipendenti
		// 2 - Il sistema restituisce l'elenco di dipendenti dell'attore
		// 3a - L'attore richiede la visualizzazione della scheda di un dipendente
		// Rimanda al caso d'uso UC35: Visualizzazione scheda dipendente	
	}
	
	// Scenario alternativo 3b: L'attore richiede la creazione di un nuovo account 
	// dipendente. Questo riporta al caso d'uso UC29: Creazione nuovo account dipendente
	@Test
	public void UC20_scenarioAlternativo_3B() {
		// 1 - L'attore richiede la visualizzazione dipendenti
		// 2 - Il sistema restituisce l'elenco di dipendenti dell'attore
		// 3a - L'attore richiede la creazione di un nuovo account dipendente
		// Va al caso d'uso UC29: Creazione nuovo account dipendente
	}
	
	// Scenario alternativo 3b: L'attore richiede la visualizzazione dei soli 
	// dipendenti attivi o non.
	@Test
	public void UC20_scenarioAlternativo_3C() {
		// 1a - L'attore richiede la visualizzazione dipendenti specificandone lo stato
		// Il sistema restituisce i dipendenti, con lo stato specificato, dell'attore
		manager.setLoggedIn(true);
		admin.setLoggedIn(true);
		assertEquals(1, s.printMyEmployeeList(manager,true).length);
		assertEquals(0, s.printMyEmployeeList(manager,false).length);
	}
}