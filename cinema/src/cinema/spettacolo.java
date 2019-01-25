package cinema;

import java.math.BigDecimal;

public abstract class spettacolo {

	private String idSpettacolo, dateShow, startingTime,endingTime;
	private static BigDecimal ticketCost,reducedTicketCost;
	
	public spettacolo(String id,String dateShow,String start,String end){
		this.idSpettacolo = id;
		this.setDateShow(dateShow);
		this.setStartingTime(start);
		this.setEndingTime(end);
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
}