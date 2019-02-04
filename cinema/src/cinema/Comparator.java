package cinema;

public class Comparator {
	private int count;
	public Comparator(){
		this.count = 0;
	}
	
	public int Compare(String tag,String s){
		this.count = 0;
		if( ((String) s).contains(tag)){
			count++;
		}	
		return count;
	}
	
	public int Compare(String tag, String[] s){
		this.count = 0;
		if(s!=null){
			for(String comp : s){
				if(comp.contains(tag))
					count++;
			}
		}
		return count;
	}
	
	public void resetCount(){
		count = 0;
	}
	
	public int getCount(){
		return count;
	}
		
	
}
