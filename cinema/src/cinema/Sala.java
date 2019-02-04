package cinema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sala {
	private String cinemaId, salaId, name, usableSeats;
	private Integer capacity,rows,columns;
	//
	
	private HashMap<String, spettacolo> listaSpettacoli;

	
public Sala(String cinemaId, String name, String usableSeats, Integer cap, Integer rows,
			Integer col) {
	int length;
	this.cinemaId = cinemaId;
	this.name = name;
	this.usableSeats = usableSeats;
	this.capacity = cap;
	this.rows = rows;
	this.columns = col;
	this.salaId = cinemaId + name;
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

public String getSalaId() {
	return salaId;
}

public void setSalaId(String salaId) {
	this.salaId = salaId;
}

public void printInfo(){
	System.out.println(salaId + "//" + cinemaId  + "//" + name + "//" + usableSeats + "//" +
				capacity + "//" + rows + "//" + columns);
}

public HashMap<String, spettacolo> getListaSpettacoli() {
	return listaSpettacoli;
}

public void setListaSpettacoli(HashMap<String, spettacolo> listaSpettacoli) {
	this.listaSpettacoli = listaSpettacoli;
}
}
