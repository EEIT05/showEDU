package showEDU.com.web.forum.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.forum.service.ArticleService;
import showEDU.com.web.forum.service.CommentService;

@SessionAttributes("loginMember")
@Controller
public class CommentControllerCRUD {
	@Autowired
	CommentService commentService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;

	@PostMapping("/addSecComment")
	public String addSecComment(@RequestParam Integer commentId, Integer memberId, String SecContent) {
		System.out.println("內容為:" + SecContent + "---------------------------------------------");
		ArticleBean articleBean = commentService.getArticleBeanByCommentId(commentId);
		Integer artId = articleBean.getArtId();
		CommentSecBean commentSecBean = new CommentSecBean();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		commentSecBean.setRegisterTime(timestamp);
		commentService.addNewSecComment(commentId, memberId, SecContent, commentSecBean);
		System.out.println("新增一筆SecComment資料=====================================");
		return "redirect:/comment/" + artId;
	}
	
	
	
	// 第一層按讚
	@PostMapping(value = "/thumbUpCalculate", produces = { "application/json" })
	public ResponseEntity<CommentBean> updateThumbUp(@RequestParam Integer commentId, Integer memberId, Integer count) {
		if (count == null) {
			count = 0;
		}
		Integer sum = count;
		ResponseEntity<CommentBean> re = null;
		List<ThumbsUpBean> allthumbsByCommentId = commentService.getAllthumbsByCommentId(commentId);
		for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
			// 如果按讚的會員跟當前按讚的Bean的會員一樣,表示此會員按過讚
			if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
					&& (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
				if (thumbsUpBean.getStatus() == 1) {
					commentService.deleteThumbUpByCommentId(commentId, memberId); // 將此筆按讚刪除
					count--;
					CommentBean commentBean1 = commentService.getCommentBeanByCommentId(commentId);
					commentBean1.setLikeCount(count);
					re = new ResponseEntity<>(commentBean1, HttpStatus.OK);
					System.out.println("按讚資料刪除=========================================================");
					System.out.println("按讚數為:" + count);
					break;
				}
			}
		}
		if (sum == count) {
			// 新增一比按讚數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(1);
			commentService.addNewThumbUp(commentId, memberId, thumbsUpBean2);
			count++;
			CommentBean commentBean2 = commentService.getCommentBeanByCommentId(commentId);
			commentBean2.setLikeCount(count);
			re = new ResponseEntity<>(commentBean2, HttpStatus.OK);
			System.out.println("新增一筆按讚資料=================================================================");
			System.out.println("按讚數為:" + count);
			return re;
		} else {
			return re;
		}
	}
	// 第一層按爛
	@PostMapping(value = "/thumbDownCalculate", produces = { "application/json" })
	public ResponseEntity<CommentBean> updateThumbDown(@RequestParam Integer commentId, Integer memberId,
			Integer count) {
		if (count == null) {
			count = 0;
		}
		Integer sum = count;
		ResponseEntity<CommentBean> re = null;
		List<ThumbsUpBean> allthumbsByCommentId = commentService.getAllthumbsByCommentId(commentId);
		for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
			// 如果按爛的會員跟當前按爛的Bean的會員一樣,表示此會員按過爛
			if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
					&& (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
				if (thumbsUpBean.getStatus() == 0) {
					commentService.deleteThumbDownByCommentId(commentId, memberId); // 將此筆按讚刪除
					count--;
					CommentBean commentBean1 = commentService.getCommentBeanByCommentId(commentId);
					commentBean1.setDislikeCount(count);
					re = new ResponseEntity<>(commentBean1, HttpStatus.OK);
					System.out.println("按爛資料刪除=========================================================");
					System.out.println("按爛數為:" + count);
					break;
				}
			}
		}
		if (sum == count) {
			// 新增一比按爛數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(0);
			commentService.addNewThumbUp(commentId, memberId, thumbsUpBean2);
			count++;
			CommentBean commentBean2 = commentService.getCommentBeanByCommentId(commentId);
			commentBean2.setDislikeCount(count);
			re = new ResponseEntity<>(commentBean2, HttpStatus.OK);
			System.out.println("新增一筆按爛資料=================================================================");
			System.out.println("按爛數為:" + count);
			return re;
		} else {
			return re;
		}
	}
	// 第二層按讚
	@PostMapping(value = "/SecThumbUpCalculate", produces = { "application/json" })
	public ResponseEntity<CommentSecBean> updateSecThumbUp(@RequestParam Integer commentSecId, Integer memberId,
			Integer count) {
		if (count == null) {
			count = 0;
		}
		Integer sum = count;
		ResponseEntity<CommentSecBean> re = null;
		List<ThumbsUpBean> allthumbsByCommentSecId = commentService.getAllthumbsByCommentSecId(commentSecId);
		for (ThumbsUpBean thumbsUpBean : allthumbsByCommentSecId) {
			// 如果按讚的會員跟當前按讚的Bean的會員一樣,表示此會員按過讚
			if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
					&& (thumbsUpBean.getCommentSecBean().getCommentSecId() == commentSecId)) {
				if (thumbsUpBean.getStatus() == 1) {
					commentService.deleteThumbUpByCommentSecId(commentSecId, memberId);// 將此筆按讚刪除
					count--;
					CommentSecBean commentSecBean1 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
					commentSecBean1.setLikeCount(count);
					re = new ResponseEntity<>(commentSecBean1, HttpStatus.OK);
					System.out.println("Sec按讚資料刪除=========================================================");
					System.out.println("Sec按讚數為:" + count);
					break;
				}
			}
		}
		if (sum == count) {
			// 新增一比按讚數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(1);
			commentService.addNewSecThumbUp(commentSecId, memberId, thumbsUpBean2);
			count++;
			CommentSecBean commentSecBean2 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
			commentSecBean2.setLikeCount(count);
			re = new ResponseEntity<>(commentSecBean2, HttpStatus.OK);
			System.out.println("新增一筆Sec按讚資料=================================================================");
			System.out.println("Sec按讚數為:" + count);
			return re;
		} else {
			return re;
		}
	}
	// 第二層按爛
	@PostMapping(value = "/SecThumbDownCalculate", produces = { "application/json" })
	public ResponseEntity<CommentSecBean> updateSecThumbDown(@RequestParam Integer commentSecId, Integer memberId,
			Integer count) {
		if (count == null) {
			count = 0;
		}
		Integer sum = count;
		ResponseEntity<CommentSecBean> re = null;
		List<ThumbsUpBean> allthumbsByCommentSecId = commentService.getAllthumbsByCommentSecId(commentSecId);
		for (ThumbsUpBean thumbsUpBean : allthumbsByCommentSecId) {
			// 如果按讚的會員跟當前按讚的Bean的會員一樣,表示此會員按過讚
			if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
					&& (thumbsUpBean.getCommentSecBean().getCommentSecId() == commentSecId)) {
				if (thumbsUpBean.getStatus() == 0) {
					commentService.deleteThumbDownByCommentSecId(commentSecId, memberId);
					count--;
					CommentSecBean commentSecBean1 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
					commentSecBean1.setDislikeCount(count);
					re = new ResponseEntity<>(commentSecBean1, HttpStatus.OK);
					System.out.println("Sec按爛資料刪除=========================================================");
					System.out.println("Sec按爛數為:" + count);
					break;
				}
			}
		}
		if (sum == count) {
			// 新增一比按讚數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(0);
			commentService.addNewSecThumbUp(commentSecId, memberId, thumbsUpBean2);
			count++;
			CommentSecBean commentSecBean2 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
			commentSecBean2.setDislikeCount(count);
			re = new ResponseEntity<>(commentSecBean2, HttpStatus.OK);
			System.out.println("新增一筆Sec按讚資料=================================================================");
			System.out.println("Sec按讚數為:" + count);
			return re;
		} else {
			return re;
		}
	}

	// 第一層留言按讚新增Or刪除
//		@PostMapping("/thumbUpCalculate")
//		public String updateThumbUp(@RequestParam Integer commentId, Integer memberId, Model model) {
//				List<ThumbsUpBean> allthumbsByCommentId = commentService.getAllthumbsByCommentId(commentId); 
//				ArticleBean articleBean = commentService.getArticleBeanByCommentId(commentId);
//				Integer artId = articleBean.getArtId();
//				for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
//					// 如果按讚的會員跟當前按讚的Bean的會員一樣,表示此會員按過讚
//					if ((thumbsUpBean.getMemberBean().getMemberId() == memberId) && (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
//						if (thumbsUpBean.getStatus() == 1) {
//							commentService.deleteThumbUpByThumbId(commentId, memberId); // 將此筆按讚刪除
//							return "redirect:/comment/" + artId;
//						}
//					}
//				}
//				ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
//				thumbsUpBean2.setStatus(1);
//				commentService.addNewThumbUp(commentId, memberId, thumbsUpBean2);
//			return "redirect:/comment/" + artId;
//		}
	// 第一層留言按爛新增Or刪除
//		@PostMapping("/thumbDownCalculate")
//		public String updateThumbDown(@RequestParam Integer commentId, Integer memberId, Model model) {
//				List<ThumbsUpBean> allthumbsByCommentId = commentService.getAllthumbsByCommentId(commentId); 
//				ArticleBean articleBean = commentService.getArticleBeanByCommentId(commentId);
//				Integer artId = articleBean.getArtId();
//				for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
//					// 如果按讚的會員跟當前按讚的Bean的會員一樣,表示此會員按過讚
//					if ((thumbsUpBean.getMemberBean().getMemberId() == memberId) && (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
//						if (thumbsUpBean.getStatus() == 0) {
//							commentService.deleteThumbDownByThumbId(commentId, memberId); // 將此筆按讚刪除
//							return "redirect:/comment/" + artId;
//						}
//					}
//				}
//				ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
//				thumbsUpBean2.setStatus(0);
//				commentService.addNewThumbUp(commentId, memberId, thumbsUpBean2);
//			return "redirect:/comment/" + artId;
//		}

}
