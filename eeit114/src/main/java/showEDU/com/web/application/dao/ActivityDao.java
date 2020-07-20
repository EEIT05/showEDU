package showEDU.com.web.application.dao;

import java.sql.Date;
import java.util.List;

import showEDU.com.web.application.model.ActivityBean;

public interface ActivityDao {
	List<ActivityBean> getAllActivities();
	void updateActivity(ActivityBean activity,int actId);
	ActivityBean getActivityById(int actId);
	List<ActivityBean> getAllActivitiesByDate(Date date);
	public void addActivity(ActivityBean activity );
	public List<ActivityBean> getAllActivitiesPerPage(int pageNo); 
	public List<ActivityBean> getAllActivitiesByDatePerPage(Date date,int pageNo );
}
