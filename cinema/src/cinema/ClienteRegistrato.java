package cinema;

/**
 * Classe <code>ClienteRegistrato</code> che estente classe Utente. 
 * Si tratta di normali clienti che si sono registrati.
 * 
 * @author Marta Bacigalupo
 */
public class ClienteRegistrato extends Utente {
	
	/**
	 * Costruttore della classe <code>ClienteRegistrato</code> che richiama il 
	 * costruttore della classe base <code>Utente</code>.
	 * 
	 * @param nickname			Nickname/username 
	 * @param name				Nome proprio
	 * @param surname			Cognome
	 * @param email				Indirizzo e-mail
	 * @param dateOfBirth		Data di nascita
	 * @param password			Password
	 */
	public ClienteRegistrato( String nickname, String name, String surname, String email, String dateOfBirth,
			String password) {
		super( nickname, name, surname, email, dateOfBirth, password);
		permesso = Permesso.USER;
		status = true;
	}

	/** 
	 * Ritorna lo stato dell'Utente.
	 * 
	 * @return true se attivo
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Sets lo status.
	 * 
	 * @param boolean a cui lo si vuole settare.
	 */
	public void setStatus(boolean b) {
		this.status = b;
		return;	
	}

	@Override
	public String getPhone() {
		return null;
	}

	@Override
	public String getCinema() {
		return null;
	}	
}
