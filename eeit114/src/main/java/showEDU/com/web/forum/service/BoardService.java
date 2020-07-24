package showEDU.com.web.forum.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;

public interface BoardService {
	
	List<DiscussionBoardBean> getAllBoards();
	
	List<DiscussionBoardBean> getSortedBoards();
	
	ForumMovieBean getMovieBeanByFKMovieId(int movieId);
	
	
	DiscussionBoardBean getBoardBeanByBoardId(int boardId);

	
	List<ForumMovieBean> getMovieBeanList();
	
	List<Integer> getMovieIdsByBoardBean();

	void addBoard(DiscussionBoardBean board);
	
	void deleteBoard(int boardId);
	
	// ------------分頁---------------
	
	public int getTotalPages();
		
	public List<DiscussionBoardBean> getPageBoards(int pageNo);

	
	//---------------------------------(以下為重構)------------------------------------------------

	Map<Integer, String> getMovieMap(Map<Integer, String> movieMap ,List<ForumMovieBean> movieBeanList,List<Integer> movieIdsByBoardBean);
	
	
	DiscussionBoardBean setviewCount(DiscussionBoardBean db, BindingResult result);
}
