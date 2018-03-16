package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.MySqlDAOFactory;
import ua.khpi.golik.db.dao.UserDAO;

/**
 * Servlet implementation class UnlockUserController
 */
@WebServlet("/UnlockUserController")
public class UnlockUserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(UnlockUserController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnlockUserController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("userUnlockID"));
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		try {
			boolean b = userDAO.unlockUserByID(id);
			if(b == true) {
				LOG.info("User with id " + id + " has been unloked");
				response.sendRedirect("admin_office.jsp");
			} else {
				LOG.error("User with id " + id + " has not been uloked");
				response.sendRedirect("admin_office.jsp");
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in UnlockUserController " + exc.getMessage());
		}
	}

}
