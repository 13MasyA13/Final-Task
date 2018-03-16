package ua.khpi.golik.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.servlets.AdminCabController;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	private static final Logger LOG = Logger.getLogger(AdminCabController.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         LOG.info("Session With ID :: " + se.getSession().getId() + " has been created in " + se.getSession().getCreationTime());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	LOG.info("Session With ID :: " + se.getSession().getId() + " has been destroyed");
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	String sessionId = se.getSession().getId();
        String attributeName = se.getName();
        Object attributeValue = se.getValue();
        LOG.info("IN HTTP_SESSION WITH ID " + sessionId + " :: Attribute added : " + attributeName + " : " + attributeValue);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	String sessionId = se.getSession().getId();
        String attributeName = se.getName();
        Object attributeValue = se.getValue();
        LOG.info("IN HTTP_SESSION WITH ID " + sessionId + " :: Attribute removed : " + attributeName + " : " + attributeValue);
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	String sessionId = se.getSession().getId();
        String attributeName = se.getName();
        Object attributeValue = se.getValue();
        LOG.info("IN HTTP_SESSION WITH ID " + sessionId + " :: Attribute replaced : " + attributeName + " : " + attributeValue);
    }
	
}
