package showEDU.com.web.forum.dao;

import java.sql.Timestamp;
import java.util.List;

import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.member.model.MemberBean;

public interface CommentDao {
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
	
	
	
	
	// -------------------------------CRUD------------------------------
	void addNewSecComment(int commentId, int memberId, String content,CommentSecBean commentSecBean);
	
	// 第一層
	// 刪除按讚(1)
	void deleteThumbUpByCommentId(int commentId, int memberId);
	// 第一層
	// 刪除按爛(1)
	void deleteThumbDownByCommentId(int commentId, int memberId);
	// 第二層
	// 刪除按讚(2)
	void deleteThumbUpByCommentSecId(int commentSecId, int memberId);
	// 第二層
	// 刪除按爛(2)
	void deleteThumbDownByCommentSecId(int commentSecId, int memberId);
	
	void addNewThumbUp(int commentId, int memberId, ThumbsUpBean thumbsUpBean);
	
	void addNewSecThumbUp(int commentSecId, int memberId, ThumbsUpBean thumbsUpBean);
	
	ArticleBean getArticleBeanByCommentId(int commentId);
}
