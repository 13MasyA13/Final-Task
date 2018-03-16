package ua.khpi.golik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.AdministratorDBOperations;

public class AdministratorDAO implements AdministratorDBOperations{
	
	private static final Logger LOG = Logger.getLogger(AdministratorDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SELECT_ADMIN_BY_LOG_PASS = "SELECT * FROM admins WHERE login = ? AND password = ?";
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;
	
	@Override
	public boolean selectAdminByLogPass(String login, String password) {
		try {
			int i = 0;
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SELECT_ADMIN_BY_LOG_PASS);
			prSt.setString(1, login);
			prSt.setString(2, password);
			rs = prSt.executeQuery();
			while(rs.next()) {
				String log = rs.getString(2);
				String pass = rs.getString(3);
				if(login.equals(log) & password.equals(pass)) {
					LOG.info("Admin log & pass EQUALS!");
					i++;
				} else {
					LOG.warn("Admin tried to input incorrect login & password");
				}
			}
			if(i > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException exc) {
			LOG.error("SQL EXCEPTION IN METHOD selectAdminByLogPass()" + " -> " + exc.getMessage());
			return false;
		}
	}

}
