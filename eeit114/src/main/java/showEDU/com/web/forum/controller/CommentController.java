package showEDU.com.web.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.CommentService;
import showEDU.com.web.member.model.MemberBean;

@Controller
@SessionAttributes({"memberBean"}) 
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;

	Long timeNow = System.currentTimeMillis(); // 為了製作現在操作的時間與留言時間的時間差
	Long callTime;
	Date date = new Date();
	
	

	@GetMapping("/comment/{artId}")
	public String getAllCommentByArtId(@PathVariable Integer artId, Model model) {
		List<CommentBean> commentBeans = commentService.getAllCommentsByArtId(artId);
		String registerTime = commentService.getMaxTimeRegisterByArtId(artId).toString().substring(0, 19); // 將文章的時間轉為字串傳回前端
		ArticleBean articleBean = articleService.getArticleByArtId(artId); // 得到此文章中資訊
		List<CommentSecBean> commentSecList = new ArrayList<>();
		for (CommentBean commentBean : commentBeans) {
			calculateTime(commentBean); // 將時間戳記轉為離現在多久以前
			// 設置第一層留言的按讚數
			for (ThumbsUpBean thumbsUpBean : commentService.getAllthumbsByCommentId(commentBean.getCommentId())) { 
				commentService.setThumbCountsByCommentId(thumbsUpBean, commentBean);
			}
			// 收集第二層留言形成一個屬性物件commentSecList
			for (CommentSecBean commentSecBean : commentService.getCommentSecBeansByCommentId(commentBean.getCommentId())) {
				calculateSecTime(commentSecBean); 
				commentSecList.add(commentSecBean);
				// 設置第二層留言的按讚數
				for (ThumbsUpBean thumbsUpBean : commentService.getAllthumbsByCommentSecId(commentSecBean.getCommentSecId())) {
					commentService.setThumbCountsByCommentSecId(thumbsUpBean, commentSecBean);
				}
				System.out.println("留言內容為:" + commentSecBean.getContent() + "時間為:" + commentSecBean.getTime());
			}
		}
		int commentBeanslength = commentBeans.size();
		model.addAttribute("commentBeanslength", commentBeanslength);
		model.addAttribute("commentSecList", commentSecList); // 一則留言裡的二層留言
		model.addAttribute("commentBeans", commentBeans);
		model.addAttribute("articleBean", articleBean);
		model.addAttribute("registerTime", registerTime);
		MemberBean mb = (MemberBean)model.getAttribute("LoginOK");
		model.addAttribute("LoginOK", mb);
		if (mb == null) {
			System.out.println("LoginOkSession值為會員: " + mb + "==============================================================");
		} else {
			System.out.println("目前登入會員為: " + mb.getName() + "==============================================================");
		}
		return "forum/comment";
	}
	
	
	
	
	// 取會員圖
	@GetMapping("/getPictureComment/{memberId}")
	public ResponseEntity<byte[]> getMemberPicture(HttpServletResponse resp, @PathVariable Integer memberId) {
		MemberBean memberBean = commentService.getMemberBeanByMemberId(memberId);
		return calculatePicture(memberBean);
	}
	
	// 取文章圖
	@GetMapping("/getPictureArticle/{artId}")
	public ResponseEntity<byte[]> getArtPicture(HttpServletResponse resp, @PathVariable Integer artId) {
		ArticleBean articleBean = articleService.getArticleByArtId(artId);
		return calculateArtPicture(articleBean);
	}
	
	
	
	
	private ResponseEntity<byte[]> calculatePicture(MemberBean memberBean) {
		String noImagePath = "\\resources\\images\\noImage.gif";
		ResponseEntity<byte[]> re = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			Blob blob = memberBean.getImage();
			if (blob != null) {
				is = blob.getBinaryStream();
			} else {
				;
			}
			if (is == null)
				is = ctx.getResourceAsStream(noImagePath);
			baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b1 = new byte[819200];
			while ((len = is.read(b1)) != -1) {
				baos.write(b1, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] b2 = baos.toByteArray();
		String filename = memberBean.getFileName(); // 取得副檔名
		String mimeType = null;
		MediaType mediaType = null;
		if (filename != null) {
			mimeType = ctx.getMimeType(filename); // 由Servlet取得圖片的mimeType
		} else {
			mimeType = ctx.getMimeType(noImagePath);
		}
		mediaType = MediaType.valueOf(mimeType); // 將參數轉成本方法類別的物件 ex: Integer.valueOf('1');
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType); // setContentType 需要Set一個mediaType型別的物件
		headers.setCacheControl(CacheControl.noCache().getHeaderValue()); // 不要把圖片塞進Cache裡面(靜態圖片才需要放入快取區)
		re = new ResponseEntity<byte[]>(b2, headers, HttpStatus.OK);
		return re;
	}
	// 取得文章圖片
	private ResponseEntity<byte[]> calculateArtPicture(ArticleBean articleBean) {
		String noImagePath = "\\resources\\images\\noImage.gif";
		ResponseEntity<byte[]> re = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			Blob blob = articleBean.getArticleImage();
			if (blob != null) {
				is = blob.getBinaryStream();
			} else {
				;
			}
			if (is == null)
				is = ctx.getResourceAsStream(noImagePath);
			baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b1 = new byte[819200];
			while ((len = is.read(b1)) != -1) {
				baos.write(b1, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] b2 = baos.toByteArray();
		String filename = articleBean.getFileName(); // 取得副檔名
		String mimeType = null;
		MediaType mediaType = null;
		if (filename != null) {
			mimeType = ctx.getMimeType(filename); // 由Servlet取得圖片的mimeType
		} else {
			mimeType = ctx.getMimeType(noImagePath);
		}
		mediaType = MediaType.valueOf(mimeType); // 將參數轉成本方法類別的物件 ex: Integer.valueOf('1');
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType); // setContentType 需要Set一個mediaType型別的物件
		headers.setCacheControl(CacheControl.noCache().getHeaderValue()); // 不要把圖片塞進Cache裡面(靜態圖片才需要放入快取區)
		re = new ResponseEntity<byte[]>(b2, headers, HttpStatus.OK);
		return re;
	}
	
	
	// 設置留言時間
	public void calculateTime(CommentBean commentBean) {
		Timestamp time = commentService.getRegisterTimeByCommentId(commentBean.getCommentId());
		date = time;
		callTime = timeNow - (date.getTime());
		if (callTime / 1000 > 14515200) {
			commentBean.setTime("6個月前");
		} else if (callTime / 1000 > 7257600) {
			commentBean.setTime("3個月前");
		} else if (callTime / 1000 > 4838400) {
			commentBean.setTime("2個月前");
		} else if (callTime / 1000 > 2419200) {
			commentBean.setTime("1個月前");
		} else if (callTime / 1000 > 1209600) {
			commentBean.setTime("2個禮拜前");
		} else if (callTime / 1000 > 604800) {
			commentBean.setTime("1個禮拜前");
		} else if (callTime / 1000 > 518400) {
			commentBean.setTime("6天前");
		} else if (callTime / 1000 > 432000) {
			commentBean.setTime("5天前");
		} else if (callTime / 1000 > 345600) {
			commentBean.setTime("4天前");
		} else if (callTime / 1000 > 259200) {
			commentBean.setTime("3天前");
		} else if (callTime / 1000 > 172800) {
			commentBean.setTime("2天前");
		} else if (callTime / 1000 > 86400) {
			commentBean.setTime("1天前");
		} else if (callTime / 1000 > 82800) {
			commentBean.setTime("23小時前");
		} else if (callTime / 1000 > 79200) {
			commentBean.setTime("22小時前");
		} else if (callTime / 1000 > 75600) {
			commentBean.setTime("21小時前");
		} else if (callTime / 1000 > 72000) {
			commentBean.setTime("20小時前");
		} else if (callTime / 1000 > 68400) {
			commentBean.setTime("19小時前");
		} else if (callTime / 1000 > 64800) {
			commentBean.setTime("18小時前");
		} else if (callTime / 1000 > 61200) {
			commentBean.setTime("17小時前");
		} else if (callTime / 1000 > 57600) {
			commentBean.setTime("16小時前");
		} else if (callTime / 1000 > 54000) {
			commentBean.setTime("15小時前");
		} else if (callTime / 1000 > 50400) {
			commentBean.setTime("14小時前");
		} else if (callTime / 1000 > 46800) {
			commentBean.setTime("13小時前");
		} else if (callTime / 1000 > 43200) {
			commentBean.setTime("12小時前");
		} else if (callTime / 1000 > 39600) {
			commentBean.setTime("11小時前");
		} else if (callTime / 1000 > 36000) {
			commentBean.setTime("10小時前");
		} else if (callTime / 1000 > 32400) {
			commentBean.setTime("9小時前");
		} else if (callTime / 1000 > 28800) {
			commentBean.setTime("8小時前");
		} else if (callTime / 1000 > 25200) {
			commentBean.setTime("7小時前");
		} else if (callTime / 1000 > 21600) {
			commentBean.setTime("6小時前");
		} else if (callTime / 1000 > 18000) {
			commentBean.setTime("5小時前");
		} else if (callTime / 1000 > 14400) {
			commentBean.setTime("4小時前");
		} else if (callTime / 1000 > 10800) {
			commentBean.setTime("3小時前");
		} else if (callTime / 1000 > 7200) {
			commentBean.setTime("2小時前");
		} else if (callTime / 1000 > 3600) {
			commentBean.setTime("1小時前");
		} else if (callTime / 1000 > 3540) {
			commentBean.setTime("59分前");
		} else if (callTime / 1000 > 3480) {
			commentBean.setTime("58分前");
		} else if (callTime / 1000 > 3420) {
			commentBean.setTime("57分前");
		} else if (callTime / 1000 > 3360) {
			commentBean.setTime("56分前");
		} else if (callTime / 1000 > 3300) {
			commentBean.setTime("55分前");
		} else if (callTime / 1000 > 3240) {
			commentBean.setTime("54分前");
		} else if (callTime / 1000 > 3180) {
			commentBean.setTime("53分前");
		} else if (callTime / 1000 > 3120) {
			commentBean.setTime("52分前");
		} else if (callTime / 1000 > 3060) {
			commentBean.setTime("51分前");
		} else if (callTime / 1000 > 3000) {
			commentBean.setTime("50分前");
		} else if (callTime / 1000 > 2940) {
			commentBean.setTime("49分前");
		} else if (callTime / 1000 > 2880) {
			commentBean.setTime("48分前");
		} else if (callTime / 1000 > 2820) {
			commentBean.setTime("47分前");
		} else if (callTime / 1000 > 2760) {
			commentBean.setTime("46分前");
		} else if (callTime / 1000 > 2700) {
			commentBean.setTime("45分前");
		} else if (callTime / 1000 > 2640) {
			commentBean.setTime("44分前");
		} else if (callTime / 1000 > 2580) {
			commentBean.setTime("43分前");
		} else if (callTime / 1000 > 2520) {
			commentBean.setTime("42分前");
		} else if (callTime / 1000 > 2460) {
			commentBean.setTime("41分前");
		} else if (callTime / 1000 > 2400) {
			commentBean.setTime("40分前");
		} else if (callTime / 1000 > 2340) {
			commentBean.setTime("39分前");
		} else if (callTime / 1000 > 2280) {
			commentBean.setTime("38分前");
		} else if (callTime / 1000 > 2220) {
			commentBean.setTime("37分前");
		} else if (callTime / 1000 > 2160) {
			commentBean.setTime("36分前");
		} else if (callTime / 1000 > 2100) {
			commentBean.setTime("35分前");
		} else if (callTime / 1000 > 2040) {
			commentBean.setTime("34分前");
		} else if (callTime / 1000 > 1980) {
			commentBean.setTime("33分前");
		} else if (callTime / 1000 > 1920) {
			commentBean.setTime("32分前");
		} else if (callTime / 1000 > 1860) {
			commentBean.setTime("31分前");
		} else if (callTime / 1000 > 1800) {
			commentBean.setTime("30分前");
		} else if (callTime / 1000 > 1740) {
			commentBean.setTime("29分前");
		} else if (callTime / 1000 > 1680) {
			commentBean.setTime("28分前");
		} else if (callTime / 1000 > 1620) {
			commentBean.setTime("27分前");
		} else if (callTime / 1000 > 1560) {
			commentBean.setTime("26分前");
		} else if (callTime / 1000 > 1500) {
			commentBean.setTime("25分前");
		} else if (callTime / 1000 > 1440) {
			commentBean.setTime("24分前");
		} else if (callTime / 1000 > 1380) {
			commentBean.setTime("23分前");
		} else if (callTime / 1000 > 1320) {
			commentBean.setTime("22分前");
		} else if (callTime / 1000 > 1260) {
			commentBean.setTime("21分前");
		} else if (callTime / 1000 > 1200) {
			commentBean.setTime("20分前");
		} else if (callTime / 1000 > 1140) {
			commentBean.setTime("19分前");
		} else if (callTime / 1000 > 1080) {
			commentBean.setTime("18分前");
		} else if (callTime / 1000 > 1020) {
			commentBean.setTime("17分前");
		} else if (callTime / 1000 > 960) {
			commentBean.setTime("16分前");
		} else if (callTime / 1000 > 900) {
			commentBean.setTime("15分前");
		} else if (callTime / 1000 > 840) {
			commentBean.setTime("14分前");
		} else if (callTime / 1000 > 780) {
			commentBean.setTime("13分前");
		} else if (callTime / 1000 > 720) {
			commentBean.setTime("12分前");
		} else if (callTime / 1000 > 660) {
			commentBean.setTime("11分前");
		} else if (callTime / 1000 > 600) {
			commentBean.setTime("10分前");
		} else if (callTime / 1000 > 540) {
			commentBean.setTime("9分前");
		} else if (callTime / 1000 > 480) {
			commentBean.setTime("8分前");
		} else if (callTime / 1000 > 420) {
			commentBean.setTime("7分前");
		} else if (callTime / 1000 > 360) {
			commentBean.setTime("6分前");
		} else if (callTime / 1000 > 300) {
			commentBean.setTime("5分前");
		} else if (callTime / 1000 > 240) {
			commentBean.setTime("4分前");
		} else if (callTime / 1000 > 180) {
			commentBean.setTime("3分前");
		} else if (callTime / 1000 > 120) {
			commentBean.setTime("2分前");
		} else if (callTime / 1000 > 60) {
			commentBean.setTime("1分前");
		}  else {
			commentBean.setTime("剛剛");
		}
	}
	
	// 設置第二層留言時間
	public  void calculateSecTime(CommentSecBean commentSecBean) {
		Timestamp time = commentService.getRegisterTimeByCommentSecId(commentSecBean.getCommentSecId());
		date = time;
		callTime = timeNow - (date.getTime());
		if (callTime / 1000 > 14515200) {
			commentSecBean.setTime("6個月前");
		} else if (callTime / 1000 > 7257600) {
			commentSecBean.setTime("3個月前");
		} else if (callTime / 1000 > 4838400) {
			commentSecBean.setTime("2個月前");
		} else if (callTime / 1000 > 2419200) {
			commentSecBean.setTime("1個月前");
		} else if (callTime / 1000 > 1209600) {
			commentSecBean.setTime("2個禮拜前");
		} else if (callTime / 1000 > 604800) {
			commentSecBean.setTime("1個禮拜前");
		} else if (callTime / 1000 > 518400) {
			commentSecBean.setTime("6天前");
		} else if (callTime / 1000 > 432000) {
			commentSecBean.setTime("5天前");
		} else if (callTime / 1000 > 345600) {
			commentSecBean.setTime("4天前");
		} else if (callTime / 1000 > 259200) {
			commentSecBean.setTime("3天前");
		} else if (callTime / 1000 > 172800) {
			commentSecBean.setTime("2天前");
		} else if (callTime / 1000 > 86400) {
			commentSecBean.setTime("1天前");
		} else if (callTime / 1000 > 82800) {
			commentSecBean.setTime("23小時前");
		} else if (callTime / 1000 > 79200) {
			commentSecBean.setTime("22小時前");
		} else if (callTime / 1000 > 75600) {
			commentSecBean.setTime("21小時前");
		} else if (callTime / 1000 > 72000) {
			commentSecBean.setTime("20小時前");
		} else if (callTime / 1000 > 68400) {
			commentSecBean.setTime("19小時前");
		} else if (callTime / 1000 > 64800) {
			commentSecBean.setTime("18小時前");
		} else if (callTime / 1000 > 61200) {
			commentSecBean.setTime("17小時前");
		} else if (callTime / 1000 > 57600) {
			commentSecBean.setTime("16小時前");
		} else if (callTime / 1000 > 54000) {
			commentSecBean.setTime("15小時前");
		} else if (callTime / 1000 > 50400) {
			commentSecBean.setTime("14小時前");
		} else if (callTime / 1000 > 46800) {
			commentSecBean.setTime("13小時前");
		} else if (callTime / 1000 > 43200) {
			commentSecBean.setTime("12小時前");
		} else if (callTime / 1000 > 39600) {
			commentSecBean.setTime("11小時前");
		} else if (callTime / 1000 > 36000) {
			commentSecBean.setTime("10小時前");
		} else if (callTime / 1000 > 32400) {
			commentSecBean.setTime("9小時前");
		} else if (callTime / 1000 > 28800) {
			commentSecBean.setTime("8小時前");
		} else if (callTime / 1000 > 25200) {
			commentSecBean.setTime("7小時前");
		} else if (callTime / 1000 > 21600) {
			commentSecBean.setTime("6小時前");
		} else if (callTime / 1000 > 18000) {
			commentSecBean.setTime("5小時前");
		} else if (callTime / 1000 > 14400) {
			commentSecBean.setTime("4小時前");
		} else if (callTime / 1000 > 10800) {
			commentSecBean.setTime("3小時前");
		} else if (callTime / 1000 > 7200) {
			commentSecBean.setTime("2小時前");
		} else if (callTime / 1000 > 3600) {
			commentSecBean.setTime("1小時前");
		} else if (callTime / 1000 > 3540) {
			commentSecBean.setTime("59分前");
		} else if (callTime / 1000 > 3480) {
			commentSecBean.setTime("58分前");
		} else if (callTime / 1000 > 3420) {
			commentSecBean.setTime("57分前");
		} else if (callTime / 1000 > 3360) {
			commentSecBean.setTime("56分前");
		} else if (callTime / 1000 > 3300) {
			commentSecBean.setTime("55分前");
		} else if (callTime / 1000 > 3240) {
			commentSecBean.setTime("54分前");
		} else if (callTime / 1000 > 3180) {
			commentSecBean.setTime("53分前");
		} else if (callTime / 1000 > 3120) {
			commentSecBean.setTime("52分前");
		} else if (callTime / 1000 > 3060) {
			commentSecBean.setTime("51分前");
		} else if (callTime / 1000 > 3000) {
			commentSecBean.setTime("50分前");
		} else if (callTime / 1000 > 2940) {
			commentSecBean.setTime("49分前");
		} else if (callTime / 1000 > 2880) {
			commentSecBean.setTime("48分前");
		} else if (callTime / 1000 > 2820) {
			commentSecBean.setTime("47分前");
		} else if (callTime / 1000 > 2760) {
			commentSecBean.setTime("46分前");
		} else if (callTime / 1000 > 2700) {
			commentSecBean.setTime("45分前");
		} else if (callTime / 1000 > 2640) {
			commentSecBean.setTime("44分前");
		} else if (callTime / 1000 > 2580) {
			commentSecBean.setTime("43分前");
		} else if (callTime / 1000 > 2520) {
			commentSecBean.setTime("42分前");
		} else if (callTime / 1000 > 2460) {
			commentSecBean.setTime("41分前");
		} else if (callTime / 1000 > 2400) {
			commentSecBean.setTime("40分前");
		} else if (callTime / 1000 > 2340) {
			commentSecBean.setTime("39分前");
		} else if (callTime / 1000 > 2280) {
			commentSecBean.setTime("38分前");
		} else if (callTime / 1000 > 2220) {
			commentSecBean.setTime("37分前");
		} else if (callTime / 1000 > 2160) {
			commentSecBean.setTime("36分前");
		} else if (callTime / 1000 > 2100) {
			commentSecBean.setTime("35分前");
		} else if (callTime / 1000 > 2040) {
			commentSecBean.setTime("34分前");
		} else if (callTime / 1000 > 1980) {
			commentSecBean.setTime("33分前");
		} else if (callTime / 1000 > 1920) {
			commentSecBean.setTime("32分前");
		} else if (callTime / 1000 > 1860) {
			commentSecBean.setTime("31分前");
		} else if (callTime / 1000 > 1800) {
			commentSecBean.setTime("30分前");
		} else if (callTime / 1000 > 1740) {
			commentSecBean.setTime("29分前");
		} else if (callTime / 1000 > 1680) {
			commentSecBean.setTime("28分前");
		} else if (callTime / 1000 > 1620) {
			commentSecBean.setTime("27分前");
		} else if (callTime / 1000 > 1560) {
			commentSecBean.setTime("26分前");
		} else if (callTime / 1000 > 1500) {
			commentSecBean.setTime("25分前");
		} else if (callTime / 1000 > 1440) {
			commentSecBean.setTime("24分前");
		} else if (callTime / 1000 > 1380) {
			commentSecBean.setTime("23分前");
		} else if (callTime / 1000 > 1320) {
			commentSecBean.setTime("22分前");
		} else if (callTime / 1000 > 1260) {
			commentSecBean.setTime("21分前");
		} else if (callTime / 1000 > 1200) {
			commentSecBean.setTime("20分前");
		} else if (callTime / 1000 > 1140) {
			commentSecBean.setTime("19分前");
		} else if (callTime / 1000 > 1080) {
			commentSecBean.setTime("18分前");
		} else if (callTime / 1000 > 1020) {
			commentSecBean.setTime("17分前");
		} else if (callTime / 1000 > 960) {
			commentSecBean.setTime("16分前");
		} else if (callTime / 1000 > 900) {
			commentSecBean.setTime("15分前");
		} else if (callTime / 1000 > 840) {
			commentSecBean.setTime("14分前");
		} else if (callTime / 1000 > 780) {
			commentSecBean.setTime("13分前");
		} else if (callTime / 1000 > 720) {
			commentSecBean.setTime("12分前");
		} else if (callTime / 1000 > 660) {
			commentSecBean.setTime("11分前");
		} else if (callTime / 1000 > 600) {
			commentSecBean.setTime("10分前");
		} else if (callTime / 1000 > 540) {
			commentSecBean.setTime("9分前");
		} else if (callTime / 1000 > 480) {
			commentSecBean.setTime("8分前");
		} else if (callTime / 1000 > 420) {
			commentSecBean.setTime("7分前");
		} else if (callTime / 1000 > 360) {
			commentSecBean.setTime("6分前");
		} else if (callTime / 1000 > 300) {
			commentSecBean.setTime("5分前");
		} else if (callTime / 1000 > 240) {
			commentSecBean.setTime("4分前");
		} else if (callTime / 1000 > 180) {
			commentSecBean.setTime("3分前");
		} else if (callTime / 1000 > 120) {
			commentSecBean.setTime("2分前");
		} else if (callTime / 1000 > 60) {
			commentSecBean.setTime("1分前");
		}  else {
			commentSecBean.setTime("剛剛");
		}
	}


}
