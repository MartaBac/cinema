package cinema;

public class Cassiere extends Utente {
	
	private String phoneNumber;
	private String cinemaId;
	protected boolean status;


	public Cassiere( String nickname, String name, String surname, String email, String dateOfBirth,
			String password, String phoneNumber, String cinemaId) {
		super( nickname, name, surname, email, dateOfBirth, password);
		this.phoneNumber = phoneNumber;
		this.cinemaId = cinemaId;
		this.status = true;
		permesso = Permesso.CASSIERE;
	}
	
	@Override
	public String showProfile() {
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
