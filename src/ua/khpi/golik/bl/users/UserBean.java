package ua.khpi.golik.bl.users;

public final class UserBean extends AnyUser {
	
	private int id;
	
	private int order_id;

	protected String firstName;
	
	protected String lastName;
	
	private String status;
	
	private int countOfMoney;
	
	protected boolean smallDamaged;
	
	protected boolean brokenCar;
	
	protected boolean isBlocked;
	
	protected String email;
	
	private boolean isLogged;
	
	private boolean isAcceptred;
	
	private String description;
	
	private String reason;
	
	public UserBean() {
		this.status = "USER";
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the smallDamaged
	 */
	public boolean isSmallDamaged() {
		return smallDamaged;
	}

	/**
	 * @param smallDamaged the smallDamaged to set
	 */
	public void setSmallDamaged(boolean smallDamaged) {
		this.smallDamaged = smallDamaged;
	}

	/**
	 * @return the brokenCar
	 */
	public boolean isBrokenCar() {
		return brokenCar;
	}

	/**
	 * @param brokenCar the brokenCar to set
	 */
	public void setBrokenCar(boolean brokenCar) {
		this.brokenCar = brokenCar;
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
	 * @return the countOfMoney
	 */
	public int getCountOfMoney() {
		return countOfMoney;
	}

	/**
	 * @param countOfMoney the countOfMoney to set
	 */
	public void setCountOfMoney(int countOfMoney) {
		this.countOfMoney = countOfMoney;
	}

	/**
	 * @return the isBlocked
	 */
	public boolean isBlocked() {
		return isBlocked;
	}

	/**
	 * @param isBlocked the isBlocked to set
	 */
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public boolean isAccepted() {
		return isAcceptred;
	}

	public void setAcceptred(boolean isAcceptred) {
		this.isAcceptred = isAcceptred;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
