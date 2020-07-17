package showEDU.com.web.application.controller;

import java.sql.Date;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.application.model.ActClassBean;
import showEDU.com.web.application.model.ActivityBean;
import showEDU.com.web.application.model.ApplicationBean;
import showEDU.com.web.application.service.ApplicationService;

import showEDU.com.web.member.model.MemberBean;




@Controller
@SessionAttributes({"loginMember"})
public class ApplicationController {
	@Autowired
	ApplicationService aplcService;
	@Autowired
	ServletContext ctx;
	
	@GetMapping("/application/add")
	public String getAddNewApplicationForm(Model model) {
		ApplicationBean aplcBean =new ApplicationBean();
		model.addAttribute("aplcBean", aplcBean);
		MemberBean memberBean = (MemberBean) model.getAttribute("loginMember");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		return 	"application/addApplication";
	}
	
	@GetMapping("/getOrderableTime")
	public ResponseEntity<List<String>> getOrderableTime
	(@RequestParam(value="selectDate", defaultValue = "0") Date selectDate)  {
		List<String> list = aplcService.getOrderableTime(selectDate);
		ResponseEntity<List<String>> re = new ResponseEntity<>(list,HttpStatus.OK);
		return re;
	}
	
	
	@PostMapping("/application/add")
	public String processAddNewApplicationForm(Model model,@ModelAttribute("aplcBean") ApplicationBean ab, 
		   BindingResult result	) {
		MemberBean memberBean = (MemberBean) model.getAttribute("loginMember");
		int memberId = memberBean.getMemberId();
		ab.setMemberId(memberId);
		ab.setTheaterId(3);
		ab.setTotalAmount(48000);
		java.util.Date current = new Date(System.currentTimeMillis());
		Timestamp aplcTime = new Timestamp(current.getTime());
		ab.setAplcTime(aplcTime);
		ab.setStatusId(1);
		ab.setPayStatus(0);
		aplcService.addApplication(ab,ab.getDate(),ab.getTime());
		return 	"redirect: " + ctx.getContextPath() + "/yourApplication";
	}


	@ModelAttribute("actClassList")
	public Map<Integer, String> getActClassList(){
		 Map<Integer, String> map = new HashMap<Integer, String>();
		 List<ActClassBean>  list = aplcService.getAllActClass();
		 for(ActClassBean ac : list) {
			 map.put(ac.getActClassId(), ac.getName());
		 }
		return map;
	}
	@ModelAttribute("orderableDate")
	public List<String> getorderableDate(){
		java.util.Date current = new Date(System.currentTimeMillis());
		//根據某個特定的時間 date (Date 型) 計算
		Calendar startDateC = Calendar.getInstance();
		startDateC.setTime(current); //注意在此處將 specialDate 的值改為特定日期
		startDateC.add(Calendar.DATE, 7); //特定時間的年後
		java.util.Date startDate = startDateC.getTime();
		
		Calendar endDateC = Calendar.getInstance();
		endDateC.setTime(current); //注意在此處將 specialDate 的值改為特定日期
		endDateC.add(Calendar.DATE, 31); //特定時間的年後
		java.util.Date endDate = endDateC.getTime();
		
		List<Date> orderableDates = aplcService.getOrderableDate(startDate, endDate);
		List<String> orderableDatesStr = new ArrayList<String>();
		for(Date orderableDate:orderableDates) {
			orderableDatesStr.add(orderableDate.toString());
		}
		
		return orderableDatesStr;
	}
	
	@GetMapping("/yourApplication")
	public String getYourApplication(Model model){
		MemberBean memberBean = (MemberBean) model.getAttribute("loginMember");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		int memberId = memberBean.getMemberId();
		List<ApplicationBean> beans = aplcService.getAllAplcBeanById(memberId);
		model.addAttribute("yourApplication", beans);
		return "application/showYourApplication";
	}
	
	@GetMapping("/allApplication")
	public String getAllApplication(Model model){
		MemberBean memberBean = (MemberBean) model.getAttribute("loginMember");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		
		List<ApplicationBean> beans = aplcService.getAllAplcBean();
		model.addAttribute("allApplication", beans);
		return "application/showAllApplication";
	}
	
	@GetMapping("/passApplication/{aplcId}")
	public String passApplication(Model model,@PathVariable int aplcId) {
		aplcService.changeAplcBeanStatusById(aplcId, 2);
		
		return "redirect:/allApplication";
	}
	@GetMapping("/failApplication/{aplcId}")
	public String failApplication(Model model,@PathVariable int aplcId) {
		aplcService.changeAplcBeanStatusById(aplcId, 3);
		
		return "redirect:/allApplication";
	}
	@GetMapping("/cancleApplication/{aplcId}")
	public String cancleApplication(Model model,@PathVariable int aplcId) {
		aplcService.changeAplcBeanStatusById(aplcId, 4);
		
		return "redirect:/allApplication";
	}
	
	
	

	
	
	
}
