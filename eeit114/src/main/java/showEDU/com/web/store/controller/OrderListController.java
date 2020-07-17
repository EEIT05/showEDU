package showEDU.com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.store.model.ProductOrdersBean;
import showEDU.com.web.store.service.ShoppingCartService;

@Controller
@SessionAttributes({"loginMember","ShoppingCart"})
public class OrderListController {
	@Autowired
	ServletContext ctx;
	
	@Autowired
	ShoppingCartService cartService;
	
	@GetMapping("orderList")
	protected String orderList(Model model) {
		MemberBean memberBean = (MemberBean)model.getAttribute("loginMember");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		List<ProductOrdersBean>memberOrders =  cartService.getMemberOrders(memberBean.getMemberId());
		model.addAttribute("memberOrders",memberOrders);
		return "OrderList";
	}
	
	@GetMapping("orderDetail")
	protected String orderDetail(Model model,@RequestParam("orderNo")Integer no) {
		MemberBean memberBean = (MemberBean)model.getAttribute("loginMember");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		ProductOrdersBean pob = cartService.getOrder(no);
		model.addAttribute("pob",pob);
		return "ShowOrderDetail";
	}
	
}
