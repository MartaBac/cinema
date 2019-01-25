package cinema;

import java.math.BigDecimal;

public class spettacolo2D extends spettacolo {
	public spettacolo2D(String id, String dateShow, String start, String end) {
		super(id, dateShow, start, end);
		// TODO Auto-generated constructor stub
	}
	static BigDecimal ticketCost = new BigDecimal("8.50");
	static BigDecimal reducedTicketcost = new BigDecimal("6");
}
