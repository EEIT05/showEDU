package showEDU.com.web.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.forum.service.MemberForumService;
import showEDU.com.web.member.model.MemberBean;
//@Controller
//@SessionAttributes({"LoginOK"}) 
//public class LoginController {
//	@Autowired
//	MemberForumService memberService;
//	
//	@GetMapping("/login")
//	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
//		List<MemberBean> allMembers = memberService.getAllMembers();
//		Map<Integer, MemberBean> memberMap = new HashMap<>();
//		for (MemberBean memberBean : allMembers) {
//			memberMap.put(memberBean.getMemberId(), memberBean);
//		}
//		int r = 0;
//		r = (int)(Math.random()*5)+1;
//		MemberBean mb = memberMap.get(r);
//		model.addAttribute("LoginOK", mb);
//		// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
//		HttpSession session = request.getSession();
//		String nextPath = (String)session.getAttribute("requestURI");
//		if (nextPath == null) {
//			nextPath = request.getContextPath();
//		}
//		System.out.println("將會員" + mb.getName() + "加入session內");
//		return "redirect: " + nextPath;
//	}
//}
