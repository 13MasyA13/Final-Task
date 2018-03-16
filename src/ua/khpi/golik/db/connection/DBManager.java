package ua.khpi.golik.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class DBManager {
	
	private final static Logger LOG = Logger.getLogger(DBManager.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private static DBManager instance;

	private DataSource ds;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/testDB");
		} catch (NamingException exc) {
			LOG.error("ERROR WHILE CREATING DATA SOURCE " + exc.getMessage(), exc);
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException exc) {
			LOG.error("ERROR WHILE TRYING TO CONNECTING TO DB " + exc.getMessage(), exc);
		}
		return con;
	}
}
