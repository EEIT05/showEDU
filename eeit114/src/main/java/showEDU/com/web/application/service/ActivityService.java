package showEDU.com.web.application.service;

import java.sql.Date;
import java.util.List;

import showEDU.com.web.application.model.ActivityBean;

public interface ActivityService {
	List<ActivityBean> getAllActivities();
	void updateActivity(ActivityBean activity,int actId);
	ActivityBean getActivityById(int actId);
	List<ActivityBean> getAllActivitiesByDate(Date date);
	void addActivity(ActivityBean activity);
	
}
