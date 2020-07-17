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
import showEDU.com.web.forum.model.ArticleBeanWithImageData;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.CommentService;

@RestController
public class AjaxArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;
	@Autowired
	CommentService commentService;
	@GetMapping("/artType")
	public ResponseEntity<List<ArticleBeanWithImageData>> getArticleBeanByTypeId(
			@RequestParam (value="typeId", defaultValue = "0") Integer typeId) {
		List<ArticleBeanWithImageData> articleBeans = articleService.getArtBeansImageDataByTypeId(typeId); 
		ResponseEntity<List<ArticleBeanWithImageData>> re = new ResponseEntity<List<ArticleBeanWithImageData>>(articleBeans, HttpStatus.OK);
		setAbwidTimeAndReply(articleBeans);
		return re;
	}
	@GetMapping("/allArtType")
	public ResponseEntity<List<ArticleBean>> getAllArticleBean(){
		List<ArticleBean> allArticle = articleService.getAllArticle();
		ResponseEntity<List<ArticleBean>> re = new ResponseEntity<List<ArticleBean>>(allArticle, HttpStatus.OK);
		setAllArtTypeTimeAndReply(allArticle);
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
}
