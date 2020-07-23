package showEDU.com.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import showEDU.com.web.member.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/back")
	public String back() {
		return "back";
	}

}
