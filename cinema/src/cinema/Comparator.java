package cinema;
/**
 * 
 * Viene utilizzato per la ricerca
 */
public class Comparator {
	private int count;
	public Comparator(){
		this.count = 0;
	}
	
	/*
	 * Assegna un punteggio in base a quante volte appare la parola
	 */
	public int Compare(String tag,String s){
		this.count = 0;
		if( ((String) s).contains(tag)){
			count++;
		}	
		return count;
	}
	
	public int Compare(String tag, String[] s){
		int count = 0;		
		for(String comp : (String[]) s){
			if(comp.contains(tag))
				count++;
		}		
		return count;
	}
}
