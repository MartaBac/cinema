package cinema;

import java.security.InvalidParameterException;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe <code>Sala</code> permette la creazione di un oggetto <code>Sala</code> e 
 * l'interazione con esso. 
 * Ogni <code>Sala</code> sarà associata ad un cinema, ha N <code>Seat</code> e si assume
 *  abbia una mappa di sedili rettangolare, la cui numerazione parte dal sedile più a 
 * sinistra e vicino allo schermo e scorre verso destra fino alla fine della riga per poi
 *  continuare con la riga successiva ripartendo da sinistra. Se la  <code>Sala</code> 
 * non risulta essere rettangolare basta crearne una rettangolare che la inglobi e 
 * settare a "0" i sedili in più, che in realtà non ci sono.
 * 
 * @version 1.00
 * @author Marta Bacigalupo
 */

public class Sala {
	private String cinemaId, name, usableSeats;
	private String salaId;
	private Integer capacity,rows,columns;
	private Map<Seat, String> map;
	private HashMap<String, Spettacolo> listaSpettacoli;

	/**
	 * Inizializzazione dell'oggetto Sala.
	 * Si assume che la mappa dei sedili sia rettangolare; in caso contrario basta 
	 * settare a 0 in UsableSeats i posti non esistenti, come se non fossero agibili.
	 * 
	 * @param cinemaId 			cinema associato alla sala
	 * @param name				nome della sala
	 * @param usableSeats		Stringa di 0 e 1 che indica i sedili sono usabili(1) e i 
	 * 							non (0)
	 * @param cap				(Integer) indica la capacità, che deve essere uguale alla
	 * 							 lunghezza della String usableSeats. Conta anche i posti 
	 * 							non utilizzabili.
	 * @param rows				numero file
	 * @param col				numero posti per fila
	 */		
	public Sala(String cinemaId, String name, String usableSeats, Integer cap, Integer 
			rows, Integer col) {
		int length;
		this.cinemaId = cinemaId;
		this.name = name;
		this.usableSeats = usableSeats;
		this.capacity = cap;
		this.rows = rows;
		this.columns = col;
		this.salaId = cinemaId + name;
		length = usableSeats.length();
		listaSpettacoli = new  HashMap<String, Spettacolo>();
		if (length % columns != 0 || (columns*rows) != length||length != capacity ) 
		{ 
		    throw new InvalidParameterException("Invalid Input: capacity/usable seats "
		    		+ "and # of column/rows does not match"); 
		} 
		createSeatsMap();
	}

	/**
	 * Crea una mappa contenente di posti a sedere della Sala come key, e come value "1"
	 * se il posto è usabile, "0" altrimenti.
	 */
	private void createSeatsMap() {
		Seat[] s = new Seat[capacity];
		map = new HashMap<Seat, String>();
		int t = 0;
		boolean b = true;
		char y;
		
		for(Integer i = 0;i<rows;i++){
			for(Integer j=0;j<columns;j++){
				if(usableSeats.charAt(i*columns +j) == '1')
					b = true;
				else 
					b = false;
				s[t] = new Seat(i, j, b);
				y = usableSeats.charAt(t);
				map.put(s[t], Character.toString(y));
				t++;
			}	
		}
	}

	/**
	 * Gets una String contenente lo stato di usabilità di tutti i sedili. Si tratta di 
	 * una Stringa di 0 (non usabile) e 1(usabile).
	 * 
	 * @return String usableSeats
	 */
	public String getUsableSeats() {
		return usableSeats;
	}

	/**
	 * Sets la stringa che indica l'usabilità dei sedili
	 * 
	 * @param usableSeats stringa di 0 e 1 che indica se i sedili sono agibili(1) o no(0)
	 */
	public void setUsableSeats(String usableSeats) {
		if(usableSeats!=null && usableSeats.matches("^[01]+$") && usableSeats.length()==
				(this.rows*this.columns))
			this.usableSeats = usableSeats;
		else
			System.out.println("->Error: Invalid input.");
	}
	
	/**
	 * Gets il nome della sala
	 * 
	 * @return name - Stringa del nome della Sala
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets il nome della Sala
	 * 
	 * @param name nome della Sala
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets il numero di righe/file della sala
	 * 
	 * @return rows
	 */
	public Integer getRows() {
		return rows;
	}
	
	/**
	 * Gets il numero di colonne/posti per fila della sala
	 * 
	 * @return columns
	 */
	public Integer getColumns() {
		return columns;
	}
	
	/**
	 * Gets la capacità della sala, numero di posti totali, compresi i non usabili.
	 * 
	 * @return capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}
	
	/**
	 * Gets l'id del Cinema
	 * 
	 * @return cinemaId
	 */
	public String getCinemaId() {
		return cinemaId;
	}
	
	/**
	 * Gets l'id della sala
	 * 
	 * @return salaId
	 */
	public String getSalaId() {
		return salaId;
	}
	
	/**
	 * Stampa tutte le informazioni della sala.
	 */
	public void printInfo(){
		String format = "%-40s%s%n";
		String repeated = new String(new char[60]).replace("\0", "-");
		System.out.printf(format, "Sala:", salaId);
		System.out.printf(format, "Cinema:", this.cinemaId);
		System.out.printf(format, "Nome:", this.name);
		System.out.printf(format, "Posti usabili:", this.usableSeats);
		System.out.printf(format, "Capacità:", this.capacity);
		System.out.printf(format, "FilexColonne:", this.rows + "x" + this.columns);
		System.out.println(repeated);
	}
	
	/**
	 * Gets l'hashmap contenente tutti gli spettacoli in programma in quella sala.
	 * 
	 * @return listaSpettacoli - key: idSpettacolo, value: Spettacolo
	 */
	public HashMap<String, Spettacolo> getListaSpettacoli() {
		return this.listaSpettacoli;
	}
	
	/**
	 * Sets la lista spettacoli.
	 * 
	 * @param listaSpettacoli - HashMap con: key - idSpettacolo; value - Spettacolo
	 */
	public void setListaSpettacoli(HashMap<String, Spettacolo> listaSpettacoli) {
		this.listaSpettacoli = listaSpettacoli;
	}
	
	/**
	 * Se assente, aggiunge uno Spettacolo alla lista degli spettacoli della Sala.
	 * 
	 * @param s - Spettacolo da aggiungere
	 * @return true se è stato aggiunto
	 */
	public boolean addSpettacolo(Spettacolo s){
		s.setSalaId(salaId);
		
		for(Entry<String, Spettacolo> entry : this.listaSpettacoli.entrySet()) {
		    Spettacolo value = entry.getValue();
	
		    if(s.equalsSpett(value)){
		    	System.out.println("-> Errore: Si sta cercando di inserire uno "
		    			+ "spettacolo già in programma");
		    	return false;
		    }
		}
		if(this.listaSpettacoli.containsKey(s.getIdSpettacolo())){
			return false;
		}
		this.listaSpettacoli.put(s.getIdSpettacolo(), s);
		return true;
	}
	
	/**
	 * Permette la rimozione di uno Spettacolo dalla listaSpettacoli della Sala.
	 * 
	 * @param s - id dello spettacolo da rimuovere
	 * @return true se è stato trovato e quindi rimosso, false altrimenti.
	 */
	public boolean removeSpettacolo(String s){
		if(this.listaSpettacoli.containsKey(s)){
			this.listaSpettacoli.remove(s);
			return true;
		}
		return false;
	}
	
	/**
	 * Stampa la lista degli spettacoli in programmazione nella Sala.
	 */
	public void printSpettacoli(){
		if(listaSpettacoli.size()==0)
			return;
		System.out.println(new String(new char[60]).replace("\0", "-"));
		listaSpettacoli.forEach((k,v) -> System.out.println(v.toString()));
		System.out.println(new String(new char[60]).replace("\0", "-"));
		}
}
