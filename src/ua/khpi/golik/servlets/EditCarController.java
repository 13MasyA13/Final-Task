package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class EditCarController
 */
@WebServlet("/EditCarController")
public class EditCarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(EditCarController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCarController() {
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
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("carID"));
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		CarsDAO carDAO = factory.getCarsDAO();
		try {
			AnyCar car = carDAO.selectCarByID(id);
			session.setAttribute("editCar", car);
			session.setAttribute("carID", id);
			response.sendRedirect("editCar.jsp");
		} catch (SQLException e) {
			LOG.error("SQLException in EditCarController() " + e.getMessage());
			response.sendRedirect("500.jsp");
		}
	}

}
