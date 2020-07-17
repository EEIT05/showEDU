package showEDU.com.web.store.dao;

import java.util.List;

import showEDU.com.web.store.model.ProductOrdersBean;

public interface OrdersDao {
	void insertOrder(ProductOrdersBean pob);
	
	ProductOrdersBean getOrder(int formNumber);
	
	List<ProductOrdersBean>getAllOrders();
	
	 List<ProductOrdersBean>getMemberOrders(Integer memberId );
}
