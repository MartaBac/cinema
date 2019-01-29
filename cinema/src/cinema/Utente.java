package cinema;

public abstract class Utente {
	private String nickname,name,surname,email,dateOfBirth,
		password;
	protected String role;
	private boolean isLoggedIn;
	
	public Utente(String nickname, String name, String surname, String email, 
			String dateOfBirth, String password) {
		this.setNickname(nickname);
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.setLoggedIn(false);

		
		
	}
	@Override
	public String toString() {
		return name + " " + surname + " " + role;
	}

	public void showProfile() {
		
	}

	public String getPassword() {
		return password;
	}





	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
