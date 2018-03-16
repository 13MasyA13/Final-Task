package ua.khpi.golik.servlets;

import java.io.IOException;
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
 * Servlet implementation class UserCabController
 */
@WebServlet("/UserCabController")
public class UserCabController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCabController() {
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
		UserBean user = (UserBean) request.getSession().getAttribute("currentUser");
		response.sendRedirect("user_office.jsp");
		/*
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		OrdersDAO orderDAO = factory.getOrderDAO();
		OrdersBean order = orderDAO.selectOrderById(user.getOrder_id());
		if(order != null) {
			request.getSession().setAttribute("currentOrder", order);
			response.sendRedirect("user_office.jsp");
		} else {
			// TODO if error
		}
		*/
	}

}
