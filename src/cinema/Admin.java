package cinema;

/**
 * Rappresenta l'amministratore del sistema, del circuito nella sua totalità.
 * Ci può essere un solo <code>Admin</code>.
 * 
 * @author Marta Bacigalupo
 */

public final class Admin extends ManagerCinema {

	/**
	 * Costruisce un oggetto <code>Admin</code> usando i parametri passategli e 
	 * chiamando il costruttore della classe <code>ManagerCinema.java</code> che esso 
	 * estende.
	 * 
	 * @param nickname 				Il nicknname/username di autenticazione dell'Utente
	 * @param name					Il nome
	 * @param surname				Il cognome
	 * @param email					Indirizzo e-mail
	 * @param dateOfBirth			Data di nascita
	 * @param password				Password
	 * @param phoneNumber			Numero di telefono
	 * @param cinemaId				Id del cinema che verrà messo a null, in quanto 
	 * 								parametro ereditato da <code>ManagerCinema</code>, 
	 * 								<code>Admin</code> non lo ha
	 */
	public Admin( String nickname, String name, String surname, String email, String dateOfBirth,
			String password,  String phoneNumber, String cinemaId) {
		super( nickname, name, surname, email, dateOfBirth, password,  phoneNumber, cinemaId);
		permesso = Permesso.ADMIN;
		cinemaId = null;
		status = true;
	}	
	
	@Override
	public void setCinemaId(String c){
		throw new UnsupportedOperationException();		
	}
	
	@Override
	public void setStatus(boolean b){
		return;
	}
}
