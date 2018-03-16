package ua.khpi.golik.db.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.PriceCoefs;
import ua.khpi.golik.bl.cars.AnyCar;
import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.CarsDBOperations;

public class CarsDAO implements CarsDBOperations {
	
	private static Logger LOG = Logger.getLogger(CarsDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SQL_SELECT_ALL_CARS = "SELECT * FROM cars";
	
	private final String SQL_SELECT_CAR_BY_ID = "SELECT * FROM cars WHERE id = ?";
	
	private final String SQL_INSERT_NEW_CAR = "INSERT INTO cars VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String SQL_DELETE_CAR_BY_ID = "DELETE * FROM cars WHERE id = ?";
	
	private final String SQL_UPDATE_CAR_BY_ID = "UPDATE cars SET carName = ?, carMark = ?, carClass_ru = ?, carClass_en = ?, price_30_more = ?, "
			+ "price_10_to_30 = ?, price_4_to_9 = ?, price_2_to_3 = ?, pladge = ?, image = ? WHERE id = ?";
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;
	
	@Override
	public ArrayList<AnyCar> selectAllCars() throws SQLException, IOException{
		ArrayList<AnyCar> listOfCars = new ArrayList<AnyCar>();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ALL_CARS);
			rs = prSt.executeQuery();
			while(rs.next()) {
				AnyCar car = new AnyCar();
				car.setId(rs.getInt(1));
				car.setCarName(rs.getString(2));
				car.setCarMark(rs.getString(3));
				car.setCarClassRU(rs.getString(4));
				car.setCarClassEN(rs.getString(5));
				car.setFrom30toMoreDaysPrice(rs.getInt(6));
				car.setFrom10to30DaysPrice(rs.getInt(7));
				car.setFrom4to9DaysPrice(rs.getInt(8));
				car.setFrom2to3DaysPrice(rs.getInt(9));
				car.setPledge(rs.getInt(10));
				car.setImage(rs.getString(11));
				listOfCars.add(car);
				LOG.info(car.getCarName() + " has added to ArrayList");
			}
		} catch (SQLException e) {
			LOG.error("SQL Exceptione in selectAllCars() " + e.getMessage());
		}
		return listOfCars;
	}
	
	@Override
	public AnyCar selectCarByID(int id) throws SQLException {
		AnyCar car = new AnyCar();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_CAR_BY_ID);
			prSt.setInt(1, id);
			rs = prSt.executeQuery();
			while(rs.next()) {
				car.setId(rs.getInt(1));
				car.setCarName(rs.getString(2));
				car.setCarMark(rs.getString(3));
				car.setCarClassRU(rs.getString(4));
				car.setCarClassEN(rs.getString(5));
				car.setFrom30toMoreDaysPrice(rs.getInt(6));
				car.setFrom10to30DaysPrice(rs.getInt(7));
				car.setFrom4to9DaysPrice(rs.getInt(8));
				car.setFrom2to3DaysPrice(rs.getInt(9));
				car.setPledge(rs.getInt(10));
				car.setImage(rs.getString(11));
				return car;
			}
			
		} catch(SQLException exc) {
			LOG.error("SQL Exception in selectCarByID() " + exc.getMessage());
			return null;
		}
		return car;
	}
	
	@Override
	public boolean deleteCarByID(int id) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_DELETE_CAR_BY_ID);
			prSt.setInt(1, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				conn.commit();
				LOG.info("DELETED CAR ID = " + id);
			} else {
				throw new Exception();
			}
			return true;
		} catch(SQLException exc) {
			conn.rollback();
			LOG.error("SQL Exception in deleteCarByID()" + " " + exc.getMessage());
			return false;
		} catch (Exception e) {
			LOG.error("CAR WITH ID " + id +" hasn't deleted");
			conn.rollback();
			return false;
		}
	}
	
	@Override
	public boolean updateCarByID(int id, String mark, String carName, String carClass_ru, String carClass_en, int price, int pledge, String image, AnyCar car) throws SQLException{
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_UPDATE_CAR_BY_ID);
			if(carName.equals("")) {
				prSt.setString(1, car.getCarName());
			} else {
				prSt.setString(1, carName);
			}
			
			if(mark.equals("")) {
				prSt.setString(2, car.getCarName());
			} else {
				prSt.setString(2, mark);
			}
			
			if(carClass_ru.equals("")) {
				prSt.setString(3, car.getCarClassRU());
			} else {
				prSt.setString(3, carClass_ru);
			}
			
			if(carClass_en.equals("")) {
				prSt.setString(4, car.getCarClassEN());
			} else {
				prSt.setString(4, carClass_en);
			}
			
			if(price == 0) {
				prSt.setInt(5, car.getFrom30toMoreDaysPrice());
				prSt.setInt(6, car.getFrom10to30DaysPrice());
				prSt.setInt(7, car.getFrom4to9DaysPrice());
				prSt.setInt(8, car.getFrom2to3DaysPrice());
			} else {
				int priceFrom30toMore = price * (int)PriceCoefs.FROM_30_TO_MORE_DAYS_COEF;
				int priceFrom10to30 = price * (int)PriceCoefs.FROM_10_TO_30_DAYS_COEF;
				int priceFrom4to9 = price * (int)PriceCoefs.FROM_4_TO_9_DAYS_COEF;
				int priceFrom2to3 = price * (int)PriceCoefs.FROM_2_TO_3_DAYS_COEF;
				prSt.setInt(5, priceFrom30toMore);
				prSt.setInt(6, priceFrom10to30);
				prSt.setInt(7, priceFrom4to9);
				prSt.setInt(8, priceFrom2to3);
				LOG.info(priceFrom30toMore);
				LOG.info(priceFrom10to30);
				LOG.info(priceFrom4to9);
				LOG.info(priceFrom2to3);
			}
			
			if(pledge == 0) {
				prSt.setInt(9, car.getPledge());
			} else {
				prSt.setInt(9, pledge);
			}
			
			if(image.equals("")) {
				prSt.setString(10, car.getImage());
			} else {
				prSt.setString(10, image);
			}
			
			prSt.setInt(11, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				conn.commit();
				LOG.info("Car with id " + id + " has been updated");
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.info("SQLException in updateCarByID() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			conn.rollback();
			LOG.error("Car with id " + id + " has not been updated");
			return false;
		}
	}
	
	@Override
	public boolean insertNewCar(AnyCar car) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_INSERT_NEW_CAR);
			prSt.setString(1, car.getCarName());
			prSt.setString(2, car.getCarMark());
			prSt.setString(3, car.getCarClassRU());
			prSt.setString(4, car.getCarClassEN());
			prSt.setInt(5, car.getFrom30toMoreDaysPrice());
			prSt.setInt(6, car.getFrom10to30DaysPrice());
			prSt.setInt(7, car.getFrom4to9DaysPrice());
			prSt.setInt(8, car.getFrom2to3DaysPrice());
			prSt.setInt(9, car.getPledge());
			prSt.setString(10, car.getImage());
			int i = prSt.executeUpdate();
			if(i > 0) {
				conn.commit();
				LOG.info("New Car has been inserted with name " + car.getCarName());
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in insertNewCar() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("No one row hasn't been updated");
			conn.rollback();
			return false;
		}
	}
}
