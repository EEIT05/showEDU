package showEDU.com.web.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.store.model.ProductOrdersBean;
import showEDU.com.web.store.model.ProductOrdersItemsBean;
import showEDU.com.web.store.service.ShoppingCartService;

@Controller
@SessionAttributes({"memberBean","ShoppingCart"})
public class OrderListController {
	@Autowired
	ServletContext ctx;
	
	@Autowired
	ShoppingCartService cartService;
	
	@GetMapping("orderList")
	protected String orderList(Model model) {
		MemberBean memberBean = (MemberBean)model.getAttribute("memberBean");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		List<ProductOrdersBean>memberOrders =  cartService.getMemberOrders(memberBean.getMemberId());
		model.addAttribute("memberOrders",memberOrders);
		return "store/MemberOrder";
	}
	
	@GetMapping("/orderDetail")
	protected String orderDetail(@RequestParam("orderNo")Integer no,Model model) {
		MemberBean memberBean = (MemberBean)model.getAttribute("memberBean");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		ProductOrdersBean pob = cartService.getOrder(no);
		
		model.addAttribute("pob",pob);
		System.out.println(no+"===============");
		System.out.println(pob.toString()+"===============");
		System.out.println(pob.getItems()+"===============");
		//return "store/ShowOrderDetail";
		return "store/NewFile";
	}
	
	@GetMapping("/ajaxOrderDetail")
	public ResponseEntity<List<ProductOrdersBean>>ajaxOrderDetail(@RequestParam(value = "orderNo")Integer no){
		ProductOrdersBean p = cartService.getOrder(no);
		List<ProductOrdersBean> listp = new ArrayList<ProductOrdersBean>();
		listp.add(p);
		
		ResponseEntity<List<ProductOrdersBean>> re = new ResponseEntity<List<ProductOrdersBean>>(listp,HttpStatus.OK);
		return re;
	}
	
}
