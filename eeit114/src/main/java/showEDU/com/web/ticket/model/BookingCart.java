package showEDU.com.web.ticket.model;

import java.util.LinkedHashMap;
import java.util.Map;
public class BookingCart {   
	
	private Map<Integer, MovieOrderDetailBean> cart = new LinkedHashMap< >();
	
	public BookingCart() {
	}

	public Map<Integer, MovieOrderDetailBean> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, MovieOrderDetailBean> cart) {
		this.cart = cart;
	}

	public BookingCart(Map<Integer, MovieOrderDetailBean> cart) {
		super();
		this.cart = cart;
	}
	


	
}
