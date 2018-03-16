package ua.khpi.golik.db.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.khpi.golik.bl.FeedbackBean;

public interface FeedbackDBOperations {
	
	public ArrayList<FeedbackBean> selectAllFeedbacks();
	
	public boolean insertNewFeedbackAnonim(FeedbackBean feedback) throws SQLException;
	
	public boolean insertNewFeedbackUser(FeedbackBean feedback) throws SQLException;
}
