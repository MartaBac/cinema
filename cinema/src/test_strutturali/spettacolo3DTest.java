package test_strutturali;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import cinema.Sala;
import cinema.Spettacolo3D;

public class spettacolo3DTest {
	Sala sala;
	Spettacolo3D s,s1;
	@Before
	public void setUp() throws Exception {
		sala = new Sala("cinema0", "salaBLU", "011011", 6, 3, 2);
		s = new Spettacolo3D("spett0", "02/12/1999", "21:30", "23:30","0","mov",sala);
		s1 = new Spettacolo3D("spett1", "03/12/1999", "20:30", "22:30",true,"0","mov",
				sala);
	}

	@Test
	public void testSpettacolo() {
		assertEquals("spett0",s.getIdSpettacolo());
		assertEquals("mov",s.getIdMovie());
		assertEquals("02/12/1999",s.getDateShow());
		assertEquals("21:30",s.getStartingTime());
		assertEquals("23:30",s.getEndingTime());
		assertFalse(s.getVietato());
		BigDecimal bd1= new BigDecimal("10");
		BigDecimal bd2= new BigDecimal("8");
		assertEquals(bd1,s.getTicketCost());
		assertEquals(bd2,s.getReducedTicketCost());
	}
}
