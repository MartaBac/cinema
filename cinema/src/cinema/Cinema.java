package cinema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Cinema {
	private String idCinema;
	private String nome,citta, indirizzo;
	// Ogni sala contiene l'elenco di spettacoli associati, la key è idSala
	private HashMap<String, Sala> listaSale;

	public Cinema(String id,String name, String citta, String ind) {
		this.idCinema = id;
		this.nome = name;
		this.citta = citta;
		this.indirizzo = ind;
		this.listaSale = new HashMap<String, Sala>();
	}
	
	public boolean addSala(Sala sala ) {
		if(listaSale.containsKey(sala.getSalaId())){
			return false;
		}
		else{
			listaSale.put(sala.getSalaId(), sala);
			return true;
		}
	}

	public String printAllInfo(){
		String s ="Nome\n" + nome + "\nIndirizzo:\n" + indirizzo + "\nNumero sale:\n" + 
				listaSale.size();
		return s;
	}
	
	public String printBaseInfo(){
		String s = "Nome: " + nome + "; Città: " + citta;	
		return s;
	}
	
	/**
	 * Assegna un punteggio in base a quanto i tag sono frequenti fra i parametri
	 * del Cinema e dove appaiono, in quanto non tutti i campi valgono uguale
	 * @param t - String[] di tag da comparare
	 * @return int - punteggio
	 */
	
	public Integer compareTag(String[] t){		
		int[] count = new int[]{0,0,0};
		// Pesi per nome, città e indirizzo
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
		return idCinema + " - " + nome;
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
	
	public HashMap<String, Sala> getSaleMap(){
		return listaSale;
	}
	
	/**
	 * Permette di settare gli spettacoli, eventi del cinema.
	 * La key dell'HashMap è l'id dello spettacolo
	 * 
	 * @return true se almeno un inserimento è stato effettuato
	 */
	
	public boolean addSpettacoli(HashMap<String,Spettacolo> s){
		boolean b = false;
		String id = null;
		String key = null;
		Spettacolo spett = null;
		for (Entry<String, Spettacolo> pair : s.entrySet()) {
			key = pair.getKey();
			id = pair.getValue().getIdSala();
			spett = pair.getValue();
			if(this.listaSale.get(id)==null)
				continue;
			// Se la sala non contiene idSpettacolo, lo spettacolo viene aggiunto		
		    if(!this.listaSale.get(id).getListaSpettacoli().containsKey(key)){
		    	this.listaSale.get(id).addSpettacolo(spett);
		    	b = true;
		    }
		    else // Se lo spettacolo è già presente identico o modificato non lo aggiungo
		    		continue; 		
		    }
		return b;	
	}
	
	/** Permette di ottenere un HashMap contenente tutti gli spettacoli in programma per 
	 * un film specificato.
	 * 
	 * @param (String) idMovie - id del film di cui cercare gli spettacoli
	 * @return HashMap<String, Spettacolo>
	 */
	public HashMap<String, Spettacolo> searchSpettacolo(String idMovie){
		Iterator<Entry<String, Sala>> it = this.listaSale.entrySet().iterator();
		Iterator<Entry<String, Spettacolo>> it2; 
		HashMap<String, Spettacolo> temp = new HashMap<String, Spettacolo>();
		Spettacolo s;
		while (it.hasNext()) {
		    Entry<String, Sala> pair = it.next();
		    temp = pair.getValue().getListaSpettacoli();
		    it2 = temp.entrySet().iterator();
		    while (it2.hasNext()){
		    	s = it2.next().getValue();
		    	if(s.getIdMovie().equals(idMovie))
		    		temp.put(s.getIdSpettacolo(), s);
		    }
		   }
		return temp;
	}
	
	/** Stampa l'elenco di spettacoli in programma nel cinema
	 * 
	 */
	
	public void printSpettacoli(){
		this.listaSale.forEach((k,v)-> v.printSpettacoli());
	}
}
