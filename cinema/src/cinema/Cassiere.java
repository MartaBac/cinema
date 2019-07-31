package cinema;

/**
 * Rappresenta un dipendente cassiere di uno specifico cinema della filiale.
 * Ci possono essere più cassieri per cinema, ma ognuno può lavorare per un unico cinema.
 * @author marta
 */
public class Cassiere extends Utente {
	
	private String phoneNumber;
	private String cinemaId;
	protected boolean status;

	/**
	 * Costruttore di un oggetto di tipo Cassiere usando i parametri di input.
	 *
	 * Chiama il costruttore della classe Utente.java.
	 * 
	 * @param  nickname					Il nickname del Cassiere (id)
	 * @param  name						Il nome proprio del Cassiere
	 * @param  surname					Il cognome del Cassiere
	 * @param  email					L'email del Cassiere
	 * @param  dateOfBirth				La data di nascita del Cassiere
	 * @param  password					La password per l'accesso del Cassiere
	 * @param  phoneNumber				Il numero di telefono del Cassiere
	 * @param cinemaId					L'id del cinema di cui è dipendente il Cassiere
	 */
	public Cassiere( String nickname, String name, String surname, String email, String dateOfBirth,
			String password, String phoneNumber, String cinemaId) {
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


	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return this.status;
	}

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
