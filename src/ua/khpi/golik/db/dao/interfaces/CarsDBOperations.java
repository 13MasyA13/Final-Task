package ua.khpi.golik.db.dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.khpi.golik.bl.cars.AnyCar;

public interface CarsDBOperations {
	
	public ArrayList<AnyCar> selectAllCars() throws SQLException, IOException;
	
	public AnyCar selectCarByID(int id) throws SQLException;
	
	public boolean insertNewCar(AnyCar car) throws SQLException;
	
	public boolean deleteCarByID(int id) throws SQLException;
	
	public boolean updateCarByID(int id, String carName, String carMark, String carClass_RU, String carClass_EN, int price, int pledge, String image, AnyCar car) throws SQLException;
}
