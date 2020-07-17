package showEDU.com.web.store.service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.member.repository.MemberRepository;
import showEDU.com.web.store.dao.OrdersDao;
import showEDU.com.web.store.dao.OrdersItemsDao;
import showEDU.com.web.store.model.PeripheralProductBean;
import showEDU.com.web.store.model.ProductOrdersBean;
import showEDU.com.web.store.model.ProductOrdersItemsBean;
import showEDU.com.web.store.service.ShoppingCartService;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	OrdersDao od;
	@Autowired
	OrdersItemsDao oid;
	@Autowired
	MemberRepository mr;
	
	@Transactional
	@Override
	public void persistOrder(ProductOrdersBean pob) {
		// 檢查並更新會員的未付款餘額
		//mr.updateUnpaidOrderAmount(pob);
		
		try {
			checkStock(pob);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		// 儲存該筆訂單
		od.insertOrder(pob);
	}
	
	public void checkStock(ProductOrdersBean pob) throws Exception {
		Set<ProductOrdersItemsBean>items = pob.getItems();
		for(ProductOrdersItemsBean poib : items) {
			oid.updateProductStock(poib);
		}
	}

	@Transactional
	@Override
	public ProductOrdersBean getOrder(int formNumber) {
		ProductOrdersBean bean = null;
		bean = od.getOrder(formNumber);
		return bean;
	}

	@Transactional
	@Override
	public List<ProductOrdersBean> getAllOrders() {
		List<ProductOrdersBean>list = null;
		list = od.getAllOrders();
		return list;
	}

	@Transactional
	@Override
	public List<ProductOrdersBean> getMemberOrders(Integer memberId) {
		List<ProductOrdersBean>list = null;
		list = od.getMemberOrders(memberId);
		return list;
	}

}
