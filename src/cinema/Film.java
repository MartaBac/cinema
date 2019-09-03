package cinema;

/**
 * Rappresenta un film presente nel circuito cinema. 
 * 
 * @author Marta Bacigalupo
 */

public class Film {

	private String idFilm, title, releaseDate, summary, country;	
	private String[] actors,genre,tags,producer,director;
	
	/**
	 * Costruisce un oggetto di tipo Film sfruttando i parametri passati.
	 *  
	 * @param idFilm			Id del film
	 * @param title				Titolo dell'opera
	 * @param releaseDate		Data di uscita
	 * @param summary			Trama
	 * @param country			Nazione di produzione
	 * @param actors			Array contenente i nomi degli attori
	 * @param genre				Array con i generi del film
	 * @param tags				Array contenente tag, parole chiave
	 * @param producer			Array coi nomi dei produttori
	 * @param director			Array coi nomi dei direttori
	 */
	public Film(String idFilm, String title, String releaseDate, String summary,
			String country, String[] actors, String[] genre, String[] tags,
			String[] producer, String[] director){
		this.idFilm = idFilm;
		this.title = title;
		this.releaseDate = releaseDate;
		this.summary = summary;
		this.country = country;
		this.actors = actors;
		this.genre = genre;
		this.tags = tags;
		this.producer = producer;
		this.director = director;
	}
	
	/**
	 * Stampa tutte le informazioni che si hanno sul film.
	 * 
	 * @return una Stringa contenente tutte le informazioni
	 */
	public String printAllInfo(){	
		String s = "Titolo film:\t" + this.title + "\nData di uscita:\t" + this.releaseDate
				+ "\nTrama:\t" + this.summary + "\nPaese di produzione:\t" + this.country
				+ "\nAttori:\n" + String.join("\n", this.actors) + "\nGenere:\n"
				+ String.join("\n", this.genre) + "\nProduttore:\n"
				+ String.join("\n", this.producer) + "\nDirettore:\n" 
				+ String.join("\n", this.director);
		return s;
	}
	
	/**
	 * Permette di ottenere un punteggio in base alle occorrenze delle parole cercate nella scheda
	 * del film; questo punteggio è ottenuto pesato, cioè a seconda dell'ambito in cui appare la parola
	 * vale più o meno ai fini del punteggio.
	 * 
	 * @param t String[] contenente i tag
	 * @return intero che rappresenta il numero di occorrenze delle parola cercata nella
	 * scheda del film
	 **/
	public Integer compareTag(String[] t){
		
		int[] count = new int[]{0,0,0,0,0,0,0,0};
		int[] weight = new int[]{100,1,5,55,15,50,25,25}; // Pesi per ogni categoria
		int point = 0;
		Comparator c = new Comparator();
		
		for(String e : t){
			if(e == null)
				continue;
			count[0] = count[0] + c.compare(e, this.title);
			count[1] = count[1] + c.compare(e, this.summary);
			count[2] = count[2] + c.compare(e, this.country);
			count[3] = count[3] + c.compare(e, this.actors);
			count[4] = count[4] + c.compare(e, this.genre);
			count[5] = count[5] + c.compare(e, this.tags);
			count[6] = count[6] + c.compare(e, this.producer);
			count[7] = count[7] + c.compare(e, this.director);
		}	

		for(int j=0; j<count.length; j++){
			point = point + (count[j]*weight[j]);
		}		
			return point;
	}
	
	/**
	 * Stampa il titolo del film.
	 */
	public void printBaseInfo(){
		System.out.println(this.title);
	}

	/**
	 * Gets titolo film.
	 * 
	 * @return Stringa contenente title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets idFilm.
	 * 
	 * @return Stringa contenente idFilm
	 */
	public String getIdFilm() {
		return idFilm;
	}
	
	/**
	 * Gets data di uscita del film.
	 * 
	 * @return Stringa conentenete releaseDate
	 */	
	public String getReleaseDate(){
		return releaseDate;
	}
	
	/**
	 * Gets la trama del film.
	 * 
	 * @return Stringa contenente summary
	 */
	public String getSummary(){
		return summary;
	}
	
	/**
	 * Gets Stato di produzione del film.
	 * 
	 * @return String conentente country
	 */
	public String getCountry(){
		return country;
	}
	
	@Override
	public String toString() {
		return idFilm + " - " + title;
	}

	/**
	 * Gets elenco dei produttori del Film.
	 * 
	 * @return array di stringhe contenenti i produttori
	 */
	public String[] getProducer() {
		return producer;
	}

	/**
	 * Sets l'array di produttori del film
	 * 
	 * @param producer - l'array di produttori
	 */
	public void setProducer(String[] producer) {
		this.producer = producer;
	}

	/**
	 * Gets l'elenco di direttori del film.
	 * 
	 * @return array di stringhe contenente i direttori
	 */
	public String[] getDirector() {
		return director;
	}

	/**
	 * Sets l'aelenco di direttori del film
	 * 
	 * @param director - array di direttori
	 */
	public void setDirector(String[] director) {
		this.director = director;
	}

	/**
	 * Gets l'elenco di generi del film.
	 * 
	 * @return array di stringhe contenente i generi del film
	 */	
	public String[] getGenre() {
		return genre;
	}
	
	/**
	 * Gets l'elenco di attori del film.
	 * 
	 * @return array di stringhe contenente gli attori
	 */
	public String[] getActor(){
		return actors;
	}

	/**
	 * Sets l'elenco di generi del film.
	 * 
	 * @param genre array di Stringhe contenente i generi
	 */
	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	/**
	 * Gets l'elenco di tag del film.
	 * 
	 * @return array di stringhe contenente i tag
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * Gets l'elenco di tag del film.
	 * 
	 * @param tags array di stringhe contenente i tag del film
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
