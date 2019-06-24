package cinema;

public class ManagerCinema extends Cassiere {
		
	public ManagerCinema(String nickname, String name, String surname, String email, String dateOfBirth,
			String password, String phoneNumber,String cinemaId) {
		super(nickname, name, surname, email, dateOfBirth, password, phoneNumber, cinemaId);
		permesso = Permesso.MANAGER;
		status = true;
	}
	
}
