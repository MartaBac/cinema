package test_strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import cinema.Admin;
import cinema.Cassiere;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.ManagerCinema;
import cinema.Sistema;
import cinema.Utente;

@RunWith(Parameterized.class)
public class SistemaParamLoginTest {
	Admin admin;
	Utente u,c,m,a;
	Cinema cin;
	Sistema s;
	
	@Parameter(0) public String password;
	@Parameter(1) public String nomeUtente;	
	@Parameter(2) public boolean expectedValue;
	
	@Before
	public void setUp() throws Exception {
		cin = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		admin = new Admin("root1", "mike", "cass", "email1@mail.com", "15/02/1999",
				"radice","+399999990","0");
		s = new Sistema(admin);
		u = new ClienteRegistrato("nick0", "noi", "ion", "email@mail.com", "12/02/1999",
				"hola12");
		c = new Cassiere("cass1", "hey", "cass", "email1@mail.com", "15/02/1999",
				"hola1123","+399999990","0");
		m = new ManagerCinema("man1", "carl", "man", "email1@mail.com", "15/02/1999",
				"man1","+399999990","0");
		HashMap<String, Utente> h = new HashMap<String, Utente>();
		h.put("nick0", u);
		h.put("cass1", c);
		h.put("man1", m);
		s.setUtenti(h);
		admin.setLoggedIn(false);
	}
	
	@Parameters
	public static Collection<Object[]> data() { 
		return Arrays.asList(new Object[][] {			
			{"hola12", "nick0", true}, 
			{"hola1123", "cass1", true},
			{"man1", "man1", true},
			{"radice", "root1", true},	
			{"rade", "root1", false},
			{"wrong", "cass1", false},
			{"wrong", "wrong", false},
		});
	}

	@Test
	public void testLogin() {
		/*  Il test avviene per tutti i 4 tipi di utenti, e anche in caso di psw errata
		 *  e di utente non trovato */	
		assertEquals(expectedValue,s.login(nomeUtente, password));
	}
	

}