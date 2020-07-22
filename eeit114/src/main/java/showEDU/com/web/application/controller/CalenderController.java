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
@SessionAttributes({"memberBean"})
public class CalenderController {
	@Autowired
	ApplicationService aplcService;
	@Autowired
	ServletContext ctx;
	
	
	
	@GetMapping("/showCalender")
	public String showCalender() {
		return "application/calender";
	}
	
	@GetMapping("/showAplcBeanByMonth")
	public ResponseEntity<List<Map<String, Object>>>showAplcBeanByMonth
	(Model model,@RequestParam("month")int month){
		List<ApplicationBean> beans = aplcService.getAplcBeanByMonth(month);

		List<Map<String, Object>> list =new ArrayList<>();
		System.out.println(beans);
		for(ApplicationBean bean:beans) {
			Map<String, Object> Map = new LinkedHashMap<>();
			Date date = bean.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int aplcDate = cal.get(Calendar.DAY_OF_MONTH);
			Map.put("date", aplcDate);
			Map.put("time", bean.getTime());
			list.add(Map);
		}

		ResponseEntity<List<Map<String, Object>>> re = new ResponseEntity<>(list, HttpStatus.OK);
		return re;
	}
	
	
	
	

	
	
	
}
