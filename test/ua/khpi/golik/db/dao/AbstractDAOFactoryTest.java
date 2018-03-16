package ua.khpi.golik.db.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractDAOFactoryTest {
	AbstractDAOFactory factory = MySqlDAOFactory.getDAOFactory();
	@Test
	public void testGetDAOFactory() {
		AbstractDAOFactory factory;
		assertNotNull(factory = MySqlDAOFactory.getDAOFactory());
	}

}
