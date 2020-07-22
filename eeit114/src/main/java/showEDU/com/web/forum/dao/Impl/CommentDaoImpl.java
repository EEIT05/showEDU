package showEDU.com.web.forum.dao.Impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.forum.dao.CommentDao;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.member.model.MemberBean;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentBean> getAllCommentsByArtId(int artId) {
		String hql = "From CommentBean c Where c.articleBean.artId = :aid";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("aid", artId).getResultList();
	}

	@Override
	public Timestamp getMaxTimeRegisterByArtId(int artId) {
		String hql = "Select Max(c.registerTime) From CommentBean c Where c.articleBean.artId = :aid";
		Session session = factory.getCurrentSession();
		Timestamp timestamp = null;
		try {
			timestamp = (Timestamp)session.createQuery(hql).setParameter("aid", artId).getSingleResult();
		} catch (NoResultException e) {
			;
		}
		if (timestamp == null) {
			timestamp = new Timestamp(System.currentTimeMillis());
		}
		return timestamp;
	}

	@Override
	public Timestamp getMaxTimeRegisterByBoardId(int boardId) {
		String hql = "Select Max(c.registerTime) From CommentBean c Where c.boardBean.boardId = :bid";
		Session session = factory.getCurrentSession();
		Timestamp timestamp = null;
		try {
			timestamp = (Timestamp)session.createQuery(hql).setParameter("bid", boardId).getSingleResult();
		} catch (NoResultException e) {
			;
		}
		if (timestamp == null) {
			timestamp = new Timestamp(System.currentTimeMillis());
		}
		return timestamp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentSecBean> getCommentSecBeansByCommentId(int commentId) {
		String hql = "From CommentSecBean c Where c.commentBean.commentId = :cid";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("cid", commentId).getResultList();
	}

	@Override
	public MemberBean getMemberBeanByMemberId(int memberId) {
		String hql = "From MemberBean m Where m.memberId = :mid";
		Session session = factory.getCurrentSession();
		return (MemberBean) session.createQuery(hql).setParameter("mid", memberId).getSingleResult();
	}

	@Override
	public Timestamp getRegisterTimeByCommentId(int commentId) {
		String hql = "Select c.registerTime From CommentBean c Where c.commentId = :cid";
		Session session = factory.getCurrentSession();
		return (Timestamp) session.createQuery(hql).setParameter("cid", commentId).getSingleResult();
	}

	@Override
	public Timestamp getRegisterTimeByCommentSecId(int commentSecId) {
		String hql = "Select c.registerTime From CommentSecBean c Where c.commentSecId = :cid";
		Session session = factory.getCurrentSession();
		return (Timestamp) session.createQuery(hql).setParameter("cid", commentSecId).getSingleResult();
	}

	@Override
	public CommentBean getCommentBeanByCommentId(int commentId) {
		String hql = "From CommentBean c Where c.commentId = :cid";
		Session session = factory.getCurrentSession();
		return (CommentBean) session.createQuery(hql).setParameter("cid", commentId).getSingleResult();
	}
	
	@Override
	public CommentSecBean getCommentSecBeanByCommentSecId(int commentSecId) {
		String hql = "From CommentSecBean c Where c.commentSecId = :cid";
		Session session = factory.getCurrentSession();
		return (CommentSecBean) session.createQuery(hql).setParameter("cid", commentSecId).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ThumbsUpBean> getAllthumbsByCommentId(int commentId) {
		String hql = "From ThumbsUpBean tb Where tb.commentBean.commentId = :cid";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("cid", commentId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ThumbsUpBean> getAllthumbsByCommentSecId(int commentSecId) {
		String hql = "From ThumbsUpBean tb Where tb.commentSecBean.commentSecId = :csid";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("csid", commentSecId).getResultList();
	}

	// -------------------------------CRUD---------------------------
	@Override
	public void deleteCommentBeanByCommentId(int commentId) {
		String hql = "Delete From CommentBean c Where c.commentId = :cid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentId).executeUpdate();
	}
	
	@Override
	public void deleteCommentSecBeanByCommentSecId(int commentSecId) {
		String hql = "Delete From CommentSecBean c Where c.commentSecId = :cid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentSecId).executeUpdate();
	}
	
	@Override
	public void deleteCommentSecBeanByCommentId(int commentId) {
		String hql = "Delete From CommentSecBean c Where c.commentBean.commentId = :cid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentId).executeUpdate();
	}
	// 刪除一層留言所有按讚
	@Override
	public void deleteAllThumbsByCommentId(int commentId) {
		String hql = "Delete From ThumbsUpBean tb Where tb.commentBean.commentId = :cid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentId).executeUpdate();
	}
	// 刪除二層留言所有按讚
	@Override
	public void deleteAllThumbsByCommentSecId(int commentSecId) {
		String hql = "Delete From ThumbsUpBean tb Where tb.commentSecBean.commentSecId = :cid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentSecId).executeUpdate();
	}
	
	// 新增第一層回復留言
	@Override
	public void addNewComment(int artId, int boardId, int memberId, String content, CommentBean commentBean) {
		Session session = factory.getCurrentSession();
		ArticleBean articleBean = session.get(ArticleBean.class, artId);
		MemberBean memberBean = session.get(MemberBean.class, memberId);
		DiscussionBoardBean boardBean = session.get(DiscussionBoardBean.class, boardId);
		commentBean.setArticleBean(articleBean);
		commentBean.setMemberBean(memberBean);
		commentBean.setBoardBean(boardBean);
		commentBean.setContent(content);
		session.save(commentBean);
	}
	
	// 新增第二層回覆留言
	@Override
	public void addNewSecComment(int commentId, int memberId, String content,CommentSecBean commentSecBean) {
		Session session = factory.getCurrentSession();
		CommentBean commentBean = session.get(CommentBean.class, commentId);
		MemberBean memberBean = session.get(MemberBean.class, memberId);
		commentSecBean.setCommentBean(commentBean);
		commentSecBean.setMemberBean(memberBean);
		commentSecBean.setContent(content);
		session.save(commentSecBean);
	}
	
	
	// 刪除按讚(1)
	@Override 
	public void deleteThumbUpByCommentId(int commentId, int memberId) {
		String hql = "Delete From ThumbsUpBean tb Where tb.commentBean.commentId = :cid and tb.memberBean.memberId = :mid and tb.status = 1" ;
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentId).setParameter("mid", memberId).executeUpdate();
	}
	// 刪除按爛(1)
	@Override
	public void deleteThumbDownByCommentId(int commentId, int memberId) {
		String hql = "Delete From ThumbsUpBean tb Where tb.commentBean.commentId = :cid and tb.memberBean.memberId = :mid and tb.status = 0" ;
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentId).setParameter("mid", memberId).executeUpdate();
	}
	// 刪除按讚(2)
	@Override
	public void deleteThumbUpByCommentSecId(int commentSecId, int memberId) {
		String hql = "Delete From ThumbsUpBean tb Where tb.commentSecBean.commentSecId = :cid and tb.memberBean.memberId = :mid and tb.status = 1" ;
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentSecId).setParameter("mid", memberId).executeUpdate();
	}
	// 刪除按爛(2)
	@Override
	public void deleteThumbDownByCommentSecId(int commentSecId, int memberId) {
		String hql = "Delete From ThumbsUpBean tb Where tb.commentSecBean.commentSecId = :cid and tb.memberBean.memberId = :mid and tb.status = 0" ;
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("cid", commentSecId).setParameter("mid", memberId).executeUpdate();
	}

	@Override // 新增按讚(1)
	public void addNewThumbUp(int commentId, int memberId, ThumbsUpBean thumbsUpBean) {
		CommentBean cb = getCommentBeanByCommentId(commentId);
		MemberBean mb = getMemberBeanByMemberId(memberId);
		thumbsUpBean.setCommentBean(cb);
		thumbsUpBean.setMemberBean(mb);
		Session session = factory.getCurrentSession();
		session.save(thumbsUpBean);
	}
	
	@Override // 新增按讚(2)
	public void addNewSecThumbUp(int commentSecId, int memberId, ThumbsUpBean thumbsUpBean) {
		CommentSecBean cb = getCommentSecBeanByCommentSecId(commentSecId);
		MemberBean mb = getMemberBeanByMemberId(memberId);
		thumbsUpBean.setCommentSecBean(cb);
		thumbsUpBean.setMemberBean(mb);
		Session session = factory.getCurrentSession();
		session.save(thumbsUpBean);
	}


	@Override
	public ArticleBean getArticleBeanByCommentId(int commentId) {
		String hql = "Select c.articleBean From CommentBean c Where c.commentId = :cid";
		Session session = factory.getCurrentSession();
		return (ArticleBean) session.createQuery(hql).setParameter("cid", commentId).getSingleResult();
	}

	

	

	

	

	

	

	

	

	
	



	
	
	
	
	
	
	
	
	
	

}
