package ua.khpi.golik.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.CallbackDAO;
import ua.khpi.golik.db.dao.MySqlDAOFactory;

/**
 * Servlet implementation class CallBackController
 */
@WebServlet("/CallBackController")
public class CallBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallBackController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		CallbackDAO callbackDAO = factory.getCallbackDAO();
		String telephone = request.getParameter("telephone");
		String name = request.getParameter("name");
		boolean b = callbackDAO.insertNewCallback(telephone, name);
		if(b == true) {
			// TODO Thanks to user
		} else {
			// TODO Not Correct
		}
	}

}
