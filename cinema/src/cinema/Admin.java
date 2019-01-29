package cinema;

public class Admin extends ManagerCinema {

	public Admin( String nickname, String name, String surname, String email, String dateOfBirth,
			String password, boolean log, String phoneNumber, boolean status) {
		super( nickname, name, surname, email, dateOfBirth, password, log, phoneNumber, status);
		role = "Admin";
	}

	public void addMovie(){
		
	}
	
	public void addCinema(){
		
	}
	
	public void removeMovie(){
		
	}
	
	public void removeCinema(){
		
	}
}
