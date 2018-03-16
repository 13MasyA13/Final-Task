package ua.khpi.golik.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet Filter implementation class IsNotNullFilter
 */
@WebFilter(urlPatterns = {"/LoggingServlet"})
public class LoggingIsNotNullFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(LoggingIsNotNullFilter.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
    /**
     * Default constructor. 
     */
    public LoggingIsNotNullFilter() {
        // TODO SOME CHANGES FOR JUNIT AND SPRING MOCK
    }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		if(!login.equals("") && !password.equals("") && userType != null) {
			LOG.info("doFilter() in LoggingIsNotNullFilter");
			chain.doFilter(request, response);
		} else {
			String language = (String) req.getSession().getAttribute("language");
			if(language.equals("ru")) {
				req.getSession().setAttribute("nullParam", "Заполните пустые поля");
				LOG.warn("Login or Password is empty!");
				resp.sendRedirect("logging.jsp");
			} else {
				req.getSession().setAttribute("nullParam", "You should fill empty fields");
				LOG.warn("Login or Password is empty!");
				resp.sendRedirect("logging.jsp");
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
