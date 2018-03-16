package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.UserDAO;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(RegistrationController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory();
		boolean isEnglish = false;
		char ch = request.getParameter("firstName").charAt(0);
		if(ch <= 122) {
			isEnglish = true;
		}
		String login = request.getParameter("login");
		UserDAO userDAO = factory.getUserDAO();
		ArrayList<String> logins = userDAO.selectAllUsersLogins();
		UserBean user = new UserBean();
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		try {
			boolean b = userDAO.insertNewUser(user, isEnglish, logins);
			if(b == true) {
				// TODO Send to user thanks for reg
				LOG.info("The new user has registred " + user.getLogin());
				user = null;
				response.sendRedirect("index.jsp");
			} else {
				// TODO Show to user login is not unique
			}
		} catch (SQLException e) {
			LOG.error("Some error in doPost() [reg controller] " + e.getMessage());
		}
	}
}
