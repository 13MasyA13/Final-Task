package ua.khpi.golik.db.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class AdministratorDAOTest {
	
	private String SELECT_ADMIN_BY_LOG_PASS = "SELECT * FROM admins WHERE login = ? AND password = ?";
	
	private String userName = "root";
	private String userPassword = "max13120510";

	private Connection conn = null;
	private ResultSet rs;
	private PreparedStatement prSt;
	private String login;
	private String password;
	
	@Before
	public void getConn() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental", userName, userPassword);
		rs = null;
		prSt = null;
		login = "admin123";
		password = "q1w2e3";
	}
	
	@Test
	public void testSelectAdminByLogPass() throws SQLException {
		int i = 0;
		prSt = conn.prepareStatement(SELECT_ADMIN_BY_LOG_PASS);
		prSt.setString(1, login);
		prSt.setString(2, password);
		rs = prSt.executeQuery();
		while(rs.next()) {
			String log = rs.getString(2);
			String pass = rs.getString(3);
			assertEquals(log, login);
			assertEquals(pass, password);
		}
	}

}
