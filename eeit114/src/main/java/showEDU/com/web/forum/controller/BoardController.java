package showEDU.com.web.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.BoardService;
import showEDU.com.web.forum.service.CommentService;

@SessionAttributes({"movieList","memberBean"})
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	

	@GetMapping("/boards")
	public String getAllBoardMovie(Model model, HttpServletRequest req) {
		List<DiscussionBoardBean> boards = boardService.getAllBoards();
		List<DiscussionBoardBean> sortedBoards = boardService.getSortedBoards();
		putInfoIntoDiscussionBoard(boards, sortedBoards); // 將日期、文章數的資料放入board的暫存欄位
		model.addAttribute("sortedBoards", sortedBoards); 
		model.addAttribute("boards", boards);
		return "forum/boards";
	}

	// 回傳一個movieList(含有movieName的值)的Session物件到前端,讓ArticleController拿的到有關此電影的相關資訊
	@ModelAttribute("movieList")
	public Map<Integer, String> getMovieList() {
		Map<Integer, String> movieMap = new HashMap<>();
		List<ForumMovieBean> list = boardService.getMovieBeanList();
		for (ForumMovieBean mb : list) {
			// value值含有movieName
			movieMap.put(mb.getMovieId(), mb.getName());
		}
		return movieMap;
	}



	@GetMapping("/getPicture/{movieId}")
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer movieId) {
		ForumMovieBean moviebean = boardService.getMovieBeanByFKMovieId(movieId);
		return calculatePicture(moviebean);
	}
	
	@GetMapping("/backstage")
	public String backstage(Model model) {
		List<DiscussionBoardBean> allBoards = boardService.getAllBoards();
		List<ArticleBean> allArticles = articleService.getAllArticle();
		model.addAttribute("allBoards", allBoards);
		model.addAttribute("allArticles", allArticles);
		return "forum/editorForum";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ---------------------------------------重構-------------------------------------------------

	private ResponseEntity<byte[]> calculatePicture(ForumMovieBean moviebean) {
		String noImagePath = "\\resources\\images\\noImage.gif";
		ResponseEntity<byte[]> re = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			Blob blob = moviebean.getImage();
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

		String filename = moviebean.getFilename(); // 取得副檔名
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
	
	private void putInfoIntoDiscussionBoard(List<DiscussionBoardBean> boards, List<DiscussionBoardBean> sortedBoards) {
		for (DiscussionBoardBean boardBean : boards) {
			boardBean.setRegisterTime(commentService.getMaxTimeRegisterByBoardId(boardBean.getBoardId()).toString().substring(0, 19));
		}
		for (DiscussionBoardBean boardBean : sortedBoards) {
			boardBean.setRegisterTime(commentService.getMaxTimeRegisterByBoardId(boardBean.getBoardId()).toString().substring(0, 19));
		}
		for (DiscussionBoardBean boardBean : boards) {
			List<ArticleBean> list = articleService.getArticlesByBoardId(boardBean.getBoardId());
			if (list == null) {
				boardBean.setReplyCounts(0);
			} else {
				boardBean.setReplyCounts(list.size());
			}
		}
		for (DiscussionBoardBean boardBean : sortedBoards) {
			List<ArticleBean> list = articleService.getArticlesByBoardId(boardBean.getBoardId());
			if (list == null) {
				boardBean.setReplyCounts(0);
			} else {
				boardBean.setReplyCounts(list.size());
			}
		}
	}

}
