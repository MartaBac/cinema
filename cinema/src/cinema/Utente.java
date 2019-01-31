package cinema;

public abstract class Utente {
	private String nickname,name,surname, email,dateOfBirth,
		password;
	protected Permesso permesso;
	private boolean isLoggedIn;
	protected abstract void setStatus(boolean b);
	protected abstract boolean getStatus();
	protected abstract boolean isEmployee();
	
	public Utente(String nickname, String name, String surname, String email, 
			String dateOfBirth, String password) {
		this.nickname = nickname;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.setLoggedIn(false);	
	}
	@Override
	public String toString() {
		return name + " " + surname + " " + permesso;
	}

	public void showProfile() {
		System.out.println("nickname: " + nickname);
		System.out.println("nome: " + name);
		System.out.println("cognome: " + surname);
		System.out.println("email: " + email);
		System.out.println("data di nascita: " + dateOfBirth);	
	}

	public String getPassword() {
		return password;
	}

	public Permesso getRole() {
		return permesso;
	}

	public void setRole(Permesso permesso) {
		this.permesso = permesso;
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
}
