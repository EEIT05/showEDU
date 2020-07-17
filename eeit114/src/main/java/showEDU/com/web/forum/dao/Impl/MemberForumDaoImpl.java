package showEDU.com.web.forum.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.forum.dao.MemberForumDao;
import showEDU.com.web.member.model.MemberBean;
@Repository
public class MemberForumDaoImpl implements MemberForumDao {

	@Autowired
	SessionFactory factory;
	
	@Override
	public MemberBean queryMember(Integer memberId) {
		MemberBean mb = null;
		String hql = "From MemberBean m Where m.memberId = :mid";
		Session session = factory.getCurrentSession();
		MemberBean memberBean = (MemberBean) session.createQuery(hql).setParameter("mid", memberId).getSingleResult();
		mb = memberBean;
		return mb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getAllMembers() {
		String hql = "From MemberBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

}
