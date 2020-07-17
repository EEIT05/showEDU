package showEDU.com.web.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

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

import showEDU.com.web.forum.model.ArtTypeBean;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.CommentService;

@Controller
@SessionAttributes({"movieList","loginMember"})
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/article/{id}")  // 由討論版的頁面點擊任意版面,發送此版面ID的請求
	public String showBoardOfArticles(@PathVariable Integer id, Model model) {         //接收到一個自由參數由boardId傳入的值
		List<ArticleBean> articles = articleService.getArticlesByBoardId(id);          // 由此方法得到boardId相對應的文章內容
		Integer movieId = articleService.getMovieIdByBoardId(id);                      // 由此方法得到此電影版對應的MovieId
		Map<Integer, String> movieMap = (Map<Integer, String>)model.getAttribute("movieList");
		String movieName = movieMap.get(movieId);
		for (ArticleBean articleBean : articles) {
			articleBean.setRegisterTime(commentService.getMaxTimeRegisterByArtId(articleBean.getArtId()).toString().substring(0, 19));
		}
		for (ArticleBean articleBean : articles) {
			articleBean.setReplyCount(commentService.getAllCommentsByArtId(articleBean.getArtId()).size());
		}
		ArticleBean articleBean = articles.get(0);
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
