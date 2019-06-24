package delete;

import cinema.Admin;
import cinema.Cassiere;
import cinema.Cinema;
import cinema.ClienteRegistrato;
import cinema.Film;
import cinema.ManagerCinema;
import cinema.Sistema;
import cinema.Utente;

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
		Utente ad,man;
		/* Creo situazione in cui ho almeno un account per tipo , tutti offline */
		s.addNewUser(ad=new Admin("NickName1", "aldo", "bosi", "i@o.it", "22/11/1980",
				"kkoopp",  "+398888888", null));
		System.out.println("Creo account Manager");
		
		s.addNewUser(man = new ManagerCinema("manager1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"kkoopp",  "+398888888", "cinema1"));
		System.out.println("Creo account Cassiere");
		s.addNewUser(new Cassiere("cassiere1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"psw1",  "+398888888","cinema1"));
		System.out.println("Creo account Cliente");
		s.addNewUser(new ClienteRegistrato("utente1", "gio", "pozzi", "i@o.it", "22/11/1980",
				"kkoopp"));

		System.out.println("Employee list completa:");
		s.printUserList();

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
					
		//UC28: Visualizzazione elenco dipendenti
		s.login("manager1", "kkoopp");
		System.out.println("Employee list di un datore:");
		System.out.println(((Cassiere) man).getCinema());		
		s.printMyEmployeeList(man);
		s.logout("manager1"); // Rimetto la condizione di 'tutti offline'
		
		

		//UC 40 Aggiungere un film
		System.out.println("UC40: Aggiungo un film");
		Film f = new Film("Film000", "troy", "", "", "",null,null,null,null,null);
		ad.setLoggedIn(true);
		s.addNewFilm(f, ad);
		System.out.println("Film inserito");
		
		//UC3 Visualizzazione elenco film
		System.out.println("UC3: visualizzo elenco film del circuito");
		s.printMovieList();
		
		//UC8 Visualizzazione scheda film
		System.out.println("UC8: Visualizzo scheda film");
		s.showMovie("Film000");
		
		//UC39: Aggiungere un cinema	
		System.out.println("UC39: Aggiungo un cinema");
		Cinema c = new Cinema("0", "Odeon", "Genova", "via parma 1");
		s.addNewCinema(c,ad);
		
		//UC2 Visualizzazione elenco cinema
		System.out.println("UC2: Visualizzazione elenco cinema del circuito");
		s.printCinemaList();
		System.out.println(c.getIdCinema());
		
		//UC6 Visualizzazione scheda cinema
		System.out.println("UC6: Visualizzazione scheda cinema");
		s.showCinema("0");
		
		//UC12 Visualizzazione proprio profilo
		System.out.println("UC12: Visualizzazione proprio profilo");
		ad.showProfile();
		


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
		s.addNewFilm(f,man);
		f = new Film("Film002", "troy", "", "troy", "",null,null,null,null,null);
		s.addNewFilm(f,man);
		
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
