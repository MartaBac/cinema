package cinema;

/**
 * È una rappresentazione di un posto a sedere nella <code>Sala</code> di un 
 * <code>Cinema</code>.
 * Ogni <code>Seat</code> ha come attributi un id, il numero di riga e di colonna, se è 
 * un posto usabile (non lo è ad esempio se il sedile è rotto) e gli attributi free e 
 * clientId i quali servono quando viene aggiunto un nuovo spettacolo, in quanto esso 
 * comporta la creazione di una copia dei posti a sedere della sala in cui avverrà lo 
 * spettacolo e questi posti dovranno avere gli attributi che permettano di sapere se 
 * sono liberi i posti, e in caso di prenotazione da chi sono stati prenotati.
 * 
 * @author Marta Bacigalupo
 */

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

	/**
	 * Gets il numero della colonna 
	 * 
	 * @return int - column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Sets il numero della colonna 
	 * 
	 * @param int - column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Gets il numero di riga/fila
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets il numero di riga
	 * 
	 * @param int - row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Ritorna la stato del sedile, se è disponibile oppure occupato.
	 * 
	 * @return true se il posto è libero
	 */
	public boolean isFree() {
		return free;
	}

	/**
	 * Permette di impostare il sedile allo stato di occupato o libero
	 * 
	 * @param boolean - b
	 */
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

	/**
	 * Ritorna lo stato di usabilità del sedile
	 * 
	 * @return true se si tratta di un posto utilizzabile
	 */
	public boolean isUsable() {
		return usable;
	}

	/**
	 * Sets lo stato di usabilità di un sedile 
	 * 
	 * @param boolean - usable
	 */
	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	/**
	 * Gets l'id del Seat
	 * 
	 * @return String - id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets l'id del sedile
	 * 
	 * @param String id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets l'id del cliente che ha prenotato il posto
	 * 
	 * @return String cliendId, null se non prenotato
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Sets clientId
	 * 
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
