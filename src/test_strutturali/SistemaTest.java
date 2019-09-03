package test_strutturali;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
import cinema.Sala;
import cinema.Seat;
import cinema.Sistema;
import cinema.Spettacolo;
import cinema.Spettacolo2D;
import cinema.Utente;

public class SistemaTest {
	Admin admin;
	Utente u;
	Cassiere c;
	ManagerCinema m;
	Cinema cin;
	Film f, film;
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
		film = new Film("film2", "ciao belli", "01/03/2013", "Un tizio saluta",""
				+ "Spagna", f.getActor(),f.getGenre(), f.getTags(),f.getProducer(),
				f.getDirector());
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
		HashMap<String, Film> cine = new HashMap<String, Film>();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    assertTrue(s.printMovieList());
	    cine.put(f.getIdFilm(), f);
	    s.setMovie(cine);
		String t = film.toString() + "\r\n" + f.toString() + "\r\n";
		assertEquals(t,outContent.toString());
		// Inserisco una lista vuota, deve riportare false
		cine = new HashMap<String, Film>();
		s.setMovie(cine);
		assertFalse(s.printMovieList());
	}

	@Test
	public void testPrintCinemaList() {
		HashMap<String, Cinema> cine = new HashMap<String, Cinema>();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    assertTrue(s.printCinemaList());
		String t = cin.toString() + "\r\n";
		assertEquals(t,outContent.toString());
		// Inserisco una lista vuota, deve riportare false
		s.setCinema(cine);
		assertFalse(s.printCinemaList());
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
		String[] e = s.printMyEmployeeList(m);
		String[] f = new String[]{c.toString()};
		e = s.printMyEmployeeList(m);
		// Mi aspetto che l'unico dipendente sia il cassiere del cinema '0'
		assertArrayEquals(f,e);
		e = s.printMyEmployeeList(admin);
		// Riporta ogni dipendente dell'admin (escluso se stesso) 
		assertEquals(2,e.length);
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
		// Tentativo di inserimento come manager
		assertFalse(s.addNewFilm(film, m));
		// Tentativo di inserimento come admin 
		assertTrue(s.addNewFilm(film, admin));		
	}

	@Test
	public void testAddNewCinema() {
		Cinema cine = new Cinema( "2","Odeon", "Genova", "Via Puggia 2");
		// Tentativo di inserimento come manager 
		assertFalse(s.addNewCinema(cine, m));
		// Tentativo di inserimento come admin
		assertTrue(s.addNewCinema(cine, admin));
	}

	@Test
	public void testGetAdmin() {
		assertEquals(this.admin,s.getAdmin());
	}
	
	@Test
	public void testSetCinema(){
		HashMap<String, Cinema> cin = new HashMap<String, Cinema>();
		assertFalse(s.setCinema(null));	
		assertTrue(s.setCinema(cin));
	}
	
	@Test
	public void testSetMovie(){
		HashMap<String, Film> cin = new HashMap<String, Film>();
		assertFalse(s.setMovie(null));	
		assertTrue(s.setMovie(cin));
	}
	
	@Test
	public void testGetMyCinema(){
		// False perché utenti e cassieri non sono responsabili di alcun cinema
		assertFalse(s.getMyCinema(u)); 
		assertFalse(s.getMyCinema(c)); 
		// True per admin e manager
		assertTrue(s.getMyCinema(admin));
		assertTrue(s.getMyCinema(m));
	}
	
	@Test
	public void testModifyCinema(){
		Cinema cin1 = new Cinema( "222","Loston", "Modena", "Via Puggia 2");
		Cinema cin2 = new Cinema( "0","Loston", "Modena", "Via Puggia 2");
		assertNotEquals(null, s.searchCinemaById("0"));
		assertEquals(null, s.searchCinemaById("222"));
		assertFalse("Deveessere impossibile modificare un cinema inesistente",
				s.modifyCinema(cin1));
		assertTrue(s.modifyCinema(cin2));
		assertEquals(cin2.getNome(),s.searchCinemaById(cin2.getIdCinema()).getNome());
	}
	
	@Test
	public void testPrintEmployeeProfile(){
		assertTrue(s.printEmployeeProfile(c.getNickname()));
		assertFalse(s.printEmployeeProfile(u.getNickname()));
		assertFalse(s.printEmployeeProfile("NicknameNotInList"));
	}

	@Test
	public void testSearchPrenotazioni(){
		// In caso di assenza di prenotazioni
		assertEquals(0, s.searchPrenotazioni("non ci sono").size());		
		// Inserisco una prenotazione a nome dell'Utente 'u'
		Cinema cine = new Cinema( "1","Ariston", "Sanremo", "Via Puggia 2");
		Sala sala = new Sala("1", "salaBLU", "011011", 6, 3, 2);
		Spettacolo spett;
		assertEquals(0, s.searchPrenotazioni("notpresent").size());	
		spett = new Spettacolo2D("spett0", "02/12/1999", "21:30", "23:30",
				"1","mov", sala);
		ArrayList<Seat> se = new ArrayList<Seat>();
		Seat s1 = new Seat(1,2,true);
		Seat s2 = new Seat(2,1,true);
		se.add(s1);
		spett.prenote(se, u.getNickname());
		se = new ArrayList<Seat>();
		se.add(s2);
		spett.prenote(se, "ll");
		sala.addSpettacolo(spett);		
		cine.addSala(sala);
		s.addNewCinema(cine, admin);
		// Vi deve essere una prenotazione a suo nome
		assertEquals(1, s.searchPrenotazioni(u.getNickname()).size());
	}
	
	@Test
	public void testPrintMyEmployeeListStatus(){
		String[] e = s.printMyEmployeeList(m,true);
		String[] f = new String[]{c.toString()};
		e = s.printMyEmployeeList(m,true);
		// Mi aspetto che l'unico dipendente attivo sia il cassiere del cinema '0'
		assertArrayEquals(f,e);
		// Mi aspetto non ci siano dipendenti inattivi
		assertEquals(0,s.printMyEmployeeList(m,false).length);
		// Riporta ogni dipendente dell'admin (escluso se stesso) 
		assertEquals(2,s.printMyEmployeeList(admin,true).length);
		assertNull(s.printMyEmployeeList(c,true));
		/* Inserisco un cassiere dipendente di un altro cinema; non deve riportarlo
			fra i dipendenti di m. */
		Cassiere temp = new Cassiere("cass2", "hey", "cass", "email1@mail.com", "15/02/1999",
				"hola1123","+399999990","cinema8");
		s.addNewUser(temp);
		assertArrayEquals(f,s.printMyEmployeeList(m,true));	
	}
	
	@Test
	public void testSearchMovieById(){
		s.addNewFilm(f, admin);
		assertEquals(f.toString(),s.searchMovieById(f.getIdFilm()).toString());
		assertNull(s.searchMovieById("noncesicuro"));
	}

	
	@Test
	public void testSearchCinemaById(){
		s.addNewCinema(cin, admin);
		assertEquals(cin.toString(),s.searchCinemaById(cin.getIdCinema()).toString());
		assertNull(s.searchMovieById("noncesicuro"));
	}
	

}
