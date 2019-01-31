package cinema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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
		if(listaUtenti.containsKey(username)){
			return false;
		}else{
			u = listaUtenti.get(username);
			if(u.getPassword().equals(password)){
				if (u instanceof Admin)
    				System.out.println("Benvenuto Admin " + u.getNickname());
    			if (u instanceof ManagerCinema)
    				System.out.println("Benvenuto Manager " + u.getNickname());
    			if (u instanceof Cassiere)
    				System.out.println("Benvenuto Cassiere" + u.getNickname());
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
			// Non cambio lo status perché era già offline o utente non trovato, ritorno un fallimento
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
	
	public boolean searchMovie(){
		
		return false;
	}
	
	public boolean searchCinema(){
		
		return false;
	}
	
	public boolean showCinema(String cinemaId){
		
		return false;
	}

	public boolean showMovie(String movieId){
		
		return false;
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
		return false;
	}

	
	public boolean removeCinema(String idCinema){
		
		return false;
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



