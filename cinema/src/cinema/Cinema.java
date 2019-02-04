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
	
	public void printBaseInfo(){
		System.out.println("Nome: " + nome + "; Città: " + citta);	
	}
	
public Integer compareTag(String[] t){
		
		int[] count = new int[]{0,0,0};
		// Pesi da rivalutare
		int[] weight = new int[]{100,50,20};
		int point = 0;
		Comparator c = new Comparator();
		
		// Implementare algoritmo per trovare le corrispondenze migliori		
		//Per ogni parola ricercata
		for(String e : t){
			if(e == null)
				continue;
			count[0] = count[0] + c.Compare(e, this.nome);
			count[1] = count[1] + c.Compare(e, this.citta);
			count[2] = count[2] + c.Compare(e, this.indirizzo);
	}	

	for(int j=0; j<count.length; j++){
		point = point + (count[j]*weight[j]);
	}		
		return point;
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
