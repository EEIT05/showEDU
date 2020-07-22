package showEDU.com.web.store.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

 

public class ShoppingCart {
	
	Map<Integer, ProductOrdersItemsBean>  cart = new LinkedHashMap< >();

	public ShoppingCart() {
		
	}
	
	public Map<Integer, ProductOrdersItemsBean>  getContent() { // ${ShoppingCart.content}
		
		return cart;
	}
	
	public void addToCart(int productId,ProductOrdersItemsBean poib) {
		if(poib.getBuyCount() <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if(cart.get(productId) == null) {
			cart.put(productId, poib);
		}else {
			 // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			ProductOrdersItemsBean poiBean = cart.get(productId);
			// 加購的數量：poib.getBuyCount()
			// 原有的數量：poiBean.getBuyCount()	
			poib.setBuyCount(poib.getBuyCount() + poiBean.getBuyCount());
		}
	}
	
	
	public boolean modifyQty(int productId,int newBuyCount) {
		if(cart.get(productId)!= null){
			ProductOrdersItemsBean poiBean = cart.get(productId);
			poiBean.setBuyCount(newBuyCount);
			return true;
		}else {
			return false;
		}
	}
	
	public int deleteproduct(int productId) {
		if(cart.get(productId) != null) {
			cart.remove(productId);
			return 1;
		}else {
			return 0;
		}
	}
	
	public int getItemNumber() {
		return cart.size();
	}
	
	public double getBuyTotal() {
		double buyTotal = 0;
		Set<Integer> set = cart.keySet();
		for(int n : set) {
			ProductOrdersItemsBean poib = cart.get(n);
			double price = poib.getPeripheralProductBean().getPrice();
			int buyCount = poib.getBuyCount();
			buyTotal += price * buyCount;
		}
		return buyTotal;
	}
	
}
