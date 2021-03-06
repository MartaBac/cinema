package cinema;

import java.math.BigDecimal;

/**
 * Estende la classe <code>Spettacolo</code>. Rappresenta uno spettacolo di tipo 3D.
 * 
 * @author Marta Bacigalupo
 */
public class Spettacolo3D extends Spettacolo {
	public Spettacolo3D(String id, String dateShow, String start, String end,
			String idCinema,String idMovie, Sala sala) {
		super(id, dateShow, start, end, idCinema,idMovie, sala);
		ticketCost = new BigDecimal("10");
		reducedTicketCost = new BigDecimal("8");
	}
	
	public Spettacolo3D(String id, String dateShow, String start, String end,
			boolean vm, String idCinema,String idMovie, Sala sala) {
		super(id, dateShow, start, end, idCinema,vm,idMovie, sala);
		ticketCost = new BigDecimal("10");
		reducedTicketCost = new BigDecimal("8");
	}
}

