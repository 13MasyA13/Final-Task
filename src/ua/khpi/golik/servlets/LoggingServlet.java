package ua.khpi.golik.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.users.AdministratorBean;
import ua.khpi.golik.bl.users.AnyUser;
import ua.khpi.golik.bl.users.ManagerBean;
import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.AdministratorDAO;
import ua.khpi.golik.db.dao.ManagerDAO;
import ua.khpi.golik.db.dao.UserDAO;

/**
 * Servlet implementation class LoggingServlet
 */
@WebServlet("/LoggingServlet")
public class LoggingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(LoggingServlet.class);
    
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoggingServlet() {
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
		request.getSession().invalidate();
		AnyUser user = new AnyUser();
		HttpSession session = request.getSession();
		LOG.info("Creating new Session");
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory();
		String userType = request.getParameter("userType");
		if(userType.equals("MANAGER")) {
			LOG.info("Try to log manager...");
			ManagerDAO dao = factory.getManagerDAO();
			boolean b = dao.selectManagerLogPass(user.getLogin(), user.getPassword());
			if(b == true) {
				LOG.info("MANAGER " + user.getLogin() + " HAS LOGGED!");
				ManagerBean currentUser = new ManagerBean();
				currentUser.setLogin(user.getLogin());
				currentUser.setLogged(true);
				session.setAttribute("userLogin", currentUser.getLogin());
				session.setAttribute("userType", currentUser.getStatus());
				session.setAttribute("currentUser", currentUser);
				response.sendRedirect("index.jsp");
			} else {
				// TODO If user not finded.
				LOG.error("Invaild data in MANAGER");
				response.sendRedirect("logging.jsp");
			}
		} else if(userType.equals("USER")) {
			UserDAO dao = factory.getUserDAO();
			boolean b = dao.selectUserLogPas(user.getLogin(), user.getPassword());
			if(b == true) {
				UserBean currentUser = dao.selectCurrentUser(user.getLogin());
				if(currentUser.isBlocked() == true) {
					LOG.warn("User with login " + currentUser.getLogin() + " tried to login when he has blocked my admin");
					session.invalidate();
					request.getSession().setAttribute("blockUser", "You are blocked!");
				} else {
					LOG.info("USER " + user.getLogin() + " HAS LOGGED!");
					session.setAttribute("userLogin", currentUser.getLogin());
					session.setAttribute("userType", "USER");
					session.setAttribute("currentUser", currentUser);
					response.sendRedirect("index.jsp");
				}
			} else {
				request.getSession().setAttribute("loginError","Incorrect password"); 
				LOG.error("Indvalid data in USER");
				response.sendRedirect("logging.jsp");
			}
		} else if(userType.equals("ADMIN")) {
			AdministratorDAO dao = factory.getAdministratorDAO();
			boolean b = dao.selectAdminByLogPass(user.getLogin(), user.getPassword());
			if(b == true) {
				LOG.info("ADMIN HAS LOGGED!");
				AdministratorBean currentUser = new AdministratorBean();
				currentUser.setLogin(request.getParameter("login"));
				currentUser.setLogged(true);
				session.setAttribute("userLogin", currentUser.getLogin());
				session.setAttribute("userType", currentUser.getStatus());
				session.setAttribute("currentUser", currentUser);
				response.sendRedirect("index.jsp");
			} else {
				// TODO if user not finded.
				LOG.error("Invalid data in ADMIN");
				response.sendRedirect("logging.jsp");
			}
		}
	}
}
