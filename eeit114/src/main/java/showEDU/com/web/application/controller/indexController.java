package showEDU.com.web.application.controller;

import java.sql.Date;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import showEDU.com.web.application.service.ActivityService;
import showEDU.com.web.application.service.ApplicationService;

import showEDU.com.web.member.model.MemberBean;




@Controller
@SessionAttributes({"memberBean"})
public class indexController {
	@Autowired
	ActivityService activityService;
	@Autowired
	ServletContext ctx;
	

	
	@GetMapping("/showAplcBeanAtIndex")
	public ResponseEntity<List<ActivityBean>>showAplcBeanAtIndex(Model model){
		List<ActivityBean> beans = activityService.getAllActivities();
		Collections.reverse(beans);
		List<ActivityBean> list =beans.subList(0, 3);
		System.out.println(list);

		ResponseEntity<List<ActivityBean>> re = new ResponseEntity<>(list, HttpStatus.OK);
		return re;
	}
	
	
	
	

	
	
	
}
