package cinema;

/** Rappresenta un <code>Utente</code> di tipo <code>ManagerCinema</code> del Circuito. 
 * Vi è almeno un Manager per ogni cinema, ma ogni Manager non può gestire più di un 
 * <code>Cinema</code>.
 *  
 * @author Marta Bacigalupo
 */
public class ManagerCinema extends Cassiere {
		
	public ManagerCinema(String nickname, String name, String surname, String email, String dateOfBirth,
			String password, String phoneNumber,String cinemaId) {
		super(nickname, name, surname, email, dateOfBirth, password, phoneNumber, cinemaId);
		permesso = Permesso.MANAGER;
		status = true;
	}
}
