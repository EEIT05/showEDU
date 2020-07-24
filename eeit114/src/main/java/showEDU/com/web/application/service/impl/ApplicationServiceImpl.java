package showEDU.com.web.application.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import showEDU.com.web.application.dao.ApplicationDao;
import showEDU.com.web.application.model.ActClassBean;
import showEDU.com.web.application.model.ApplicationBean;
import showEDU.com.web.application.model.StatusBean;
import showEDU.com.web.application.service.ApplicationService;

@Transactional
@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	ApplicationDao aplcDao;
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void addApplication(int memberId, ApplicationBean aplcBean, Date selectDate, String selectTime)
			throws Exception {
		List<ApplicationBean> beans = aplcDao.getAllAplcBeanById(memberId);
		List<ApplicationBean> beansInMonth = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(selectDate);
		int selectMonth = cal.get(Calendar.MONTH) + 1;

		for (ApplicationBean bean : beans) {
			Integer status = bean.getStatusBean().getStatusId();
			Integer payStatus = bean.getPayStatus();
			Calendar beanCal = Calendar.getInstance();
			beanCal.setTime(bean.getDate());
			int beanMonth = beanCal.get(Calendar.MONTH)+1;
			System.out.println(beanMonth);
			System.out.println(selectMonth);
			if (status == 2 && payStatus == 0) {
				throw new Exception("您尚有未付款的影廳租借申請\n請至您的申請清單查看");
			}
			if(beanMonth == selectMonth && (status!= 4)) {
				beansInMonth.add(bean);
			}
		}
		System.out.println(beansInMonth.size());
		if(beansInMonth.size() > 5) {
			throw new Exception("您本月的申請次數已達上限");
		}
		aplcDao.addApplication(aplcBean);
		aplcDao.changeOrderableTimeStatus(selectDate, selectTime, 1);

	}

	@Override
	public List<Date> getOrderableDate(java.util.Date startDate, java.util.Date endDate) {
		List<Date> allOrderableDates = aplcDao.getOrderableDate();
		List<Date> nowOrderableDates = new ArrayList<Date>();
		for (Date date : allOrderableDates) {
			if (date.after(startDate) && date.before(endDate)) {
				nowOrderableDates.add(date);
			}
		}
		return nowOrderableDates;
	}

	@Override
	public ActClassBean getActClassById(int actClassId) {
		return aplcDao.getActClassById(actClassId);
	}

	@Override
	public StatusBean getStatusById(int statusId) {
		return aplcDao.getStatusById(statusId);
	}

	@Override
	public List<ActClassBean> getAllActClass() {
		return aplcDao.getAllActClass();
	}

	@Override
	public List<StatusBean> getAllStatus() {

		return aplcDao.getAllStatus();
	}

	@Override
	public List<String> getOrderableTime(Date selectDate) {

		return aplcDao.getOrderableTime(selectDate);
	}

	@Override
	public List<ApplicationBean> getAllAplcBeanById(int memberId) {

		return aplcDao.getAllAplcBeanById(memberId);
	}

	@Override
	public List<ApplicationBean> getAllAplcBean() {

		return aplcDao.getAllAplcBean();
	}

	@Override
	public void changeAplcBeanStatusById(int aplcId, int status) {
		aplcDao.changeAplcBeanStatusById(aplcId, status);
		ApplicationBean applicationBean = aplcDao.getAplcBeanId(aplcId);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("eeit114showedu@gmail.com");
		message.setTo(applicationBean.getMemberBean().getAccount());
		message.setSubject("showEDU 場地租借申請審核通知");
		if (status == 2) {
			message.setText(pass);
			javaMailSender.send(message);
		} else if (status == 3) {
			message.setText(fail);
			aplcDao.changeOrderableTimeStatus(applicationBean.getDate(), applicationBean.getTime(), 0);
			javaMailSender.send(message);
		} else if (status == 4) {
			aplcDao.changeOrderableTimeStatus(applicationBean.getDate(), applicationBean.getTime(), 0);
		}
		;

	}

	String pass = "親愛的客戶您好，感謝您利用showEDU 場地租借系統。" + "\n您的申請已通過，請在7天內完成繳費" + "\nshowEDU";
	String fail = "親愛的客戶您好，感謝您利用showEDU 場地租借系統。" + "\n很抱歉由於本館的安排當日無法利用租借服務" + "\n期待您的再次利用" + "\nshowEDU";
	

	@Override
	public List<ApplicationBean> getAplcBeanByMonth(int month) {
		return aplcDao.getAplcBeanByMonth(month);
	}

	@Override
	public void changeAplcBeanPayStatusById(int aplcId, int payStatus) {
		aplcDao.changeAplcBeanPayStatusById(aplcId, payStatus);

	}

	@Override
	public List<ApplicationBean> getAllAplcBeanByStatus(int statusId) {
		return aplcDao.getAllAplcBeanByStatus(statusId);

	}

	@Override
	public ApplicationBean getAplcBeanId(int aplcId) {

		return aplcDao.getAplcBeanId(aplcId);
	}

}
