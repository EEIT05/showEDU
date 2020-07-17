package showEDU.com.web.member.repository.Impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.member.model.AdministratorBean;
import showEDU.com.web.member.repository.AdministratorRepository;

@Repository
public class AdministratorRepositoryImpl implements AdministratorRepository {

	@Autowired
	SessionFactory factory;

	public Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void save(AdministratorBean adm) {
		Session session = factory.getCurrentSession();
		session.save(adm);
	}

	@Override
	public void deleteAdm(Integer admId) {
		Session session = factory.getCurrentSession();
		AdministratorBean adm = getAdmById(admId);
		if (adm != null) {
			session.delete(adm);
		}
	}

	@Override
	public AdministratorBean getAdmById(int admId) {
		Session session = factory.getCurrentSession();
		return session.get(AdministratorBean.class, admId);
	}

	@Override
	public void updateAdm(AdministratorBean adm) {
		if (adm != null && adm.getAdmId() != null) {
			Session session = factory.getCurrentSession();
			session.saveOrUpdate(adm);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdministratorBean> getAlladms() {
		String hql = "FROM AdministratorBean";
		Session session = factory.getCurrentSession();
		List<AdministratorBean> list=session.createQuery(hql)
				                            .getResultList();
		return list;
	}

	@Override
	public AdministratorBean login(String adaccount, String adpswd) {
		
		AdministratorBean adm=null;
		Session session = factory.getCurrentSession();
		String hql = "From AdministratorBean a WHERE a.adaccount = :adc and a.adpswd = :pwd";
		System.out.println("dao");
		try {
			adm=(AdministratorBean)session.createQuery(hql)
					                      .setParameter("adc", adaccount)
					                      .setParameter("pwd", adpswd)
					                      .getSingleResult();
			System.out.println(adm.getAdmId());
			System.out.println("登入成功");
		} catch (NoResultException e) {
			System.out.println("登入失敗");
		}
		System.out.println(adm);
		return adm;
	}

}
