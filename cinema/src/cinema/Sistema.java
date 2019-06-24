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
 * Classe <code>Sistema</code> raccoglie le informazioni di tutto il circuito di cinema
 * 
 * @version 1.00
 * @author Marta Bacigalupo
 *
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
				System.out.println("Benvenuto Admin " + admin.getNickname());
				admin.setLoggedIn(true);
				return true;
				}else
					return false;
			}else
			return false;
		}else{
			u = listaUtenti.get(username);
			if(u.getPassword().equals(password)){
    			if (u instanceof ManagerCinema)
    				System.out.println("Benvenuto Manager " + u.getNickname());
    			if (u instanceof Cassiere)
    				System.out.println("Benvenuto Cassiere " + u.getNickname());
    			if (u instanceof ClienteRegistrato)
    				System.out.println("Benvenuto " + u.getNickname());
				listaUtenti.get(username).setLoggedIn(true);
				System.out.println("Logged in");
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
	 */
	
	public String[] printMovieList(){	
		ArrayList<String> s1 = new ArrayList<String>();
		String[] s = new String[]{};
		System.out.println("Trovati "+listaFilm.size() +"\n risultati:");
		
		for (Map.Entry<String, Film> item : listaFilm.entrySet()) {
		    Film value = item.getValue();
		    System.out.println(value.toString());
		    s1.add(value.toString());
		}
		s = s1.toArray(new String[s1.size()]);
		return s;
	}
	
	/**
	 * Stampa l'elenco di Cinema del circuito
	 */
	
	public String[] printCinemaList(){
		ArrayList<String> s1 = new ArrayList<String>();
		String[] s = new String[]{};
		System.out.println("Trovati "+listaCinema.size() +"\n risultati:");
		
		for (Map.Entry<String, Cinema> item : listaCinema.entrySet()) {
		    Cinema value = item.getValue();
		    System.out.println(value.toString());
		    s1.add(value.toString());
		}
		s = s1.toArray(new String[s1.size()]);
		return s;
	}
	
	/**
	 * Aggiunge un nuovo utente nella lista utenti del circuito
	 * 
	 * @param u
	 * @return esito aggiunta
	 */
	public boolean addNewUser(Utente u){
		if(listaUtenti.containsKey(u.getNickname())){
			System.out.println("Account già esistente");
			return false;
		}else
		listaUtenti.put(u.getNickname(), u);
		return true;
	}
	
	/**
	 * Setta gli utenti del sistema
	 * @param u - HashMap di utenti
	 */
	
	public void setUtenti( HashMap<String, Utente> u){	
		Sistema.listaUtenti = u;
	}
	
	/**
	 * Setta i cinema del circuito
	 * @param cin - HashMap contenente i cinema
	 */
	
	public void setCinema( HashMap<String, Cinema> cin){	
		Sistema.listaCinema = cin;
	}
	
	/**
	 * Effettua la ricerca di film in base a key-words.
	 * 
	 * @param tag
	 * @return HashMap contenente i film in ordine dal più inerente ai tag al meno
	 */
	
	public LinkedHashMap<String, Film> searchMovie(String[] tag){
		LinkedHashMap<String, Film> tm = new LinkedHashMap<String, Film>();
		Integer score = null;
		 HashMap<String, Integer> scoredMovies = new HashMap<String, Integer>();
		
		for(Film f : listaFilm.values()){
			System.out.println("Comparazione tag: "+tag.length);
			score = f.compareTag(tag);
			scoredMovies.put(f.getIdFilm(), score);
			score = null;
		}

		List<Entry<String,Integer>> list = new LinkedList<Map.Entry<String, 
				Integer>>(scoredMovies.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>(){

			@Override
			public int compare(Map.Entry<String, Integer> arg1, Map.Entry<String, Integer> arg0) {
				return arg0.getValue().compareTo(arg1.getValue());
			}
		});		
		Map<String,Integer> sortedMap = new LinkedHashMap<String,Integer>();
		for(Entry<String,Integer> entry : list){
			sortedMap.put(entry.getKey(), entry.getValue());
		}		
		System.out.println("Risulati ricerca:");
		for(String id : sortedMap.keySet()){
			listaFilm.get(id).printBaseInfo();
			tm.put(id, listaFilm.get(id));
		}			
		return tm;
	}
	
	/**
	 * Effettua la ricerca di Cinema del circuito in base a key-words.
	 * 
	 * @param tag
	 * @return HashMap contenente i cinema in ordine dal più inerente ai tag al meno
	 */
	
	public LinkedHashMap<String, Cinema> searchCinema(String[] tag){
		LinkedHashMap<String, Cinema> cin = new LinkedHashMap<String, Cinema>();
		Integer score = null;
		HashMap<String, Integer> scoredCinema = new HashMap<String, Integer>();
		
		for(Cinema c : listaCinema.values()){
			System.out.println("Comparazione tag: "+tag.length);
			score = c.compareTag(tag);
			scoredCinema.put(c.getIdCinema(), score);
			score = null;
		}

		List<Entry<String,Integer>> list = new LinkedList<Map.Entry<String, 
				Integer>>(scoredCinema.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>(){

		@Override
		public int compare(Map.Entry<String, Integer> arg1, Map.Entry<String, Integer> arg0) {
			return arg0.getValue().compareTo(arg1.getValue());
		}
		});
		
		
		LinkedHashMap<String,Integer> sortedMap = new LinkedHashMap<String,Integer>();
		for(Entry<String,Integer> entry : list){
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("Risulati ricerca:");
		for(String id : sortedMap.keySet()){
			listaCinema.get(id).printBaseInfo();
			cin.put(id, listaCinema.get(id));
		}
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
			System.out.println("Unable to find the specified cinema.");
			e.printStackTrace();		
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
			System.out.println("Unable to find the specified movie.");
			e.printStackTrace();		
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
		System.out.println("List size: " + listaUtenti.size());
		for (Map.Entry<String, Utente> item : listaUtenti.entrySet()) {
		    Utente value = item.getValue();	    
		    s1.add(value.toString());
		    System.out.println(s1);
		}
		s = s1.toArray(new String[s1.size()]);
		return s;
	}
	
	/**
	 * Permette di ottenere la lista dei dipententi di un specifico dipendente
	 * 
	 * @param datore - specifica chi è il datore di lavoro
	 * @return String[] dei dipendenti se loggato
	 */
	
	public String[]  printMyEmployeeList(Utente datore){
		ArrayList<String> s1 = new ArrayList<String>();
		String[] s = new String[]{};
		if(!datore.isLoggedIn()){
			System.out.println("Error, to make this operation you must be logged in.");
			return null;
		}
		if(datore.permesso.equals(Permesso.ADMIN)){
		System.out.println("List size: " + listaUtenti.size());
		
		for (Map.Entry<String, Utente> entry: listaUtenti.entrySet()){
			if(entry.getValue().getPermesso()==Permesso.CASSIERE.getPermission()||
					entry.getValue().getPermesso()==Permesso.MANAGER.getPermission()){
				System.out.println(entry.getValue().toString());
				s1.add(entry.getValue().toString());
			}
		}
		s = s1.toArray(new String[s1.size()]);
		return s;
		}
		else if(datore.permesso.equals(Permesso.MANAGER)){
			String cinema = datore.getCinema();
			Iterator<Utente> i = listaUtenti.values().iterator();
			Utente u;
			System.out.println("Lista dipendenti:");
			while(i.hasNext()){
				u = i.next();
				if(u.getPermesso() == Permesso.CASSIERE.getPermission() && 
						u.getCinema().equals(cinema)){
					System.out.println(u.getNickname() + ": " + " " + u.getName() + " " + u.getSurname());	
					s1.add(u.toString());
				}
			}
			s = s1.toArray(new String[s1.size()]);
		}			
		else{
			System.out.println("Not allowed to perform this action");
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
			System.out.println("Not allowed to perform this action");
			return false;	
		}
		if(!u.isLoggedIn()){
			System.out.println("Error! You must be logged.");
			return false;
		}
		Iterator<Film> allFilm = listaFilm.values().iterator();
		while (allFilm.hasNext()) {
			if (allFilm.next().getIdFilm().equals(f.getIdFilm())) {
				System.out.println("Movie already exist");
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
			System.out.println("Not allowed to perform this action");
			return false;		
		}
		if(!u.isLoggedIn()){
			System.out.println("Error! You must be logged.");
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}



