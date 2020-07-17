package showEDU.com.web.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.BoardService;
import showEDU.com.web.forum.service.CommentService;

@Controller
public class BoardControllerCRUD {
	@Autowired
	BoardService boardService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	
	// 新增討論版
		@GetMapping("/boards/add")
		public String getAddNewBoardForm(Model model) {
			DiscussionBoardBean boardBean = new DiscussionBoardBean();
			model.addAttribute("boardBean", boardBean);
			return "addBoard";
		}

		@PostMapping("/boards/add")
		public String processAddNewBoardForm(@ModelAttribute("boardBean") DiscussionBoardBean db, BindingResult result) {
			boardService.setviewCount(db, result); // 設置viewCount並檢驗Post欄位
			boardService.addBoard(db);
			return "redirect:/boards";
		}
		
		// 企業邏輯:
		// 回傳一個MovieMap(Key:movieId, value:movieName)
		// 此MovieMap不包括討論區已存在的電影版
		// 功能:不會在討論版新增重複的電影
		@ModelAttribute("movieNameList")
		public Map<Integer, String> getmovieBeanList() {
			Map<Integer, String> movieMap = new HashMap<>();
			List<ForumMovieBean> movieBeanList = boardService.getMovieBeanList(); // 得到所有的MovieBean
			List<Integer> movieIdsByBoardBean = boardService.getMovieIdsByBoardBean(); // 得到討論區所有的MovieId
			return boardService.getMovieMap(movieMap, movieBeanList, movieIdsByBoardBean);// 如果有相同的movieId,將此movieBean從全部的movieBean中去除
		}
		
		@DeleteMapping("/boards/delete{boardId}")
		public String deleteBoard(@PathVariable("boardId") Integer boardId) {
			boardService.deleteBoard(boardId);
			return "redirect:/boards";
		}
}
