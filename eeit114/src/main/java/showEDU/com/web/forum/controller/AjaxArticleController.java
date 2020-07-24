package showEDU.com.web.forum.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.ArticleBeanWithImageData;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.BoardService;
import showEDU.com.web.forum.service.CommentService;

@RestController
public class AjaxArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	@Autowired
	BoardService boardService;
	@GetMapping("/artType")
	public ResponseEntity<List<ArticleBeanWithImageData>> getArticleBeanByTypeId(
			@RequestParam (value="typeId", defaultValue = "0") Integer typeId) {
		List<ArticleBeanWithImageData> articleBeans = articleService.getArtBeansImageDataByTypeId(typeId); 
		ResponseEntity<List<ArticleBeanWithImageData>> re = new ResponseEntity<List<ArticleBeanWithImageData>>(articleBeans, HttpStatus.OK);
		setAbwidTimeAndReply(articleBeans);
		for (ArticleBeanWithImageData articleBeanWithImageData : articleBeans) {
			ArticleBean articleBean = articleBeanWithImageData.getArticleBean();
			setReplyCount(articleBean);
		}
		return re;
	}
	@GetMapping("/allArtType")
	public ResponseEntity<List<ArticleBean>> getAllArticleBean(){
		List<ArticleBean> allArticle = articleService.getAllArticle();
		ResponseEntity<List<ArticleBean>> re = new ResponseEntity<List<ArticleBean>>(allArticle, HttpStatus.OK);
		setAllArtTypeTimeAndReply(allArticle);
		for (ArticleBean articleBean : allArticle) {
			setReplyCount(articleBean);
		}
		return re;
	}
	
	// 欄位查詢
	@GetMapping("/selectChar")
	public ResponseEntity<List<ArticleBean>> getArticleByName(
			@RequestParam(value = "selectArticle")String name) {
		List<ArticleBean> articleBeans = articleService.getArticlesByName(name);
		System.out.println(name);
		ResponseEntity<List<ArticleBean>> re = new ResponseEntity<>(articleBeans, HttpStatus.OK);
		System.out.println(re.toString());
		setAllArtTypeTimeAndReply(articleBeans);
		for (ArticleBean articleBean : articleBeans) {
			setReplyCount(articleBean);
		}
		return re;
	}
	// 製作分頁
	@GetMapping("/pagingBoards")
	public ResponseEntity<Map<String, Object>> getBoardsByPageAjax(
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "totalPage", defaultValue = "0") Integer totalPage) {
		if (totalPage <= 0) {
			totalPage = boardService.getTotalPages();
		}
		Map<String, Object> map = new LinkedHashMap<>();
		List<DiscussionBoardBean> boardBeans = boardService.getPageBoards(pageNo);
		for (DiscussionBoardBean boardBean : boardBeans) {
			boardBean.setRegisterTime(commentService.getMaxTimeRegisterByBoardId(boardBean.getBoardId()).toString().substring(0, 19));
		}
		for (DiscussionBoardBean boardBean : boardBeans) {
			List<ArticleBean> list = articleService.getArticlesByBoardId(boardBean.getBoardId());
			if (list == null) {
				boardBean.setReplyCounts(0);
			} else {
				boardBean.setReplyCounts(list.size());
			}
		}
		map.put("boardBeans", boardBeans);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		ResponseEntity<Map<String, Object>> re = new ResponseEntity<>(map, HttpStatus.OK);
		return re;
	}
	
	
	
	
	
	
	
	
	public void setAllArtTypeTimeAndReply(List<ArticleBean> allArticle) {
		for (ArticleBean articleBean : allArticle) {
			articleBean.setRegisterTime(commentService.getMaxTimeRegisterByArtId(articleBean.getArtId()).toString().substring(0, 19));
		}
		for (ArticleBean articleBean : allArticle) {
			articleBean.setReplyCount(commentService.getAllCommentsByArtId(articleBean.getArtId()).size());
		}
	}
	
	public void setAbwidTimeAndReply(List<ArticleBeanWithImageData> articleBeans) {
		for (ArticleBeanWithImageData abwid : articleBeans) {
			abwid.getArticleBean().setRegisterTime(commentService.getMaxTimeRegisterByArtId(abwid.getArticleBean().getArtId()).toString().substring(0, 19));
		}
		for (ArticleBeanWithImageData abwid : articleBeans) {
			abwid.getArticleBean().setReplyCount(commentService.getAllCommentsByArtId(abwid.getArticleBean().getArtId()).size());
		}
	}
	
	public void setReplyCount(ArticleBean articleBean) {
			List<CommentBean> allCommentsByArtId = commentService.getAllCommentsByArtId(articleBean.getArtId());
			int count = 0;
			for (CommentBean commentBean : allCommentsByArtId) {
				count += commentService.getCommentSecBeansByCommentId(commentBean.getCommentId()).size();
			}
			articleBean.setReplyCount(allCommentsByArtId.size() + count);
	}
}
