package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.cars.AnyCar;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.CarsDAO;
import ua.khpi.golik.db.dao.MySqlDAOFactory;

/**
 * Servlet implementation class InsertNewCarController
 */
@WebServlet("/InsertNewCarController")
public class InsertNewCarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(InsertNewCarController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNewCarController() {
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
		Locale loc = (Locale) request.getSession().getAttribute("language");
		String language = loc.toString();
		Integer id = (Integer) request.getSession().getAttribute("carID");
		AnyCar currentCar = (AnyCar) request.getSession().getAttribute("editCar");
		String newCarName = request.getParameter("carName");
		String newCarMark = request.getParameter("carMark");
		String newCarClass_RU = request.getParameter("carClassRU");
		String newCarClass_EN = request.getParameter("carClassEN");
		int newCarPrice = Integer.parseInt(request.getParameter("carPrice"));
		int newCarPledge = Integer.parseInt(request.getParameter("carPledge"));
		String newCarImage = request.getParameter("carImage");
		AbstractDAOFactory facroty = MySqlDAOFactory.getDAOFactory();
		CarsDAO car = facroty.getCarsDAO();
		try {
			boolean b = car.updateCarByID(id, newCarName, newCarMark, newCarClass_RU, newCarClass_EN, newCarPrice, newCarPledge, newCarImage, currentCar);
			if(b == true) {
				LOG.info("Car with id " + id + " has been updated!");
					request.getSession().setAttribute("carUpdate", "Car with id " + id + " has been updated!");
					response.sendRedirect("auto.jsp");
			} else {
				LOG.error("ERROR WHILE UPDATING THE CAR WITH ID " + id);
				response.sendRedirect("500.jsp");
			}
		} catch (SQLException e) {
			LOG.error("SQLException in InsertNewCarController() " + e.getMessage());
			response.sendRedirect("500.jsp");
		}
	}

}
