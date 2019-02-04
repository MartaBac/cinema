package cinema;

import java.util.Arrays;

public class Film {

	private String idFilm, title, releaseDate, summary, country;
	
	private String[] actors,genre,tags,producer,director;
	
	public Film(String idFilm, String title, String releaseDate, String summary,
			String country, String[] actors, String[] genre, String[] tags,
			String[] producer, String[] director){
		this.setIdFilm(idFilm);
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
	
	public void printAllInfo(){	
		System.out.println("Titolo film:\t" + this.title +"\n");
		System.out.print("Data di uscita:\t" + this.releaseDate + "\n");
		System.out.println("Trama:\t" + this.summary + "\n");
		System.out.println("Paese di produzione:\t" + this.country + "\n");
		System.out.println("Attori:\t" );
		System.out.println(Arrays.toString(actors));
		System.out.println("Genere:\t" );
		System.out.println(Arrays.toString(genre));
		System.out.println("Produttore:\t" );
		System.out.println(Arrays.toString(producer));
		System.out.println("Direttore:\t" );
		System.out.println(Arrays.toString(director));
	}
	
	/**
	 * Permette di ottenere un punteggio in base alle occorrenze delle parole cercate nella scheda
	 * del film; questo punteggio è ottenuto pesato, cioè a seconda dell'ambito in cui appare la parola
	 * vale più o meno ai fini del punteggio.
	 * @param String[] tags
	 **/
	public Integer compareTag(String[] t){
		
		int[] count = new int[]{0,0,0,0,0,0,0,0};
		// Pesi da rivalutare
		int[] weight = new int[]{100,1,5,25,15,40,25,25};
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

	public void setIdFilm(String idFilm) {
		this.idFilm = idFilm;
	}
	
	/*
	 * da completare restituendo altri dati?
	 */
	@Override
	public String toString() {
		return title;
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

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	private int compare(String tag, Object s){
		int count = 0;
		if(s == null)
			return 0;
		if(s instanceof String[]){			
			for(String comp : (String[]) s){
				if(comp.contains(tag))
					count++;
			}		
		}
		else if(s instanceof java.lang.String){
			if( ((String) s).contains(tag)){
				count++;
			}
		}
		return count;
	}
}
