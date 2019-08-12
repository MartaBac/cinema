package cinema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/** 
 * Rappresenta un singolo Cinema del circuito. Contiene come parametri il proprio id,
 * il nome del cinema, la città e indirizzo di esso e contiene una mappa delle Sale 
 * presenti.
 * 
 * @author Marta Bacigalupo
 */
public class Cinema {
	
	private String idCinema;
	private String nome,citta, indirizzo;
	private HashMap<String, Sala> listaSale;

	/**
	 * Costruisce un oggetto di tipo Cinema.
	 * 
	 * @param id			Id del <code>Cinema</code>
	 * @param name			Nome 
	 * @param citta			Città di locazione
	 * @param ind			Indirizzo
	 */
	public Cinema(String id,String name, String citta, String ind) {
		this.idCinema = id;
		this.nome = name;
		this.citta = citta;
		this.indirizzo = ind;
		this.listaSale = new HashMap<String, Sala>();
	}
	
	/** 
	 * Permette di aggiungere una nuova Sala al cinema
	 * 
	 * @param sala Oggetto Sala
	 * @return true se l'inserimento è avvenuto con successo
	 */
	public boolean addSala(Sala sala ) {
		if(listaSale.containsKey(sala.getSalaId())){
			return false;
		}
		else{
			listaSale.put(sala.getSalaId(), sala);
			return true;
		}
	}

	/** 
	 * Stampa la scheda del cinema, tutte le info che si hanno su di esso;
	 * ritorna le suddette informazioni sotto forma di String
	 * 
	 * @return String
	 */
	public String printAllInfo(){
		String s ="Nome\n" + nome + "\nIndirizzo:\n" + indirizzo + "\nNumero sale:\n" + 
				listaSale.size();
		return s;
	}
	
	/**
	 * Stampa le informazioni base del Cinema, cioè il suo nome e città.
	 *  
	 * @return String contenente le informazioni
	 */
	public String printBaseInfo(){
		String s = "Nome: " + nome + "; Città: " + citta;	
		return s;
	}
	
	/**
	 * Assegna un punteggio in base a quanto i tag sono frequenti fra i parametri
	 * del Cinema e dove appaiono, in quanto non tutti i campi hanno lo stesso peso.
	 * 
	 * @param t String[] di tag da comparare
	 * @return int punteggio
	 */
	public Integer compareTag(String[] t){		
		int[] count = new int[]{0,0,0};
		int[] weight = new int[]{100,50,20};
		int point = 0;
		Comparator c = new Comparator();
			
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
	
	/**
	 * Gets nome del cinema
	 * 
	 * @return stringa contenente il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets il nome del Cinema
	 * 
	 * @param String nome del cinema
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets nome del cinema
	 * 
	 * @return stringa contenente il nome
	 */
	public String getIdCinema() {
		return idCinema;
	}

	/**
	 * Gets indirizzo del cinema
	 * 
	 * @return stringa contenente il indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets l'indirizzo del Cinema
	 * 
	 * @param String indirizzo del cinema
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets numero sale del cinema
	 * 
	 * @return int contenente il numero di sale
	 */
	public int getNumeroSale() {
		return listaSale.size();
	}

	/**
	 * Gets nome città del cinema
	 * 
	 * @return stringa contenente il nome della città
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Sets la città del Cinema
	 * 
	 * @param String nome della città in cui vi è il cinema
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	/**
	 * Gets mappa delle sale del cinema. La key è l'id della sala e il value la sala.
	 * 
	 * @return HashMap contenente le sale del cinema
	 */
	public HashMap<String, Sala> getSaleMap(){
		return listaSale;
	}
	
	/**
	 * Permette di settare gli spettacoli, eventi del cinema.
	 * La key dell'HashMap è l'id dello spettacolo.
	 * 
	 * @param s HashMap con key gli idSpettacolo e value gli Spettacolo.
	 * @return true se almeno un inserimento è stato effettuato; esso avviene se la
	 * 	Sala non contiene già l'idSpettacolo che si cerca di inserire.
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
			// Se la sala non era presente
			if(this.listaSale.get(id)==null)
				continue;	
			// Spettacolo già presente
			if(this.listaSale.get(id).getListaSpettacoli().containsKey(key))
				continue;
			else{
		    	this.listaSale.get(id).addSpettacolo(spett);
		    	b = true;
		    }			    
		}
		return b;	
	}
	
	/** 
	 * Permette di ottenere un HashMap contenente tutti gli spettacoli in programma per 
	 * un film specificato.
	 * 
	 * @param String id del film di cui cercare gli spettacoli
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
	
	/** 
	 * Stampa l'elenco di spettacoli in programma nel cinema
	 */	
	public void printSpettacoli(){
		this.listaSale.forEach((k,v)-> v.printSpettacoli());
	}
}
