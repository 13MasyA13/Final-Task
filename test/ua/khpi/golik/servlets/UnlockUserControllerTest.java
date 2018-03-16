package ua.khpi.golik.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.MySqlDAOFactory;
import ua.khpi.golik.db.dao.UserDAO;

public class UnlockUserControllerTest extends Mockito {
	@Mock
	HttpServletRequest req;
	
	@Mock
	HttpServletResponse resp;
	
	@Test
	public void unlockUser() throws ServletException, IOException, SQLException {		
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		when(userDAO.unlockUserByID(4)).thenReturn(true);
	}

}
