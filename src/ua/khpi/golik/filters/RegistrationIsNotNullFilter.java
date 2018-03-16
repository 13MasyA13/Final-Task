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
 * Servlet Filter implementation class RegistrationIsNotNullFilter
 */
@WebFilter(urlPatterns = {"/RegistrationController"})
public class RegistrationIsNotNullFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(RegistrationIsNotNullFilter.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
    /**
     * Default constructor. 
     */
    public RegistrationIsNotNullFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String confPassword = req.getParameter("confirmPassword");
		if(!login.equals("") & !password.equals("") & !firstName.equals("") & !lastName.equals("") & !email.equals("")
		& !confPassword.equals("")) {
			LOG.info("All parameters in regiser form are not NULL!");
			if(password.equals(confPassword)) {
				LOG.info("Password has confirmed");
				chain.doFilter(request, response);
			} else {
				LOG.warn("Password has't confirmed");
				// TODO If passwords not equals
			}
		} else {
			LOG.warn("One of the register parameter is NULL");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
