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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet Filter implementation class AdminCabFilter
 */
@WebFilter("/admin_office.jsp")
public class AdminCabFilter implements Filter {

    /**
     * Default constructor. 
     */
	private static final Logger LOG = Logger.getLogger(AdminCabFilter.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
    public AdminCabFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if(session.getAttribute("userType").equals("ADMIN")) {
			LOG.info("AdminCabFilter doFilter()");
			chain.doFilter(req, resp);
		} else if(session.getAttribute("userType").equals("not")){
			LOG.warn("Attempt to access adminCab " + session.getAttribute("userType"));
			resp.sendRedirect("index.jsp");
		} else {
			LOG.warn("Attempt to access adminCab " + session.getAttribute("userType"));
			resp.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
