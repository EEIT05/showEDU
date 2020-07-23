package showEDU.com.web.store.controller;



import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.store.model.ProductOrdersBean;
import showEDU.com.web.store.model.ProductOrdersItemsBean;
import showEDU.com.web.store.model.ShoppingCart;
import showEDU.com.web.store.service.ShoppingCartService;

@Controller
@SessionAttributes({"memberBean","ShoppingCart"})
public class ProcessOrderController {
	
//	public static AllInOne all;
	@Autowired
	ServletContext ctx;
	
	@Autowired
	ShoppingCartService cartService;
	
//	private static void Initial(){
//		all = new AllInOne("");
//	}
	
	@PostMapping("ProcessOrder")
	protected String ProcessOrder(Model model,
			@RequestParam("ShippingAddress")String address2,
			@RequestParam("ShippingPhone")String phone,
			@RequestParam("ShippingName")String name,
			WebRequest webRequest, SessionStatus status) {
		
		MemberBean memberBean = (MemberBean)model.getAttribute("memberBean");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		ShoppingCart sc = (ShoppingCart)model.getAttribute("ShoppingCart");
		if (sc == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		
		Integer memberId = memberBean.getMemberId();
		double totalAmount = Math.round(sc.getBuyTotal());// 計算訂單總金額 
		Date ordersDate = new Date();
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
//		ProductOrdersBean pob = new ProductOrdersBean(null, memberId, totalAmount, ordersDate, 
//				null);
		ProductOrdersBean pob = new ProductOrdersBean();
		pob.setFormNumber(null);
		pob.setMemberId(memberId);
		pob.setOrdersDate(ordersDate);
		pob.setPayStatus("未付款");
		pob.setSendStatus("未寄送");
		pob.setTotalAmount(totalAmount);
		pob.setItems(null);
		// 取出存放在購物車內的商品，放入Map型態的變數cart，準備將其內的商品一個一個轉換為OrderItemBean
		Map<Integer, ProductOrdersItemsBean> content = sc.getContent();
		Set<ProductOrdersItemsBean>items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for(Integer i : set) {
			ProductOrdersItemsBean poib = content.get(i);
			poib.setAddress(address2);
			poib.setPhone(phone);
			poib.setName(name);
			poib.setUseDiscount("N");
			poib.setProductOrdersBean(pob);
			items.add(poib);
		}
		// 執行到此，購物車內所有購買的商品已經全部轉換為為OrderItemBean物件，並放在Items內
		pob.setItems(items);
		try {
			cartService.persistOrder(pob);
			
			System.out.println("Order Process OK");
//			Initial();
//			AioCheckOutALL obj = new AioCheckOutALL();
			return "forward:" + "removeShoppingCart";
			
		} catch (RuntimeException e) {
			String message = e.getMessage();
			String shortMsg = "" ;   
			System.out.println("message=" + message);
			shortMsg =  message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			model.addAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
			return "redirect: " + ctx.getContextPath() + "/store/ShowCartContent";
		}
		
	}
	
}
