package cinema;

public class Cassiere extends Utente {
	
	private String phoneNumber;
	private boolean status;

	public Cassiere( String nickname, String name, String surname, String email, String dateOfBirth,
			String password, boolean log, String phoneNumber, boolean status) {
		super( nickname, name, surname, email, dateOfBirth, password);
		// TODO Auto-generated constructor stub
		
		this.phoneNumber = phoneNumber;
		this.status = status;
		role = "Cassiere";
	}

}
