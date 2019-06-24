package cinema;

import java.math.BigDecimal;
import java.util.HashMap;

public abstract class Spettacolo {

	private String idSpettacolo, dateShow, startingTime,endingTime, salaId, idMovie;
	private boolean vietatoMinori;
	protected static BigDecimal ticketCost;
	protected static BigDecimal reducedTicketCost;
	private String idCinema, idSala;
	
	// tenerlo qua o cosa?
	private HashMap<String, Seat> prenotazioni;
	
	
	public Spettacolo(String id,String dateShow,String start, String end,
			String idCinema,String idMovie, Sala sala){
		this.idMovie = idMovie;
		this.idSpettacolo = id;
		this.dateShow = dateShow;
		this.startingTime = start;
		this.endingTime = end;
		this.idCinema = idCinema;
		this.idSala = sala.getSalaId();
		prenotazioni = new HashMap<String, Seat>();
		// Se non specificato si assume che lo spettacolo sia adatto a tutti
		this.vietatoMinori = false;
		// Creazione mappa posti a sedere
		createMap(sala);	
	}
	
	public Spettacolo(String id,String dateShow,String start,String end,String idCinema,
			boolean vietato,String idMovie, Sala sala){
		this.idSpettacolo = id;
		this.setDateShow(dateShow);
		this.setStartingTime(start);
		this.setEndingTime(end);
		this.idSala = sala.getSalaId();
		prenotazioni = new HashMap<String, Seat>();
		createMap(sala);
		this.vietatoMinori = vietato;
	}

	public boolean getVietato(){
		return vietatoMinori;
	}
	
	public void setVietato(boolean b){
		this.vietatoMinori = b;		
	}
	
	public  String getSpettacoloId(){
		return idSpettacolo;
	}

	public String getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	public String getDateShow() {
		return dateShow;
	}

	public void setDateShow(String dateShow) {
		this.dateShow = dateShow;
	}

	public static void setTicketCost(BigDecimal ticketCost) {
		Spettacolo.ticketCost = ticketCost;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String st) {
		this.startingTime = st;
	}



	public static void setReducedTicketCost(BigDecimal reducedTicketCost) {
		Spettacolo.reducedTicketCost = reducedTicketCost;
	}

	public String getSalaId() {
		return salaId;
	}

	public void setSalaId(String salaId) {
		this.salaId = salaId;
	}
	

	public BigDecimal getTicketCost() {
		return ticketCost;
	}

	public BigDecimal getReducedTicketCost() {
		return reducedTicketCost;
	}

	public String getIdCinema() {
		return idCinema;
	}

	public void setIdCinema(String idCinema) {
		this.idCinema = idCinema;
	}

	public String getIdSala() {
		return idSala;
	}
	
	public String getIdSpettacolo() {
		return idSpettacolo;
	}	

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}
	
	public HashMap<String, Seat> getPrenotazioni(){
		return this.prenotazioni;
	}
	
	public void createMap(Sala sala){
		Seat seat = null;
		int r = 1;
		int c = 1;
		// Ciclo per ogni seat della sala; creo un sedile virtuale per lo spettacolo
		for(int i = 0; i<sala.getCapacity(); i++){
			if(sala.getUsableSeats().charAt(i) == '1'){
				// Sedile usabile
				seat = new Seat(r, c, true);
			}
			else{
				// Sedile non usabile
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
	
	public boolean prenote(int row, int column, String idUtente){
		String idSeat = "row" + row + "column" + column;
		if(this.prenotazioni.get(idSeat).isFree() && this.prenotazioni.get(idSeat).isUsable()){
			this.prenotazioni.get(idSeat).setOccupied(idUtente);
			return true;
		}
		return false;	
	}
	
	public boolean equalsSpett(Spettacolo sp){
		// Checka tutto tranne le prenotazioni
		System.out.println(this.idSala);
		System.out.println(sp.getIdSala());
		if(this.dateShow.equals(sp.getDateShow()) && this.endingTime.equals(
				sp.getEndingTime()) 
				&& this.idCinema.equals(sp.getIdCinema()) 
				&&
		this.idSala.equals(sp.getIdSala()) && 
		this.idSpettacolo.equals(sp.getIdSpettacolo()) 
		 && 
		 this.idMovie.equals(sp.getIdMovie()) &&
		 this.startingTime.equals(sp.getStartingTime()) 
		 && 
		this.vietatoMinori==sp.getVietato())
		return true;
		else
			return false;
	}

	public String getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}
}