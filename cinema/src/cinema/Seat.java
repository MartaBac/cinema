package cinema;

public class Seat {
	private String id;
	private int row;
	private int column;
	private boolean free, usable;
	private String clientId;
	
	public Seat(Integer row, Integer column, boolean usable){
		this.row = row;
		this.column = column;
		this.free = true;
		this.usable = usable;
		this.clientId = null;
		this.id = "row" + this.row + "column" + this.column;
	}
	
	@Override
	public String toString(){
		return id + " " + Integer.toString(row) + " " + Integer.toString(column);
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean b) {
		this.free = b;
	}
	
	/**
	 * Imposta ad occupato da un cliente un posto
	 * 
	 * @param u - idUtente che prenota
	 * @return true se si ha successo
	 */
	public boolean setOccupied(String u){
		if(this.free == true){
			System.out.println("Set to " + u);
			this.setClientId(u);
			this.free = false;
			return true;
		}
		else
			return false;
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	

}
