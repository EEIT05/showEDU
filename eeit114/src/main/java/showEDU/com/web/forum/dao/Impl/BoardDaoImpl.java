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
	Integer recordsPerPage = 3;

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
	
	
	@Override
	public DiscussionBoardBean getBoardBeanByBoardId(int boardId) { // 由boardId得到對應的BoardBean
		Session session = factory.getCurrentSession();
		return session.get(DiscussionBoardBean.class, boardId);
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
		ForumMovieBean movieBean = session.get(ForumMovieBean.class, board.getMovieId());
		board.setMovieBean(movieBean);
		session.save(board);
	}

	@Override
	public void deleteBoard(int boardId) {
		String hql = "Delete DiscussionBoardBean dcb Where dcb.boardId = :dcbid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("dcbid", boardId).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussionBoardBean> getPageBoardsFrom(Integer pageNo){
		int startRecordNo = 0;
		startRecordNo = (pageNo - 1) * recordsPerPage;
		String hql = "From DiscussionBoardBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setFirstResult(startRecordNo).setMaxResults(recordsPerPage).getResultList();
	}

	// 得到分頁總數量
		@Override
		public int getTotalPages() {
			int totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
			return totalPages;
		}
		
		@SuppressWarnings("unchecked")
		public long getRecordCounts() {
			long count = 0; // 必須使用 long 型態
			String hql = "SELECT count(*) FROM DiscussionBoardBean"; // 共幾筆資料
			Session session = factory.getCurrentSession();
			List<Long> list = session.createQuery(hql).getResultList();
	        if (list.size() > 0) {
	            count = list.get(0);
	        }
	        return count; 
		}

		@Override
		public List<DiscussionBoardBean> getPageBoards(int pageNo) {
			List<DiscussionBoardBean> pageBoards = getPageBoardsFrom(pageNo);
			return pageBoards;
		}

	

	
	


}
