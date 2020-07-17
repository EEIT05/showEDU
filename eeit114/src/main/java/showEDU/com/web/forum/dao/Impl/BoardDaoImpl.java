package showEDU.com.web.forum.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.forum.dao.BoardDao;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;
@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussionBoardBean> getAllBoards() {   // 得到所有討論版內容
		Session session = factory.getCurrentSession();
		String hql = "From DiscussionBoardBean";
		return session.createQuery(hql).getResultList();
	}

	@Override
	public ForumMovieBean getMovieBeanByFKMovieId(int movieId) { // 由movieId得到對應的movieBean
		Session session = factory.getCurrentSession();
		return session.get(ForumMovieBean.class, movieId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumMovieBean> getMovieBeanList() { 
		String hql = "From ForumMovieBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussionBoardBean> getSortedBoards() { // 依照觀看次數排序討論版
		String hql = "From DiscussionBoardBean d Order By cast(d.viewCount as integer) DESC";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getMovieIdsByBoardBean() { // 得到討論區全部的MovieIds
		String hql = "Select d.movieBean.movieId From DiscussionBoardBean d";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public void addBoard(DiscussionBoardBean board) { // 新增一個討論版
		Session session = factory.getCurrentSession();
		ForumMovieBean mb = getMovieBeanByFKMovieId(board.getMovieId());
		board.setMovieBean(mb);
		session.save(board);
	}

	@Override
	public void deleteBoard(int boardId) {
//		Session session = factory.getCurrentSession();
//		DiscussionBoardBean boardeBean = session.get(DiscussionBoardBean.class, boardId);
//		if (boardeBean != null) {
//			boardeBean.setArticleBeans(null);
//		}
//		session.delete(boardeBean);
//		String hql1 = "From ArticleBean a Where a.discussionBoardBean.boardId = :bid";
//		String hql2 = "From CommentBean c Where c.articleBean.artId = :aid";
//		String hql3 = "From ThumbsUpBean t Where t.commentBean.commentId = :cid";
//		Session session = factory.getCurrentSession();
//		DiscussionBoardBean board = session.get(DiscussionBoardBean.class, boardId);
//		List<ArticleBean> articleList = session.createQuery(hql1).setParameter("bid", boardId).getResultList();
//		ArrayList<Object> commentLists = new ArrayList<>(articleList.size());
//		for (ArticleBean articleBean : articleList) {
//			System.out.println("開始articleList迴圈");
//			List<CommentBean> commentBeans = session.createQuery(hql2).setParameter("aid", articleBean.getArtId()).getResultList();
//			System.out.println("結束articleList一次迴圈");
//			
//		}
//		ArrayList<ThumbsUpBean> thumbsUpBeans = null;
//		for (CommentBean commentBean : commentLists) {
//				System.out.println("開始commentLists迴圈");
//				thumbsUpBeans.add((ThumbsUpBean) session.createQuery(hql3).setParameter("cid", commentBean.getCommentId()).getSingleResult());
//				System.out.println("結束commentLists一次迴圈");
//		}
//		for (ThumbsUpBean thumbsUpBean : thumbsUpBeans) {
//			if(thumbsUpBean != null) {
//				session.delete(thumbsUpBean);
//				System.out.println("刪除tumbsUp");
//			}
//		}
//		for(CommentBean commentBean : commentLists ) {
//				if(commentBean != null) {
//					commentBean.setArticleBean(null);
//					System.out.println("setArticleBean為空");
//					commentBean.setBoardBean(null);
//					System.out.println("setBoardBean為空");
//					commentBean.setMemberBean(null);
//					System.out.println("setMemberBean為空");
//					session.delete(commentBean);
//					System.out.println("刪除commentBean");
//				
//			}
//		}
//		for (ArticleBean articleBean : articleList) {
//			if (articleBean != null) {
//				articleBean.setArtTypeBean(null);
//				System.out.println("setArtTypeBean為空");
//				articleBean.setDiscussionBoardBean(null);
//				System.out.println("setDiscussionBoardBean為空");
//				articleBean.setMemberBean(null);
//				System.out.println("setMemberBean為空");
//				session.delete(articleBean);
//				System.out.println("刪除articleBean");
//			}
//		}
//		board.setArticleBeans(null);
//		session.delete(board);
	}
	


}
