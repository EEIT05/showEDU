package showEDU.com.web.forum.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.CommentService;

@RestController
public class AjaxBackStageController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	
	@GetMapping("/getArticles")
	public ResponseEntity<List<ArticleBean>> getArticlesByBoardId(
			@RequestParam (value = "boardId", defaultValue = "0") Integer boardId) {
		List<ArticleBean> articleBeans = articleService.getArticlesByBoardId(boardId);
		ResponseEntity<List<ArticleBean>> re = new ResponseEntity<>(articleBeans, HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getComments")
	public ResponseEntity<List<CommentBean>> getCommentsByArtId(
			@RequestParam (value = "artId", defaultValue = "0") Integer artId) {
		List<CommentBean> commentBeans = commentService.getAllCommentsByArtId(artId);
		ResponseEntity<List<CommentBean>> re = new ResponseEntity<>(commentBeans, HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getSecComments")
	public ResponseEntity<List<CommentSecBean>> getSecCommentsByCommentId(
			@RequestParam (value = "commentId", defaultValue = "0") Integer commentId) {
		List<CommentSecBean> commentSecBeans = commentService.getCommentSecBeansByCommentId(commentId);
		ResponseEntity<List<CommentSecBean>> re = new ResponseEntity<>(commentSecBeans, HttpStatus.OK);
		return re;
	}
}
