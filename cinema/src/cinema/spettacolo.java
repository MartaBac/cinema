package cinema;

import java.math.BigDecimal;
import java.util.HashMap;

public abstract class spettacolo {

	private String idSpettacolo, dateShow, startingTime,endingTime, salaId;
	private boolean vietatoMinori;
	private static BigDecimal ticketCost,reducedTicketCost;
	// tenerlo qua o cosa?
	private HashMap<Seat, Utente> prenotazioni;
	
	
	public spettacolo(String id,String dateShow,String start,String end){
		this.idSpettacolo = id;
		this.setDateShow(dateShow);
		this.setStartingTime(start);
		this.setEndingTime(end);
		prenotazioni = null;
		// Se non specificato si assume che lo spettacolo sia adatto a tutti
		this.vietatoMinori = false;
	}
	
	public spettacolo(String id,String dateShow,String start,String end, boolean vietato){
		this.idSpettacolo = id;
		this.setDateShow(dateShow);
		this.setStartingTime(start);
		this.setEndingTime(end);
		prenotazioni = null;
		this.vietatoMinori = vietato;
	}

	public boolean getVietato(){
		return vietatoMinori;
	}
	
	public void setVietato(boolean b){
		this.vietatoMinori = b;		
	}
	
	public  String getMovieId(){
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

	public BigDecimal getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(BigDecimal ticketCost) {
		spettacolo.ticketCost = ticketCost;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public static BigDecimal getReducedTicketCost() {
		return reducedTicketCost;
	}

	public static void setReducedTicketCost(BigDecimal reducedTicketCost) {
		spettacolo.reducedTicketCost = reducedTicketCost;
	}

	public String getSalaId() {
		return salaId;
	}

	public void setSalaId(String salaId) {
		this.salaId = salaId;
	}
}