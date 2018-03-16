package ua.khpi.golik.db.dao.interfaces;

public interface AdministratorDBOperations {
	
	public boolean selectAdminByLogPass(String login, String password);
}
