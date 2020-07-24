package showEDU.com.web.forum.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.forum.dao.CommentDao;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.forum.service.CommentService;
import showEDU.com.web.member.model.MemberBean;
@Transactional
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Override
	public List<CommentBean> getAllCommentsByArtId(int artId) {
		return commentDao.getAllCommentsByArtId(artId);
	}

	@Override
	public Timestamp getMaxTimeRegisterByArtId(int artId) {
		return commentDao.getMaxTimeRegisterByArtId(artId);
	}
	@Override
	public Timestamp getMaxTimeRegisterByBoardId(int boardId) {
		return commentDao.getMaxTimeRegisterByBoardId(boardId);
	}

	@Override
	public List<CommentSecBean> getCommentSecBeansByCommentId(int commentId) {
		return commentDao.getCommentSecBeansByCommentId(commentId);
	}
	@Override
	public MemberBean getMemberBeanByMemberId(int memberId) {
		return commentDao.getMemberBeanByMemberId(memberId);
	}
	@Override
	public Timestamp getRegisterTimeByCommentId(int commentId) {
		return commentDao.getRegisterTimeByCommentId(commentId);
	}
	@Override
	public Timestamp getRegisterTimeByCommentSecId(int commentSecId) {
		return commentDao.getRegisterTimeByCommentSecId(commentSecId);
	}
	@Override
	public CommentBean getCommentBeanByCommentId(int commentId) {
		return commentDao.getCommentBeanByCommentId(commentId);
	}
	@Override
	public CommentSecBean getCommentSecBeanByCommentSecId(int commentSecId) {
		return commentDao.getCommentSecBeanByCommentSecId(commentSecId);
	}
	
	@Override
	public List<ThumbsUpBean> getAllthumbsByCommentId(int commentId) {
		return commentDao.getAllthumbsByCommentId(commentId);
	}
	@Override
	public List<ThumbsUpBean> getAllthumbsByCommentSecId(int commentSecId) {
		return commentDao.getAllthumbsByCommentSecId(commentSecId);
	}

	// ---------------------------------CRUD---------------------------------
	
	@Override
	public void deleteCommentBeanByCommentId(int commentId) {
		commentDao.deleteCommentBeanByCommentId(commentId);
	}
	@Override
	public void deleteCommentSecBeanByCommentSecId(int commentSecId) {
		commentDao.deleteCommentSecBeanByCommentSecId(commentSecId);
	}
	
	@Override
	public void deleteCommentSecBeanByCommentId(int commentId) {
		commentDao.deleteCommentSecBeanByCommentId(commentId);
	}
	@Override
	public void deleteAllThumbsByCommentId(int commentId) {
		commentDao.deleteAllThumbsByCommentId(commentId);
	}
	@Override
	public void deleteAllThumbsByCommentSecId(int commentSecId) {
		commentDao.deleteAllThumbsByCommentSecId(commentSecId);
	}

	
	@Override
	public void addNewComment(int artId, int boardId, int memberId, String content, CommentBean commentBean) {
		commentDao.addNewComment(artId, boardId, memberId, content, commentBean);
	}
	
	@Override
	public void addNewSecComment(int commentId, int memberId, String content, CommentSecBean commentSecBean) {
		commentDao.addNewSecComment(commentId, memberId, content, commentSecBean);
	}
	
	@Override 
	public void deleteThumbUpByCommentId(int commentId, int memberId) {
		commentDao.deleteThumbUpByCommentId(commentId, memberId);
	}
	@Override
	public void deleteThumbDownByCommentId(int commentId, int memberId) {
		commentDao.deleteThumbDownByCommentId(commentId, memberId);
	}
	@Override       // 刪除按讚(2)
	public void deleteThumbUpByCommentSecId(int commentSecId, int memberId) {
		commentDao.deleteThumbUpByCommentSecId(commentSecId, memberId);
	}
	@Override       // 刪除按爛(2)
	public void deleteThumbDownByCommentSecId(int commentSecId, int memberId) {
		commentDao.deleteThumbDownByCommentSecId(commentSecId, memberId);
	}
	
	@Override
	public void addNewThumbUp(int commentId, int memberId, ThumbsUpBean thumbsUpBean) {
		commentDao.addNewThumbUp(commentId, memberId, thumbsUpBean);
	}
	@Override
	public void addNewSecThumbUp(int commentSecId, int memberId, ThumbsUpBean thumbsUpBean) {
		commentDao.addNewSecThumbUp(commentSecId, memberId, thumbsUpBean);
	}
	@Override
	public List<ThumbsUpBean> getAllThumbsBean() {
		return commentDao.getAllThumbsBean();
	}
	@Override
	public ArticleBean getArticleBeanByCommentId(int commentId) {
		return commentDao.getArticleBeanByCommentId(commentId);
	}
	
	// ----------------------------------重構---------------------------------
	
	/*
	 * 為第一層留言設置按讚的總數
	 */
	@Override
	public void setThumbCountsByCommentId(ThumbsUpBean thumbsUpBean,CommentBean commentBean) {
		if (thumbsUpBean.getCommentBean().getCommentId() != null) {
			if (commentBean.getCommentId() == thumbsUpBean.getCommentBean().getCommentId()) {
				if (thumbsUpBean.getStatus() == 1) {
					Integer likeCount = commentBean.getLikeCount();
					likeCount++;
					commentBean.setLikeCount(likeCount);
				} else {
					Integer dislikeCount = commentBean.getDislikeCount();
					dislikeCount++;
					commentBean.setDislikeCount(dislikeCount);
				}
			}
		}
	}
	/*
	 * 為第二層留言設置按讚的總數
	 */
	@Override
	public void setThumbCountsByCommentSecId(ThumbsUpBean thumbsUpBean, CommentSecBean commentSecBean) {
		if (thumbsUpBean.getCommentSecBean().getCommentSecId() != null) {
			if (commentSecBean.getCommentSecId() == thumbsUpBean.getCommentSecBean().getCommentSecId()) {
				if (thumbsUpBean.getStatus() == 1) {
					Integer likeCount = commentSecBean.getLikeCount();
					likeCount++;
					commentSecBean.setLikeCount(likeCount);
				} else {
					Integer dislikeCount = commentSecBean.getDislikeCount();
					dislikeCount++;
					commentSecBean.setDislikeCount(dislikeCount);
				}
			}
		}
	}

	@Override
	public void addNewReport(int commentId) {
		commentDao.addNewReport(commentId);
	}
	
	@Override
	public void addNewReportSec(int commentSecId) {
		commentDao.addNewReportSec(commentSecId);
	}

	

	

	

	

	

	

	
	

	

	

	

	

	

	

	

	

	

	
	
	
	
}
