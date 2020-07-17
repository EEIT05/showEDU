package showEDU.com.web.application.service.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import showEDU.com.web.application.dao.ActivityDao;
import showEDU.com.web.application.model.ActivityBean;
import showEDU.com.web.application.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	ActivityDao  activityDao;
	
	@Transactional
	@Override
	public List<ActivityBean> getAllActivities() {
		return activityDao.getAllActivities();
	}
	
	@Transactional
	@Override
	public ActivityBean getActivityById(int actId) {
		return activityDao.getActivityById(actId);
	}
	@Transactional
	@Override
	public List<ActivityBean> getAllActivitiesByDate(Date date) {
		List<ActivityBean> beans = null; 
		if(date == null) {
			beans = activityDao.getAllActivities();
		}else {
			beans = activityDao.getAllActivitiesByDate(date);
		}
		return beans;
	}
	@Transactional
	@Override
	public void addActivity(ActivityBean activity) {
		activityDao.addActivity(activity);
		
	}
	
	@Transactional
	@Override
	public void updateActivity(ActivityBean activity,int actId) {
		activityDao.updateActivity(activity,actId);
		
	}

	
	

}
