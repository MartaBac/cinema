package cinema;

/**
 * Rappresenta un dipendente cassiere di uno specifico cinema della filiale.
 * Ci possono essere più cassieri per cinema, ma ognuno può lavorare per un unico cinema.
 * 
 * @author Marta Bacigalupo
 */
public class Cassiere extends Utente {
	
	private String phoneNumber;
	private String cinemaId;
	protected boolean status;

	/**
	 * Costruttore di un oggetto di tipo <code>Cassiere</code> che chiama il costruttore
	 *  della classe base <code>Utente</code>.
	 *
	 * Chiama il costruttore della classe Utente.java.
	 * 
	 * @param  nickname					Il nickname del <code>Cassiere</code> (id)
	 * @param  name						Il nome proprio
	 * @param  surname					Il cognome
	 * @param  email					L'indirizzo e-mail
	 * @param  dateOfBirth				La data di nascita
	 * @param  password					La password per l'accesso
	 * @param  phoneNumber				Il numero di telefono
	 * @param cinemaId					L'id del cinema di cui è dipendente
	 */
	public Cassiere( String nickname, String name, String surname, String email, 
			String dateOfBirth, String password, String phoneNumber, String cinemaId) {
		super( nickname, name, surname, email, dateOfBirth, password);
		this.phoneNumber = phoneNumber;
		this.cinemaId = cinemaId;
		this.status = true;
		permesso = Permesso.CASSIERE;
	}
	
	/**
	 * Restituisce una stringa contenente le informazioni sul profilo del Cassiere e 
	 * le stampa.
	 * 
	 * @return String contenente le info stampate
	 */
	@Override
	public String showProfile() {
		String format = "%-40s%s%n";
		String repeated = new String(new char[60]).replace("\0", "-");
		System.out.println(repeated);
		System.out.printf(format, "Nickname:", this.getNickname());
		System.out.printf(format, "Name:", this.getName());
		System.out.printf(format, "Cognome:", this.getSurname());
		System.out.printf(format, "E-mail:", this.getEmail());
		System.out.printf(format, "Data di nascita:", this.getBirth());
		System.out.printf(format, "Numero di telefono:", this.getPhone());
		System.out.printf(format, "Luogo di lavoro:", this.getCinema());
		System.out.printf(format, "Permesso:", permesso);
		System.out.println(repeated);
		
		String s = "nickname: " + this.getNickname() + "\nnome: " + this.getName()
		+ "\ncognome: " + this.getSurname() + "\nemail: " + this.getEmail() + 
		"\ndata di nascita: " + this.getBirth() + "\nphone number: " + this.getPhone()
		+ "\ncinema: " + this.getCinema() + "\npermesso: " + permesso;
		return s;
	}

	/**
	 * Sets il numero di telefono del Cinema
	 * 
	 * @param String numero del telefono
	 */
	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Permette di settare lo stato.
	 * 
	 * @param boolean status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/** Ritorna lo stato, se è attivo o meno.
	 * 
	 * @return true se attivo
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Sets l'id del Cinema
	 * 
	 * @param String id del cinema
	 */
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}

	@Override
	public String getPhone() {
		return phoneNumber;		
	}
	@Override
	public String getCinema() {
		return cinemaId;
	}
}
