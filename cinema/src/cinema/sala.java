package cinema;

import java.util.HashMap;
import java.util.Map;

public class sala {
	private String cinemaId, name, usableSeats;
	private Integer capacity,rows,columns;

	
public sala(String cinemaId, String name, String usableSeats, Integer cap, Integer rows,
			Integer col) {
	int length;
	this.setCinemaId(cinemaId);
	this.setName(name);
	this.setUsableSeats(usableSeats);
	this.setCapacity(cap);
	this.setRows(rows);
	this.columns = col;
	length = usableSeats.length();
	if (length % columns != 0 || (columns*rows) != length||(columns*rows) != capacity ) 
	{ 
	    System.out.println("Invalid Input: capacity/usable seats and # of column/rows "
	    		+ "does not match"); 
	    return; 
	} 
	

}

public void getSeatsMap() {
	Seat[] s = new Seat[capacity];
	Map<Seat, String> map = new HashMap<Seat, String>();
	int t = 0;
	char y;
	// Making a map with seats and their availability (broken or not )
	for(Integer i = 0;i<rows;i++){
		for(Integer j=0;j<columns;j++){
			s[t] = new Seat(i, j);
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
	this.usableSeats = usableSeats;
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

public void setRows(Integer rows) {
	this.rows = rows;
}

public Integer getCapacity() {
	return capacity;
}

public void setCapacity(Integer capacity) {
	this.capacity = capacity;
}

public String getCinemaId() {
	return cinemaId;
}

public void setCinemaId(String cinemaId) {
	this.cinemaId = cinemaId;
}
}
