package cinema;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Sistema {
		 
	private static  HashMap<String, Utente> listaUtenti;
	private static  HashMap<String, Cinema> listaCinema;
	private static HashMap<String, Film> listaFilm;
	
	static {	
		listaCinema = new HashMap<String, Cinema>();
		listaUtenti = new HashMap<String, Utente>();
		listaFilm = new HashMap<String, Film>();
	}

	public boolean login(String username, String password) {
		Utente u;
		if(!listaUtenti.containsKey(username)){
			return false;
		}else{
			u = listaUtenti.get(username);
			if(u.getPassword().equals(password)){
				if (u instanceof Admin)
    				System.out.println("Benvenuto Admin " + u.getNickname());
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


	public boolean logout(String username) {
		if (listaUtenti.get(username).isLoggedIn()) {	
			listaUtenti.get(username).setLoggedIn(false);
			return true;
		} else {
			// Non cambio lo status perché era offline o inesistente, ritorno un fallimento
			return false;
		}
	}
	
	
	public boolean printMovieList(){
		// Printing every movie title in the list
		System.out.println("Numero risultati: " + listaFilm.size());	
		for (Map.Entry entry: listaFilm.entrySet()){
		    System.out.println(entry.getValue().toString());
		}
		return false;
	}
	
	public void printCinemaList(){
		// Printing every movie title in the list
		System.out.println("Numero risultati: " + listaCinema.size());	
		for (Map.Entry entry: listaCinema.entrySet()){
		    System.out.println(entry.getValue().toString());
		}
	}
	
	public boolean addNewUser(Utente u){
		if(listaUtenti.containsKey(u.getNickname())){
			System.out.println("Account già esistente");
			return false;
		}else
		listaUtenti.put(u.getNickname(), u);
		return true;
	}
	
	public boolean searchMovie(String[] tag){
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
		
		//Sorting the list
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
		}
			
		return true;
	}
	
	public boolean searchCinema(String[] tag){
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
		
		//Sorting the list
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
			listaCinema.get(id).printBaseInfo();
		}
		return true;
	}
	
	public void showCinema(String cinemaId){
		try{
			listaCinema.get(cinemaId).printAllInfo();
		}catch(Exception e){
			System.out.println("Unable to find the specified cinema.");
			e.printStackTrace();		
		}
	}

	public void showMovie(String movieId){
		try{
			listaFilm.get(movieId).printAllInfo();
		}catch(Exception e){
			System.out.println("Unable to find the specified movie.");
			e.printStackTrace();		
		}


	}
	
	public void printProfilo(Integer idUtente){
		
	}
	
	public void reserve(String idCinema, String idSala, String idMovie, String idShow){
		
	}
	
	public void cancelReservation(String idReservation, Integer idUser){
		
	}
	
	public String printEmployeeList(){
		System.out.println("List size: " + listaUtenti.size());
		return Arrays.asList(listaUtenti).toString();
	}
	
	
	public void addNewFilm(Film f){
		
		Iterator<Film> allFilm = listaFilm.values().iterator();
		while (allFilm.hasNext()) {
			if (allFilm.next().getIdFilm().equals(f.getIdFilm())) {
				return;
			}
		}
		
		listaFilm.put(f.getIdFilm(), f);
		
	}
	
	public void addNewCinema(Cinema c){

		Iterator<Cinema> allCinema = listaCinema.values().iterator();
		while (allCinema.hasNext()) {
			if (allCinema.next().getIdCinema().equals(c.getIdCinema())) {
				return;
			}
		}	
		listaCinema.put(c.getIdCinema(), c);
}
	
	public void addNewSala(String cinemaId, String name, String usableSeats, Integer cap, Integer rows,
			Integer col){
		Sala s = new Sala(cinemaId, name, usableSeats, cap, rows, col);
		s.printInfo();
		listaCinema.get(cinemaId).addSala(s);	
}

	
	public boolean removeMovie(String idMovie){	
		if(listaFilm.get(idMovie) != null){
			listaFilm.remove(idMovie);
		}else{
			System.out.println("Invalid parameter");
			return false;
		}
		System.out.println(idMovie + " removed");
		return true;
	}

	
	public boolean removeCinema(String idCinema){
		if(listaCinema.get(idCinema) != null){
			listaCinema.remove(idCinema);
		}else{
			System.out.println("Invalid parameter");
			return false;
		}
		System.out.println(idCinema + " removed");
		return true;
	}
	
	public 	boolean changeEmployeeStatus(String idMio, String idUtente, boolean a){
		Utente m = Sistema.listaUtenti.get(idMio);
		Utente u = Sistema.listaUtenti.get(idUtente);
		
		/*
		 *  L'operazione può avvenire solo su utenti 'cassiere' o 'gestorecinema' da parte
		 *  di un utente con permessi maggiori
		 */
		if(m == null || u == null ){
			System.out.println("Operazione non consentita.");
			return false;
			
			//TODO: refactor
		}else if(m.permesso.getPermission()<=1){
			System.out.println("Permessi insufficienti");
			return false;
		}else if(m.permesso.getPermission()>u.permesso.getPermission()){
			this.listaUtenti.get(idUtente).setStatus(a);
			}else{
				System.out.println("Permessi insufficienti");
				return false;
			}	
		return true;
	}
	
	// Permette di visualizzare la lista dei propri impiegati attivi o inattivi
	public void showActiveOrNotEmployee(String myid, boolean active){
		Utente u;
		if(listaUtenti.get(myid)==null){
			System.out.println("Utente non esistente");
			return;
		}
		Permesso mio = listaUtenti.get(myid).permesso;
		if(mio.getPermission()<=1){
			System.out.println("Permessi insufficienti");
			return;
		}else{
			Iterator<Utente> i = listaUtenti.values().iterator();
			while(i.hasNext()){
				u = i.next();
				
				// Per ogni impiegato attivo che sia 'al di sotto' del richiedente come gerarchia
				if(u.getStatus() == active && u.permesso.getPermission()<mio.getPermission()){
					System.out.println(u.getNickname() + ": " + " " + u.getName() + " " + u.getSurname());				
				}
			}
		}
	}
	/**
	 * Returns a ListaCinemaIterator over the list of Cinema of the GestoreCinema
	 * specified by the username passed as input.
	 * <p>
	 * Returns null if the specified GestoreCinema is not registered in the
	 * system.
	 * 
	 * @param usernameGestoreCinema	The username of the manager of the cinemas
	 * @return			The iterator over the list of cinemas
	 */
	/*
	public static ListaCinemaIterator getListaCinemaIterator() {
			return (cinema.ListaCinemaIterator) listaCinema.getIterator();
	}
	
*/

}



