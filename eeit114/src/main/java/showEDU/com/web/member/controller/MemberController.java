package showEDU.com.web.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.member.service.AdministratorService;
import showEDU.com.web.member.service.MemberService;
import showEDU.com.web.member.validators.MemberValidator;

@Controller
@RequestMapping("/member/crm")
@SessionAttributes({"loginMember"})
public class MemberController {
	String noImage = "/images/NoImage.png";

	@Autowired
	MemberService memberService;
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	ServletContext context;

	// 顯示所有會員資料
	@GetMapping("/showAllMembers")
	public String list(Model model) {
		model.addAttribute("members", memberService.getAllMembers());
		return "member/crm/members";
	}
	
	
	// 顯示本會員資料
	@GetMapping("/member")
	public String listMem(@RequestParam Integer memberId,Model model) {
		model.addAttribute("member", memberService.getMemberById(memberId));
		return "member/crm/member";
	}


	// 本方法於新增時，送出空白的表單讓使用者輸入資料
	@GetMapping(value = "/mem")
	public String showEmptyForm(Model model) {
		MemberBean member = new MemberBean();
//      下列敘述簡化測試時的資料輸入		
		
		return "member/crm/insertMember";
	}

	// 當使用者填妥資料按下Submit按鈕後，本方法接收將瀏覽器送來的會員資料，新進行資料的檢查，
	// 若資料有誤，轉向寫入錯誤訊息的網頁，若資料無誤，則呼叫Service元件寫入資料庫
	@PostMapping(value = ""+ "/mem")
	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
	public String add(
			@ModelAttribute("member") /* @Valid */ MemberBean member, 
			BindingResult result, Model model,
			HttpServletRequest request) {
		MemberValidator validator = new MemberValidator();
		// 呼叫Validate進行資料檢查
		validator.validate(member, result);
		if (result.hasErrors()) {
//          下列敘述可以理解Spring MVC如何處理錯誤			
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError error : list) {
//				System.out.println("有錯誤：" + error);
//			}
			return "member/crm/insertMember";
		}
		MultipartFile picture = member.getMemImage();
		String originalFilename = picture.getOriginalFilename();
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			member.setFileName(originalFilename);
		}
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (picture != null && !picture.isEmpty()) {
			try {
				byte[] b = picture.getBytes();
				Blob blob = new SerialBlob(b);
				member.setImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
//時間戳記
		Timestamp registerTime = new Timestamp(System.currentTimeMillis());
		member.setRegisterTime(registerTime);
		
		member.setUnpaidAmount(0.0);
		member.setUserType("M");
		
		
////		// 檢查 memberId是否重複
//		if (memberService.idExists(member.getMemberId())) {
//			result.rejectValue("memberId", "", "帳號已存在，請重新輸入");
//			return "member/crm/insertMember";
//		}
//		
		try {
			memberService.save(member);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			result.rejectValue("account", "", "帳號已存在，請重新輸入");
			return "crm/insertMember";
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
			result.rejectValue("account", "", "請通知系統人員...");
			return "member/crm/insertMember";
		}
		
		return "redirect:/member/crm/showAllMembers";
	}

	// 當使用者需要修改時，本方法送回含有會員資料的表單，讓使用者進行修改
	// 由這個方法送回修改記錄的表單...
	@GetMapping(value = "/mem/{memberId}")
	public String showDataForm(@PathVariable("memberId") Integer memberId, Model model) {
		MemberBean member = memberService.getMemberById(memberId);
		model.addAttribute(member);
		return "member/crm/updateMember";
	}
	
	// 當將瀏覽器送來修改過的會員資料時，由本方法負責檢核，若無誤則寫入資料庫
	@PostMapping("/mem/{memberId}")
	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
	// 
	public String modify(
			@ModelAttribute("member") MemberBean member, 
			BindingResult result, 
			Model model,
			@PathVariable Integer memberId, 
			HttpServletRequest request) {
		MemberValidator validator = new MemberValidator();
		validator.validate(member, result);
		if (result.hasErrors()) {
			System.out.println("result hasErrors(), member=" + member);
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println("有錯誤：" + error);
			}
			return "member/crm/insertMember";
		}

		MultipartFile picture = member.getMemImage();

		if (picture.getSize() == 0) {
			// 表示使用者並未挑選圖片
//			Member original = memberService.get(id);
//			member.setImage(original.getImage());
		} else {
			String originalFilename = picture.getOriginalFilename();
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				member.setFileName(originalFilename);
			}

			// 建立Blob物件
			if (picture != null && !picture.isEmpty()) {
				try {
					byte[] b = picture.getBytes();
					Blob blob = new SerialBlob(b);
					member.setImage(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
		}
		memberService.updateMember(member);
		return "redirect:/member/crm/showAllMembers";
	}


	// 刪除一筆紀錄
	// 由這個方法刪除記錄...
	@DeleteMapping("/mem/{memberId}")
	public String delete(@PathVariable("memberId") Integer memberId) {
		memberService.deleteMember(memberId);
		return "redirect:/member/crm/showAllMembers";
	}

	@GetMapping("/picture/{memberId}")
	public ResponseEntity<byte[]> getPicture(@PathVariable Integer memberId) {
		byte[] body = null;
		ResponseEntity<byte[]> re = null;
		MediaType mediaType = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		MemberBean member = memberService.getMemberById(memberId);
		if (member == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		String filename = member.getFileName();
		if (filename != null) {
			if (filename.toLowerCase().endsWith("jfif")) {
				mediaType = MediaType.valueOf(context.getMimeType("dummy.jpeg"));
			} else {
				mediaType = MediaType.valueOf(context.getMimeType(filename));
				headers.setContentType(mediaType);
			}
		}
		Blob blob = member.getImage();
		if (blob != null) {
			body = blobToByteArray(blob);
		} else {
			String path = null;

			body = fileToByteArray(path);
		}
		re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

		return re;
	}


	@ModelAttribute
	public void getMember(@PathVariable(value="memberId", required = false ) Integer memberId, Model model) {
		System.out.println("@ModelAttribute.getMember()...");
		if (memberId != null) {
			MemberBean member = memberService.getMemberById(memberId);
			model.addAttribute("member", member);
		} else {
			MemberBean member = new MemberBean();
			member.setLogin("false");
			model.addAttribute("member", member);
		}
		
	}
	

	// 本方法可對WebDataBinder進行組態設定。除了表單資料外，絕大多數可以傳入控制器方法的
	// 參數都可以傳入以@InitBinder修飾的方法。本方法最常使用的參數為WebDataBinder。
	//
	// org.springframework.web.bind.WebDataBinder
	// 為 org.springframework.validation.DataBinder 的子類別，它將夾帶在請求物件內
	// 的請求參數綁定在JavaBean內。
	// registerCustomEditor(): 可對JavaBean內之特定型態自定該型態的屬性編輯器(PropertyEditor)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	private byte[] fileToByteArray(String path) {
		byte[] result = null;
		try (InputStream is = context.getResourceAsStream(path);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public byte[] blobToByteArray(Blob blob) {
		byte[] result = null;
		try (InputStream is = blob.getBinaryStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

	
///////登入
	@GetMapping("/login")
	public String LoginContext(Model model, HttpServletRequest request) {
		System.out.println("======================================A");
		MemberBean member = new MemberBean();
//		model.getAttribute("memberBean");
		model.addAttribute("memberBeans", member);
		return "member/crm/login";
	}
	///////登入
	@PostMapping("/login")
	public String LoginContextCheck(@ModelAttribute("memberBeans") MemberBean member,Model model) {	
		MemberBean meb = memberService.login(member.getAccount(), member.getPswd());
	
		System.out.println("==================="+meb);
		
		//身分為管理員A
		if(meb != null){

			model.addAttribute("loginMember", meb);
			System.out.println("將會員" + meb.getName() + "加入session內"+meb.getUserType());
			System.out.println(meb.getUserType());
					
		}else {
			return "member/crm/login";
		}
		
		String type = meb.getUserType();
		
		if(type.equals("M")) {
			return "redirect:/";
		}else {
			return "member/adm/administrators";
		}
	}
	///登出
	@RequestMapping("/loginout")
	public String getLogOut(SessionStatus status) {
		System.out.println("執行session,setComplete();");
		System.out.println("以登出");
		status.setComplete();
		return "redirect:/";
	}
//	@ModelAttribute
//	public LoginMember getLoginMember(Model model){
//		
//		LoginMember loginMember = new LoginMember();
//		return loginMember;
//	}
//	@ModelAttribute
//	public LoginAdministrator getLoginAdministrator(Model model){
//		
//		LoginAdministrator loginAdministrator = new LoginAdministrator();
//		return loginAdministrator;
//	}

}