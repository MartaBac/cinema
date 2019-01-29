package cinema;

public class ManagerCinema extends Cassiere {

	private static boolean status = true;	
	
	public ManagerCinema(String nickname, String name, String surname, String email, String dateOfBirth,
			String password, boolean log, String phoneNumber, boolean status) {
		super(nickname, name, surname, email, dateOfBirth, password, log, phoneNumber, status);
		role = "Manager";
	}

	public void disableEmployee(String employeeId){
		
	}
	
	public void showEmployeeList(){
		
	}
	
	public void manageCalendar(){
		
	}
	
	public void showEmployeeProfile(String employeeId){
		
	}
}
