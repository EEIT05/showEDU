package showEDU.com.web.store.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.store.dao.OrdersDao;
import showEDU.com.web.store.model.ProductOrdersBean;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	private String memberId = null;
	@Autowired
	SessionFactory factory;
	int orderNo = 0;
	
	@Override
	public void insertOrder(ProductOrdersBean pob) {
		Session session = factory.getCurrentSession();
		session.save(pob);

	}
	
	public ProductOrdersBean getOrder(int formNumber) {
		ProductOrdersBean pob = null;
		Session session = factory.getCurrentSession();
		pob = session.get(ProductOrdersBean.class, formNumber);
		return  pob;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductOrdersBean>getAllOrders(){
		List<ProductOrdersBean>list = null;
		String hql = "From ProductOrders";
		Session session = factory.getCurrentSession();
		
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductOrdersBean>getMemberOrders(Integer memberId ){
		List<ProductOrdersBean>list = null;
		Session session = factory.getCurrentSession();
		String hql = "From ProductOrdersBean pob where pob.MemberBean.memberId =:mid";
		list = session.createQuery(hql).setParameter("mid", memberId).getResultList();
		return list;
	}


}
