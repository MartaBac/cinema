package cinema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe <code>Spettacolo</code> rappresenta un evento spettacolo in programma nella 
 * sala di un cinema in una certa data.
 * I parametri sono l'idSpettacolo, dateShow che è la data in cui avverrà lo spettacolo,
 * startingTime e endingTime rappresentano l'inizio e fine dello spettacolo, salaId è
 * l'id della Sala in cui avverrà lo spettacolo, idMovie è l'id del Film che viene
 * proposto e idCinema l'id del cinema in cui viene trasmesso. vieatatoMinori dice se lo 
 * spettacolo è fruibile per tutti (se non lo si specifica si assume lo sia) o solo per 
 * i maggiorenni, il costo dei biglietti lo  dicono i parametri ticketCost(prezzo pieno) 
 * e reducedTicketCost(ridotto). Prenotazioni è un'HashMap contenente i posti a sedere, 
 * che specificano se sono prenotati o no.
 *  
 * @author Marta Bacigalupo
 */
public abstract class Spettacolo {

	private String idSpettacolo, dateShow, startingTime,endingTime, salaId, idMovie;
	private String idCinema;
	private boolean vietatoMinori;
	protected static BigDecimal ticketCost;
	protected static BigDecimal reducedTicketCost;
	private HashMap<String, Seat> prenotazioni;
	
	public Spettacolo(String id,String dateShow,String start, String end,
			String idCinema,String idMovie, Sala sala){
		this.idMovie = idMovie;
		this.idSpettacolo = id;
		this.dateShow = dateShow;
		this.startingTime = start;
		this.endingTime = end;
		this.idCinema = idCinema;
		this.salaId = sala.getSalaId();
		prenotazioni = new HashMap<String, Seat>();
		this.vietatoMinori = false;
		createMap(sala);	
	}
	
	public Spettacolo(String id,String dateShow,String start,String end,String idCinema,
			boolean vietato,String idMovie, Sala sala){
		this.idSpettacolo = id;
		this.setDateShow(dateShow);
		this.setStartingTime(start);
		this.setEndingTime(end);
		this.idMovie = idMovie;
		this.salaId = sala.getSalaId();
		prenotazioni = new HashMap<String, Seat>();
		createMap(sala);
		this.vietatoMinori = vietato;
	}
	
	@Override
	public String toString(){
		return idSpettacolo + " " + dateShow + " " + startingTime + " " + salaId;
	}

	/**
	 * Gets se è vietato ai minori o no
	 * 
	 * @return vietatoMinori - boolean
	 */
	public boolean getVietato(){
		return vietatoMinori;
	}
	
	/**
	 * Sets se è vietato ai minori o meno.
	 * 
	 * @param b - boolean
	 */
	public void setVietato(boolean b){
		this.vietatoMinori = b;		
	}
	
	/**
	 * Gets l'id dello Spettacolo
	 * 
	 * @return idSpettacolo - String
	 */
	public  String getSpettacoloId(){
		return idSpettacolo;
	}
	
	/**
	 * Gets l'orario di fine dello Spettacolo
	 * 
	 * @return endingTime - String
	 */
	public String getEndingTime() {
		return endingTime;
	}

	/**
	 * Sets l'endingTime dello Spettacolo
	 * 
	 * @param endingTime - String
	 */
	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	/**
	 * Gets data dello spettacolo
	 * 
	 * @return dateShow
	 */
	public String getDateShow() {
		return dateShow;
	}

	/**
	 * Sets la data dello spettacolo
	 * 
	 * @param dateShow data dello spettacolo
	 */
	public void setDateShow(String dateShow) {
		this.dateShow = dateShow;
	}

	/**
	 * Sets il costo dei biglietti base
	 * 
	 * @param ticketCost costo del biglietto intero
	 */
	public static void setTicketCost(BigDecimal ticketCost) {
		Spettacolo.ticketCost = ticketCost;
	}

	/**
	 * Gets l'orario di inizio dello spettacolo
	 * 
	 * @return startingTime - String
	 */
	public String getStartingTime() {
		return startingTime;
	}

	/**
	 * Sets l'orario di inizio dello spettacolo
	 * 
	 * @param st - String
	 */
	public void setStartingTime(String st) {
		this.startingTime = st;
	}

	/**
	 * Sets il costo dei biglietti ridotti
	 * 
	 * @param reducedTicketCost prezzo del biglietto ridotto
	 */
	public static void setReducedTicketCost(BigDecimal reducedTicketCost) {
		Spettacolo.reducedTicketCost = reducedTicketCost;
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
	 * Sets l'id della Sala
	 * 
	 * @param salaId id della Sala
	 */
	public void setSalaId(String salaId) {
		this.salaId = salaId;
	}
	
	/**
	 * Gets il prezzo di un biglietto intero
	 * 
	 * @return ticketCost
	 */
	public BigDecimal getTicketCost() {
		return ticketCost;
	}

	/**
	 * Gets il prezzo di un biglietto ridotto
	 * 
	 * @return reducedTicketCost
	 */
	public BigDecimal getReducedTicketCost() {
		return reducedTicketCost;
	}

	/**
	 * Gets l'id del cinema
	 * 
	 * @return idCinema
	 */
	public String getIdCinema() {
		return idCinema;
	}

	/**
	 * Sets l'id del cinema.
	 * 
	 * @param idCinema id del cinema
	 */
	public void setIdCinema(String idCinema) {
		this.idCinema = idCinema;
	}

	/**
	 * Gets id della sala
	 * 
	 * @return salaId - l'id della Sala
	 */
	public String getIdSala() {
		return salaId;
	}
	
	/**
	 * Gets l'id dello spettacolo.
	 * 
	 * @return idSpettacolo - id dello spettacolo.
	 */
	public String getIdSpettacolo() {
		return idSpettacolo;
	}	

	/**
	 * Sets l'id della Sala.
	 * 
	 * @param idSala - id della Sala
	 */
	public void setIdSala(String idSala) {
		this.salaId = idSala;
	}
	
	/**
	 * Gets HashMap delle prenotazioni.
	 * 
	 * @return prenotazioni - HashMap con key: (String) idSeat e value: Seat.
	 */
	public HashMap<String, Seat> getPrenotazioni(){
		return this.prenotazioni;
	}
	
	/**
	 * Crea mappa prenotazioni, che ha come key l'id del sedile e come value il sedile.
	 * 
	 * @param Sala sala 
	 */
	private void createMap(Sala sala){
		Seat seat = null;
		int r = 1;
		int c = 1;
		for(int i = 0; i<sala.getCapacity(); i++){
			if(sala.getUsableSeats().charAt(i) == '1'){
				seat = new Seat(r, c, true);
			}
			else{
				seat = new Seat(r, c, false);
			}
			prenotazioni.put(seat.getId(), seat);
			if(c>=sala.getColumns()){
				c = 1;
				r++;
			}
			else
				c++;
		}
	}
	
	/**
	 * Permette di prenotare a nome di un Utente N posti di uno spettacolo.
	 * Vengono prenotati tutti gli N posti o se vi è un errore nessuno.
	 * 
	 * @param seat - ArrayList di posti che si vogliono prenotare
	 * @param idUtente - id dell'Utente che vuole effettuare la prenotazione
	 * @return false se almeno uno dei posti è già prenotato o inusabile; true altrimenti
	 */
	public boolean prenote(ArrayList<Seat> seat, String idUtente){
		ArrayList<String> s = new ArrayList<String>();
		int row, column;
		for(int i=0;i<seat.size();i++){
			row = seat.get(i).getRow();
			column = seat.get(i).getColumn();
			String idSeat = "row" + row + "column" + column;
			if(this.prenotazioni.get(idSeat).isFree() && 
					this.prenotazioni.get(idSeat).isUsable()){
				s.add(idSeat);			
			}
			else{
				return false;	
			}
		}
		s.forEach((se) -> this.prenotazioni.get(se).setOccupied(idUtente));		
		return true;
	}
	
	/** Ritorna i posti ancora liberi - e usabili - per uno spettacolo.
	 * 
	 * @return ArrayList di Seat che sono posti liberi e usabili
	 */
	public ArrayList<Seat> getFreeSeat(){
		ArrayList<Seat> seatFree = new ArrayList<Seat>();	
		Iterator<Entry<String, Seat>> it = this.prenotazioni.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String, Seat> pair = it.next();
		    if(pair.getValue().isFree() && pair.getValue().isUsable())
		    	seatFree.add(pair.getValue()); 
		    }
		return seatFree;
	}
	
	/**
	 * Controlla se lo spettacolo è uguale ad un altro.
	 * 
	 * @param sp - Spettacolo con cui effettuare il confronto.
	 * @return true se tutti i campi tranne 'prenotazioni' e 'salaId' sono uguali,
	 *  false altrimenti.
	 */

	protected boolean equalsSpett(Spettacolo sp){
		if(this.dateShow.equals(sp.getDateShow()) && this.endingTime.equals(
				sp.getEndingTime()) && this.idCinema.equals(sp.getIdCinema())&&
				this.idMovie.equals(sp.getIdMovie()) &&
				this.startingTime.equals(sp.getStartingTime()) && 
				this.vietatoMinori==sp.getVietato())
			return true;
		else
			return false;
	}

	public String getIdMovie() {
		return this.idMovie;
	}

	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}
	
	public boolean deletePrenotazione(String id){
		if(prenotazioni.remove(id) == null)
			return false;
		else
			return true;		
	}
	
	public boolean getPrenotazione(String idPrenotazione){
		if(this.prenotazioni.get(idPrenotazione)==null)
			return false;
		System.out.println(new String(new char[60]).replace("\0", "-"));
		System.out.println(this.prenotazioni.get(idPrenotazione).toString());
		System.out.println(new String(new char[60]).replace("\0", "-"));
		return true;
	}
}