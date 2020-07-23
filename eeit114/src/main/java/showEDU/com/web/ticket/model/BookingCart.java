//package showEDU.com.web.ticket.model;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Set;
//public class BookingCart {   
//	
//	private Map<Integer, MovieOrderDetailBean> cart = new LinkedHashMap< >();
//	
//	public BookingCart() {
//	}
//	
//	public BookingCart(Map<Integer, MovieOrderDetailBean> cart) {
//		super();
//		this.cart = cart;
//	}
//
//	public Map<Integer, MovieOrderDetailBean> getCart() {
//		return cart;
//	}
//
//	public void setCart(Map<Integer, MovieOrderDetailBean> cart) {
//		this.cart = cart;
//	}
//
//	public Map<Integer, MovieOrderDetailBean>  getContent() { // ${ShoppingCart.content}
//		return cart;
//	}
//	public void addToCart(Integer movieTicketId, MovieOrderDetailBean  oib) {
//		if (cart.size() <= 0 ) {
//			return;
//		}
//		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
//		if ( cart.get(movieTicketId) == null ) {
//		    cart.put(movieTicketId, oib);
//		} else {
//	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
//			MovieOrderDetailBean oiBean = cart.get(movieTicketId);
////			 加購的數量：bean.getQuantity()
////			 原有的數量：oBean.getQuantity()			
//		}
//	}
//
//	public int getTicketNumber(){   // ShoppingCart.itemNumber
//		return cart.size();
//	}
//	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
//	public double getSubtotal(){
//		double subTotal = 0 ;
//		Set<Integer> set = cart.keySet();
//		for(int n : set){
//			MovieOrderDetailBean oib = cart.get(n);
//			Integer price    = oib.getUnitPrice();
//			subTotal +=  price ;
//		}
//		return subTotal;
//	}
//	
//}
