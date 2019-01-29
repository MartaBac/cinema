package cinema;

public class Main {

	/**
     * Main function
     *
     * @param args argomenti
     */
	public static void main(String[] args) {
		System.out.println("Creo nuovo Sistema");
		Sistema s = new Sistema();
		System.out.println("Creato");
		System.out.println("Creo account Admin");
		
		/* Creo situazione in cui ho almeno un account per tipo , tutti offline */
		s.addNewUser(new Admin("NickName1", "aldo", "bosi", "i@o.it", "22/11/1980",
				"kkoopp", true, "+398888888", true));
		System.out.println("Creato");
		System.out.println("Creo account Manager");
		s.addNewUser(new ManagerCinema("manager1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"kkoopp", true, "+398888888", true));
		System.out.println("Creato");
		System.out.println("Creo account Cassiere");
		s.addNewUser(new Cassiere("cassiere1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"kkoopp", true, "+398888888", true));
		System.out.println("Creato");
		System.out.println("Creo account Cliente");
		s.addNewUser(new ClienteRegistrato("utente1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"kkoopp"));
		System.out.println("Creato");
		System.out.println(s.printEmployeeList());
		
		Film f = new Film("Film000", "troy", "", "", "",null,null,null,null,null);
		s.addNewFilm(f);
		
		s.printMovieList();
		
		// Aggiungere un cinema
		
		Cinema c = new Cinema("Cinema001", "Odeon", "Genova", "via parma 1");
		s.addNewCinema(c);
		s.printCinemaList();
		System.out.println(c.getIdCinema());
		s.addNewSala(c.getIdCinema(), "sala1", "111110111111" , 12, 2, 6);
		

		/*
		s.addNewFilm("Film000", "troy", "", "", "","", "",null,null,null,null,null,
				null,null,null,null,null);
			
		// Print dei titoli di tutti i film in lista
		s.printMovieList();
			*/
		
		

	}
}
