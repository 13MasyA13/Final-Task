package ua.khpi.golik.bl.users;

public final class AdministratorBean extends AnyUser {
	
	private String status;
	
	private boolean isLogged;
	
	public AdministratorBean() {
		this.status = "ADMIN";
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * @param isLogged the isLogged to set
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}
