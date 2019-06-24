package test_strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.Before;
import org.junit.Test;
import cinema.Admin;
import cinema.Cassiere;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.Film;
import cinema.ManagerCinema;
import cinema.Sistema;
import cinema.Utente;

public class SistemaTest {
	Admin admin;
	Utente u,c,m;
	Cinema cin;
	Film f;
	Sistema s;
	
	@Before
	public void setUp() throws Exception {
		admin = new Admin("root0", "mike", "cass", "email1@mail.com", "15/02/1999",
				"radice","+399999990","0");
		cin = new Cinema( "0","Ariston", "Sanremo", "Via Puggia 2");
		String[] act = new String[] {"Fernando Moya", "Alicia Gonzales"};
		String[] genre = new String[] {"Drama"};
		String[] tag = new String[] {"fuga", "inseguimento","Gonzales"};
		String[] prod= new String[]{"prod0", "prod1", "prod2"};
		String[] dir = new String[]{"dir1","dir2"};
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo "
				+ "inseguono.","Spagna", act,genre, tag,prod,dir);
		s = new Sistema(admin);
		u = new ClienteRegistrato("nick0", "noi", "ion", "email@mail.com", "12/02/1999",
				"hola12");
		c = new Cassiere("cass1", "hey", "cass", "email1@mail.com", "15/02/1999",
				"hola1123","+399999990","0");
		m = new ManagerCinema("man1", "carl", "man", "email1@mail.com", "15/02/1999",
				"man1","+399999990","0");
		HashMap<String, Utente> h = new HashMap<String, Utente>();
		HashMap<String, Cinema> cinema = new HashMap<String, Cinema>();
		cinema.put("0", cin);
		s.setCinema(cinema);
		h.put("nick0", u);
		h.put("cass1", c);
		h.put("man1", m);
		s.setUtenti(h);
		admin.setLoggedIn(true);
		s.addNewCinema(cin, admin);
		s.addNewFilm(f,admin);
	}

	@Test
	public void testLogout() {
		admin.setLoggedIn(true);
		assertTrue(s.logout(admin.getNickname()));
		// False perché è già offline
		assertFalse(s.logout(admin.getNickname()));
		// Utente non presente in lista -> false
		assertFalse(s.logout("miss"));
		assertFalse(s.logout("nick0"));
		u.setLoggedIn(true);
		assertTrue(s.logout("nick0"));
	}

	@Test
	public void testPrintMovieList() {
		String t = f.toString();
		// Deve contenere il film.toString inserito precedentemente
		assertTrue(Arrays.asList(s.printMovieList()).contains(t));	
	}

	@Test
	public void testPrintCinemaList() {
		String[] t = new String[]{cin.toString()};
		assertArrayEquals(t,s.printCinemaList());
	}
	
	@Test
	public void testAddNewUser() {
		Cassiere c = new Cassiere("cass2", "gin", "cass", "email1@mail.com", "15/02/1999",
				"hola1123","+399999990","0");
		assertFalse(s.addNewUser(u));
		assertTrue(s.addNewUser(c));
	}

	@Test
	public void testSearchMovie() {		
		LinkedHashMap<String, Film> res = s.searchMovie(new String[]{"fuga","gonzales"});
		assertEquals(2,res.size());
		assertTrue(res.containsKey("film0"));
		assertTrue(res.containsKey("film2"));
		// Il primo risultato deve essere il film 'la fuga'
		assertEquals("film0=" + f.toString(),res.entrySet().toArray()[0].toString());
	}

	@Test
	public void testSearchCinema() {
		Cinema k = new Cinema( "3","Odeon", "Genova", "Via Puggia 2");
		admin.setLoggedIn(true);
		s.addNewCinema(k, admin);
		LinkedHashMap<String, Cinema> res = s.searchCinema(new String[]{"Ariston", "Sanremo"});
		// Deve contenere il cinema precedentemente inserito '0' 
		assertTrue(res.containsKey("0"));
		assertTrue(res.containsKey("3"));
		assertEquals("0=" + cin.toString(),res.entrySet().toArray()[0].toString());
		assertEquals("3=" +k.toString(),res.entrySet().toArray()[1].toString());
	}

	@Test
	public void testShowCinema() {
		assertNull(s.showCinema("1111"));
		assertNotNull(s.showCinema("0"));
	}

	@Test
	public void testShowMovie() {
		assertNull(s.showMovie("nothere"));
		assertNotNull(s.showMovie("film0"));
	}

	@Test
	public void testPrintUserList() {
		String[] k = s.printUserList();
		String[] p = new String[]{u.toString(),c.toString(),m.toString()};
		assertArrayEquals(p,k);
	}

	@Test
	public void testPrintMyEmployeeList() {
		m.setLoggedIn(false);
		String[] e = s.printMyEmployeeList(m);
		String[] f = new String[]{c.toString()};
		// m è logged off
		assertNull(e);
		m.setLoggedIn(true);
		e = s.printMyEmployeeList(m);
		// Mi aspetto che l'unico dipendente sia il cassiere del cinema '0'
		assertArrayEquals(f,e);
		admin.setLoggedIn(true);
		e = s.printMyEmployeeList(admin);
		// Riporta ogni dipendente dell'admin (escluso se stesso) 
		assertEquals(2,e.length);
		c.setLoggedIn(true);
		assertNull(s.printMyEmployeeList(c));
		/* Inserisco un cassiere dipendente di un altro cinema; non deve riportarlo
			fra i dipendenti di m. */
		Cassiere temp = new Cassiere("cass2", "hey", "cass", "email1@mail.com", "15/02/1999",
				"hola1123","+399999990","cinema8");
		s.addNewUser(temp);
		assertArrayEquals(f,s.printMyEmployeeList(m));		
	}

	@Test
	public void testAddNewFilm() {
		Film film = new Film("film2", "ciao belli", "01/03/2013", "Un tizio saluta",""
				+ "Spagna", f.getActor(),f.getGenre(), f.getTags(),f.getProducer(),
				f.getDirector());
		// Tentativo di inserimento come admin, ma da sloggato
		admin.setLoggedIn(false);
		assertFalse(s.addNewFilm(film, admin));
		// Tentativo di inserimento come manager da loggato
		m.setLoggedIn(true);
		assertFalse(s.addNewFilm(film, m));
		// Tentativo di inserimento come admin loggato
		admin.setLoggedIn(true);
		assertTrue(s.addNewFilm(film, admin));		
	}

	@Test
	public void testAddNewCinema() {
		Cinema cine = new Cinema( "2","Odeon", "Genova", "Via Puggia 2");
		// Tentativo di inserimento come admin, ma da sloggato
		admin.setLoggedIn(false);
		assertFalse(s.addNewCinema(cine, admin));
		// Tentativo di inserimento come manager da loggato
		m.setLoggedIn(true);
		assertFalse(s.addNewCinema(cine, m));
		// Tentativo di inserimento come admin loggato
		admin.setLoggedIn(true);
		assertTrue(s.addNewCinema(cine, admin));
	}

	@Test
	public void testGetAdmin() {
		assertEquals(this.admin,s.getAdmin());
	}
}
