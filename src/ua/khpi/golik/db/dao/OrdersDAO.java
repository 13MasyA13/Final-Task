package ua.khpi.golik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.OrdersBean;
import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.OrdersDBOperations;

public class OrdersDAO implements OrdersDBOperations {
	
	private final static Logger LOG = Logger.getLogger(OrdersDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
	
	private final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
	
	private final String SQL_ADD_NEW_ORDER_RU = "INSERT INTO orders VALUES(NULL, ?, ?, ?, ?, NULL, NULL, ?, ?, ?, ?, ?, ?, ?, NULL, NULL)";
	
	private final String SQL_ADD_NEW_ORDER = "INSERT INTO orders VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL)";
	
	private final String SQL_ACCEPT_ORDER_BY_ID = "UPDATE orders SET isAccepted = 1 WHERE order_id = ?";
	
	private final String SQL_NOTACCEPT_ORDER_BY_ID = "UPDATE orders SET isAccepted = 0 WHERE id = ?";
	
	private final String SQL_UPDATE_DESCRIPTION = "UPDATE orders SET description = ? WHERE order_id = ?";
	
	private final String SQL_GET_USER_ID_BY_ORDER_ID = "SELECT user_id FROM orders WHERE order_id = ?";
	
	private final String SQL_GET_PRICE_BY_ORDER_ID = "SELECT total_price FROM orders WHERE order_id = ?";
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;
	
	@Override
	public ArrayList<OrdersBean> selectAllOrders() {
		ArrayList<OrdersBean> ordersList = new ArrayList<>();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ALL_ORDERS);
			rs = prSt.executeQuery();
			while(rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setId(rs.getInt(1));
				order.setCar_id(rs.getInt(2));
				order.setUser_id(rs.getInt(3));
				if(rs.getString(4) == null) {
					order.setFirstName(rs.getString(6));
					order.setLastName(rs.getString(7));
				} else {
					order.setFirstName(rs.getString(4));
					order.setLastName(rs.getString(5));
				}
				order.setAccepted(rs.getBoolean(15));
				order.setDescription(rs.getString(16));
				ordersList.add(order);
				LOG.info("Order with id " + order.getId() + " has been added to ordersList");
			}
			
			if(ordersList != null) {
				LOG.info("Orders list has returned");
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in selectAllOrders() " + exc.getMessage());
		} catch(Exception exc) {
			LOG.error("No one orders was't added to the ordersList");
			return null;
		}
		return ordersList;
	}

	@Override
	public boolean acceptOrderById(int id) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_ACCEPT_ORDER_BY_ID);
			prSt.setInt(1, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Order with id " + id + " have been accepted");
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in acceptOrderById " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Row wasn't updated");
			conn.rollback();
			return false;
		}
	}

	@Override
	public boolean notAcceptOrderById(int id) throws SQLException{
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_NOTACCEPT_ORDER_BY_ID);
			prSt.setInt(1, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Order with id " + id + " have been unaccepted");
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException in acceptOrderById " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Row wasn't updated");
			conn.rollback();
			return false;
		}
	}

	@Override
	public boolean insertNewOrder(OrdersBean order, UserBean user) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_ADD_NEW_ORDER);
			prSt.setInt(1, order.getCar_id());
			prSt.setInt(2, user.getId());
			if(user.getFirstName().charAt(0) <= 122) {
				prSt.setString(3, null);
				prSt.setString(4, null);
				prSt.setString(5, user.getFirstName());
				prSt.setString(6, user.getLastName());
			} else {
				prSt.setString(3, user.getFirstName());
				prSt.setString(4, user.getLastName());
				prSt.setString(5, null);
				prSt.setString(6, null);
			}
			prSt.setString(7, order.getPassport());
			prSt.setString(8, order.getDateOfBirthday());
			prSt.setString(9, order.getAddress());
			prSt.setString(10, order.getFromDate());
			prSt.setString(11, order.getToDate());
			prSt.setBoolean(12, order.isWithDriver());
			prSt.setInt(13, order.getTotal_price());
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Order with price " + order.getTotal_price() + " has been added to table");
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQL Exception in insertNewOrder " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Row has't updated");
			conn.rollback();
			return false;
		}
	}

	@Override
	public boolean updateDescriptionById(int id, String desc) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_UPDATE_DESCRIPTION);
			prSt.setString(1, desc);
			prSt.setInt(2, id);
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Description " + desc + " added to order" + id);
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQL Exception in updateDescriptionById() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Decription wasn't updated");
			conn.rollback();
			return false;
		}
	}

	@Override
	public OrdersBean selectOrderById(int id) {
		OrdersBean order = new OrdersBean();
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ORDER_BY_ID);
			prSt.setInt(1, id);
			rs = prSt.executeQuery();
			while(rs.next()) {
				order.setId(rs.getInt(1));
				order.setCar_id(rs.getInt(2));
				order.setUser_id(rs.getInt(3));
				if(rs.getString(4).equals(null)) {
					order.setFirstName(rs.getString(6));
					order.setLastName(rs.getString(7));
				} else {
					order.setFirstName(rs.getString(4));
					order.setLastName(rs.getString(5));
				}
				order.setFromDate(rs.getString(11));
				order.setToDate(rs.getString(12));
				order.setAccepted(rs.getBoolean(15));
				order.setDescription(rs.getString(16));
				if(order != null) {
					LOG.info("Order with id " + order.getId() + " has inserted");
				}		
			}
			return order;
		} catch(SQLException exc) {
			LOG.error("SQLException in selectOrderById() " + exc.getMessage());
			return null;
		}
	}

	@Override
	public int getUserIdByOrderId(int id) {
		try {
			int answ = 0;
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_GET_USER_ID_BY_ORDER_ID);
			prSt.setInt(1, id);
			rs = prSt.executeQuery();
			while(rs.next()) {
				answ = rs.getInt(1);
				LOG.info("User id = " + answ + " when order id = " + id);
			}
			return answ;
		} catch(SQLException exc) {
			LOG.error("SQLException in getUserIdByOrderId() " + exc.getMessage());
			return 0;
		}
	}

	@Override
	public int getPriceByOrderId(int id) {
		try {
			int answ = 0;
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_GET_PRICE_BY_ORDER_ID);
			prSt.setInt(1, id);
			rs = prSt.executeQuery();
			while(rs.next()) {
				answ = rs.getInt(1);
				LOG.info("Price = " + answ + " when order id = " + id);
			}
			return answ;
		} catch(SQLException exc) {
			LOG.error("SQLException in getPriceByOrderId() " + exc.getMessage());
			return 0;
		}
	}

}
