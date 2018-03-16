package ua.khpi.golik.db.dao;

public class MySqlDAOFactory extends AbstractDAOFactory{

	@Override
	public UserDAO getUserDAO() {
		return new UserDAO();
	}

	@Override
	public ManagerDAO getManagerDAO() {
		return new ManagerDAO();
	}

	@Override
	public CarsDAO getCarsDAO() {
		return new CarsDAO();
	}

	@Override
	public OrdersDAO getOrderDAO() {
		return new OrdersDAO();
	}

	@Override
	public AdministratorDAO getAdministratorDAO() {
		return new AdministratorDAO();
	}

	@Override
	public CallbackDAO getCallbackDAO() {
		return new CallbackDAO();
	}

	@Override
	public FeedbackDAO getFeedbackDAO() {
		return new FeedbackDAO();
	}

}
