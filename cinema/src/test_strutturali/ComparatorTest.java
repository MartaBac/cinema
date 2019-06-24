package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cinema.Comparator;

public class ComparatorTest {
	Comparator c;
	
	@Before
	public void setUp() throws Exception {
		c = new Comparator();
	}

	@Test
	public void testCompareStringString() {
		String s0 = "hola";
		String s1 = "nada";
		int i = c.Compare(s0,s1);
		assertEquals(0,i);
		s1 = "nadahola";
		i = c.Compare(s0,s1);
		assertEquals(1,i);
	}

	@Test
	public void testCompareStringStringArray() {
		String s0 = "cerca";
		String[] s1 = new String[]{"cerca","non","cercami","ho fatto cerca"};
		int i = c.Compare(s0, s1);
		assertEquals(3,i);
	}
}
