package ua.khpi.golik.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoggingIsNotNullFilterTest {
	private LoggingIsNotNullFilter filter;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockFilterChain chain;
	
	@Before
	public void setParameters() {
		filter = new LoggingIsNotNullFilter();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		chain = new MockFilterChain();
	}
	
	@Test
	public void testDoFilter() throws IOException, ServletException {
		request.setParameter("login", "someLogin");
		request.setParameter("password", "secret");
		request.setParameter("userType", "USER");
		
		filter.doFilter(request, response, chain);
		
		assertNotNull(request.getParameterNames());
	}

}
