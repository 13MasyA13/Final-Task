package ua.khpi.golik.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.SortOperations;
import ua.khpi.golik.bl.cars.AnyCar;
import ua.khpi.golik.db.dao.CarsDAO;

/**
 * Servlet implementation class CarSortController
 */
@WebServlet("/CarSortController")
public class CarSortController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(CarsDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarSortController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<AnyCar> list = (ArrayList<AnyCar>) request.getSession().getAttribute("carList");
		String q = request.getQueryString();
		String mark = request.getParameter("carMark");
		String markAttr = (String) request.getAttribute("carMark");
		String carClass = request.getParameter("carClass");
		boolean sortByPrice = Boolean.getBoolean(request.getParameter("sortByPrice"));
		boolean sortByName = Boolean.getBoolean(request.getParameter("sortByName"));
		if(mark.equals("")) {
			// DO NOTHING
		} else {
			list = SortOperations.choiceForMark(list, mark);
			LOG.info("CarList has choiced for Mark " + mark);
		}
		
		if(carClass.equals("")) {
			// DO NOTHING
		} else {
			list = SortOperations.choiceForClass(list, carClass);
			LOG.info("CarList has choiced for CarClass " + carClass);
		}
		
		if(sortByPrice == false) {
			list = SortOperations.normalSortByPrice(list);
			LOG.info("CarList has normal sorted by price");
		} else {
			list = SortOperations.reverseSortByPrice(list);
			LOG.info("CarList has reverse sorted by price");
		}
		
		if(sortByName == false) {
			list = SortOperations.normalSortByName(list);
			LOG.info("CarList has normal sorted by name");
		} else {
			list = SortOperations.reverseSortByName(list);
			LOG.info("CarList has reverse sorted by name");
		}
		
		request.setAttribute("carList", list);
		request.getRequestDispatcher("auto.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
