package test_strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import cinema.Permesso;

@RunWith(Parameterized.class)
public class PermessoTest {
	
	@Parameter(0) public boolean employee;
	@Parameter(1) public int permission;	
	@Parameter(2) public boolean employer;
	@Parameter(3) public boolean exp1;
	@Parameter(4) public int exp2;	
	@Parameter(5) public boolean exp3;
	
	
	@Parameters
	public static Collection<Object[]> data() { 
		return Arrays.asList(new Object[][] {			
			{Permesso.ADMIN.isEmployee(), Permesso.ADMIN.getPermission(), 
				Permesso.ADMIN.isEmployer(), true, 3, true}, 
			{Permesso.MANAGER.isEmployee(), Permesso.MANAGER.getPermission(), 
				Permesso.MANAGER.isEmployer(), true, 2, true},
			{Permesso.CASSIERE.isEmployee(), Permesso.CASSIERE.getPermission(),
					Permesso.CASSIERE.isEmployer(), true, 1, false},
			{Permesso.USER.isEmployee(), Permesso.USER.getPermission(),
						Permesso.USER.isEmployer(), false, 0, false},	
		});
	}
	
	@Test
	public void testIsEmployee() {
		
		assertEquals(exp1, employee);
	}

	@Test
	public void testGetPermission() {
		assertEquals(exp2, permission);
	}

	@Test
	public void testIsEmployer() {
		assertEquals(exp3, employer);
	}

}
