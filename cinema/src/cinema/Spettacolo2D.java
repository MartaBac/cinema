package cinema;

import java.math.BigDecimal;

/**
 * Estende la classe <code>Spettacolo</code>. Rappresenta uno spettacolo di tipo 2D.
 * 
 * @author Marta Bacigalupo
 */
public class Spettacolo2D extends Spettacolo {

	public Spettacolo2D(String id, String dateShow, String start, String end, 
			String idCinema,String idMovie, Sala sala) {
		super(id, dateShow, start, end, idCinema,idMovie, sala);
		ticketCost = new BigDecimal("8.50");
		reducedTicketCost = new BigDecimal("6");
	}
	
	public Spettacolo2D(String id, String dateShow, String start, String end, 
			String idCinema, boolean vm,String idMovie, Sala sala) {
		super(id, dateShow, start, end, idCinema,vm,idMovie, sala);
		ticketCost = new BigDecimal("8.50");
		reducedTicketCost = new BigDecimal("6");
	}
}
