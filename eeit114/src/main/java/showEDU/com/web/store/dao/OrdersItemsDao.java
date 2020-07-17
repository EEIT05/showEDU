package showEDU.com.web.store.dao;

import showEDU.com.web.store.model.ProductOrdersItemsBean;

public interface OrdersItemsDao {
	
	double findItemAmount(ProductOrdersItemsBean poib);
	
	int updateProductStock(ProductOrdersItemsBean poib) throws Exception;
}
