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

import ua.khpi.golik.bl.users.ManagerBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.ManagerDAO;
import ua.khpi.golik.db.dao.MySqlDAOFactory;

/**
 * Servlet implementation class NewManagerController
 */
@WebServlet("/NewManagerController")
public class NewManagerController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(NewManagerController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewManagerController() {
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
		try {
			String login = request.getParameter("managerLogin");
			String password = request.getParameter("managerPassword");
			String confPas = request.getParameter("confirmPassword");
			if(!password.equals(confPas)) {
				throw new NullPointerException();
			}
			ManagerBean newManager = new ManagerBean();
			newManager.setLogin(login);
			newManager.setPassword(password);
			AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
			ManagerDAO managerDAO = factory.getManagerDAO();
			ArrayList<String> list = (ArrayList<String>) getServletContext().getAttribute("logins");
			boolean b = managerDAO.insertNewManager(newManager, list);
			if(b == false) {
				throw new Exception();
			} else {
				LOG.info("New manager has been register with login " + newManager.getLogin());
				response.sendRedirect("admin_office.jsp");
			}
		} catch(SQLException exc) {
			LOG.info("SQLException in NewManagerController " + exc.getMessage());
		} catch (NullPointerException exc) {
			LOG.info("Passwords not equals in NewManagerController");
		} catch(Exception exc) {
			LOG.info("SQL Query exc");
		}
	}

}
