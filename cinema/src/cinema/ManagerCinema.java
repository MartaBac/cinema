package cinema;

public class ManagerCinema extends Cassiere {
		
	public ManagerCinema(String nickname, String name, String surname, String email, String dateOfBirth,
			String password, boolean log, String phoneNumber, boolean status) {
		super(nickname, name, surname, email, dateOfBirth, password, log, phoneNumber);
		permesso = Permesso.MANAGER;	
	}
	
	@Override
	public boolean getStatus(){
		return true;
	}
	
	@Override
	public void setStatus(boolean status) {
		return;
	}
}
