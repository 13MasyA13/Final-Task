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

import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.MySqlDAOFactory;
import ua.khpi.golik.db.dao.OrdersDAO;
import ua.khpi.golik.db.dao.UserDAO;

/**
 * Servlet implementation class AcceptOrderController
 */
@WebServlet("/AcceptOrderController")
public class AcceptOrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = Logger.getLogger(AcceptOrderController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptOrderController() {
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
		int id = Integer.parseInt(request.getParameter("acceptID"));
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		OrdersDAO orderDAO = factory.getOrderDAO();
		UserDAO userDAO = factory.getUserDAO();
		try {
			boolean b = orderDAO.acceptOrderById(id);
			int userID = orderDAO.getUserIdByOrderId(id);
			int price = orderDAO.getPriceByOrderId(id);
			boolean ab = userDAO.payForOrder(price, userID);
			UserBean user = userDAO.selectUserById(id);
			if(ab == true) {
				LOG.info("User with ID " + userID + " has finally payed for his order");
			} else {
				boolean n = orderDAO.notAcceptOrderById(id);
				if(user.getFirstName().charAt(0) <= 155) {
					boolean c = orderDAO.updateDescriptionById(id, "On your bank accout not enough money, please top up "
							+ "your account to complete the order");
				} else {
					boolean c = orderDAO.updateDescriptionById(id, "Ќа вашем счете не достаточно средств, пожалуйста, пополните ваш счет "
							+ "дл€ совершени€ заказа");
				}
			}
		} catch (SQLException e) {
			LOG.info("SQLException in AcceptOrderController " + e.getMessage());
		}
	}

}
