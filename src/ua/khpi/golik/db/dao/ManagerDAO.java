package ua.khpi.golik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.users.ManagerBean;
import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.ManagerDBOperations;

public class ManagerDAO implements ManagerDBOperations {
	
	private static final Logger LOG = Logger.getLogger(ManagerDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SQL_SELECT_LOG_PASS = "SELECT * FROM managers WHERE login = ? AND password = ?";
	
	private final String SQL_SELECT_ALL_MANAGERS = "SELECT * FROM managers";
	
	private final String SQL_INSERT_NEW_MANAGER = "INSERT INTO managers VALUES(NULL, ?, ?)";
	
	private final String SQL_GET_MANAGERS_LOGGINS = "SELECT login FROM managers WHERE id > ?";
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;
	
	@Override
	public ArrayList<ManagerBean> selectAllManagers() throws SQLException {
		ArrayList<ManagerBean> managerList = new ArrayList<>();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ALL_MANAGERS);
			rs = prSt.executeQuery();
			while(rs.next()) {
				ManagerBean manager = new ManagerBean();
				manager.setId(rs.getInt(1));
				manager.setLogin(rs.getString(2));
				LOG.info(manager.getLogin() + " has added to ArrayList");
				managerList.add(manager);
			}
		} catch(SQLException exc) {
			LOG.error("SQL Exception in selectAllManagers()" + " " + exc.getMessage());
		}
		return managerList;
	}

	@Override
	public boolean insertNewManager(ManagerBean manager, ArrayList<String> logins) throws SQLException {
		int i = 0;
		try {
			String newLogin = manager.getLogin();
			Iterator<String> iterator = logins.iterator();
			while(iterator.hasNext()) {
				if(iterator.next().equals(newLogin)) {
					throw new Exception();
				}
			}
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_INSERT_NEW_MANAGER);
			prSt.setString(1, manager.getLogin());
			prSt.setString(2, manager.getPassword());
			i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Manager with login = " + manager.getLogin() + " has been insert");
				conn.commit();
				return true;
			} else {
				conn.rollback();
				LOG.info("Manager with login = " + manager.getLogin() + " has't been insert");
				return false;
			}
			
		} catch(SQLException exc) {
			LOG.error("SQL Exception in insertNewManager()" + " " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Manager with login " + manager.getLogin() + " has already exists");
			conn.rollback();
			return false;
		}
	}

	@Override
	public boolean selectManagerLogPass(String login, String password) {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_LOG_PASS);
			prSt.setString(1,login);
			prSt.setString(2, password);
			rs = prSt.executeQuery();
			while(rs.next()) {
				String log = rs.getString(2);
				String pas = rs.getString(3);
				if(log.equals(login) & pas.equals(password)) {
					LOG.info("MANAGER LOG & PASS -> EQUALS!");
					break;
				} else {
					LOG.warn("MANAGER LOG & PASS -> NOT EQUALS(");
					throw new Exception();
				}
			}
			return true;
		} catch(SQLException exc) {
			LOG.error("SQL Exception in selectManagerLogPass()" + " " + exc.getMessage());
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<String> getAllManagersLogins() {
		ArrayList<String> list = new ArrayList<>();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_GET_MANAGERS_LOGGINS);
			prSt.setInt(1, 0);
			rs = prSt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
				LOG.info("Manager with login " + rs.getString(1) + " has added from the list");
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in getAllManagersLogins() " + exc.getMessage());
			return null;
		}
		return list;
	}
	
}
