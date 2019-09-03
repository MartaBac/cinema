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
import cinema.Utente;

public class UC29 {
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
		manager = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","0");
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");	
		s = new Sistema(admin);
		s.addNewCinema(c, admin);
		s.addNewUser(manager);
	}

	// Scenario principale UC29: Creazione nuovo account Cassiere o ManagerCinema
	@Test
	public void UC29_scenarioPrincipale() {
		Utente u1;
		// 1 - L'attore chiede di creare un nuovo account dipendente
		// 2 - Il sistema chiede i dati dell'account da creare
		// 3 - L'attore inserisce i dati
		u1 = new Cassiere("test1","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","cinema0");
		// 4 - Il sistema chiede conferma sulla creazione
		// 5 - L'attore conferma
		// 6 - Il sistema verifica i permessi/dati
		// 7 - Il sistema crea il nuovo account

		assertTrue(s.addNewEmployee(admin, u1));
	}
	
	// Scenario alternativo 6a: Credenziali non valide
	@Test
	public void UC29_scenarioAlternativo_6A() {
		Utente u, u1, u2;
		// 1 - L'attore chiede di creare un nuovo account dipendente
		// 2 - Il sistema chiede i dati dell'account da creare
		// 3 - L'attore inserisce i dati
		// 4 - Il sistema chiede conferma sulla creazione
		// 5 - L'attore conferma
		// 6a - - Il sistema individua credenziali/dati non valide per la creazione 
		// Il sistema notifica l'attore dell'errore
		// Torna al punto due dello scenario principale
		
		u = new ClienteRegistrato("test0","Gino", "Cozzi", "email@gmail.com",
				"15/02/1980", "psw123");
		u1 = new Cassiere("testC","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","cinema0");
		u2 = new Admin("test2", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		s.addNewEmployee(admin, u1);
		// Fallimento - si cerca di aggiungere un account già esistente
		assertFalse(s.addNewEmployee(admin, u1));	
		// Fallimento - si cerca di creare un utente e non un dipendente
		assertFalse(s.addNewEmployee(admin, u));
		// Fallimento - si cerca di creare nuovo admin
		assertFalse(s.addNewEmployee(admin, u2));
	}
	
	// Scenario alternativo 3a:L'attore annulla la creazione
	@Test
	public void UC29_scenarioAlternativo_3A() {
		// 1 - L'attore chiede di creare un nuovo account dipendente
		// 2 - Il sistema chiede i dati dell'account da creare
		// 3a - L'attore esce
		// Il caso d'uso termina
		
		// 1 - L'attore chiede di creare un nuovo account dipendente
		// 2 - Il sistema chiede quale tipo di account occorre creare
		// 3 - L'attore sceglie un tipo di account
		// 4 - Il sistema chiede i dati del nuovo account
		// 5 - L'attore inserisce i dati
		// 6 - Il sistema individua credenziali non sufficienti
		// Il sistema notifica l'errore
		// Torna al punto 2 dello scenario principale
		Utente u, u1, u2;
		u = new ClienteRegistrato("test0","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		u1 = new Cassiere("testC","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","cinema0");
		u2 = new Admin("test2", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		s.addNewEmployee(admin, u1);
		// Fallimento - si cerca di aggiungere un account già esistente
		assertFalse(s.addNewEmployee(admin, u1));	
		// Fallimento - si cerca di creare un utente e non un dipendente
		assertFalse(s.addNewEmployee(admin, u));
		// Fallimento - si cerca di creare nuovo admin
		assertFalse(s.addNewEmployee(admin, u2));
	}
	
	// Scenario alternativo 5a:L'attore non conferma
	@Test
	public void UC29_scenarioAlternativo_5A() {
		// 1 - L'attore chiede di creare un nuovo account dipendente
		// 2 - Il sistema chiede i dati dell'account da creare
		// 3 - L'attore inserisce i dati
		// 4 - Il sistema chiede conferma sulla creazione
		// 5a - L'attore non conferma
		// Torna al punto 2 dello scenario principale
			
		// Il sistema notifica l'errore
		// Torna al punto 2 dello scenario principale
		Utente u, u1, u2;
		u = new ClienteRegistrato("test0","Gino", "Cozzi", "email@gmail.com","15/02/1980",
				"psw123");
		u1 = new Cassiere("testC","Alda", "Bosi", "mail@mail.com","13/07/1977",
				"psw1234","+3334556678","cinema0");
		u2 = new Admin("test2", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		s.addNewEmployee(admin, u1);
		// Fallimento - si cerca di aggiungere un account già esistente
		assertFalse(s.addNewEmployee(admin, u1));	
		// Fallimento - si cerca di creare un utente e non un dipendente
		assertFalse(s.addNewEmployee(admin, u));
		// Fallimento - si cerca di creare nuovo admin
		assertFalse(s.addNewEmployee(admin, u2));
	}
}	
	