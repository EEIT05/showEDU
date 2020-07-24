package showEDU.com.web.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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

@SessionAttributes("memberBean")
@Controller
public class CommentControllerCRUD {
	@Autowired
	CommentService commentService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ServletContext ctx;

	// 刪除第一層留言
	@PostMapping("/deleteComment")
	public void deleteComment(@RequestParam Integer commentId, HttpServletResponse response) {
		List<CommentSecBean> commentSecBeans = commentService.getCommentSecBeansByCommentId(commentId);
		for (CommentSecBean commentSecBean : commentSecBeans) {
			System.out.println("二層留言Id=====================" + commentSecBean.getCommentSecId());
			commentService.deleteAllThumbsByCommentSecId(commentSecBean.getCommentSecId());
		}
		commentService.deleteCommentSecBeanByCommentId(commentId);
		commentService.deleteAllThumbsByCommentId(commentId); // 刪掉一層讚
		commentService.deleteCommentBeanByCommentId(commentId);
		System.out.println("刪除一則留言------------------------------------");
		PrintWriter writer;
		try {
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String msg = "alert('留言删除成功!!!');history.go(-1)";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 刪除第二層留言
	@PostMapping("/deleteSecComment")
	public void deleteSecComment(@RequestParam Integer commentSecId, Integer artId, HttpServletResponse response) {
		commentService.deleteAllThumbsByCommentSecId(commentSecId);
		commentService.deleteCommentSecBeanByCommentSecId(commentSecId);
		PrintWriter writer;
		try {
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String msg = "alert('回覆删除成功!!!');history.go(-1)";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 新增第一層留言
	@PostMapping("/addComment")
	public String addComment(@RequestParam Integer artId, Integer memberId, String content) {
		System.out.println("內容為:" + content + "---------------------------------------------");
		ArticleBean articleBean = articleService.getArticleByArtId(artId);
		Integer boardId = articleBean.getDiscussionBoardBean().getBoardId();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		CommentBean commentBean = new CommentBean();
		commentBean.setRegisterTime(timestamp);
		commentService.addNewComment(artId, boardId, memberId, content, commentBean);
		System.out.println("新增一筆Comment資料========================================");
		return "redirect:/comment/" + artId;
	}

	// 新增第二層留言
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
	public ResponseEntity<CommentBean> updateThumbUp(@RequestParam Integer commentId, Integer memberId, Integer count,
			Integer discount) {
		if (count == null) {
			count = 0;
		}
		if (discount == null) {
			discount = 0;
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
					commentBean1.setDislikeCount(discount);
					re = new ResponseEntity<>(commentBean1, HttpStatus.OK);
					System.out.println("按讚資料刪除=========================================================");
					System.out.println("按讚數為:" + count);
					break;
				}
			}
		}
		if (sum == count) {
			// 如果會員沒按過讚 但按過爛的話, 刪除按爛的再新增按讚的
			for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
				if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
						&& (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
					if (thumbsUpBean.getStatus() == 0) {
						commentService.deleteThumbDownByCommentId(commentId, memberId); // 將按爛刪除
						discount--;
					}
				}
			}
			// 新增一比按讚數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(1);
			commentService.addNewThumbUp(commentId, memberId, thumbsUpBean2);
			count++;
			CommentBean commentBean2 = commentService.getCommentBeanByCommentId(commentId);
			commentBean2.setLikeCount(count);
			commentBean2.setDislikeCount(discount);
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
	public ResponseEntity<CommentBean> updateThumbDown(@RequestParam Integer commentId, Integer memberId, Integer count,
			Integer discount) {
		if (discount == null) {
			discount = 0;
		}
		if (count == null) {
			count = 0;
		}
		Integer sum = discount;
		ResponseEntity<CommentBean> re = null;
		List<ThumbsUpBean> allthumbsByCommentId = commentService.getAllthumbsByCommentId(commentId);
		for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
			// 如果按爛的會員跟當前按爛的Bean的會員一樣,表示此會員按過爛
			if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
					&& (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
				if (thumbsUpBean.getStatus() == 0) {
					commentService.deleteThumbDownByCommentId(commentId, memberId); // 將此筆按讚刪除
					discount--;
					CommentBean commentBean1 = commentService.getCommentBeanByCommentId(commentId);
					commentBean1.setLikeCount(count);
					commentBean1.setDislikeCount(discount);
					re = new ResponseEntity<>(commentBean1, HttpStatus.OK);
					System.out.println("按爛資料刪除=========================================================");
					System.out.println("按爛數為:" + discount);
					break;
				}
			}
		}
		if (sum == discount) {
			// 如果會員沒按過爛 但按過讚的話, 刪除按讚的再新增按爛的
			for (ThumbsUpBean thumbsUpBean : allthumbsByCommentId) {
				if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
						&& (thumbsUpBean.getCommentBean().getCommentId() == commentId)) {
					if (thumbsUpBean.getStatus() == 1) {
						commentService.deleteThumbUpByCommentId(commentId, memberId); // 將按讚刪除
						count--;
					}
				}
			}
			// 新增一比按爛數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(0);
			commentService.addNewThumbUp(commentId, memberId, thumbsUpBean2);
			discount++;
			CommentBean commentBean2 = commentService.getCommentBeanByCommentId(commentId);
			commentBean2.setDislikeCount(discount);
			commentBean2.setLikeCount(count);
			re = new ResponseEntity<>(commentBean2, HttpStatus.OK);
			System.out.println("新增一筆按爛資料=================================================================");
			System.out.println("按爛數為:" + discount);
			return re;
		} else {
			return re;
		}
	}

	// 第二層按讚
	@PostMapping(value = "/SecThumbUpCalculate", produces = { "application/json" })
	public ResponseEntity<CommentSecBean> updateSecThumbUp(@RequestParam Integer commentSecId, Integer memberId,
			Integer count, Integer discount) {
		if (count == null) {
			count = 0;
		}
		if (discount == null) {
			discount = 0;
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
					commentSecBean1.setDislikeCount(discount);
					commentSecBean1.setLikeCount(count);
					re = new ResponseEntity<>(commentSecBean1, HttpStatus.OK);
					System.out.println("Sec按讚資料刪除=========================================================");
					System.out.println("Sec按讚數為:" + count);
					break;
				}
			}
		}
		if (sum == count) {
			// 如果會員沒按過讚 但按過爛的話, 刪除按爛的再新增按讚的
			for (ThumbsUpBean thumbsUpBean : allthumbsByCommentSecId) {
				if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
						&& (thumbsUpBean.getCommentSecBean().getCommentSecId() == commentSecId)) {
					if (thumbsUpBean.getStatus() == 0) {
						commentService.deleteThumbDownByCommentSecId(commentSecId, memberId);
						discount--;
					}
				}
			}
			// 新增一比按讚數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(1);
			commentService.addNewSecThumbUp(commentSecId, memberId, thumbsUpBean2);
			count++;
			CommentSecBean commentSecBean2 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
			commentSecBean2.setDislikeCount(discount);
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
			Integer count, Integer discount) {
		if (discount == null) {
			discount = 0;
		}
		if (count == null) {
			count = 0;
		}
		Integer sum = discount;
		ResponseEntity<CommentSecBean> re = null;
		List<ThumbsUpBean> allthumbsByCommentSecId = commentService.getAllthumbsByCommentSecId(commentSecId);
		for (ThumbsUpBean thumbsUpBean : allthumbsByCommentSecId) {
			// 如果按讚的會員跟當前按讚的Bean的會員一樣,表示此會員按過讚
			if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
					&& (thumbsUpBean.getCommentSecBean().getCommentSecId() == commentSecId)) {
				if (thumbsUpBean.getStatus() == 0) {
					commentService.deleteThumbDownByCommentSecId(commentSecId, memberId);
					discount--;
					CommentSecBean commentSecBean1 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
					commentSecBean1.setLikeCount(count);
					commentSecBean1.setDislikeCount(discount);
					re = new ResponseEntity<>(commentSecBean1, HttpStatus.OK);
					System.out.println("Sec按爛資料刪除=========================================================");
					System.out.println("Sec按爛數為:" + discount);
					break;
				}
			}
		}
		if (sum == discount) {
			// 如果會員沒按過爛 但按過讚的話, 刪除按讚的再新增按爛的
			for (ThumbsUpBean thumbsUpBean : allthumbsByCommentSecId) {
				if ((thumbsUpBean.getMemberBean().getMemberId() == memberId)
						&& (thumbsUpBean.getCommentSecBean().getCommentSecId() == commentSecId)) {
					if (thumbsUpBean.getStatus() == 1) {
						commentService.deleteThumbUpByCommentSecId(commentSecId, memberId);
						count--;
					}
				}
			}
			// 新增一比按讚數
			ThumbsUpBean thumbsUpBean2 = new ThumbsUpBean();
			thumbsUpBean2.setStatus(0);
			commentService.addNewSecThumbUp(commentSecId, memberId, thumbsUpBean2);
			discount++;
			CommentSecBean commentSecBean2 = commentService.getCommentSecBeanByCommentSecId(commentSecId);
			commentSecBean2.setLikeCount(count);
			commentSecBean2.setDislikeCount(discount);
			re = new ResponseEntity<>(commentSecBean2, HttpStatus.OK);
			System.out.println("新增一筆Sec按讚資料=================================================================");
			System.out.println("Sec按讚數為:" + discount);
			return re;
		} else {
			return re;
		}
	}

	// 新增留言檢舉
	@PostMapping("/reportComment")
	public void reportComment(@RequestParam Integer commentId, HttpServletResponse response) {
		commentService.addNewReport(commentId);
		System.out.println("新增一則檢舉------------------------------------");
		PrintWriter writer;
		try {
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String msg = "alert('檢舉成功!!!');history.go(-1)";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 新增回覆檢舉
	@PostMapping("/reportSecComment")
	public void reportSecComment(@RequestParam Integer commentSecId, HttpServletResponse response) {
		commentService.addNewReportSec(commentSecId);
		System.out.println("新增一則檢舉------------------------------------");
		PrintWriter writer;
		try {
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String msg = "alert('檢舉成功!!!');history.go(-1)";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
