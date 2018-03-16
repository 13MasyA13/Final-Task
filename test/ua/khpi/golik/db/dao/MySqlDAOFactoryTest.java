package ua.khpi.golik.db.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class MySqlDAOFactoryTest {
	
	AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
	@Test
	public void testGetAdministratorDAO() {
		assertNotNull(factory.getAdministratorDAO());
	}

	@Test
	public void testGetUserDAO() {
		assertNotNull(factory.getUserDAO());
	}

	@Test
	public void testGetManagerDAO() {
		assertNotNull(factory.getManagerDAO());
	}

	@Test
	public void testGetCarsDAO() {
		assertNotNull(factory.getCarsDAO());
	}

	@Test
	public void testGetOrderDAO() {
		assertNotNull(factory.getOrderDAO());
	}

	@Test
	public void testGetCallbackDAO() {
		assertNotNull(factory.getCallbackDAO());
	}

	@Test
	public void testGetFeedbackDAO() {
		assertNotNull(factory.getFeedbackDAO());
	}

}
