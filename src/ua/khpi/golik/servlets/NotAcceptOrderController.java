package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.MySqlDAOFactory;
import ua.khpi.golik.db.dao.OrdersDAO;

/**
 * Servlet implementation class NotAcceptOrderController
 */
@WebServlet("/NotAcceptOrderController")
public class NotAcceptOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotAcceptOrderController() {
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
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		String description = request.getParameter("description");
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		OrdersDAO orderDAO = factory.getOrderDAO();
		try {
			orderDAO.notAcceptOrderById(orderID);
			orderDAO.updateDescriptionById(orderID, description);
		} catch (SQLException e) {
		}
	}

}
