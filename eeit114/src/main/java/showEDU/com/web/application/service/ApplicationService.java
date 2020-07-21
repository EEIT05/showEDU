package showEDU.com.web.application.service;

import java.sql.Date;
import java.util.List;

import showEDU.com.web.application.model.ActClassBean;
import showEDU.com.web.application.model.ApplicationBean;
import showEDU.com.web.application.model.StatusBean;


public interface ApplicationService {
	public void addApplication(int memberId,ApplicationBean aplcBean,Date selectDate,String selectTime)throws Exception;
	public ActClassBean getActClassById(int actClassId);
	public StatusBean getStatusById(int statusId);
	public List<ActClassBean> getAllActClass();
	public List<StatusBean> getAllStatus();	
	public List<Date> getOrderableDate(java.util.Date startDate, java.util.Date endDate);
	public List<String> getOrderableTime(Date selectDate);
	public List<ApplicationBean> getAllAplcBeanById(int memberId);
	public List<ApplicationBean> getAllAplcBean();
	public void changeAplcBeanStatusById(int aplcId,int status);
	public List<ApplicationBean> getAplcBeanByMonth(int month);
	public void changeAplcBeanPayStatusById(int aplcId,int payStatus);
	public List<ApplicationBean> getAllAplcBeanByStatus(int statusId);
}
