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
 * Servlet Filter implementation class NewCarIsNotNullFilter
 */
@WebFilter("/NewCarController")
public class NewCarIsNotNullFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(NewCarIsNotNullFilter.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
    /**
     * Default constructor. 
     */
    public NewCarIsNotNullFilter() {
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
		String carName = request.getParameter("carName");
		String carMark = request.getParameter("carMark");
		String carClassRU = request.getParameter("carClassRU");
		String carClassEN = request.getParameter("carClassEN");
		int carPrice = Integer.parseInt(request.getParameter("carPrice"));
		int carPladge = Integer.parseInt(request.getParameter("carPladge"));
		String carImage = request.getParameter("carImage");
		if(!carName.equals("") && !carMark.equals("") && !carClassRU.equals("") && !carClassEN.equals("")&& 
		carPrice != 0 && carPladge != 0 && !carImage.equals("")) {
			LOG.info("NewCarIsNotNullFilter doFilter()");
			chain.doFilter(request, response);
		} else {
			LOG.warn("NewCarIsNotNullFilter one or more fields is NULL!");
			resp.sendRedirect("admin_office.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
