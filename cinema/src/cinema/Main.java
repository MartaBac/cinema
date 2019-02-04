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
		Utente u;
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
				"psw1", true, "+398888888"));
		System.out.println("Creato");
		System.out.println("Creo account Cliente");
		s.addNewUser(new ClienteRegistrato("utente1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"kkoopp"));
		System.out.println("Creato");
		System.out.println(s.printEmployeeList());
		
		// UC9 Autenticazione
		if(s.login("cassiere1", "psw1"))
			System.out.println("Login eseguito");
		else
			System.out.println("Login fallito");
		// UC10 Deautenticazione
		if(s.logout("cassiere1"))
			System.out.println("Logout eseguito");
		else
			System.out.println("Logout fallito");
		
		//Aggiungere un film
		Film f = new Film("Film000", "troy", "", "", "",null,null,null,null,null);
		s.addNewFilm(f);
		
		//UC3 Visualizzazione elenco film
		s.printMovieList();
		
		//UC8 Visualizzazione scheda film
		s.showMovie("Film000");
		
		//Rimozione film
		if(s.removeMovie("Film000"))
			System.out.println("Movie removed");
		else{
			System.out.println("Movie removal insuccessfull");
		}
		s.addNewFilm(f);


		// Aggiungere un cinema	
		Cinema c = new Cinema("Cinema001", "Odeon", "Genova", "via parma 1");
		s.addNewCinema(c);
		
		//UC2 Visualizzazione elenco cinema
		s.printCinemaList();
		System.out.println(c.getIdCinema());
		
		//UC6 Visualizzazione scheda cinema
		s.showCinema("Cinema001");
		
		//Rimozione cinema
		if(s.removeCinema("Cinema001"))
			System.out.println("Cinema removed");
		else{
			System.out.println("Cinema removal insuccessfull");
		}
		
		// reinserisco il cinema rimosso
		s.addNewCinema(c);
		c = new Cinema("Cinema002", "Odeon maxi", "Genova", "via Ariston 1");
		s.addNewCinema(c);
		c = new Cinema("Cinema003", "Ariston", "Genova", "via Genova 1");
		s.addNewCinema(c);

		System.out.println("Ricerca cinema");
		String[] cin = new String[]{"Odeon", "maxi"};
		s.searchCinema(cin);
		
		s.addNewSala(c.getIdCinema(), "sala1", "111110111111" , 12, 2, 6);
		
		System.out.println("Dipendente attivo?" );
		s.showActiveOrNotEmployee("NickName1", true);
		System.out.println("Cassiere inattivo?" );
		s.showActiveOrNotEmployee("NickName1", false);
		

		
		// Cambio di stato di attività
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
		
		//Visualizzazione lista attivi (da manager)
		System.out.println("Chiedo lista attivi essendo manager");
		s.showActiveOrNotEmployee("manager1", true);
		System.out.println("Chiedo lista inattivi essendo manager");
		s.showActiveOrNotEmployee("manager1", false);
		System.out.println("Chiedo lista attivi essendo utente");
		s.showActiveOrNotEmployee("utente1", true);
		System.out.println("Chiedo lista attivi essendo non registrato");
		s.showActiveOrNotEmployee("kk", true);
		s.showActiveOrNotEmployee(null, true);
		
		
		f = new Film("Film001", "hula", "", "", "",null,null,null,null,null);
		s.addNewFilm(f);
		f = new Film("Film002", "troy", "", "troy", "",null,null,null,null,null);
		s.addNewFilm(f);
		
		String[] t = new String[]{"troy", "nop"};
		s.searchMovie(t);
		String[] sl = new String[]{"",""};
		System.out.println(sl.getClass().toString());
		System.out.println(sl.getClass().toString());
		
		

		/*
		s.addNewFilm("Film000", "troy", "", "", "","", "",null,null,null,null,null,
				null,null,null,null,null);
			
		// Print dei titoli di tutti i film in lista
		s.printMovieList();
			*/
		
		

	}
}
