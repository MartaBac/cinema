package cinema;

/**
 * 
 * Viene utilizzato per la ricerca, per contare il numero di occorrenze di una stringa.
 */
public class Comparator {
	private int count;
	public Comparator(){
		this.count = 0;
	}
	
	/**
	 * Assegna un punteggio in base a quante volte appare la parola
	 * 
	 * @param tag - Stringa da verificare se presente
	 * @param s - Stringa che potrebbe contenere 'tag'
	 * @return count - Il numero di occorrenze di tag in s
	 */
	public int Compare(String tag,String s){
		this.count = 0;
		if( ((String) s).contains(tag)){
			count++;
		}	
		return count;
	}
	
	/**
	 * Assegna un punteggio in base a quante volte appare la parola
	 * 
	 * @param tag - Stringa da verificare se presente
	 * @param s - Array di Stringhe che potrebbero contenere 'tag'
	 * @return count - il numero di volte che si è trovato 'tag' nelle 's'
	 */
	public int Compare(String tag, String[] s){
		int count = 0;		
		for(String comp : (String[]) s){
			if(comp.contains(tag))
				count++;
		}		
		return count;
	}
}
