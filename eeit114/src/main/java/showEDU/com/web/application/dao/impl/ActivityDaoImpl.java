package showEDU.com.web.application.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.application.dao.ActivityDao;
import showEDU.com.web.application.model.ActivityBean;

@Repository
public class ActivityDaoImpl implements ActivityDao {
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBean> getAllActivities() {
		String hql = "from ActivityBean";
		Session session = factory.getCurrentSession();

		return session.createQuery(hql).getResultList();
	}

	@Override
	public void updateActivity(ActivityBean activity, int actId) {
		if (activity.getFileName() != null && activity.getActImg() != null) {
			String hql = "update ActivityBean ab set ab.actTitle = :t, ab.startDate= :s"
					+ " ,ab.endDate = :e,ab.descr = :d, ab.fileName= :f, ab.actImg= :b, ab.postDate= :p"
					+ " where ab.actId = :id ";
			Session session = factory.getCurrentSession();
			session.createQuery(hql).setParameter("t", activity.getActTitle())
					.setParameter("s", activity.getStartDate()).setParameter("e", activity.getEndDate())
					.setParameter("d", activity.getDescr()).setParameter("f", activity.getFileName())
					.setParameter("b", activity.getActImg()).setParameter("p", activity.getPostDate())
					.setParameter("id", actId).executeUpdate();
		} else {
			String hql = "update ActivityBean ab set ab.actTitle = :t, ab.startDate= :s"
					+ " ,ab.endDate = :e,ab.descr = :d, ab.postDate= :p" + " where ab.actId = :id ";
			Session session = factory.getCurrentSession();
			session.createQuery(hql).setParameter("t", activity.getActTitle())
					.setParameter("s", activity.getStartDate()).setParameter("e", activity.getEndDate())
					.setParameter("d", activity.getDescr()).setParameter("p", activity.getPostDate())
					.setParameter("id", actId).executeUpdate();
		}

	}

	@Override
	public ActivityBean getActivityById(int actId) {
		Session session = factory.getCurrentSession();
		return session.get(ActivityBean.class, actId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBean> getAllActivitiesByDate(Date date) {
		String hql = "from ActivityBean ab where ab.startDate <= :date and ab.endDate >= :date";
		Session session = factory.getCurrentSession();

		return session.createQuery(hql).setParameter("date", date).getResultList();
	}

	@Override
	public void addActivity(ActivityBean activity) {
		Session session = factory.getCurrentSession();
		session.save(activity);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBean> getAllActivitiesPerPage(int pageNo) {
		String hql = "from ActivityBean";
		Session session = factory.getCurrentSession();
		int startRecordNo = (pageNo - 1) * 10;
		return session.createQuery(hql).setFirstResult(startRecordNo)
				.setMaxResults(10)
				.getResultList();
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBean> getAllActivitiesByDatePerPage(Date date, int pageNo) {
		String hql = "from ActivityBean ab where ab.startDate <= :date and ab.endDate >= :date";
		Session session = factory.getCurrentSession();
		int startRecordNo = (pageNo - 1) * 10;
		return session.createQuery(hql).setParameter("date", date)
										.setFirstResult(startRecordNo)
										.setMaxResults(10)
										.getResultList();
		
	}

}
