package cinema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe <code>Sistema</code> raccoglie le informazioni di tutto il circuito di cinema.
 * Ha l'attributo admin che è l'amministratore sistema, la listaUtenti che è una 
 * mappa contenente tutti gli utenti del sistema -tranne l'admin- come value e il loro id
 * è usato come key, listaCinema è una mappa di cinema(value) con come key il loro id;
 * listaFilm è anch'essa mappa con key = id, value = Film.
 * 
 * @version 1.00
 * @author Marta Bacigalupo
 */

public class Sistema {
	
	private Admin admin;
	/** Lista di tutti gli utenti del sistema. */
	private static  HashMap<String, Utente> listaUtenti;
	/** Lista di tutte le filiali cinema del circuito. */
	private static  HashMap<String, Cinema> listaCinema;
	/** Lista di tutti i film in catalogo */
	private static HashMap<String, Film> listaFilm;
	
	static {	
		listaCinema = new HashMap<String, Cinema>();
		listaUtenti = new HashMap<String, Utente>();
		listaFilm = new HashMap<String, Film>();
	}
	
	public Sistema(Admin a){
		this.setAdmin(a);
	}
	
	/**
	 * Esegue il login
	 * 
	 * @param username - nome d'accesso dell'utente
	 * @param password - password d'accesso
	 * 
	 */
	public boolean login(String username, String password) {
		Utente u;
		if(!listaUtenti.containsKey(username)){
			if(admin.getNickname().equals(username)){
				if(admin.getPassword().equals(password)){
				admin.setLoggedIn(true);
				return true;
				}else
					return false;
			}else
			return false;
		}else{
			u = listaUtenti.get(username);
			if(u.getPassword().equals(password)){
				listaUtenti.get(username).setLoggedIn(true);
			}
			else{
				return false;
			}
		}
		return true;
	}

	/**
	 * Esegue la disconnessione dell'utente.
	 * Alla fine della funzione l'utente sarà disconnesso.
	 * 
	 * @param username - nome utente
	 * @return true se la disconnessione è avvenuta, false se era già sconnesso
	 */
	
	public boolean logout(String username) {
		if(admin.getNickname().equals(username)){
			if(admin.isLoggedIn()){
				admin.setLoggedIn(false);
				return true;
			}else{
				return false;
			}
		}
		if(listaUtenti.get(username)==null)
			return false;
		if (listaUtenti.get(username).isLoggedIn()) {	
			listaUtenti.get(username).setLoggedIn(false);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Stampa l'elenco di film presenti, specificandone nome e genere.
	 * 
	 * @return false se la lista è vuota, true altrimenti
	 */
	public boolean printMovieList(){	
		if(listaFilm.size()==0)
			return false;
		for (Map.Entry<String, Film> item : listaFilm.entrySet()) {
		    Film value = item.getValue();
		    System.out.println(value.toString());
		}
		return true;
	}
	
	/**
	 * Stampa l'elenco di Cinema del circuito
	 * 
	 * @return false se la listaCinema è vuota, altrimenti ritorna true
	 */
	public boolean printCinemaList(){
		if(listaCinema.size()==0)
			return false;
		for (Map.Entry<String, Cinema> item : listaCinema.entrySet()) {
		    Cinema value = item.getValue();
		    System.out.println(value.toString());
		}
		return true;
	}
	
	/**
	 * Aggiunge un nuovo utente nella lista utenti del circuito
	 * 
	 * @param u
	 * @return true se l'aggiunta avviente, false altrimenti
	 */
	public boolean addNewUser(Utente u){
		if(listaUtenti.containsKey(u.getNickname())){
			System.out.println("-> Errore: Account già esistente");
			return false;
		}else
		listaUtenti.put(u.getNickname(), u);
		return true;
	}
	
	/**
	 * Fa creare un nuovo account dipendente.
	 * 
	 * @param d - datore di lavoro che vuole creare l'account
	 * @param u - account da creare
	 * @return esito creazione, sarà true solo se l'account del datore ha i permessi 
	 * (deve essere Admin o Manager) e l'account che si cerca di creare deve essere
	 * di tipo dipendente e , nel caso del Manager, proprio dipendente e non di altre
	 * filiali.
	 */
	public boolean addNewEmployee(Utente d, Utente u){
		switch(d.getPermesso()) {
		case 3:
			if(u.getPermesso()!=2 && u.getPermesso()!=1){
				System.out.println("-> Errore: Impossibile creare account di questa "
						+ "classe.");
				return false;
			}
			break;
		case 2:
			if(u.getPermesso()!=1){
				System.out.println("->Errore: Impossibile creare account di questa "
						+ "classe.");
				return false;	
			}
			else{
				if(d.getCinema()!=u.getCinema()){
					System.out.println("->Errore:  Impossibile creare account dipendente"
							+ " del cinema richiesto.");
					return false;		
				}
			}
			break;
		default:
			System.out.println("->Errore: Permesso negato.");
			return false;
		}
		if(listaUtenti.containsKey(u.getNickname())){
			System.out.println("-> Errore: Account già esistente " + u.getNickname());
			return false;
		}else{
			listaUtenti.put(u.getNickname(), u);
			return true;
		}
	}
	
	/**
	 * Setta gli utenti del sistema
	 * 
	 * @param u - HashMap di utenti
	 */
	public void setUtenti( HashMap<String, Utente> u){	
		Sistema.listaUtenti = u;
	}
	
	/**
	 * Setta i cinema del circuito
	 * 
	 * @param cin - HashMap contenente i cinema
	 */
	public boolean setCinema( HashMap<String, Cinema> cin){	
		if(cin == null){
			System.out.println("->Errore: null input");
			return false;
		}
		Sistema.listaCinema = cin;
		return true;
	}
	
	/**
	 * Setta i film del circuito
	 * 
	 * @param cin - HashMap contenente i film
	 */
	public boolean setMovie( HashMap<String, Film> cin){	
		if(cin == null){
			System.out.println("->Errore: null input");
			return false;
		}
		Sistema.listaFilm = cin;
		return true;
	}
	
	/**
	 * Effettua la ricerca di film in base a key-words e stampa i risultati.
	 * 
	 * @param tag
	 * @return HashMap contenente i film in ordine dal più inerente ai tag al meno
	 */
	
	public LinkedHashMap<String, Film> searchMovie(String[] tag){
		LinkedHashMap<String, Film> tm = new LinkedHashMap<String, Film>();
		Integer score = null;
		 HashMap<String, Integer> scoredMovies = new HashMap<String, Integer>();
		
		for(Film f : listaFilm.values()){
			score = f.compareTag(tag);
			scoredMovies.put(f.getIdFilm(), score);
			score = null;
		}

		List<Entry<String,Integer>> list = new LinkedList<Map.Entry<String, 
				Integer>>(scoredMovies.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>(){

			@Override
			public int compare(Map.Entry<String, Integer> arg1, Map.Entry<String, 
					Integer> arg0) {
				return arg0.getValue().compareTo(arg1.getValue());
			}
		});		
		Map<String,Integer> sortedMap = new LinkedHashMap<String,Integer>();
		for(Entry<String,Integer> entry : list){
			sortedMap.put(entry.getKey(), entry.getValue());
		}		
		System.out.println(new String(new char[60]).replace("\0", "-"));
		for(String id : sortedMap.keySet()){
			tm.put(id, listaFilm.get(id));
		}	
		tm.forEach((id, film) -> System.out.println(film.toString()));	
		System.out.println(new String(new char[60]).replace("\0", "-"));
		return tm;
	}
	
	/**
	 * Fa la ricerca di Cinema del circuito in base a key-words e stampa i risultati.
	 * 
	 * @param tag
	 * @return HashMap contenente i cinema in ordine dal più inerente ai tag al meno
	 */
	public LinkedHashMap<String, Cinema> searchCinema(String[] tag){
		LinkedHashMap<String, Cinema> cin = new LinkedHashMap<String, Cinema>();
		Integer score = null;
		HashMap<String, Integer> scoredCinema = new HashMap<String, Integer>();
		
		for(Cinema c : listaCinema.values()){
			score = c.compareTag(tag);
			scoredCinema.put(c.getIdCinema(), score);
			score = null;
		}

		List<Entry<String,Integer>> list = new LinkedList<Map.Entry<String, 
				Integer>>(scoredCinema.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>(){

		@Override
		public int compare(Map.Entry<String, Integer> arg1, Map.Entry<String, 
				Integer> arg0) {
			return arg0.getValue().compareTo(arg1.getValue());
		}
		});
		
		
		LinkedHashMap<String,Integer> sortedMap = new LinkedHashMap<String,Integer>();
		for(Entry<String,Integer> entry : list){
			sortedMap.put(entry.getKey(), entry.getValue());
		}		
		System.out.println(new String(new char[60]).replace("\0", "-"));
		System.out.println("Risulati ricerca:");
		for(String id : sortedMap.keySet()){
			listaCinema.get(id).printBaseInfo();
			cin.put(id, listaCinema.get(id));
		}
		System.out.println(new String(new char[60]).replace("\0", "-"));
		cin.forEach((id, cinema) -> System.out.println(cinema.toString()));
		return cin;
	}
	
	/**
	 * Restituisce tutte le informazioni su un cinema
	 * 
	 * @param cinemaId
	 * @return String
	 */
	public String showCinema(String cinemaId){
		String s = null;
		try{
			s = listaCinema.get(cinemaId).printAllInfo();
		}catch(Exception e){
			System.out.println("->Errore: Unable to find the specified cinema.");		
		}
		return s;
	}
	
	/**
	 * Restituisce tutte le informazioni su un film
	 * 
	 * @param movieId
	 * @return String
	 */
	public String showMovie(String movieId){
		String s = null;
		try{
			s = listaFilm.get(movieId).printAllInfo();
		}catch(Exception e){
			System.out.println("->Errore: Unable to find the specified movie.");
		}
		return s;
	}
		
	/**
	 * Permette di ottenere la lista dei dipententi del circuito
	 * 
	 * @return String[] dei dipendenti
	 */
	public String[]  printUserList(){
		ArrayList<String> s1 = new ArrayList<String>();
		String[] s = new String[]{};
		System.out.println(new String(new char[60]).replace("\0", "-"));
		System.out.println("List size: " + listaUtenti.size());
		for (Map.Entry<String, Utente> item : listaUtenti.entrySet()) {
		    Utente value = item.getValue();	    
		    s1.add(value.toString());
		    System.out.println(s1);
		}
		System.out.println(new String(new char[60]).replace("\0", "-"));
		s = s1.toArray(new String[s1.size()]);
		return s;
	}
	
	/**
	 * Permette di ottenere la lista dei dipententi di un specifico dipendente
	 * 
	 * @param datore - specifica chi è il datore di lavoro
	 * @return String[] dei dipendenti 
	 */
	public String[]  printMyEmployeeList(Utente datore){
		ArrayList<String> s1 = new ArrayList<String>();
		String[] s = new String[]{};
		if(datore.permesso.equals(Permesso.ADMIN)){	
			System.out.println(new String(new char[60]).replace("\0", "-"));
			for (Map.Entry<String, Utente> entry: listaUtenti.entrySet()){
				if(entry.getValue().getPermesso()==Permesso.CASSIERE.getPermission()||
						entry.getValue().getPermesso()==
						Permesso.MANAGER.getPermission()){
					System.out.println(entry.getValue().toString());
					s1.add(entry.getValue().toString());
				}
		}
		System.out.println(new String(new char[60]).replace("\0", "-"));
		s = s1.toArray(new String[s1.size()]);
		return s;
		}
		else if(datore.permesso.equals(Permesso.MANAGER)){
			String cinema = datore.getCinema();
			Iterator<Utente> i = listaUtenti.values().iterator();
			Utente u;
			System.out.println(new String(new char[60]).replace("\0", "-"));
			System.out.println("Lista dipendenti:");
			while(i.hasNext()){
				u = i.next();
				if(u.getPermesso() == Permesso.CASSIERE.getPermission() && 
						u.getCinema().equals(cinema)){
					System.out.println(u.getNickname() + ": " + " " + u.getName() + " "
						+ u.getSurname());	
					s1.add(u.toString());
				}
			}
			System.out.println(new String(new char[60]).replace("\0", "-"));
			s = s1.toArray(new String[s1.size()]);
		}			
		else{
			System.out.println("-> Errore: Not allowed to perform this action");
			return null;
		}
		return s;
	}
	
	/**
	 * Permette di ottenere la lista dei dipententi di un specifico attore, restituendo
	 * solo quelli con un certo status.
	 * 
	 * @param datore - specifica chi è il datore di lavoro
	 * @param status - specifica se voglio ottenere gli attivi o inattivi
	 * @return String[] dei dipendenti - se loggato -
	 */
	public String[]  printMyEmployeeList(Utente datore, boolean status){
		ArrayList<String> s1 = new ArrayList<String>();
		String[] s = new String[]{};
		if(datore.permesso.equals(Permesso.ADMIN)){
			System.out.println(new String(new char[60]).replace("\0", "-"));
		System.out.println("List size: " + listaUtenti.size());
		
		for (Map.Entry<String, Utente> entry: listaUtenti.entrySet()){
			if(entry.getValue().getPermesso()==Permesso.CASSIERE.getPermission()||
					entry.getValue().getPermesso()==Permesso.MANAGER.getPermission()){
				System.out.println(entry.getValue().toString());
				s1.add(entry.getValue().toString());
			}
		}
		s = s1.toArray(new String[s1.size()]);
		System.out.println(new String(new char[60]).replace("\0", "-"));
		return s;
		}
		else if(datore.permesso.equals(Permesso.MANAGER)){
			String cinema = datore.getCinema();
			Iterator<Utente> i = listaUtenti.values().iterator();
			Utente u;
			System.out.println(new String(new char[60]).replace("\0", "-"));
			System.out.println("Lista dipendenti:");
			while(i.hasNext()){
				u = i.next();
				if(u.getPermesso() == Permesso.CASSIERE.getPermission() && 
						u.getCinema().equals(cinema) && u.getStatus() == status){
					System.out.println(u.getNickname() + ": " + " " + u.getName() + " " +
						u.getSurname());	
					s1.add(u.toString());
				}
			}
			System.out.println(new String(new char[60]).replace("\0", "-"));
			s = s1.toArray(new String[s1.size()]);
		}			
		else{
			System.out.println("-> Error: Not allowed to perform this action");
			return null;
		}
		return s;
	}
	
	/**
	 *  Aggiunge un nuovo film fra la lista dei film del circuito
	 * @param f - film da aggiungere
	 * @param u - Utente che tenta di fare l'azione
	 * @return true se aggiunto con successo
	 */
	public boolean addNewFilm(Film f, Utente u){
		if(!u.permesso.equals(Permesso.ADMIN)){
			System.out.println("->Errore: Not allowed to perform this action");
			return false;	
		}
		Iterator<Film> allFilm = listaFilm.values().iterator();
		while (allFilm.hasNext()) {
			if (allFilm.next().getIdFilm().equals(f.getIdFilm())) {
				System.out.println("->Errore: Movie already exist");
				return false;
			}
		}
		listaFilm.put(f.getIdFilm(), f);
		return true;
	}
	
	/**
	 *  Aggiunge un nuovo Cinema  fra la lista dei Cinema del circuito
	 * @param c - Cinema da aggiungere
	 * @param u - Utente che tenta di fare l'azione
	 * @return true se aggiunto con successo
	 */
	public boolean addNewCinema(Cinema c, Utente u){
		if(!u.permesso.equals(Permesso.ADMIN)){
			System.out.println("->Errore: Not allowed to perform this action");
			return false;		
		}
		Iterator<Cinema> allCinema = listaCinema.values().iterator();
		while (allCinema.hasNext()) {
			if (allCinema.next().getIdCinema()==c.getIdCinema()) {
				return false;
			}
		}	
		listaCinema.put(c.getIdCinema(), c);
		return true;
	}
	
	/**
	 * Permette di ottenere l'oggetto Film cercandolo per id
	 * 
	 * @param String id
	 * @return Film
	 */
	public Film searchMovieById(String id){
		return listaFilm.get(id);
	}

	/**
	 * Permette di ottenere l'oggetto Cinema cercandolo per id
	 * 
	 * @param String id
	 * @return Cinema
	 */
	public Cinema searchCinemaById(String id){
		return listaCinema.get(id);
	}
	
	/**
	 * Ritorna l'oggetto Admin del sistema.
	 * 
	 * @return Admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * Setta l'admin del sistema ad un Admin
	 * 
	 * @param admin
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	/** Restituisce l'elenco di prenotazioni di un Utente.
	 * 
	 * @param id - Id dell'Utente che ha fatto le prenotazioni
	 * @return ArrayList<String>
	 */
	public ArrayList<String> searchPrenotazioni(String id){
		ArrayList<String> retur = new ArrayList<String>();
		Iterator<Entry<String, Cinema>> it = listaCinema.entrySet().iterator();
		Iterator<Entry<String, Sala>> it2;
		Iterator<Entry<String, Spettacolo>> it3;
		Iterator<Entry<String, Seat>> it4;
		HashMap<String, Sala> temp = new HashMap<String, Sala>();
		HashMap<String, Seat> prenotazioni = new HashMap<String, Seat>();
		HashMap<String, Spettacolo> temp2= new HashMap<String, Spettacolo>();
		Spettacolo examine;
		Seat tempseat;
		while (it.hasNext()) {
		    Entry<String, Cinema> pair = it.next();
		    // Ho la lista di sale di un cinema	   
		    temp = pair.getValue().getSaleMap();
		    it2 = temp.entrySet().iterator();
		    while (it2.hasNext()){
		    	// Ho un'entry id, Sala
		    	Entry<String, Sala> s = it2.next();
		    	// Voglio un unico Spettacolo
		    	temp2 = s.getValue().getListaSpettacoli();
		    	it3 = temp2.entrySet().iterator();
		    	while(it3.hasNext()){
		    		//Ho un entry id, Spettacolo
		    		Entry<String, Spettacolo> sp = it3.next();
		    		//Voglio scandire tutte le prenotazioni	    		
		    		examine = sp.getValue();
		    		prenotazioni = examine.getPrenotazioni();
		    		it4 = prenotazioni.entrySet().iterator();
		    		while(it4.hasNext()){
		    			Entry<String, Seat> seat = it4.next();
		    			tempseat = seat.getValue();
		    			System.out.println(tempseat.getClientId() + id);
		    			if((!tempseat.isFree()) && 
		    					tempseat.getClientId().equals(id)){
		    				retur.add(prenotazioni.keySet() + " - " + sp.getValue().
		    						getIdSpettacolo() + pair.getValue().getIdCinema() 
		    						+ " - " + 
		    				s.getValue().getSalaId() + " - " + sp.getValue().
		    				getIdSpettacolo() + " - " + seat.getValue().getRow() 
		    				+ " - " + seat.getValue().getColumn() + " - " +
		    				seat.getValue().getClientId());
		    			}
		    		}
		    	}    		
		   }
		}return retur;
	}
	
/** Permette di abilitare/disabilitare propri dipendenti.
 * 	
 * @param datore - deve essere Admin o ManagerCinema
 * @param utente - deve essere ManagerCinema o Cassiere
 * @return true se l'operazione ha successo.
 */
	public boolean changeUserStatus(Utente datore, Utente utente){
		if(!datore.permesso.isEmployer() || !utente.isEmployee() || 
				utente.getClass() == Admin.class){
			return false;
		}
		// Se il datore è admin o manager ( e in questo caso l'utente non è manager)
		if((datore.getClass().equals(ManagerCinema.class) && !utente.getClass().equals(
			ManagerCinema.class) && utente.getCinema().equals(datore.getCinema())) ||
			datore.getClass().equals(Admin.class) ){
				utente.setStatus(!utente.getStatus());
				return true;
		}
		return false;	
	}
	
	/** Stampa le informazioni del profilo di un impiegato
	 * 
	 * @param nickname impiegato
	 * @return true se l'utente esiste ed ha ruolo di impiegato (non admin)
	 */
	public boolean printEmployeeProfile(String nickname){
		Utente u = listaUtenti.get(nickname);
		if(u == null){
			System.out.println("-> Errore: Nickname specificato non trovato");
			return false;
		}
		if(!u.isEmployee()){
			return false;
		}
		u.showProfile();
		return true;
	}

	/** Modifica le informazioni di un cinema in lista nel sistema.
	 * 
	 * @param c - Cinema da modificare
	 * @return true - Se viene trovato un Cinema con lo stesso id da modificare
	 */
	public boolean modifyCinema(Cinema c){
		Cinema t = listaCinema.get(c.getIdCinema());
		if(t == null)
			return false;
		listaCinema.remove(c.getIdCinema());
		listaCinema.put(c.getIdCinema(), c);
		return true;
	}
	
	/** Stampa le info di tutti i Cinema di cui l'Utente è responsabile
	 * @param s
	 * @return false se l'Utente non è responsabile di nessun Cinema, quindi se è un 
	 * 				cassiere o clienteregistrato
	 */
	public boolean getMyCinema(Utente s){
		if(s.getClass()==Admin.class){
			listaCinema.forEach((k,v) -> v.printAllInfo());
			return true;
		}
		if(s.getClass()==ManagerCinema.class){
			listaCinema.get(s.getCinema()).printAllInfo();
			return true;
		}		
		return false;
	}
}

