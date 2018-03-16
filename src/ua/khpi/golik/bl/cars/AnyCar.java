package ua.khpi.golik.bl.cars;

public final class AnyCar {
	
	protected int id;
	
	protected int pledge;
	
	protected String carName;
	
	protected String carClassRU;
	
	protected String carClassEN;
	
	protected String carMark;
	
	protected int carTotalPrice;
	
	private String image;
	
	private int from30toMoreDaysPrice;
	
	private int from10to30DaysPrice;
	
	private int from4to9DaysPrice;
	
	private int from2to3DaysPrice;

	public int getFrom30toMoreDaysPrice() {
		return from30toMoreDaysPrice;
	}
	
	public void setFrom30toMoreDaysPrice(int from30toMoreDaysPrice) {
		this.from30toMoreDaysPrice = from30toMoreDaysPrice;
	}
	
	public int getFrom10to30DaysPrice() {
		return from10to30DaysPrice;
	}
	
	public void setFrom10to30DaysPrice(int from10to30DaysPrice) {
		this.from10to30DaysPrice = from10to30DaysPrice;
	}
	
	public int getFrom4to9DaysPrice() {
		return from4to9DaysPrice;
	}
	
	public void setFrom4to9DaysPrice(int from4to9DaysPrice) {
		this.from4to9DaysPrice = from4to9DaysPrice;
	}
	
	public int getFrom2to3DaysPrice() {
		return from2to3DaysPrice;
	}
	
	public void setFrom2to3DaysPrice(int from2to3DaysPrice) {
		this.from2to3DaysPrice = from2to3DaysPrice;
	}
	/**
	 * @return the pledge
	 */
	public int getPledge() {
		return pledge;
	}
	/**
	 * @param pledge the pledge to set
	 */
	public void setPledge(int pledge) {
		this.pledge = pledge;
	}
	/**
	 * @return the carName
	 */
	public String getCarName() {
		return carName;
	}
	/**
	 * @param carName the carName to set
	 */
	public void setCarName(String carName) {
		this.carName = carName;
	}
	/**
	 * @return the carClass
	 */
	/**
	 * @return the carMark
	 */
	public String getCarMark() {
		return carMark;
	}
	/**
	 * @param carMark the carMark to set
	 */
	public void setCarMark(String carMark) {
		this.carMark = carMark;
	}
	public String getCarClassRU() {
		return carClassRU;
	}
	public void setCarClassRU(String carClassRU) {
		this.carClassRU = carClassRU;
	}
	public String getCarClassEN() {
		return carClassEN;
	}
	public void setCarClassEN(String carClassEN) {
		this.carClassEN = carClassEN;
	}
	/**
	 * @return the carTotalPrice
	 */
	public int getCarTotalPrice() {
		return carTotalPrice;
	}
	/**
	 * @param carTotalPrice the carTotalPrice to set
	 */
	public void setCarTotalPrice(int carTotalPrice) {
		this.carTotalPrice = carTotalPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
