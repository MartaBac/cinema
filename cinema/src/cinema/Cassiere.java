package cinema;

public class Cassiere extends Utente {
	
	private String phoneNumber;
	protected boolean status;

	public Cassiere( String nickname, String name, String surname, String email, String dateOfBirth,
			String password, boolean log, String phoneNumber) {
		super( nickname, name, surname, email, dateOfBirth, password);
		
		this.phoneNumber = phoneNumber;
		this.status = true;
		permesso = Permesso.CASSIERE;
	}
	
	public void showProfile() {
		System.out.println("nickname: " + this.getNickname());
		System.out.println("nome: " + this.getName());
		System.out.println("cognome: " + this.getSurname());
		System.out.println("email: " + this.getEmail());
		System.out.println("data di nascita: " + this.getBirth());	
		System.out.println("phone number: " + this.getPhoneNumber());
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return this.status;
	}

	@Override
	protected boolean isEmployee() {
		// TODO Auto-generated method stub
		return true;
	}



}
