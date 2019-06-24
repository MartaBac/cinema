package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import cinema.ManagerCinema;
import cinema.Permesso;

public class ManagerCinemaTest {
	ManagerCinema mc;
	@Before
	public void setUp() throws Exception {
		mc = new ManagerCinema("man0","Marco", "Bisi", "libe@email.com","11/11/1977",
				"1234","+3344556678","cinema0");
	}

	@Test
	public void testManagerCinema() {
		assertNotNull(mc);
		assertEquals("man0",mc.getNickname());
		assertEquals("Marco",mc.getName());
		assertEquals("Bisi",mc.getSurname());
		assertEquals("libe@email.com",mc.getEmail());
		assertEquals("11/11/1977",mc.getBirth());
		assertFalse(mc.isLoggedIn());
		assertEquals("1234",mc.getPassword());
		assertEquals("+3344556678",mc.getPhone());
		assertEquals("cinema0",mc.getCinema());
		assertTrue(mc.getStatus());
		assertEquals(Permesso.MANAGER,mc.getRole());
	}
	
	@Test
	public void testGetPermesso(){
		assertEquals(Permesso.MANAGER.getPermission(),mc.getPermesso());
	}

}
