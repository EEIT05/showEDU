package showEDU.com.web.application.dao;

import java.sql.Date;
import java.util.List;

import showEDU.com.web.application.model.ActClassBean;
import showEDU.com.web.application.model.ApplicationBean;
import showEDU.com.web.application.model.StatusBean;
import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.ticket.model.TheaterBean;


public interface ApplicationDao {
	public void addApplication(ApplicationBean aplcBean);
	
	public MemberBean getMemberById(int memberId);
	public ActClassBean getActClassById(int actClassId);
	public StatusBean getStatusById(int statusId);
	public TheaterBean getTheaterById(int theaterId);
	
	public List<ActClassBean> getAllActClass();
	public List<TheaterBean> getAllTheater();
	public List<StatusBean> getAllStatus();
	
	public List<Date> getOrderableDate();
	public List<String> getOrderableTime(Date selectDate);
	
	public void changeOrderableTimeStatus(Date selectDate,String selectTime,int status);
	
	public List<ApplicationBean> getAllAplcBeanById(int memberId);
	public List<ApplicationBean> getAplcBeanByIdByStatus(int memberId,int statusId);
	
	public List<ApplicationBean> getAllAplcBean();
	public List<ApplicationBean> getAllAplcBeanByDate(Date date);
	public List<ApplicationBean> getAllAplcBeanByStatus(int statusId);
	public List<ApplicationBean> getAllAplcBeanByPayStatus(int payStatus);
	
	public ApplicationBean getAplcBeanId(int aplcId);
	public void changeAplcBeanStatusById(int aplcId,int status);
	public void changeAplcBeanPayStatusById(int aplcId,int payStatus);
	
}
