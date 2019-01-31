package cinema;


public enum Permesso { 
	ADMIN(true, 3), 
	MANAGER(true, 2), 
	CASSIERE(true, 1),
	USER(false, 0);
	
	private final boolean employee;
    private final int permission;	
    
    private Permesso(boolean e, int p) {
        this.employee = e;
        this.permission = p;
    }

	public boolean isEmployee() {
		return employee;
	}

	public int getPermission() {
		return permission;
	}
}

