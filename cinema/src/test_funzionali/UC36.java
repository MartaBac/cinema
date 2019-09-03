package test_funzionali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cinema.Admin;
import cinema.Cinema;
import cinema.Sistema;

public class UC36 {
	static Admin admin;
	static Sistema s;
	static Cinema c;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		admin = new Admin("Admin0", "Bob", "Brown", "lurbin@em.net", "30/12/1980",
				"abc0123", "+393339087654", null);
		c = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		s = new Sistema(admin);
		s.addNewCinema(c, admin);	
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
