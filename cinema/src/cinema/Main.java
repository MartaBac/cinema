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
				"kkoopp", true, "+398888888"));
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
		
		System.out.println("Dipendente attivo?" );
		s.showActiveOrNotEmployee("NickName1", true);
		System.out.println("Cassiere inattivo?" );
		s.showActiveOrNotEmployee("NickName1", false);
		s.changeEmployeeStatus("NickName1", "cassiere1", false);
		
		System.out.println("Disattivo cassiere - dipendente inattivo?" );
		s.showActiveOrNotEmployee("NickName1", false);
		System.out.println("Attivi?");
		s.showActiveOrNotEmployee("NickName1", true);
		System.out.println("Reset");
		
		s.changeEmployeeStatus("NickName1", "cassiere1", true);
		System.out.println("Attivi?");
		s.showActiveOrNotEmployee("NickName1", true);
		System.out.println("Inattivi?");
		s.showActiveOrNotEmployee("NickName1", false);
		
		System.out.println("Chiedo lista attivi essendo manager");
		s.showActiveOrNotEmployee("manager1", true);
		System.out.println("Chiedo lista inattivi essendo manager");
		s.showActiveOrNotEmployee("manager1", false);
		System.out.println("Chiedo lista attivi essendo utente");
		s.showActiveOrNotEmployee("utente1", true);
		System.out.println("Chiedo lista attivi essendo non registrato");
		s.showActiveOrNotEmployee("kk", true);
		s.showActiveOrNotEmployee(null, true);
		
		
		

		/*
		s.addNewFilm("Film000", "troy", "", "", "","", "",null,null,null,null,null,
				null,null,null,null,null);
			
		// Print dei titoli di tutti i film in lista
		s.printMovieList();
			*/
		
		

	}
}
