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

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Transactional
	@Override
	public List<CommentBean> getAllCommentsByArtId(int artId) {
		return commentDao.getAllCommentsByArtId(artId);
	}

	@Transactional
	@Override
	public Timestamp getMaxTimeRegisterByArtId(int artId) {
		return commentDao.getMaxTimeRegisterByArtId(artId);
	}
	@Transactional
	@Override
	public Timestamp getMaxTimeRegisterByBoardId(int boardId) {
		return commentDao.getMaxTimeRegisterByBoardId(boardId);
	}

	@Transactional
	@Override
	public List<CommentSecBean> getCommentSecBeansByCommentId(int commentId) {
		return commentDao.getCommentSecBeansByCommentId(commentId);
	}
	@Transactional
	@Override
	public MemberBean getMemberBeanByMemberId(int memberId) {
		return commentDao.getMemberBeanByMemberId(memberId);
	}
	@Transactional
	@Override
	public Timestamp getRegisterTimeByCommentId(int commentId) {
		return commentDao.getRegisterTimeByCommentId(commentId);
	}
	@Transactional
	@Override
	public Timestamp getRegisterTimeByCommentSecId(int commentSecId) {
		return commentDao.getRegisterTimeByCommentSecId(commentSecId);
	}
	@Transactional
	@Override
	public CommentBean getCommentBeanByCommentId(int commentId) {
		return commentDao.getCommentBeanByCommentId(commentId);
	}
	@Transactional
	@Override
	public CommentSecBean getCommentSecBeanByCommentSecId(int commentSecId) {
		return commentDao.getCommentSecBeanByCommentSecId(commentSecId);
	}
	
	@Transactional
	@Override
	public List<ThumbsUpBean> getAllthumbsByCommentId(int commentId) {
		return commentDao.getAllthumbsByCommentId(commentId);
	}
	@Transactional
	@Override
	public List<ThumbsUpBean> getAllthumbsByCommentSecId(int commentSecId) {
		return commentDao.getAllthumbsByCommentSecId(commentSecId);
	}

	// ---------------------------------CRUD---------------------------------
	@Transactional
	@Override
	public void deleteCommentBeanByCommentId(int commentId) {
		commentDao.deleteCommentBeanByCommentId(commentId);
	}
	@Transactional
	@Override
	public void deleteCommentSecBeanByCommentSecId(int commentSecId) {
		commentDao.deleteCommentSecBeanByCommentSecId(commentSecId);
	}
	
	@Transactional
	@Override
	public void deleteCommentSecBeanByCommentId(int commentId) {
		commentDao.deleteCommentSecBeanByCommentId(commentId);
	}
	@Transactional
	@Override
	public void deleteAllThumbsByCommentId(int commentId) {
		commentDao.deleteAllThumbsByCommentId(commentId);
	}
	@Transactional
	@Override
	public void deleteAllThumbsByCommentSecId(int commentSecId) {
		commentDao.deleteAllThumbsByCommentSecId(commentSecId);
	}

	
	@Transactional
	@Override
	public void addNewComment(int artId, int boardId, int memberId, String content, CommentBean commentBean) {
		commentDao.addNewComment(artId, boardId, memberId, content, commentBean);
	}
	
	@Transactional
	@Override
	public void addNewSecComment(int commentId, int memberId, String content, CommentSecBean commentSecBean) {
		commentDao.addNewSecComment(commentId, memberId, content, commentSecBean);
	}
	
	@Transactional  // 刪除按讚(1)
	@Override 
	public void deleteThumbUpByCommentId(int commentId, int memberId) {
		commentDao.deleteThumbUpByCommentId(commentId, memberId);
	}
	@Transactional  // 刪除按爛(1)
	@Override
	public void deleteThumbDownByCommentId(int commentId, int memberId) {
		commentDao.deleteThumbDownByCommentId(commentId, memberId);
	}
	@Transactional
	@Override       // 刪除按讚(2)
	public void deleteThumbUpByCommentSecId(int commentSecId, int memberId) {
		commentDao.deleteThumbUpByCommentSecId(commentSecId, memberId);
	}
	@Transactional
	@Override       // 刪除按爛(2)
	public void deleteThumbDownByCommentSecId(int commentSecId, int memberId) {
		commentDao.deleteThumbDownByCommentSecId(commentSecId, memberId);
	}
	
	@Transactional
	@Override
	public void addNewThumbUp(int commentId, int memberId, ThumbsUpBean thumbsUpBean) {
		commentDao.addNewThumbUp(commentId, memberId, thumbsUpBean);
	}
	@Transactional
	@Override
	public void addNewSecThumbUp(int commentSecId, int memberId, ThumbsUpBean thumbsUpBean) {
		commentDao.addNewSecThumbUp(commentSecId, memberId, thumbsUpBean);
	}
	@Transactional
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

	

	

	

	

	
	

	

	

	

	

	

	

	

	

	

	
	
	
	
}
