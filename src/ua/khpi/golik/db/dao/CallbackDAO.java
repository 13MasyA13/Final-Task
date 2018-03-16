package ua.khpi.golik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.CallBackDBOperations;

public class CallbackDAO implements CallBackDBOperations{
	private static Logger LOG = Logger.getLogger(CarsDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SQL_INSERT_NEW_CALLBACK = "INSERT INTO call_back VALUES (NULL, ?, ?)";
	
	Connection conn = null;
	PreparedStatement prSt = null;
	
	@Override
	public boolean insertNewCallback(String telephone, String name) {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_INSERT_NEW_CALLBACK);
			prSt.setString(1, telephone);
			prSt.setString(2, name);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("New callback has added!");
				return true;
			} else {
				LOG.warn("New call back has't added");
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQL Exception in insertNewCallback() " + exc.getMessage());
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
