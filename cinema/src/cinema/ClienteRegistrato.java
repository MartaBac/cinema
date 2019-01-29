package cinema;

public class ClienteRegistrato extends Utente {
	

	public ClienteRegistrato( String nickname, String name, String surname, String email, String dateOfBirth,
			String password) {
		super( nickname, name, surname, email, dateOfBirth, password);
		role = "Cliente";
	}
	
	public void viewReservations(){
		// TODO
	}
	
	public void cancelReservation(){
		// TODO
	}

}
