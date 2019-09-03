package test_funzionali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cinema;
import cinema.Sistema;

public class UC39 {
	static Admin admin;
	static Sistema s;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);		
		s = new Sistema(admin);	
	}

	// Scenario principale UC39: Il sistema memorizza le nuove informazioni del cinema
	@Test
	public void UC36_scenarioPrincipale() {
		// 1 - L'attore richiede di inserire un nuovo cinema
		// 2 - Il sistema richiede i dati del nuovo cinema
		// 3 - L'attore immette tutte le informazioni e conferma le modifiche
		Cinema c1 = new Cinema( "0","Ariston", "Lucca", "Via Puggia 8");
		// 4 - Il sistema salva la nuova scheda cinema
		s.addNewCinema(c1, admin);
		// 5 - Il caso d'uso termina
		assertEquals(c1.toString(), s.searchCinemaById(c1.getIdCinema()).toString());
	}
	
	// Scenario alternativo 3a: L'attore annulla l'operazione
	@Test
	public void UC36_scenarioAlternativo_3A() {
		// 1 - L'attore richiede di inserire un nuovo cinema
		// 2 - Il sistema richiede i dati del nuovo cinema
		// 3a - L'attore annulla
		// Il caso d'uso termina
	}
	
}
