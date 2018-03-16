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

import ua.khpi.golik.bl.PriceCoefs;
import ua.khpi.golik.bl.cars.AnyCar;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.CarsDAO;
import ua.khpi.golik.db.dao.MySqlDAOFactory;

/**
 * Servlet implementation class NewCarController
 */
@WebServlet("/NewCarController")
public class NewCarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(NewCarController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCarController() {
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
		String carName = request.getParameter("carName");
		String carMark = request.getParameter("carMark");
		String carClassRU = request.getParameter("carClassRU");
		String carClassEN = request.getParameter("carClassEN");
		int carPrice = Integer.parseInt(request.getParameter("carPrice"));
		int carPladge = Integer.parseInt(request.getParameter("carPladge"));
		String carImage = request.getParameter("carImage");
		
		AnyCar newCar = new AnyCar();
		newCar.setCarName(carName);
		newCar.setCarMark(carMark);
		newCar.setCarClassRU(carClassRU);
		newCar.setCarClassEN(carClassEN);
		newCar.setFrom30toMoreDaysPrice((int) (carPrice * PriceCoefs.FROM_30_TO_MORE_DAYS_COEF));
		newCar.setFrom10to30DaysPrice((int) (carPrice * PriceCoefs.FROM_10_TO_30_DAYS_COEF));
		newCar.setFrom4to9DaysPrice((int) (carPrice * PriceCoefs.FROM_4_TO_9_DAYS_COEF));
		newCar.setFrom2to3DaysPrice((int) (carPrice * PriceCoefs.FROM_2_TO_3_DAYS_COEF));
		newCar.setPledge(carPladge);
		newCar.setImage(carImage);
		
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		CarsDAO carDAO = factory.getCarsDAO();
		try {
			boolean b = carDAO.insertNewCar(newCar);
			if(b == true) {
				LOG.info("Car with name " + newCar.getCarName() + " has been inserted in DB");
				response.sendRedirect("admin_office.jsp");
			} else {
				throw new Exception();
			}
		} catch (SQLException e) {
			LOG.error("SQLException in NewCarController " + e.getMessage());
		} catch (Exception e) {
			LOG.error("Car with name " + newCar.getCarName() + " has not been inserted in DB");
		}
	}

}
