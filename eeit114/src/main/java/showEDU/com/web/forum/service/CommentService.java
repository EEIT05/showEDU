package showEDU.com.web.forum.service;

import java.sql.Timestamp;
import java.util.List;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.member.model.MemberBean;

public interface CommentService {
	List<CommentBean> getAllCommentsByArtId(int artId);

	Timestamp getMaxTimeRegisterByArtId(int artId);

	Timestamp getMaxTimeRegisterByBoardId(int boardId);

	List<CommentSecBean> getCommentSecBeansByCommentId(int commentId);
	
	MemberBean getMemberBeanByMemberId(int memberId);
	
	Timestamp getRegisterTimeByCommentId(int commentId);

	Timestamp getRegisterTimeByCommentSecId(int commentSecId);
	
	CommentBean getCommentBeanByCommentId(int commentId);
	
	CommentSecBean getCommentSecBeanByCommentSecId(int commentSecId);

	List<ThumbsUpBean> getAllthumbsByCommentId(int commentId);
	
	List<ThumbsUpBean> getAllthumbsByCommentSecId(int commentSecId);
	
	void setThumbCountsByCommentId(ThumbsUpBean thumbsUpBean,CommentBean commentBean);
	
	void setThumbCountsByCommentSecId(ThumbsUpBean thumbsUpBean,CommentSecBean commentSecBean);
	
	
	// -----------------------------CRUD---------------------------------------
	void deleteCommentBeanByCommentId(int commentId);
	
	void deleteCommentSecBeanByCommentSecId(int commentSecId);
	
	void deleteCommentSecBeanByCommentId(int commentId);
	
	void deleteAllThumbsByCommentId(int commentId);
	
	void deleteAllThumbsByCommentSecId(int commentSecId);
	
	void addNewComment(int artId, int boardId, int memberId, String content, CommentBean commentBean);
	
	void addNewSecComment(int commentId, int memberId, String content,CommentSecBean commentSecBean);
	
	// 第一層
	// 刪除按讚(1)
	void deleteThumbUpByCommentId(int commentId, int memberId);
	// 刪除按爛(1)
	void deleteThumbDownByCommentId(int commentId, int memberId);
	// 第二層
	// 刪除按讚(2)
	void deleteThumbUpByCommentSecId(int commentSecId, int memberId);
	// 第二層
	// 刪除按爛(2)
	void deleteThumbDownByCommentSecId(int commentSecId, int memberId);
	// 新增按讚(1)
	void addNewThumbUp(int commentId, int memberId, ThumbsUpBean thumbsUpBean);
	// 新增按讚(2)
	void addNewSecThumbUp(int commentSecId, int memberId, ThumbsUpBean thumbsUpBean);

	ArticleBean getArticleBeanByCommentId(int commentId);

}
