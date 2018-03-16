package ua.khpi.golik.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.khpi.golik.bl.OrdersBean;
import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.MySqlDAOFactory;
import ua.khpi.golik.db.dao.OrdersDAO;
import ua.khpi.golik.db.dao.UserDAO;

/**
 * Servlet implementation class ManagerCabController
 */
@WebServlet("/ManagerCabController")
public class ManagerCabController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerCabController() {
        super();
        // TODO Auto-generated constructor stub
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
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		OrdersDAO orderDAO = factory.getOrderDAO();
		UserDAO userDAO = factory.getUserDAO();
		ArrayList<UserBean> users = userDAO.selectAllUsers();
		ArrayList<OrdersBean> orders = orderDAO.selectAllOrders();
		request.getSession().setAttribute("allOrders", orders);
		request.getSession().setAttribute("users", users);
		response.sendRedirect("manager_office.jsp");
	}

}
