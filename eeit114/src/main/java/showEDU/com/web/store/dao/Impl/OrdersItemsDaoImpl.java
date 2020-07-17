 package showEDU.com.web.store.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.store.dao.OrdersItemsDao;
import showEDU.com.web.store.model.ProductOrdersItemsBean;
@Repository
public class OrdersItemsDaoImpl implements OrdersItemsDao {
	@Autowired
	SessionFactory factory;
	
	@Override
	public double findItemAmount(ProductOrdersItemsBean poib) {
		double butTotal = poib.getBuyCount() * poib.getPeripheralProductBean().getPrice();
		return butTotal;
	}

	@Override
	public int updateProductStock(ProductOrdersItemsBean poib) throws Exception {
		int n = 0;
		Integer stock = 0; 
		Session session = factory.getCurrentSession();
		String hql1 = "Select stock From PeripheralProductBean where productId = :productId";
		String hql2 = "Update PeripheralProductBean Set stock = stock - :butCount where productId = :productId";
		stock = (Integer) session.createQuery(hql1).setParameter("productId", poib.getPeripheralProductBean().getProductId())
										 .getSingleResult();
		if (stock == null) {
			stock = 0;
		}
		int stockLeft = stock - poib.getBuyCount();
		if (stockLeft < 0) {
			throw new Exception("庫存數量不足: productId: " + poib.getPeripheralProductBean().getProductId() + ", 在庫量: "
					+ stock + ", 訂購量: " + poib.getBuyCount());
		}
		n = session.createQuery(hql2).setParameter("butCount", poib.getBuyCount())
									.setParameter("productId", poib.getPeripheralProductBean().getProductId())
									.executeUpdate();
		return n;
	}
	 

}
