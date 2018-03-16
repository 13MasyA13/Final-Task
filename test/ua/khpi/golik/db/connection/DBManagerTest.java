package ua.khpi.golik.db.connection;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Test;

public class DBManagerTest {
	
	private static DBManagerTest instance;

	private DataSource ds;

	public static synchronized DBManagerTest getInstance() {
		if (instance == null) {
			instance = new DBManagerTest();
		}
		return instance;
	}
	
	public DBManagerTest(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/testDB");
		} catch (NamingException exc) {

		}
	}
	
	@Test
	public void testGetInstance() {
		
	}

	@Test
	public void testGetConnection() throws SQLException {
		Connection conn = null;
		assertNotNull(conn = ds.getConnection());
	}

}
