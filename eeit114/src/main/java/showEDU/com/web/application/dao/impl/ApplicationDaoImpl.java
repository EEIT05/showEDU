package showEDU.com.web.application.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.application.dao.ApplicationDao;
import showEDU.com.web.application.model.ActClassBean;
import showEDU.com.web.application.model.ApplicationBean;
import showEDU.com.web.application.model.StatusBean;
import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.ticket.model.TheaterBean;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {
	@Autowired
	SessionFactory factory;

	@Override
	public void addApplication(ApplicationBean aplcBean) {
		// 存aplcBean要先存外鍵的物件，利用外鍵PK查詢物件後set物件
		Session session = factory.getCurrentSession();
		MemberBean mb = getMemberById(aplcBean.getMemberId());
		aplcBean.setMemberBean(mb);
		ActClassBean ab = getActClassById(aplcBean.getActClassId());
		aplcBean.setActClassBean(ab);
		StatusBean sb = getStatusById(aplcBean.getStatusId());
		aplcBean.setStatusBean(sb);
		TheaterBean tb = getTheaterById(aplcBean.getTheaterId());
		aplcBean.setTheaterBean(tb);
		session.save(aplcBean);

	}

	@Override
	public MemberBean getMemberById(int memberId) {
		Session session = factory.getCurrentSession();
		return session.get(MemberBean.class, memberId);
	}

	@Override
	public ActClassBean getActClassById(int actClassId) {
		Session session = factory.getCurrentSession();
		return session.get(ActClassBean.class, actClassId);
	}

	@Override
	public StatusBean getStatusById(int statusId) {
		Session session = factory.getCurrentSession();
		return session.get(StatusBean.class, statusId);
	}

	@Override
	public TheaterBean getTheaterById(int theaterId) {
		Session session = factory.getCurrentSession();
		return session.get(TheaterBean.class, theaterId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActClassBean> getAllActClass() {
		Session session = factory.getCurrentSession();
		String hql = "from ActClassBean";
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TheaterBean> getAllTheater() {
		Session session = factory.getCurrentSession();
		String hql = "from TheaterBean";
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatusBean> getAllStatus() {
		Session session = factory.getCurrentSession();
		String hql = "from StatusBean";
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getOrderableDate() {
		Session session = factory.getCurrentSession();
		String hql = "select distinct ob.date from OrderTimeBean ob where ob.status = 0";
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOrderableTime(Date selectDate) {
		Session session = factory.getCurrentSession();
		String hql = "select ob.time from OrderTimeBean ob where ob.date = :d and ob.status = 0";

		return session.createQuery(hql).setParameter("d", selectDate).getResultList();
	}

	@Override
	public void changeOrderableTimeStatus(Date selectDate, String selectTime, int status) {
		Session session = factory.getCurrentSession();
		String hql = "update OrderTimeBean ob set ob.status = :s where ob.date = :d and ob.time = :t";
		session.createQuery(hql).setParameter("s", status).setParameter("d", selectDate).setParameter("t", selectTime)
				.executeUpdate();
	}

	// =================================================================

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAllAplcBeanById(int memberId) {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab where ab.memberBean.memberId = :id";
		return session.createQuery(hql).setParameter("id", memberId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAplcBeanByIdByStatus(int memberId, int statusId) {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab where ab.memberBean.memberId = :id and ab.statusId = :s";
		return session.createQuery(hql).setParameter("id", memberId).setParameter("s", statusId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAllAplcBean() {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab ";
		return session.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAplcBeanByMonth(int month) {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab ";
		List<ApplicationBean> applicationBeans = session.createQuery(hql).getResultList();
		List<ApplicationBean> applicationBeansByMonth = new ArrayList<>();
		for(ApplicationBean ab:applicationBeans) {
			Date date = ab.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int aplcMonth = cal.get(Calendar.MONTH) +1;
			Integer status = ab.getStatusBean().getStatusId();
			if( aplcMonth == month &&(status==1 || status==2)) {
				applicationBeansByMonth.add(ab);
			}
			
		}
		return applicationBeansByMonth;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAllAplcBeanByDate(Date date) {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab where ab.date = :d";
		return session.createQuery(hql).setParameter("d", date).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAllAplcBeanByStatus(int statusId) {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab where ab.statusBean.statusId = :s";
		return session.createQuery(hql).setParameter("s", statusId).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationBean> getAllAplcBeanByPayStatus(int payStatus) {
		Session session = factory.getCurrentSession();
		String hql = "from ApplicationBean ab where ab.payStatus = :p";
		return session.createQuery(hql).setParameter("p", payStatus).getResultList();
	}

	@Override
	public ApplicationBean getAplcBeanId(int aplcId) {
		Session session = factory.getCurrentSession();
		return session.get(ApplicationBean.class, aplcId);
	}

	@Override
	public void changeAplcBeanStatusById(int aplcId, int status) {
		Session session = factory.getCurrentSession();
		String hql = "update ApplicationBean ab set ab.statusBean.statusId = :s where ab.aplcId = :id";
		session.createQuery(hql).setParameter("s", status).setParameter("id", aplcId).executeUpdate();

	}

	@Override
	public void changeAplcBeanPayStatusById(int aplcId, int payStatus) {
		Session session = factory.getCurrentSession();
		String hql = "update ApplicationBean ab set ab.payStatus = :s where ab.aplcId = :id";
		session.createQuery(hql).setParameter("s", payStatus).setParameter("id", aplcId).executeUpdate();
	}

}
