package cinema;

public final class Admin extends ManagerCinema {

	public Admin( String nickname, String name, String surname, String email, String dateOfBirth,
			String password, boolean log, String phoneNumber, boolean status) {
		super( nickname, name, surname, email, dateOfBirth, password, log, phoneNumber, status);
		permesso = Permesso.ADMIN;
	}
	
	public boolean isEmployee(){
		return false;
	}


	
}
