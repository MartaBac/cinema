package cinema;

public class ClienteRegistrato extends Utente {
	

	public ClienteRegistrato( String nickname, String name, String surname, String email, String dateOfBirth,
			String password) {
		super( nickname, name, surname, email, dateOfBirth, password);
		permesso = Permesso.USER;
	}

	@Override
	protected void setStatus(boolean b) {
		// TODO Auto-generated method stub
		
	}

	protected boolean getStatus() {
		return true;
	}

	@Override
	protected boolean isEmployee() {
		return false;
	}
	

}
