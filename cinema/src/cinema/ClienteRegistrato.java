package cinema;

public class ClienteRegistrato extends Utente {
		
	public ClienteRegistrato( String nickname, String name, String surname, String email, String dateOfBirth,
			String password) {
		super( nickname, name, surname, email, dateOfBirth, password);
		permesso = Permesso.USER;
		status = true;
	}

	public boolean getStatus() {
		return status;
	}

	@Override
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
