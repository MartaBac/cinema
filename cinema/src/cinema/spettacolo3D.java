package cinema;

import java.math.BigDecimal;

public class spettacolo3D extends spettacolo {
	public spettacolo3D(String id, String dateShow, String start, String end) {
		super(id, dateShow, start, end);
		// TODO Auto-generated constructor stub
	}
	static BigDecimal ticketCost = new BigDecimal("10");
	static BigDecimal reducedTicketcost = new BigDecimal("8");
}

