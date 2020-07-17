package showEDU.com.web.member.controller;


import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import showEDU.com.web.member.model.AdministratorBean;
import showEDU.com.web.member.model.LoginAdministrator;
import showEDU.com.web.member.service.AdministratorService;
import showEDU.com.web.member.validators.AdministratorValidator;

@Controller

@SessionAttributes("loginAdministrator")
public class AdministratorController {

	String noImage = "/images/NoImage.png";
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	ServletContext context;
	
	// 顯示所有管理員資料
		@GetMapping("/administrators")
		public String list(Model model) {
			model.addAttribute("administrators", administratorService.getAlladms());
			return "member/adm/administrators";
		}
	
		// 本方法於新增時，送出空白的表單讓使用者輸入資料
		@GetMapping(value = "member/adm/ad")
		public String showEmptyForm(Model model) {
			AdministratorBean administrator = new AdministratorBean();
			
//	      下列敘述簡化測試時的資料輸入		
			administrator.setAdaccount("aaa@gmail.com");
			administrator.setAdname("管理員");
			administrator.setAdpswd("111111");
			model.addAttribute("administrator", administrator);
			
			return "member/adm/insertAdministrator";
		}
	
		// 當使用者填妥資料按下Submit按鈕後，本方法接收將瀏覽器送來的會員資料，新進行資料的檢查，
		// 若資料有誤，轉向寫入錯誤訊息的網頁，若資料無誤，則呼叫Service元件寫入資料庫
		@PostMapping(value = "member/adm/ad")
		// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
		public String add(
				@ModelAttribute("administrator") /* @Valid */ AdministratorBean administrator, 
				BindingResult result, Model model,
				HttpServletRequest request) {
			AdministratorValidator validator = new AdministratorValidator();
			// 呼叫Validate進行資料檢查
			validator.validate(administrator, result);
			if (result.hasErrors()) {
//	          下列敘述可以理解Spring MVC如何處理錯誤			
//				List<ObjectError> list = result.getAllErrors();
//				for (ObjectError error : list) {
//					System.out.println("有錯誤：" + error);
//				}
				return "member/adm/insertAdministrator";
			}
//			MultipartFile picture = member.getMemImage();
//			String originalFilename = picture.getOriginalFilename();
//			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
//				member.setFileName(originalFilename);
//			}
//			// 建立Blob物件，交由 Hibernate 寫入資料庫
//			if (picture != null && !picture.isEmpty()) {
//				try {
//					byte[] b = picture.getBytes();
//					Blob blob = new SerialBlob(b);
//					member.setImage(blob);
//				} catch (Exception e) {
//					e.printStackTrace();
//					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//				}
//			}
	//時間戳記
			Timestamp registerTime = new Timestamp(System.currentTimeMillis());
			administrator.setRegisterTime(registerTime);
			
			administrator.setUserType("A");
//			
			try {
				administratorService.save(administrator);
			} catch (org.hibernate.exception.ConstraintViolationException e) {
				result.rejectValue("adaccount", "", "帳號已存在，請重新輸入");
				return "adm/insertAdministrator";
			} catch (Exception ex) {
				System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
				result.rejectValue("adaccount", "", "請通知系統人員...");
				return "adm/insertAdministrator";
			}
			
			return "redirect:/administrators";
		}
		
		// 當使用者需要修改時，本方法送回含有會員資料的表單，讓使用者進行修改
		// 由這個方法送回修改記錄的表單...
		@GetMapping(value = "member/adm/ad/{admId}")
		public String showDataForm(@PathVariable("admId") Integer admId, Model model) {
			AdministratorBean administrator = administratorService.getAdmById(admId);
			model.addAttribute(administrator);
			return "member/adm/updateAdministrator";
		}
		
		
	
		
		// 當將瀏覽器送來修改過的會員資料時，由本方法負責檢核，若無誤則寫入資料庫
		@PostMapping("member/adm/ad/{admId}")
		// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
		// 
		public String modify(
				@ModelAttribute("administrator") AdministratorBean administrator, 
				BindingResult result, 
				Model model,
				@PathVariable Integer admId, 
				HttpServletRequest request) {
			AdministratorValidator validator = new AdministratorValidator();
			validator.validate(administrator, result);
			if (result.hasErrors()) {
				System.out.println("result hasErrors(), administrator=" + administrator);
				List<ObjectError> list = result.getAllErrors();
				for (ObjectError error : list) {
					System.out.println("有錯誤：" + error);
				}
				return "redirect:/member/adm/insertAdministrator";
			}

//			MultipartFile picture = administrator.getMemImage();
//
//			if (picture.getSize() == 0) {
//				// 表示使用者並未挑選圖片
////				Member original = memberService.get(id);
////				member.setImage(original.getImage());
//			} else {
//				String originalFilename = picture.getOriginalFilename();
//				if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
//					member.setFileName(originalFilename);
			//	}

//				// 建立Blob物件
//				if (picture != null && !picture.isEmpty()) {
//					try {
//						byte[] b = picture.getBytes();
//						Blob blob = new SerialBlob(b);
//						member.setImage(blob);
//					} catch (Exception e) {
//						e.printStackTrace();
//						throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//					}
//				}
//			}
			administratorService.updateAdm(administrator);
			return "redirect:/administrators";
		}

			     
		// 刪除一筆紀錄
		// 由這個方法刪除記錄...
		@DeleteMapping("member/adm/ad/{admId}")
		public String delete(@PathVariable("admId") Integer admId) {
			administratorService.deleteAdm(admId);
			return "redirect:/administrators";
		}

//		@GetMapping("/picture/{memberId}")
//		public ResponseEntity<byte[]> getPicture(@PathVariable Integer memberId) {
//			byte[] body = null;
//			ResponseEntity<byte[]> re = null;
//			MediaType mediaType = null;
//			HttpHeaders headers = new HttpHeaders();
//			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//
//			MemberBean member = memberService.getMemberById(memberId);
//			if (member == null) {
//				return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
//			}
//			String filename = member.getFileName();
//			if (filename != null) {
//				if (filename.toLowerCase().endsWith("jfif")) {
//					mediaType = MediaType.valueOf(context.getMimeType("dummy.jpeg"));
//				} else {
//					mediaType = MediaType.valueOf(context.getMimeType(filename));
//					headers.setContentType(mediaType);
//				}
//			}
//			Blob blob = member.getImage();
//			if (blob != null) {
//				body = blobToByteArray(blob);
//			} else {
//				String path = null;
//
//				body = fileToByteArray(path);
//			}
//			re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
//
//			return re;
//		}


		@ModelAttribute
		public void getadministrator(@PathVariable(value="admId", required = false ) Integer admId, Model model) {
			System.out.println("@ModelAttribute.getadministrator()...");
			if (admId != null) {
				AdministratorBean administrator= administratorService.getAdmById(admId);
				model.addAttribute("administrator", administrator);
			} else {
				AdministratorBean administrator = new AdministratorBean();
				administrator.setLogin("false");
				model.addAttribute("administrator", administrator);
			}
			
		}
		

//		// 本方法可對WebDataBinder進行組態設定。除了表單資料外，絕大多數可以傳入控制器方法的
//		// 參數都可以傳入以@InitBinder修飾的方法。本方法最常使用的參數為WebDataBinder。
//		//
//		// org.springframework.web.bind.WebDataBinder
//		// 為 org.springframework.validation.DataBinder 的子類別，它將夾帶在請求物件內
//		// 的請求參數綁定在JavaBean內。
//		// registerCustomEditor(): 可對JavaBean內之特定型態自定該型態的屬性編輯器(PropertyEditor)
//		@InitBinder
//		public void initBinder(WebDataBinder binder) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			sdf.setLenient(false);
//			binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//		}
//
//		private byte[] fileToByteArray(String path) {
//			byte[] result = null;
//			try (InputStream is = context.getResourceAsStream(path);
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
//				byte[] b = new byte[819200];
//				int len = 0;
//				while ((len = is.read(b)) != -1) {
//					baos.write(b, 0, len);
//				}
//				result = baos.toByteArray();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return result;
//		}
//
//		public byte[] blobToByteArray(Blob blob) {
//			byte[] result = null;
//			try (InputStream is = blob.getBinaryStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
//				byte[] b = new byte[819200];
//				int len = 0;
//				while ((len = is.read(b)) != -1) {
//					baos.write(b, 0, len);
//				}
//				result = baos.toByteArray();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return result;
//		}
	///////登入
		@GetMapping("/login")
		public String LoginContext(Model model, HttpServletRequest request) {
			System.out.println("======================================A");
			AdministratorBean administrator = new AdministratorBean();
//			model.getAttribute("memberBean");
			model.addAttribute("administratorBeans", administrator);
			return "adm/login";
		}
		///////登入
		@PostMapping("/login")
		public String LoginContextCheck(@ModelAttribute("administratorBeans") AdministratorBean administrator,Model model) {	
			AdministratorBean mmm = administratorService.login(administrator.getAdaccount(), administrator.getAdpswd());
			System.out.println("==================="+mmm);
			if (mmm == null) {
				
				return "adm/login";
			}
			else {
				LoginAdministrator loginAdministrator =(LoginAdministrator)model.getAttribute("loginAdministrator");
				loginAdministrator.setAdministratorBean(mmm);
//				mmm = (MemberBean)model.getAttribute("memberBean");
//				System.out.println(mmm.getMemberId());
				return "redirect:/";
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
		@ModelAttribute
		public LoginAdministrator getLoginAdministrator(Model model){
			
			LoginAdministrator loginAdministrator = new LoginAdministrator();
			return loginAdministrator;
		}
	

	}
