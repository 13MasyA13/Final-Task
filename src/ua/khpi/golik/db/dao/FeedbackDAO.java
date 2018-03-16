package ua.khpi.golik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.khpi.golik.bl.FeedbackBean;
import ua.khpi.golik.bl.users.UserBean;
import ua.khpi.golik.db.connection.DBManager;
import ua.khpi.golik.db.dao.interfaces.FeedbackDBOperations;

public class FeedbackDAO implements FeedbackDBOperations {
	
	private static final Logger LOG = Logger.getLogger(FeedbackDAO.class);
	
	static { PropertyConfigurator.configure("D:\\EPAM\\FINAL TASK\\Final-Task\\WebContent\\properties\\log4j.properties");}
	
	private final String SQL_SELECT_ALL_FEEDBACKS = "SELECT * FROM feedbacks";
	
	private final String SQL_INSERT_NEW_FEEDBACK_ANONIM = "INSERT INTO feedbacks VALUES (NULL, NULL, ?, ?)";
	
	private final String SQL_INSERT_NEW_FEEDBACK_USER = "INSERT INTO feedbacks VALUES (NULL, ?, ?, ?)";
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;
	
	@Override
	public boolean insertNewFeedbackAnonim(FeedbackBean feedback) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_INSERT_NEW_FEEDBACK_ANONIM);
			prSt.setString(1, feedback.getFeedback());
			prSt.setString(2, feedback.getDate());
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Inserted new feedback from anonim with text " + feedback.getFeedback());
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException int insertNewFeedbackAnonim() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Feedback with text " + feedback.getFeedback() + " has not been inserted");
			conn.rollback();
			return false;
		}
	}

	@Override
	public boolean insertNewFeedbackUser(FeedbackBean feedback) throws SQLException {
		try {
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_INSERT_NEW_FEEDBACK_USER);
			prSt.setString(1, feedback.getName());
			prSt.setString(2, feedback.getFeedback());
			prSt.setString(3, feedback.getDate());
			int i = prSt.executeUpdate();
			if(i > 0) {
				LOG.info("Inserted new feedback from" + feedback.getName() +  " with text " + feedback.getFeedback());
				conn.commit();
				return true;
			} else {
				throw new Exception();
			}
		} catch(SQLException exc) {
			LOG.error("SQLException int insertNewFeedbackUser() " + exc.getMessage());
			conn.rollback();
			return false;
		} catch (Exception e) {
			LOG.error("Feedback from user " + feedback.getName() + " has not been inserted");
			conn.rollback();
			return false;
		}
	}

	@Override
	public ArrayList<FeedbackBean> selectAllFeedbacks() {
		try {
			ArrayList<FeedbackBean> feedbacks = new ArrayList<>();
			conn = DBManager.getInstance().getConnection();
			prSt = conn.prepareStatement(SQL_SELECT_ALL_FEEDBACKS);
			rs = prSt.executeQuery();
			while(rs.next()) {
				FeedbackBean feedback = new FeedbackBean();
				feedback.setId(rs.getInt(1));
				feedback.setName(rs.getString(2));
				feedback.setFeedback(rs.getString(3));
				feedback.setDate(rs.getString(4));
				LOG.info("Feedback with id " + feedback.getId() + " has been inserted in list");
				feedbacks.add(feedback);
			}
			return feedbacks;
		} catch(SQLException exc) {
			LOG.error("SQLException in selectAllFeedbacks() " + exc.getMessage());
			return null;
		}
	}
}
