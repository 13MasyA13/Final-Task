package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.cars.AnyCar;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.CarsDAO;
import ua.khpi.golik.db.dao.MySqlDAOFactory;

/**
 * Servlet implementation class AutoController
 */
@WebServlet("/AutoController")
public class AutoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(AutoController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		CarsDAO dao = factory.getCarsDAO();
		HttpSession session = request.getSession(true);
		try {
			ArrayList<AnyCar> carList = dao.selectAllCars();
			getServletContext().setAttribute("carList", carList);
			session.setAttribute("carList",getServletContext().getAttribute("carList"));
			response.sendRedirect("auto.jsp");
		} catch (SQLException e) {
			LOG.error("SQL Exception in doGet() from AutoController " + e.getMessage());
		}
	}

}
