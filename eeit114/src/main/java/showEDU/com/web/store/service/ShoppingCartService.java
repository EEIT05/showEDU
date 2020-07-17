package showEDU.com.web.store.service;

import java.util.List;

import showEDU.com.web.store.model.ProductOrdersBean;




public interface  ShoppingCartService {

	void persistOrder(ProductOrdersBean pob);
	
	ProductOrdersBean getOrder(int formNumber);
	
	List<ProductOrdersBean> getAllOrders();

	List<ProductOrdersBean> getMemberOrders(Integer memberId);
}
