package test_strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import cinema.Admin;
import cinema.Cassiere;
import cinema.ClienteRegistrato;
import cinema.ManagerCinema;
import cinema.Sistema;
import cinema.Utente;

@RunWith(Parameterized.class)
public class SistemaParamTest {
	
	static Admin admin = new Admin("root0", "mike", "cass", "email1@mail.com", "15/02/1999",
			"radice","+399999990","0");
	Sistema s = new Sistema(admin);
	static Admin a2 = new Admin("adminprova", "carl", "brave", "email1@mail.com", "15/02/1999",
			"radice","+399999990","0");
	static ClienteRegistrato u = new ClienteRegistrato("crprova", "noi", "ion", 
			"email@mail.com", "12/02/1999",	"hola12");
	static ManagerCinema m = new ManagerCinema("boss1", "carl", "man", "email1@mail.com",
			"15/02/1999", "man1","+399999990","0");
	static Cassiere c = new Cassiere("cass22", "hey", "cass", "email1@mail.com", 
			"15/02/1999", "hola1123","+399999990","0");
	static Cassiere c1 = new Cassiere("AlreadyIn", "hey", "cass", "email1@mail.com", 
			"15/02/1999", "hola1123","+399999990","0");
	static Cassiere c2 = new Cassiere("CassiereWrong", "hey", "cass", "email1@mail.com", 
			"15/02/1999", "hola1123","+399999990","1");	
	
	@Parameter(0) public Utente datore;
	@Parameter(1) public Utente creato;	
	@Parameter(2) public boolean expectedValue;
	@Parameter(3) public boolean expectedChange;
	
	@Before
	public void setUp(){
		s = null;
		admin = new Admin("root0", "mike", "cass", "email1@mail.com", "15/02/1999",
				"radice","+399999990","0");
		s = new Sistema(admin);
		a2 = new Admin("adminprova", "carl", "brave", "email1@mail.com", "15/02/1999",
				"radice","+399999990","0");
		u = new ClienteRegistrato("crprova", "noi", "ion", 
				"email@mail.com", "12/02/1999",	"hola12");
		m = new ManagerCinema("boss1", "carl", "man", "email1@mail.com",
				"15/02/1999", "man1","+399999990","0");;
		c = new Cassiere("cass22", "hey", "cass", "email1@mail.com", 
				"15/02/1999", "hola1123","+399999990","0");;		
	}
	
	@Parameters(name = "Run {index}: Datore={0}, Creato={1}, Expected={2}")
	public static Collection<Object[]> data() { 
		return Arrays.asList(new Object[][] {			
			{admin, a2, false,false}, 
			{admin, m, true,true},
			{admin, c, true, true},
			{admin, c1, false, true}, // Tentativo di inserimento account già presente
			{admin, u, false, false},		
			{m, a2, false, false}, 
			{m, m, false, false},
			{m, c, true, true},
			{m, c2, false, false}, // Cassiere di un altro cinema
			{m, u, false, false},			
			{c, a2, false, false}, 
			{c, m, false, false},
			{c, c, false, false},
			{c, u, false, false},			
			{u, a2, false, false}, 
			{u, m, false, false},
			{u, c, false, false},
			{u, u, false, false},					
		});
	}

	@Test
	public void testAddNewEmployee(){
		assertEquals(expectedValue,s.addNewEmployee(datore, creato));
	}
	
	@Test
	public void testChangeUserStatus(){
		s.addNewEmployee(datore, creato);
		assertEquals(expectedChange,s.changeUserStatus(datore, creato));
	}
	
	@After
	public void tearDown(){
		HashMap<String, Utente> temp  = new HashMap<String,Utente>();
		temp.put(c1.getNickname(), c1);
		s.setUtenti(temp);
	}	
}
