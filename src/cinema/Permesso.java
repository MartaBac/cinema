package cinema;

/**
 * Permessi esistenti: ADMIN, MANAGER, CASSIERE, USER.
 * Il campo employee dice se si tratta di account dipendenti, mentre permission il
 * livello di permesso che si ha.
 * 
 * @author Marta Bacigalupo
 */
public enum Permesso { 
	ADMIN(true, 3), 
	MANAGER(true, 2), 
	CASSIERE(true, 1),
	USER(false, 0);
	
	private final boolean employee;
    private final int permission;	
    
    private Permesso(boolean employee, int permission) {
        this.employee = employee;
        this.permission = permission;
    }

    /**
     * Permette di sapere se si tratta di un dipendente.
     * 
     * @return employee - boolean che è true se si tratta di account dipendente
     */
	public boolean isEmployee() {
		return employee;
	}

	/**
	 * Restituisce il permesso
	 * 
	 * @return permission - int che dice il livello di permesso
	 */
	public int getPermission() {
		return permission;
	}
	
	/**
	 * Dice se si tratta di un datore di lavoro o meno
	 * 
	 * @return boolean se Manager o Admon
	 */
	public boolean isEmployer(){
		if(this.permission==2||this.permission==3){
			return true;
		}
		else
			return false;
	}
}

