package cinema;

public class Film {

	private String idFilm, title, releaseDate, summary, country;	
	private String[] actors,genre,tags,producer,director;
	
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
	 * @param String[] tags
	 **/
	public Integer compareTag(String[] t){
		
		int[] count = new int[]{0,0,0,0,0,0,0,0};
		// Pesi per ogni categoria
		int[] weight = new int[]{100,1,5,55,15,50,25,25};
		int point = 0;
		Comparator c = new Comparator();
		
		// Implementare algoritmo per trovare le corrispondenze migliori		
		//Per ogni parola ricercata
		for(String e : t){
			if(e == null)
				continue;
			count[0] = count[0] + c.Compare(e, this.title);
			count[1] = count[1] + c.Compare(e, this.summary);
			count[2] = count[2] + c.Compare(e, this.country);
			count[3] = count[3] + c.Compare(e, this.actors);
			count[4] = count[4] + c.Compare(e, this.genre);
			count[5] = count[5] + c.Compare(e, this.tags);
			count[6] = count[6] + c.Compare(e, this.producer);
			count[7] = count[7] + c.Compare(e, this.director);
	}	

	for(int j=0; j<count.length; j++){
		point = point + (count[j]*weight[j]);
	}		
		return point;
	}
	
	public void printBaseInfo(){
		System.out.println(this.title);
	}

	public String getTitle() {
		return title;
	}

	public String getIdFilm() {
		return idFilm;
	}
	
	public String getReleaseDate(){
		return releaseDate;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public String getCountry(){
		return country;
	}
	
	@Override
	public String toString() {
		return idFilm + " - " + title;
	}

	public String[] getProducer() {
		return producer;
	}

	public void setProducer(String[] producer) {
		this.producer = producer;
	}

	public String[] getDirector() {
		return director;
	}

	public void setDirector(String[] director) {
		this.director = director;
	}

	public String[] getGenre() {
		return genre;
	}
	
	public String[] getActor(){
		return actors;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
