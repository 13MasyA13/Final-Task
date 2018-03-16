package ua.khpi.golik.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.FeedbackBean;
import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.dao.AbstractDAOFactory;
import ua.khpi.golik.db.dao.FeedbackDAO;
import ua.khpi.golik.db.dao.MySqlDAOFactory;

/**
 * Servlet implementation class NewFeedbackUserController
 */
@WebServlet("/NewFeedbackUserController")
public class NewFeedbackUserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(NewFeedbackUserController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFeedbackUserController() {
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
		UserBean currentUser = (UserBean) request.getSession().getAttribute("currentUser");
		String text = request.getParameter("feedback");
		AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
		FeedbackDAO feedbackDAO = factory.getFeedbackDAO();
		FeedbackBean feedback = new FeedbackBean();
		feedback.setName(currentUser.getFirstName() + " " + currentUser.getLastName());
		feedback.setFeedback(text);
		Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		String date = format.format(currentDate);
		feedback.setDate(date);
		try {
			boolean b = feedbackDAO.insertNewFeedbackUser(feedback);
			if(b == true) {
				LOG.info("New feedback has been inserted in DB with userName " + currentUser.getFirstName());
				if(language.equals("ru")) {
					request.getSession().setAttribute("feedbackAnsw", "Благодарим за отзыв! Благодаря вам мы становимся лучше!");
					response.sendRedirect("FeedbacksController");
				} else {
					request.getSession().setAttribute("feedbackAnsw", "Thanks for your feedback! Thanks to you we are getting better!");
					response.sendRedirect("FeedbacksController");
				}
			} else {
				LOG.error("New feedback has not been inserted in DB with userName " + currentUser.getFirstName());
				response.sendRedirect("500.jsp");
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in NewFeedbakUserController " + exc.getMessage());
			response.sendRedirect("500.jsp");
		}
	}

}
