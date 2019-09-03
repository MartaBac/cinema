package cinema;
/**
 * Classe astratta che rappresenta un generico Utente, che è caratterizzato da un
 *  nickname, name, surname, email, dateOfBirth, password, permesso e isLoggedIn.
 * Nickname e password sono i dati necessari ad effettuare il login.
 * Permesso dipenderà dalla classe che estende questa, isLoggedIn dice se 
 * l'<code>Utente</code> è loggato o meno.
 * 
 * @author Marta Bacigalupo
 */

public abstract class Utente {
	private String nickname,name,surname, email, dateOfBirth,
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
		String format = "%-40s%s%n";
		String repeated = new String(new char[60]).replace("\0", "-");
		System.out.println(repeated);
		System.out.printf(format, "Nickname:", nickname);
		System.out.printf(format, "Name:", name);
		System.out.printf(format, "Cognome:", surname);
		System.out.printf(format, "E-mail:", email);
		System.out.printf(format, "Data di nascita:", dateOfBirth);
		System.out.printf(format, "Permesso:", permesso);
		System.out.println(repeated);
		
		String s = "nickname: " + nickname + "\nnome: " + name + "\ncognome: " + surname
				+ "\nemail: " + email + "\ndata di nascita: " + dateOfBirth + 
				"\npermesso: " + permesso;	
		return s;
	}

	/**
	 * Gets la password utente
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets il permesso Utente
	 * 
	 * @return Permesso permesso
	 */
	public Permesso getRole() {
		return permesso;
	}

	/**
	 * Ritorna se l'utente è loggato o meno
	 * 
	 * @return isLoggedIn true se è loggato
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * Cambia lo stato di autenticazione dell'Utente. Imposta isLoggedIn ad un parametro
	 * boolean specificato.
	 * 
	 * @param isLoggedIn true significa che è loggato, false che non lo è
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	/**
	 * Gets il nome dell'Utente
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets il nome dell'Utente
	 * 
	 * @param name nome utente
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets il cognome dell'Utente.
	 * 
	 * @return String surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets il cognome dell'Utente.
	 * 
	 * @param surname cognome
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets il nickname dell'Utente
	 * 
	 * @return String nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets il nickname dell'Utente
	 * 
	 * @param nickname nickname utente
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Gets l'indirizzo email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets l'indirizzo email
	 * 
	 * @param email email utente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets la data di nascita dell'Utente
	 * 
	 * @return String dateOfBirth
	 */
	public String getBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets la data di nascita dell'Utente
	 * 
	 * @param birth data di nascita dell'utente
	 */
	public void setBirth(String birth) {
		this.dateOfBirth = birth;
	}
	
	/**
	 * Dice se l'Utente è un impiegato della catena di cinema
	 *  
	 * @return true se è impiegato, false altrimenti
	 */
	public boolean isEmployee(){
		return this.permesso.isEmployee();
	}
	
	/**
	 * Permette di sapere il livello di permesso dell'Utente.
	 * 
	 * @return int - 3 permesso massimo, 0 minimo
	 */
	public int getPermesso(){
		return this.permesso.getPermission();
	}
	
	/**
	 * Gets permesso dell'Utente
	 * 
	 * @return Permesso permesso
	 */
	public Permesso getPermessoObj(){
		return this.permesso;
	}
}
