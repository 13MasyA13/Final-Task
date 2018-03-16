package ua.khpi.golik.db.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.khpi.golik.bl.users.UserBean;

public interface UserDBOperations {
	
	public ArrayList<UserBean> selectAllUsers();
	
	public ArrayList<String> selectAllUsersLogins();
	
	public boolean insertNewUser(UserBean user, boolean isEnglish, ArrayList<String> logins) throws SQLException;
	
	public boolean blockUserByID(int id, String desc) throws SQLException;
	
	public boolean unlockUserByID(int id) throws SQLException;
	
	public boolean selectUserLogPas(String login, String password);
	
	public UserBean selectCurrentUser(String login);
	
	public boolean userBrokeCar(int id) throws SQLException;
	
	public boolean userSmallDamageCar(int id) throws SQLException;
	
	public boolean payForOrder(int price, int id) throws SQLException;
	
	public UserBean selectUserById(int id);
	
}
