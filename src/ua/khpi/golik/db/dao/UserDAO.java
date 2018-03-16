package ua.khpi.golik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.UserDBOperations;

public class UserDAO implements UserDBOperations {
	
	private final static Logger LOG = Logger.getLogger(UserDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
	
	private final String SQL_SELECT_LOG_PASS = "SELECT * FROM users WHERE login = ? AND password = ?";
	
	private final String SQL_SELECT_USER_LOG = "SELECT * FROM users WHERE login = ?";
	
	private final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	
	private final String SQL_SELECT_ALL_USERS_LOGINS = "SELECT login FROM users WHERE id > ?";
	
	private final String SQL_ADD_NEW_USER_RU = "INSERT INTO users VALUES (NULL, ?, ?, ?, ?, NULL, NULL, ?, 10000, 0, NULL, NULL, NULL, NULL, NULL, NULL)";
	
	private final String SQL_ADD_NEW_USER_EN = "INSERT INTO users VALUES (NULL, ?, ?, NULL, NULL, ?, ?, ?, 10000, 0, NULL, NULL, NULL, NULL, NULL, NULL)";
	
	private final String SQL_BLOCK_USER_BY_ID = "UPDATE users SET isBlocked = 1, reason = ? WHERE id = ?";
	
	private final String SQL_UNLOCK_USER_BY_ID = "UPDATE users SET isBlocked = 0, reason = NULL WHERE id = ?";
	
	private final String SQL_USER_BROKE_THE_CAR = "UPDATE users SET brokenCar = 1 WHERE id = ?";
	
	private final String SQL_UESR_SMALL_DAMAGED = "UPDATE users SET smallDamaged = 1 WHERE id = ?";
	
	private final String SQL_PAY_FOR_ORDER = "UPDATE users SET count_of_money = count_of_money - ? WHERE id = ?";
	
	private final String SQL_SELECT_COUNT_OF_MONEY_BY_ID = "SELECT count_of_money FROM users WHERE id = ?";
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;
	
	@Override
	public ArrayList<UserBean> selectAllUsers() {
		ArrayList<UserBean> usersList = new ArrayList<>();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ALL_USERS);
			rs = prSt.executeQuery();
			while(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt(1));
				user.setLogin(rs.getString(2));
				if(rs.getString(4) == null && rs.getString(5) == null) {
					user.setFirstName(rs.getString(6));
					user.setLastName(rs.getString(7));
				} else {
					user.setFirstName(rs.getString(4));
					user.setLastName(rs.getString(5));
				}
				user.setEmail(rs.getString(8));
				user.setBlocked(rs.getBoolean(10));
				user.setOrder_id(rs.getInt(11));
				user.setSmallDamaged(rs.getBoolean(12));
				user.setBrokenCar(rs.getBoolean(13));
				LOG.info("User with id = " + user.getId() + " has been added to ArrayList");
				usersList.add(user);
			}
		} catch(SQLException exc) {
			LOG.error("SQL Exception in selectAllUsers()" + " " + exc.getMessage());
		}
		return usersList;
	}

	@Override
	public boolean insertNewUser(UserBean user, boolean isEnglish, ArrayList<String> logins) throws SQLException {
		try {
			String login = user.getLogin();
			Iterator<String> iterator = logins.iterator();
			while(iterator.hasNext()) {
				if(iterator.next().equals(login)) {
					throw new Exception();
				}
			}
			conn = DBManager.getInstance().getConnection();
			if(isEnglish == true) {
				prSt = conn.prepareStatement(SQL_ADD_NEW_USER_EN);
				prSt.setString(1, user.getLogin());
				prSt.setString(2, user.getPassword());
				prSt.setString(3, user.getFirstName());
				prSt.setString(4, user.getLastName());
				prSt.setString(5, user.getEmail());
				int i = prSt.executeUpdate();
				if(i > 0) {
					conn.commit();
					LOG.info("User with login = " + user.getLogin() + " has been inserted to table");
					return true;
				} else {
					conn.rollback();
					LOG.error("Row for " + user.getLogin() + " are not created");
					return false;
				}
			} else {
				prSt = conn.prepareStatement(SQL_ADD_NEW_USER_RU);
				prSt.setString(1, user.getLogin());
				prSt.setString(2, user.getPassword());
				prSt.setString(3, user.getFirstName());
				prSt.setString(4, user.getLastName());
				prSt.setString(5, user.getEmail());
				int i = prSt.executeUpdate();
				if(i > 0) {
					conn.commit();
					LOG.info("User with login = " + user.getLogin() + " has been inserted to table");
					return true;
				} else {
					conn.rollback();
					LOG.error("Row for " + user.getLogin() + " are not created");
					return false;
				}
			}
		} catch(SQLException exc) {
			conn.rollback();
			LOG.error("SQL Exception in insertNewUser()" + " " + exc.getMessage());
			return false;
		} catch (Exception e) {
			LOG.warn("User with login = " + user.getLogin() + " is already exists");
			return false;
		}	
	}

	@Override
	public boolean blockUserByID(int id, String desc) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_BLOCK_USER_BY_ID);
			prSt.setString(1, desc);
			prSt.setInt(2, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("User with id " + id + " was blocked");
				conn.commit();
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			conn.rollback();
			LOG.error("SQL Exception in blockUserByID() " + exc.getMessage());
			return false;
		} catch (Exception e) {
			conn.rollback();
			LOG.error("Update wasn't did");
			return false;
		}
		return true;
	}

	@Override
	public boolean selectUserLogPas(String login, String password) {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_LOG_PASS);
			prSt.setString(1, login);
			prSt.setString(2, password);
			rs = prSt.executeQuery();
			if(!rs.next())
				throw new Exception();
			while(rs.next()) {
				String log = rs.getString(2);
				String pass = rs.getString(3);
				if(log.equals(login) & pass.equals(password)) {
					LOG.info("USER'S LOG & PASS -> EQUALS");
					
				} else {
					LOG.info("USER'S LOG & PASS -> NOT EQUALS(");
					throw new Exception();
				}
			}
		} catch(SQLException exc) {
			LOG.error("SQL Exception in selectUserLogPas()" + " " + exc.getMessage());
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}

	@Override
	public boolean unlockUserByID(int id) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_UNLOCK_USER_BY_ID);
			prSt.setInt(1, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("User with id " + id + " was unlocked");
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			conn.rollback();
			LOG.error("SQL Exception in blockUserByID() " + exc.getMessage());
			return false;
		} catch (Exception e) {
			conn.rollback();
			LOG.error("Update wasn't did");
			return false;
		}
	}

	@Override
	public UserBean selectCurrentUser(String login) {
		UserBean user = new UserBean();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_USER_LOG);
			prSt.setString(1, login);
			rs = prSt.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setLogin(rs.getString(2));
				if(rs.getString(4) == null) {
					user.setFirstName(rs.getString(6));
					user.setLastName(rs.getString(7));
				} else {
					user.setFirstName(rs.getString(4));
					user.setLastName(rs.getString(5));
				}
				user.setBlocked(rs.getBoolean(10));
				user.setOrder_id(rs.getInt(11));
				user.setSmallDamaged(rs.getBoolean(12));
				user.setBrokenCar(rs.getBoolean(13));
				user.setAcceptred(rs.getBoolean(14));
				user.setDescription(rs.getString(15));
				user.setReason(rs.getString(16));
			}
			return user;
		} catch(SQLException exc) {
			LOG.error("SQL Exception in selectCurrentLog() " + exc.getMessage());
			return null;
		}
	}

	@Override
	public boolean userBrokeCar(int id) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_USER_BROKE_THE_CAR);
			prSt.setInt(1, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("User with id " + id + " has broke the car");
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in userBrokeTheCar() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Broke the car row has not updated");
			conn.rollback();
			return false;
		}
	}

	@Override
	public boolean userSmallDamageCar(int id) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_UESR_SMALL_DAMAGED);
			prSt.setInt(1, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("user");
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in userSmallDamageCar() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Row smallDamaed has not updated");
			conn.rollback();
			return false;
		}
	}

	@Override
	public ArrayList<String> selectAllUsersLogins() {
		ArrayList<String> logins = new ArrayList<>();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ALL_USERS_LOGINS);
			prSt.setInt(1, 0);
			rs = prSt.executeQuery();
			while(rs.next()) {
				logins.add(rs.getString(1));
				LOG.info("User with login " + rs.getString(1) + " has added from the list");
			}
			return logins;
		} catch(SQLException exc) {
			LOG.error("SQLException in selectAllUsersLogins() " + exc.getMessage());
			return null;
		}
	}

	@Override
	public boolean payForOrder(int price, int id) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_PAY_FOR_ORDER);
			prSt.setInt(1, price);
			prSt.setInt(2, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("User with id " + id + " pay for order");
				conn.commit();
				Connection newConn = DBManager.getInstance().getConnection();
				PreparedStatement newPrSt = newConn.prepareStatement(SQL_SELECT_COUNT_OF_MONEY_BY_ID);
				newPrSt.setInt(1, id);
				ResultSet newRs = newPrSt.executeQuery();
				while(newRs.next()) {
					if(newRs.getInt(1) < 0) {
						LOG.error("User don't have money for pay for order");
						conn.rollback();
						throw new Exception();
					}
			    }
				conn.commit();
				return true;
			} else {
				throw new ArithmeticException();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in payForOrder() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch(ArithmeticException exc) {
			conn.rollback();
			return false;
		} catch (Exception exc) {
			LOG.error("Row has not updated");
			conn.rollback();
			return false;
		}	
	}

	@Override
	public UserBean selectUserById(int id) {
		try {
			UserBean user = new UserBean();
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_USER_BY_ID);
			prSt.setInt(1, id);
			rs = prSt.executeQuery();
			while(rs.next()) {
				if(rs.getString(4) == null) {
					user.setFirstName(rs.getString(6));
					user.setLastName(rs.getString(7));
				} else {
					user.setFirstName(rs.getString(4));
					user.setLastName(rs.getString(5));
				}
			}
			return user;
		} catch(SQLException exc) {
			LOG.error("SQLException in selectUserById() " + exc.getMessage());
			return null;
		}
	}
	
}
