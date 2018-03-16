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

import ua.khpi.golik.bl.OrdersBean;
import ua.khpi.golik.bl.cars.AnyCar;
import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.MySqlDAOFactory;
import ua.khpi.golik.db.dao.OrdersDAO;

/**
 * Servlet implementation class NewOrderController
 */
@WebServlet("/NewOrderController")
public class NewOrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = Logger.getLogger(OrdersDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrderController() {
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
		Locale currentLocale = (Locale) request.getSession().getAttribute("language");
		String language = currentLocale.toString();
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		OrdersBean order = new OrdersBean();
		
		UserBean user = (UserBean) request.getSession().getAttribute("currentUser");
		AnyCar car = (AnyCar) request.getSession().getAttribute("orderCar");
		
		order.setUser_id(user.getId());
		order.setCar_id(car.getId());
		order.setFirstName(user.getFirstName());
		order.setLastName(user.getLastName());
		order.setPassport(request.getParameter("passport"));
		order.setDateOfBirthday(request.getParameter("bornDate"));
		order.setAddress(request.getParameter("address"));
		order.setFromDate(request.getParameter("firstDate"));
		order.setToDate(request.getParameter("secondDate"));
		order.setTotal_price(Integer.parseInt(request.getParameter("answ")));
		int isWithDriver = Integer.parseInt((request.getParameter("isWithDriver")));
		if(isWithDriver > 0) {
			order.setWithDriver(true);
		} else {
			order.setWithDriver(false);
		}
		OrdersDAO orderDAO = factory.getOrderDAO();
		try {
			boolean b = orderDAO.insertNewOrder(order, user);
			if(b == true) {
				LOG.info("User with id " + user.getId() + " maked the order with car " + car.getCarName());
				if(language.equals("ru_RU")) {
					request.getSession().setAttribute("confirmOrder", "Ваш заказ принят, ожидайте одтверждения!");
				} else {
					request.getSession().setAttribute("confirmOrder", "Your order is accepted, wait for confirmation!");
				}
				response.sendRedirect("AutoController");
			} else {
				LOG.info("Order with car " + car.getCarName() + " has not done");
				if(language.equals("ru_RU")) {
					request.getSession().setAttribute("badOrder", "Ошибка в процессе создания заказа, повторите попытку");
				} else {
					request.getSession().setAttribute("badOrder", "Error while creating the order, try again");
				}
				response.sendRedirect("AutoController");
			}
		} catch (SQLException e) {
			LOG.info("SQL Exception in NewOrderController " + e.getMessage());
			response.sendRedirect("500.jsp");
		}
	}
}
