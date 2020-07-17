package showEDU.com.web.forum.dao;

import java.util.List;

import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;

public interface BoardDao {
	List<DiscussionBoardBean> getAllBoards(); 
	
	List<DiscussionBoardBean> getSortedBoards();
	
	ForumMovieBean getMovieBeanByFKMovieId(int movieId);
	
	List<ForumMovieBean> getMovieBeanList(); 
	
	List<Integer> getMovieIdsByBoardBean();
	
	void addBoard(DiscussionBoardBean board);
	
	void deleteBoard(int boardId);
}
