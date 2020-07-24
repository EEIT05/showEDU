package showEDU.com.web.forum.dao;

import java.util.List;

import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;

public interface BoardDao {
	List<DiscussionBoardBean> getAllBoards(); 
	
	List<DiscussionBoardBean> getSortedBoards();
	
	ForumMovieBean getMovieBeanByFKMovieId(int movieId);
	
	DiscussionBoardBean getBoardBeanByBoardId(int boardId);
	
	List<ForumMovieBean> getMovieBeanList(); 
	
	List<Integer> getMovieIdsByBoardBean();
	
	void addBoard(DiscussionBoardBean board);
	
	void deleteBoard(int boardId);
	
	// ------------分頁-------------
	public List<DiscussionBoardBean> getPageBoardsFrom(Integer pageNo);
	
	public int getTotalPages();
		
	public List<DiscussionBoardBean> getPageBoards(int pageNo);
}
