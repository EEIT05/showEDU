package showEDU.com.web.store.controller;

import java.lang.reflect.Member;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.store.model.PeripheralProductBean;
import showEDU.com.web.store.model.ProductOrdersItemsBean;
import showEDU.com.web.store.model.ShoppingCart;
import showEDU.com.web.store.service.PeripheralService;

@Controller
@SessionAttributes({"memberBean","ShoppingCart","product"})
public class ShoppingCartControll {
	
	@Autowired
	ServletContext ctx;
	
	@Autowired
	PeripheralService peripheralService;
	
	//點選加入購物車
	@PostMapping("/addCart")
	protected String buyProduct(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		MemberBean memberBean = (MemberBean)model.getAttribute("memberBean");
		if(memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart)model.getAttribute("ShoppingCart");
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			System.out.println("新建ShoppingCart...");
			model.addAttribute("ShoppingCart", cart);
		}
		String productIdStr = request.getParameter("productId") ;
		int productId = Integer.parseInt(productIdStr.trim());
		
		String buyCountStr = request.getParameter("buyCount");
		System.out.println(buyCountStr+"+++++++++++++");
		Integer buyCount = 0;
//		String productname = request.getParameter("name");
		PeripheralProductBean bean = peripheralService.getProductById(productId);
		try {
			buyCount = Integer.parseInt(buyCountStr.trim());
			System.out.println(buyCount+"===================");
		} catch (Exception e) {
			throw new Exception(e);
		}
	
		ProductOrdersItemsBean poib = new ProductOrdersItemsBean();
		poib.setBuyCount(buyCount);
		poib.setProductId(productId);
//		poib.getPeripheralProductBean().getPrice();
//		poib.getPeripheralProductBean().getName();
		poib.setPeripheralProductBean(bean);
		cart.addToCart(productId, poib);
		
		return "redirect:/showproduct";
		}
	
	@GetMapping("ShowCartContent")
	protected String showCartContent(Model model) {
		MemberBean memberBean = (MemberBean)model.getAttribute("memberBean");
		if(memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		return "store/ShowCartContent3";
	}
	
	@GetMapping("checkout")
	protected String checkout(Model model) {
		MemberBean memberBean = (MemberBean)model.getAttribute("memberBean");
		if(memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		return "store/OrderConfirm";
		//return "store/ThanksForOrdering";
		//return "redirect: " + ctx.getContextPath() + "/store/ThanksForOrdering";
	}
}
