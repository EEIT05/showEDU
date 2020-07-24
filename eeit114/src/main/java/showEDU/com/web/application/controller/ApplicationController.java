package showEDU.com.web.application.controller;

import java.sql.Date;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
@SessionAttributes({ "memberBean" })
public class ApplicationController {
	@Autowired
	ApplicationService aplcService;
	@Autowired
	ServletContext ctx;

	// 算總頁數
	public Integer getTotalpage(int listLength) {
		if ((listLength % 2) == 0) {
			return (listLength / 2);
		} else {
			return (listLength / 2) + 1;
		}
	}

	// 申請租借，產生表單(客戶端)
	@GetMapping("/application/add")
	public String getAddNewApplicationForm(Model model) {
		ApplicationBean aplcBean = new ApplicationBean();
		model.addAttribute("aplcBean", aplcBean);
		MemberBean memberBean = (MemberBean) model.getAttribute("memberBean");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		return "application/addApplication";
	}

	// 申請租借，產生包含選擇日期且狀態為未被借出之Bean
	// 用途為將可選的時段放入下拉選單(客戶端)
	@GetMapping("/getOrderableTime")
	public ResponseEntity<List<String>> getOrderableTime(
			@RequestParam(value = "selectDate", defaultValue = "0") Date selectDate) {
		List<String> list = aplcService.getOrderableTime(selectDate);
		ResponseEntity<List<String>> re = new ResponseEntity<>(list, HttpStatus.OK);
		return re;
	}

	// 申請租借，接收表單
	@PostMapping("/application/add")
	public String processAddNewApplicationForm(Model model, @ModelAttribute("aplcBean") ApplicationBean ab,
			BindingResult result) {
		MemberBean memberBean = (MemberBean) model.getAttribute("memberBean");
		int memberId = memberBean.getMemberId();
		ab.setMemberId(memberId);
		ab.setTheaterId(3);
		ab.setTotalAmount(48000);
		java.util.Date current = new Date(System.currentTimeMillis());
		Timestamp aplcTime = new Timestamp(current.getTime());
		ab.setAplcTime(aplcTime);
		ab.setStatusId(1);
		ab.setPayStatus(0);
		String re = null;
		try {
			aplcService.addApplication(memberId, ab, ab.getDate(), ab.getTime());
			return "redirect: " + ctx.getContextPath() + "/yourApplication";
		} catch (Exception e) {
			re = e.getMessage();
			model.addAttribute("check", re);
			return "application/failAddApplication";
		}
	}

	// 申請租借，產生租借場地類別(客戶端)
	@ModelAttribute("actClassList")
	public Map<Integer, String> getActClassList() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<ActClassBean> list = aplcService.getAllActClass();
		for (ActClassBean ac : list) {
			map.put(ac.getActClassId(), ac.getName());
		}
		return map;
	}

	// 申請租借，產生租借場地日期(可選期間為7天後開始至31天後)(客戶端)
	@ModelAttribute("orderableDate")
	public List<String> getorderableDate() {
		java.util.Date current = new Date(System.currentTimeMillis());
		// 根據某個特定的時間 date (Date 型) 計算
		Calendar startDateC = Calendar.getInstance();
		startDateC.setTime(current); // 注意在此處將 specialDate 的值改為特定日期
		startDateC.add(Calendar.DATE, 7); // 特定時間的年後
		java.util.Date startDate = startDateC.getTime();

		Calendar endDateC = Calendar.getInstance();
		endDateC.setTime(current); // 注意在此處將 specialDate 的值改為特定日期
		endDateC.add(Calendar.DATE, 31); // 特定時間的年後
		java.util.Date endDate = endDateC.getTime();

		List<Date> orderableDates = aplcService.getOrderableDate(startDate, endDate);
		List<String> orderableDatesStr = new ArrayList<String>();
		for (Date orderableDate : orderableDates) {
			orderableDatesStr.add(orderableDate.toString());
		}

		return orderableDatesStr;
	}

	@GetMapping("/yourApplication")
	public String getYourApplication(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("memberBean");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		int memberId = memberBean.getMemberId();
		List<ApplicationBean> beans = aplcService.getAllAplcBeanById(memberId);
		model.addAttribute("yourApplication", beans);
		return "application/showYourApplication";
	}

	@GetMapping("/allApplication")
	public String getAllApplication(Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		MemberBean memberBean = (MemberBean) model.getAttribute("memberBean");
		if (memberBean == null) {
			return "redirect: " + ctx.getContextPath() + "/member/crm/login";
		}
		List<ApplicationBean> beans = aplcService.getAllAplcBean();
		int totalPage = getTotalpage(beans.size());
		model.addAttribute("totalPage", totalPage);
		if (pageNo == null) {
			pageNo = 1;
		}
		;
		int startRecordNo = (pageNo - 1) * 2;
		int LastRecordNo = pageNo * 2;
		if (LastRecordNo > beans.size()) {
			LastRecordNo = beans.size();
		}
		List<ApplicationBean> beansPerPage = beans.subList(startRecordNo, LastRecordNo);
		model.addAttribute("allApplication", beansPerPage);
		return "application/showAllApplication";
	}

	@GetMapping("/passApplication/{aplcId}")
	public String passApplication(Model model, @PathVariable int aplcId) {
		aplcService.changeAplcBeanStatusById(aplcId, 2);

		return "redirect:/allApplication";
	}

	@GetMapping("/failApplication/{aplcId}")
	public String failApplication(Model model, @PathVariable int aplcId) {
		aplcService.changeAplcBeanStatusById(aplcId, 3);

		return "redirect:/allApplication";
	}

	@GetMapping("/cancleApplication/{aplcId}")
	public String cancleApplication(Model model, @PathVariable int aplcId) {
		aplcService.changeAplcBeanStatusById(aplcId, 4);

		return "redirect:/yourApplication";
	}

	@GetMapping("/changePayStatus/{aplcId}")
	public String changePayStatus(Model model, @PathVariable int aplcId) {

		aplcService.changeAplcBeanPayStatusById(aplcId, 1);
		return "redirect:/allApplication";
	}

	@GetMapping("/allApplicationByStatus")
	public ResponseEntity<Map<String, Object>> getAllApplicationByStatus(Model model,
			@RequestParam(value = "statusId", required = false) Integer statusId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		List<ApplicationBean> beans = null;
		if (statusId == 0) {
			beans = aplcService.getAllAplcBean();
		} else {
			beans = aplcService.getAllAplcBeanByStatus(statusId);
			System.out.println(beans);
		}
		System.out.println(beans);
		int totalPage = getTotalpage(beans.size());
		if (pageNo == null) {
			pageNo = 1;
		}
		;
		int startRecordNo = (pageNo - 1) * 2;
		int LastRecordNo = pageNo * 2;
		if (LastRecordNo > beans.size()) {
			LastRecordNo = beans.size();
		}
		List<ApplicationBean> beansPerPage = beans.subList(startRecordNo, LastRecordNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selectedStatus", statusId);
		map.put("application", beansPerPage);
		map.put("totalPage", totalPage);
		map.put("pageNo", pageNo);
		ResponseEntity<Map<String, Object>> re = new ResponseEntity<>(map, HttpStatus.OK);
		return re;
	}

	@GetMapping("/applicationDetail/{aplcId}")
	public String getApplicationById(Model model, @PathVariable int aplcId) {
		ApplicationBean bean = aplcService.getAplcBeanId(aplcId);
		model.addAttribute("application", bean);
		return "application/applicationDetail";
	}

}
