package cinema;

import java.security.InvalidParameterException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe <code>Sala</code> permette la creazione di un oggetto Sala e l'interazione
 * con esso. Ogni Sala sarà associata ad un cinema.
 * 
 * @version 1.00
 * @author Marta Bacigalupo
 *
 */

public class Sala {
	private String cinemaId, name, usableSeats;
	static String salaId;
	private Integer capacity,rows,columns;
	Map<Seat, String> map;
	private HashMap<String, Spettacolo> listaSpettacoli;

/**
 * Inizializzazione dell'oggetto Sala.
 * Si assume che la mappa dei sedili sia rettangolare; in caso contrario basta settare
 * a 0 in UsableSeats i posti non esistenti, come se non fossero agibili.
 * 
 * @param cinemaId - cinema associato alla sala
 * @param name - nome della sala
 * @param usableSeats - Stringa di 0 e 1 che indica i sedili sono usabili(1) e i non (0).
 * @param cap - (Integer) indica la capacità, = alla lunghezza della String usableSeats.
 * @param rows - numero file
 * @param col - numero posti per fila
 */
	
public Sala(String cinemaId, String name, String usableSeats, Integer cap, Integer rows,
			Integer col) {
	int length;
	this.cinemaId = cinemaId;
	this.name = name;
	this.usableSeats = usableSeats;
	this.capacity = cap;
	this.rows = rows;
	this.columns = col;
	Sala.salaId = cinemaId + name;
	length = usableSeats.length();
	listaSpettacoli = new  HashMap<String, Spettacolo>();
	if (length % columns != 0 || (columns*rows) != length||length != capacity ) 
	{ 
	    throw new InvalidParameterException("Invalid Input: capacity/usable seats and # of column/rows "
	    		+ "does not match"); 
	} 
	createSeatsMap();
}

private void createSeatsMap() {
	Seat[] s = new Seat[capacity];
	map = new HashMap<Seat, String>();
	int t = 0;
	boolean b = true;
	char y;
	
	// Making a map with seats and their availability (broken or not )
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


public String getUsableSeats() {
	return usableSeats;
}

public void setUsableSeats(String usableSeats) {
	if(usableSeats!=null && usableSeats.matches("^[01]+$") && usableSeats.length()==(this.rows*this.columns))
		this.usableSeats = usableSeats;
	else
		System.out.println("->Error: Invalid input.");
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getRows() {
	return rows;
}

public Integer getColumns() {
	return columns;
}

public Integer getCapacity() {
	return capacity;
}

public String getCinemaId() {
	return cinemaId;
}

public String getSalaId() {
	return salaId;
}

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

public HashMap<String, Spettacolo> getListaSpettacoli() {
	return this.listaSpettacoli;
}

public void setListaSpettacoli(HashMap<String, Spettacolo> listaSpettacoli) {
	this.listaSpettacoli = listaSpettacoli;
}

public boolean addSpettacolo(Spettacolo s){
	// Se era già presente non lo inserisco
	if(this.listaSpettacoli.containsKey(s.getIdSpettacolo())){
		return false;
	}
	this.listaSpettacoli.put(s.getIdSpettacolo(), s);
	return true;
	}

public boolean removeSpettacolo(String s){
	if(this.listaSpettacoli.containsKey(s)){
		this.listaSpettacoli.remove(s);
		return true;
	}
	return false;
}

public void printSpettacoli(){
	if(listaSpettacoli.size()==0)
		return;
	System.out.println(new String(new char[60]).replace("\0", "-"));
	listaSpettacoli.forEach((k,v) -> System.out.println(v.toString()));
	System.out.println(new String(new char[60]).replace("\0", "-"));
}
}
