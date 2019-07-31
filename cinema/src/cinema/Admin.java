package cinema;

/**
 * Rappresenta l'amministratore del sistema, del circuito nella sua totalità.
 * Ci può essere un solo Admin.
 * @author marta
 *
 */

public final class Admin extends ManagerCinema {

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
