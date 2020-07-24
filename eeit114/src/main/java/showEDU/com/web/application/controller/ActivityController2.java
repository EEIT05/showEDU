package showEDU.com.web.application.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import showEDU.com.web.application.model.ActivityBean;
import showEDU.com.web.application.service.ActivityService;
import showEDU.com.web.member.model.MemberBean;

@Controller
@SessionAttributes({"memberBean"})
public class ActivityController2 {
	@Autowired
	ActivityService service;
	@Autowired
	ServletContext ctx;
	
	//算總頁數
	public Integer getTotalpage(int listLength) {
		if((listLength % 2) == 0) {
			return (listLength/2);
		}else {
			return (listLength/2)+1;
		}
	}
	
	//將字串傳轉換成sql Date
	public static Date changeStringToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}
	
	//取得所有活動(客戶端與維護端)
	@GetMapping("/activities")
	public String actList(Model model) {
		List<ActivityBean> beans = service.getAllActivities();
		Collections.reverse(beans);
		model.addAttribute("activities", beans);
		return "application/activities";

	}
	//依PK取得活動，用於取得單個活動(客戶端與維護端)
	@GetMapping("/activity")
	public String getActivityById(@RequestParam("id") Integer id, Model model) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("loginMember");
//		model.addAttribute("memberBean",memberBean);
		model.getAttribute("memberBean");
		model.addAttribute("activity", service.getActivityById(id));
		return "application/activity";
	}
	
	//依日期條件取得活動頁面(維護端)
	@GetMapping("/activitiesDate")
	public String activitiesDate(Model model,@RequestParam(value ="pageNo",required = false)Integer pageNo) {
		List<ActivityBean> beans = service.getAllActivities();
		Collections.reverse(beans);
		int totalPage = getTotalpage(beans.size());
		model.addAttribute("totalPage", totalPage);
		if(pageNo == null) {
			pageNo = 1;
		};
		int startRecordNo = (pageNo - 1) * 2;
		int LastRecordNo = pageNo * 2;
		if(LastRecordNo>beans.size()) {
			LastRecordNo = beans.size();
		}
		List<ActivityBean> beansPerPage = beans.subList(startRecordNo, LastRecordNo); 
		model.addAttribute("activities", beansPerPage);
		return "application/activitiesDate";

	}
	
	//依日期條件取得活動，回傳JSON(維護端)，沒有用到
//	@GetMapping("/activitiesByDate")
//	public ResponseEntity<List<ActivityBean>> getAllActivitiesByDate(Model model, @RequestParam(value = "date",required = false) Date date
//			,@RequestParam(value ="pageNo",required = false)Integer pageNo) {
//		System.out.println(date);
//		ResponseEntity<List<ActivityBean>> re =null;
//		List<ActivityBean> bean = null;
//		if(date == null) {
//			bean = service.getAllActivities();
//		}else {
//			bean = service.getAllActivitiesByDate(date);
//		}
//		int totalPage = getTotalpage(bean.size());
//		model.addAttribute("totalPage", totalPage);
//		if(pageNo == null) {
//			pageNo = 1;
//		};
//		int startRecordNo = (pageNo - 1) * 2;
//		int LastRecordNo = pageNo * 2;
//		if(LastRecordNo>bean.size()) {
//			LastRecordNo = bean.size();
//		}
//		List<ActivityBean> beansPerPage = bean.subList(startRecordNo, LastRecordNo); 
//		re = new ResponseEntity<>(beansPerPage, HttpStatus.OK);
//		return re;
//
//	}
	
	//依日期條件取得活動頁面有做分頁(維護端)
	@GetMapping("/activitiesByDatePerPage")
	public String getAllActivitiesByDatePerPage(Model model, @RequestParam(value = "date",required = false) Date date
			,@RequestParam(value ="pageNo",required = false)Integer pageNo) {
		System.out.println(date);
		model.addAttribute("selectDate", date);
		List<ActivityBean> bean = null;
		
		if(date == null) {
			bean = service.getAllActivities();
		}else {
			bean = service.getAllActivitiesByDate(date);
		}
		Collections.reverse(bean);
		int totalPage = getTotalpage(bean.size());
		model.addAttribute("totalPage", totalPage);
		if(pageNo == null) {
			pageNo = 1;
		};
		int startRecordNo = (pageNo - 1) * 2;
		int LastRecordNo = pageNo * 2;
		if(LastRecordNo>bean.size()) {
			LastRecordNo = bean.size();
		}
		List<ActivityBean> beansPerPage = bean.subList(startRecordNo, LastRecordNo); 
		model.addAttribute("activities", beansPerPage);
		return "application/activitiesDate";
		
	}
	@GetMapping("/activitiesByDateAll")
	public String actListByDateAll(Model model) {
		List<ActivityBean> beans = service.getAllActivities();
		Collections.reverse(beans);
		model.addAttribute("activities", beans);
		return "application/activitiesDate";

	}
	//取得圖片
	@GetMapping("/getActPicture/{actId}")
	public ResponseEntity<byte[]> getActPicture(HttpServletResponse req, @PathVariable Integer actId) {
		String noImagePath = "/resource/images/NoImage.jpg";
		ResponseEntity<byte[]> re = null;
		ActivityBean bean = service.getActivityById(actId);
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		if(bean.getActImg()!=null) {
			try {
				Blob blob = bean.getActImg();
				if (blob != null) {
					is = blob.getBinaryStream();
				} else {
					;
				}
				if (is == null) {
					is = ctx.getResourceAsStream(noImagePath);
				}
				baos = new ByteArrayOutputStream();
				int len = 0;
				byte[] b = new byte[819200];
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		byte[] b0 = baos.toByteArray();
		
		String filename = bean.getFileName();
		MediaType mediaType = null;
		if (filename != null) {
			String mimeType = ctx.getMimeType(filename);
			mediaType = MediaType.valueOf(mimeType);
		} else {
			String mimeType = ctx.getMimeType(noImagePath);
			mediaType = MediaType.valueOf(mimeType);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		re = new ResponseEntity<>(b0, headers, HttpStatus.OK);

		return re;
	}
	//生成空白表單，用於新增(維護端)
	@GetMapping("/activities/add")
	public String getAddNewActivityForm(Model model) {
		ActivityBean ab = new ActivityBean();
		model.addAttribute("activityBean", ab);
		return "application/addActivity";
	}
	
	//取得表單資料，用於新增(維護端)
	@PostMapping("/activities/add")
	public String processAddNewActivityForm(@ModelAttribute("activityBean") ActivityBean ab, BindingResult result) {

//		String[] suppressedFields = result.getSuppressedFields();
//		if (suppressedFields.length > 0) {
//			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
//		}

		MultipartFile activityImage = ab.getActivityImage();
		String originalFilename = activityImage.getOriginalFilename();
		ab.setFileName(originalFilename);
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (activityImage != null && !activityImage.isEmpty()) {
			try {
				byte[] b = activityImage.getBytes();
				Blob blob = new SerialBlob(b);
				ab.setActImg(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date postDate = new java.sql.Date(utilDate.getTime());
		ab.setPostDate(postDate);
		service.addActivity(ab);
		
		if (activityImage != null && !activityImage.isEmpty()) {
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			String rootDirectory = ctx.getRealPath("/");
			try {
				File imageFolder = new File(rootDirectory, "images");
				if (!imageFolder.exists())
					imageFolder.mkdirs();
				File file = new File(imageFolder, ab.getActId() + ext);
				activityImage.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		return "redirect:/activities";
	}
	
	//生成空白表單，修改活動(以PK為條件)(維護端)
	@GetMapping("/activity/update/{actId}")
	public String getUpdateNewActivityForm(Model model, @PathVariable Integer actId) {
		ActivityBean ab = new ActivityBean();
		ActivityBean bean = service.getActivityById(actId);
		//以下程式為在空白表單中塞入欲修改bean的資料
		ab.setActId(actId);
		ab.setActTitle(bean.getActTitle());
		ab.setStartDate(bean.getStartDate());
		ab.setEndDate(bean.getEndDate());
		ab.setDescr(bean.getDescr());
		ab.setFileName(bean.getFileName());
		ab.setActImg(bean.getActImg());
		model.addAttribute("activityBean", ab);
		return "application/updateActivity";
	}
	
	//取得表單資料，用於修改活動(以PK為條件)(維護端)
	@PostMapping("/activity/update/{actId}")
	public String processUpdateNewActivityForm(@ModelAttribute("activityBean") ActivityBean ab, BindingResult result) {

//		String[] suppressedFields = result.getSuppressedFields();
//		if (suppressedFields.length > 0) {
//			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
//		}
		MultipartFile activityImage = ab.getActivityImage();
		String originalFilename = null;
		if (activityImage != null) {
			originalFilename = activityImage.getOriginalFilename();
			ab.setFileName(originalFilename);
			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (activityImage != null && !activityImage.isEmpty()) {
				try {
					byte[] b = activityImage.getBytes();
					Blob blob = new SerialBlob(b);
					ab.setActImg(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
		}
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date postDate = new java.sql.Date(utilDate.getTime());
		ab.setPostDate(postDate);
		service.updateActivity(ab, ab.getActId());

		if (activityImage != null && !activityImage.isEmpty()) {
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			String rootDirectory = ctx.getRealPath("/");
			try {
				File imageFolder = new File(rootDirectory, "images");
				if (!imageFolder.exists())
					imageFolder.mkdirs();
				File file = new File(imageFolder, ab.getActId() + ext);
				activityImage.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		} else {
			;
		}
		return "redirect:/activities";
	}

}
