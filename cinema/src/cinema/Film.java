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
	
	public int compareTag(String[] t){
		int count = 0;
		// Implementare algoritmo per trovare le corrispondenze migliori
		return count;
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



}
