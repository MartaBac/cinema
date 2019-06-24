package cinema;

public abstract class Utente {
	private String nickname,name,surname, email,dateOfBirth,
		password;
	protected boolean status;
	protected Permesso permesso;
	private boolean isLoggedIn = false;
	protected abstract void setStatus(boolean b);
	protected abstract boolean getStatus();
	public abstract String getPhone();
	public abstract String getCinema();
	
	public Utente(String nickname, String name, String surname, String email, 
			String dateOfBirth, String password) {
		this.nickname = nickname;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.status = true;
	}
	@Override
	public String toString() {
		return name + " " + surname + " " + permesso;
	}

	public String showProfile() {
		String s = "nickname: " + nickname + "\nnome: " + name + "\ncognome: " + surname
				+ "\nemail: " + email + "\ndata di nascita: " + dateOfBirth + "\npermesso: " 
				+ permesso;	
		return s;
	}

	public String getPassword() {
		return password;
	}

	public Permesso getRole() {
		return permesso;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return dateOfBirth;
	}

	public void setBirth(String birth) {
		this.dateOfBirth = birth;
	}
	
	public boolean isEmployee(){
		return this.permesso.isEmployee();
	}
	
	public int getPermesso(){
		return this.permesso.getPermission();
	}
}
