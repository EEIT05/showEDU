package showEDU.com.web.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;


import showEDU.com.web.forum.model.ArtTypeBean;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.BoardService;
import showEDU.com.web.forum.service.CommentService;
import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.member.service.MemberService;

@Controller
@SessionAttributes({"movieList","memberBean","articles"})
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	@Autowired
	BoardService boardService;
	@Autowired
	MemberService memberService;
	
	
	@SuppressWarnings({ "unchecked", "null" })
	@GetMapping("/article/{id}")  // 由討論版的頁面點擊任意版面,發送此版面ID的請求
	public String showBoardOfArticles(@PathVariable Integer id, Model model) {         //接收到一個自由參數由boardId傳入的值
		List<ArticleBean> articles = articleService.getArticlesByBoardId(id);          // 由此方法得到boardId相對應的文章內容
		Integer movieId = articleService.getMovieIdByBoardId(id);                      // 由此方法得到此電影版對應的MovieId
		Map<Integer, String> movieMap = (Map<Integer, String>)model.getAttribute("movieList");
		String movieName = movieMap.get(movieId);
		DiscussionBoardBean boardBean = boardService.getBoardBeanByBoardId(id);
		for (ArticleBean articleBean : articles) {
			articleBean.setRegisterTime(commentService.getMaxTimeRegisterByArtId(articleBean.getArtId()).toString().substring(0, 19));
		}
		for (ArticleBean articleBean : articles) {
			List<CommentBean> allCommentsByArtId = commentService.getAllCommentsByArtId(articleBean.getArtId());
			int count = 0;
			for (CommentBean commentBean : allCommentsByArtId) {
				count += commentService.getCommentSecBeansByCommentId(commentBean.getCommentId()).size();
			}
			articleBean.setReplyCount(allCommentsByArtId.size() + count);
		}
		ArticleBean articleBean = null;
		try {
			articleBean = articles.get(0);
		} catch (Exception e) {
			;
		}
		model.addAttribute("boardBean", boardBean); // 傳到前端做判斷
		model.addAttribute("articleBean", articleBean); // 傳到前端做判斷
		model.addAttribute("movieName", movieName);
		model.addAttribute("articles", articles);
		return "forum/article";
	}
	
	@GetMapping("/getPictureType/{typeId}")
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer typeId) {
		ArtTypeBean artTypeBean = articleService.getArtTypeByTypeId(typeId);
		return calculatePicture(artTypeBean);
	}
	 
	// 新增文章
	@GetMapping("/addArticle/{boardId}/{memberId}")
	public String addArticle(@PathVariable Integer boardId ,@PathVariable Integer memberId, Model model) {
		System.out.println("討論版Id=" + boardId);
		System.out.println("MemberId=" + memberId);
		ArticleBean articleBean = new ArticleBean();
		DiscussionBoardBean boardBean = boardService.getBoardBeanByBoardId(boardId);
		MemberBean memberBean = memberService.getMemberById(memberId);
		articleBean.setDiscussionBoardBean(boardBean);
		articleBean.setMemberBean(memberBean);
		model.addAttribute("articleBean", articleBean);
		return "forum/addArticle";
	}
	// 接收文章
	@PostMapping("/addArticle/{boardId}/{memberId}")
	public String processAddNewArticle(@PathVariable Integer boardId ,@PathVariable Integer memberId , 
			@ModelAttribute("articleBean") ArticleBean articleBean, BindingResult result , Model model) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		if (articleBean.getViewCount() == null) {
			articleBean.setViewCount("0");
		}
		MultipartFile image = articleBean.getImage();
		String originalFilename = image.getOriginalFilename();
		articleBean.setFileName(originalFilename);
	//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (image != null && !image.isEmpty()) {
			try {
				byte[] bytes = image.getBytes();
				Blob blob = new SerialBlob(bytes);
				articleBean.setArticleImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}
		articleService.addArticle(articleBean, boardId, memberId);
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		String rootDirectory = ctx.getRealPath("/");
		try {
			File imageFolder = new File(rootDirectory, "article/ArtImage");
			if (!imageFolder.exists()) {
				imageFolder.mkdirs();
			}
			File file = new File(imageFolder, articleBean.getArtId() + ext);
			image.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
		}
		model.addAttribute("memberBean");
		return "redirect:/article/" + boardId;
	
	}
	// 刪除文章
	@PostMapping("/deleteArticle")
	public void deleteArticle(@RequestParam Integer artId , HttpServletResponse response) {
		List<CommentBean> commentBeans = commentService.getAllCommentsByArtId(artId);
		for (CommentBean commentBean : commentBeans) {
			List<CommentSecBean> commentSecBeans = commentService.getCommentSecBeansByCommentId(commentBean.getCommentId());
			for (CommentSecBean commentSecBean : commentSecBeans) {
				commentService.deleteAllThumbsByCommentSecId(commentSecBean.getCommentSecId());
				commentService.deleteCommentSecBeanByCommentSecId(commentSecBean.getCommentSecId());
			}
			commentService.deleteAllThumbsByCommentId(commentBean.getCommentId());
			commentService.deleteCommentBeanByCommentId(commentBean.getCommentId());
		}
		articleService.deleteArticle(artId);
		System.out.println("刪除一篇文章");
		PrintWriter writer;
		try {
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String msg = "alert('删除成功!!!');history.go(-1)"; 
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ModelAttribute("artTypeMap")
	public Map<Integer, String> getArtType() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<ArtTypeBean> allArtTypeBean = articleService.getAllArtTypeBean();
		for (ArtTypeBean artTypeBean : allArtTypeBean) {
			map.put(artTypeBean.getTypeId(), artTypeBean.getType());
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------------重構---------------------------------------------
	
	private ResponseEntity<byte[]> calculatePicture(ArtTypeBean artTypeBean){
		String noImagePath = "\\resources\\images\\noImage.gif";
		ResponseEntity<byte[]> re = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			Blob blob = artTypeBean.getImage();
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
		String filename = artTypeBean.getFilename(); // 取得副檔名
		String mimeType = null;
		MediaType mediaType = null;
		if (filename != null) {
			mimeType = ctx.getMimeType(filename); // 由Servlet取得圖片的mimeType
		} else {
			mimeType = ctx.getMimeType(noImagePath);
		}
		mediaType = MediaType.valueOf(mimeType); // 將參數轉成本方法類別的物件 ex: Integer.valueOf('1');
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);       // setContentType 需要Set一個mediaType型別的物件
		headers.setCacheControl(CacheControl.noCache().getHeaderValue()); // 不要把圖片塞進Cache裡面(靜態圖片才需要放入快取區)
		re = new ResponseEntity<byte[]>(b2, headers, HttpStatus.OK);
		System.out.println(filename);
		return re;
	}
	
}
