package test_strutturali;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

import cinema.Film;

public class FilmTest {
	Film f = null;
	
	@Before
	public void setUp() throws Exception {
		String[] act,genre,tag,prod,dir ;
		act = new String[] {"Fernando Moya", "Alicia Gonzales"};
		genre = new String[] {"Drama"};
		tag = new String[] {"fuga", "inseguimento","Gonzales"};
		prod= new String[]{"prod0", "prod1", "prod2"};
		dir = new String[]{"dir1","dir2"};
		f = new Film("film0", "la fuga", "01/03/2013", "Un tizio fugge e altri lo inseguono.",
			"Spagna", act,genre, tag,prod,dir);
	
	}

	@Test
	public void testFilm() {
		assertEquals("film0",f.getIdFilm());
		assertEquals("la fuga",f.getTitle());
		assertEquals("01/03/2013",f.getReleaseDate());
		assertEquals("Un tizio fugge e altri lo inseguono.",f.getSummary());
		assertEquals("Spagna",f.getCountry());
		assertArrayEquals(new String[] {"Fernando Moya", "Alicia Gonzales"},f.getActor());
		assertArrayEquals(new String[] {"Drama"}, f.getGenre());
		assertArrayEquals(new String[] {"fuga", "inseguimento","Gonzales"},f.getTags());
		assertArrayEquals(new String[]{"prod0", "prod1", "prod2"},f.getProducer());
		assertArrayEquals(new String[]{"dir1","dir2"},f.getDirector());
	}

	@Test
	public void testPrintAllInfo() {
		String test = "Titolo film:\tla fuga\nData di uscita:\t01/03/2013\n"
		+"Trama:\tUn tizio fugge e altri lo inseguono.\n"
				+"Paese di produzione:\tSpagna\n"
		+"Attori:\nFernando Moya\nAlicia Gonzales\nGenere:\n" 
		+"Drama\nProduttore:\nprod0\nprod1\nprod2\nDirettore:\n" 
		+"dir1\ndir2";
	     assertEquals(test.toString(),f.printAllInfo());
	     
	}

	@Test
	public void testCompareTag() {
		String[] c,c1,c2,c3;
		c = new String[]{null};
		c1 = new String[] {"fuga"};
		c2 = new String[] {"la"};
		c3 = new String[] {"Gonzales"};
		int a,b,d, e;
		a = f.compareTag(c);
		b = f.compareTag(c1);
		d = f.compareTag(c2);
		e = f.compareTag(c3);
		assertEquals(0,a);
		// 150: 100 dal titolo 50 dai tag
		assertEquals(150,b);
		assertEquals(0,a);
		// Mi aspetto che la chiave di ricerca 'fuga' sia la migliore, la peggiore la stringa nulla
		// e che il nome di un attore sia migliore di 'la'
		assertTrue(a<d&&d<e&&d<b);
		}

	@Test
	public void testPrintBaseInfo() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    f.printBaseInfo();
		assertEquals("la fuga\r\n",outContent.toString());
	}

	@Test
	public void testGetTitle() {
		assertEquals("la fuga",f.getTitle());
	}

	@Test
	public void testGetIdFilm() {
		assertEquals("film0",f.getIdFilm());
	}

	@Test
	public void testToString() {
		// To String mette id - nome
		assertEquals("film0 - la fuga",f.toString());
	}


	@Test
	public void testSetProducer() {
		String[] t = new String[]{"test0"};
		f.setProducer(null);
		assertNull(f.getProducer());
		f.setProducer(t);
		assertArrayEquals(t,f.getProducer());
	}

	@Test
	public void testSetDirector() {
		String[] t = new String[]{"test0"};
		f.setDirector(null);
		assertNull(f.getDirector());
		f.setDirector(t);;
		assertArrayEquals(t,f.getDirector());
	}


	@Test
	public void testSetGenre() {
		String[] t = new String[]{"romance"};
		f.setGenre(null);
		assertNull(f.getGenre());
		f.setGenre(t);
		assertArrayEquals(t,f.getGenre());
	}

	@Test
	public void testSetTags() {
		String[] t = new String[]{"romantico","legami"};
		f.setTags(null);;
		assertNull(f.getTags());
		f.setTags(t);
		assertArrayEquals(t,f.getTags());
	}

}
