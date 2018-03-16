package ua.khpi.golik.filters;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class UserCabFilterTest extends Mockito {
	
	@Mock
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession mockHttpSession;
	Map<String,Object> attributes = new HashMap<String,Object>();
	
	@Before
	public void getSession() {
	    Mockito.doAnswer(new Answer<Object>(){
	        @Override
	        public Object answer(InvocationOnMock invocation) throws Throwable {
	            String key = (String) invocation.getArguments()[0];
	            return attributes.get(key);
	        }
	    }).when(mockHttpSession).getAttribute(anyString());

	    Mockito.doAnswer(new Answer<Object>(){
	        @Override
	        public Object answer(InvocationOnMock invocation) throws Throwable {
	            String key = (String) invocation.getArguments()[0];
	            Object value = invocation.getArguments()[1];
	            attributes.put(key, value);
	            return null;
	        }
	    }).when(mockHttpSession).setAttribute(anyString(), any());
	}
	
	@Test
	public void testDoFilter() {
		mockHttpSession.setAttribute("userType", "NOT");
		when(mockHttpSession.getAttribute("userType")).thenReturn(false);
	}

}
