package ua.khpi.golik.db.dao;

public abstract class AbstractDAOFactory {
	
	public abstract AdministratorDAO getAdministratorDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract ManagerDAO getManagerDAO();
	
	public abstract CarsDAO getCarsDAO();
	
	public abstract OrdersDAO getOrderDAO();
	
	public abstract CallbackDAO getCallbackDAO();
	
	public abstract FeedbackDAO getFeedbackDAO();
	
	public static MySqlDAOFactory getDAOFactory() {
		return new MySqlDAOFactory();
	}
}
