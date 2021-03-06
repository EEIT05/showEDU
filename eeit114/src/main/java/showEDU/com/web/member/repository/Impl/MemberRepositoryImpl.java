package showEDU.com.web.member.repository.Impl;

import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.member.repository.MemberRepository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	@Autowired
	SessionFactory factory;

	@Override
	public void save(MemberBean member) {
		Session session = factory.getCurrentSession();
		session.save(member);
	}

	@Override
	public void deleteMember(Integer memberId) {
		Session session = factory.getCurrentSession();
		MemberBean member = getMemberById(memberId);
		if (member != null) {
			session.delete(member);
		}
	}

	@Override
	public void updateMember(MemberBean member) {
		if (member != null && member.getMemberId() != null) {
			Session session = factory.getCurrentSession();
			session.saveOrUpdate(member);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MemberBean> getAllMembers() {
		String hql = "FROM MemberBean";
		Session session = factory.getCurrentSession();
		List<MemberBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public MemberBean getMemberById(int memberId) {
		Session session = factory.getCurrentSession();
		return session.get(MemberBean.class, memberId);
	}

	@Override
	public MemberBean login(String account, String pswd) {
		MemberBean member = null;
		Session session = factory.getCurrentSession();
		String hql = "From MemberBean m WHERE m.account = :acc and m.pswd = :pwd";
		System.out.println("dao");
		try {
			member = (MemberBean)session.createQuery(hql)
									.setParameter("acc",account)
									.setParameter("pwd",pswd)
									.getSingleResult();
			System.out.println(member.getMemberId());
			System.out.println("登入成功");
		} catch (NoResultException e) {
			System.out.println("登入失敗");
		}
		System.out.println(member);
		return member;
		}

	@Override
	public boolean idExists(String account) {
		Session session = factory.getCurrentSession();
		boolean exist = false;
		String hql = "FROM MemberBean m WHERE m.account = :acc";
		@SuppressWarnings("unchecked")
		List<MemberBean> list = session.createQuery(hql)
				                       .setParameter("acc",account)
										.getResultList();
		
		if (list.size() > 0) {
			exist = true;
		}
		return exist;
		
	}

	@Override
	public MemberBean checkIdPassword(String userId, String password) {
		MemberBean member = null;
		String hql = "From MemberBean m WHERE m.account = :acc and m.pswd = :pwd";
		Session session = factory.getCurrentSession();
		try {
			member = (MemberBean)session.createQuery(hql)
								.setParameter("acc", userId)
								.setParameter("pwd", password)
								.getSingleResult();
		} catch(NoResultException ex) {
			;
		} catch(NonUniqueResultException ex) {
			;
		} 	
		return member;
	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("本類別為提供此功能");
		
	}

	@Override
	public MemberBean queryMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> serchMemberaccount() {
		String hql = "FROM MemberBean";
		Session session = factory.getCurrentSession();
		List<String> list = session.createQuery(hql).getResultList();
		return list;
		
	}

	@Override
	public MemberBean updatePasswd(String memberEmail) {
		MemberBean member = null;
		Session session = factory.getCurrentSession();
		String hql = "From MemberBean m WHERE m.account = :acc";
		System.out.println("dao");
		try {
			member = (MemberBean)session.createQuery(hql)
									.setParameter("acc",memberEmail)
									.getSingleResult();
			System.out.println(member.getMemberId());
			System.out.println("成功");
		} catch (NoResultException e) {
			System.out.println("失敗");
		}
		System.out.println(member);
		return member;
		}
	}

