package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cinema.Seat;

public class SeatTest {
	Seat s;
	@Before
	public void setUp() throws Exception {
		s = new Seat(1,2,true);
	}

	@Test
	public void testSeat() {
		assertTrue(s.isFree());
		assertEquals(1,s.getRow());
		assertEquals(2,s.getColumn());
		assertEquals(true,s.isUsable());
	}

	@Test
	public void testSetColumn() {
		s.setColumn(3);
		assertEquals(3,s.getColumn());
	}

	@Test
	public void testSetRow() {
		s.setRow(4);
		assertEquals(4,s.getRow());
	}

	@Test
	public void testSetFree() {
		s.setFree(true);
		assertTrue(s.isFree());
		s.setFree(false);
		assertFalse(s.isFree());
	}

	@Test
	public void testSetUsable() {
		s.setUsable(false);
		assertFalse(s.isUsable());
	}
	
	@Test
	public void testSetOccupied() {
		String f = "id";
		assertTrue(s.setOccupied(f));
		assertFalse(s.setOccupied(f));
	}
	
	@Test
	public void testGetId(){
		String f = "illll";
		s.setId(f);
		assertEquals(f,s.getId());
	}
	
	@Test
	public void testGetClientId() {
		String k = "client";
		s.setClientId(k);
		assertEquals(k,s.getClientId());
	}
}
