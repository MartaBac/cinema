package cinema;

import java.util.HashMap;
import java.util.Iterator;


public class Cinema {
	private String idCinema, nome,citta, indirizzo;
	private HashMap<String, Sala> listaSale;
	

	public Cinema(String id, String name, String citta, String ind) {
		this.idCinema = id;
		this.nome = name;
		this.setCitta(citta);
		this.indirizzo = ind;
		this.listaSale = new HashMap<String, Sala>();

	}
	
	public boolean addSala(Sala sala ) {
		Iterator<Sala> room = listaSale.values().iterator();
		while (room.hasNext()) {
			if (room.next().getSalaId().equals(sala.getSalaId())) {
				return false;
			}
		}
		
		listaSale.put(sala.getSalaId(), sala);
		return true;
	}
	

	public void printAllInfo(){
		System.out.println("Nome:");
		System.out.println(nome);
		System.out.println("Indirizzo:");
		System.out.println(indirizzo);
		System.out.println("Numero sale:");
		System.out.println(listaSale.size());
		
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdCinema() {
		return idCinema;
	}

	public void setIdCinema(String idCinema) {
		this.idCinema = idCinema;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getNumeroSale() {
		return listaSale.size();
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

}
