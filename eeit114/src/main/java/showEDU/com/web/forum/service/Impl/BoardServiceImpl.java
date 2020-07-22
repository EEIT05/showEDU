package showEDU.com.web.forum.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import showEDU.com.web.forum.dao.BoardDao;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;
import showEDU.com.web.forum.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Transactional
	@Override
	public List<DiscussionBoardBean> getAllBoards() {
		return boardDao.getAllBoards();
	}
	@Transactional
	@Override
	public ForumMovieBean getMovieBeanByFKMovieId(int movieId) {
		return boardDao.getMovieBeanByFKMovieId(movieId);
	}
	
	@Transactional
	@Override
	public DiscussionBoardBean getBoardBeanByBoardId(int boardId) {
		return boardDao.getBoardBeanByBoardId(boardId);
	}
	
	@Transactional
	@Override
	public List<ForumMovieBean> getMovieBeanList() {
		return boardDao.getMovieBeanList();
	}
	@Transactional
	@Override
	public List<DiscussionBoardBean> getSortedBoards() {
		return boardDao.getSortedBoards();
	}
	@Transactional
	@Override
	public List<Integer> getMovieIdsByBoardBean() {
		return boardDao.getMovieIdsByBoardBean();
	}
	@Transactional
	@Override
	public void addBoard(DiscussionBoardBean board) {
		boardDao.addBoard(board);
	}
	@Transactional
	@Override
	public void deleteBoard(int boardId) {
		boardDao.deleteBoard(boardId);
	}
	
	
	/**
	 * 比對後移除重複的movieBean
	 */
	@Override
	public Map<Integer, String> getMovieMap(Map<Integer, String> movieMap,List<ForumMovieBean> movieBeanList, List<Integer> movieIdsByBoardBean) {
		Integer movieId = null;
		for (Integer boardMovieId : movieIdsByBoardBean) {
			for (int i = 0; i < movieBeanList.size(); i++) {
				movieId = movieBeanList.get(i).getMovieId();
				if (movieId == boardMovieId) {
					movieBeanList.remove(i);
				}
			}
		}
		for (ForumMovieBean movieBean : movieBeanList) {
			movieMap.put(movieBean.getMovieId(), movieBean.getName());
		}
		return movieMap;
	}
	/*
	 * 檢視Post傳入的值,並將viewCount欄位設為0
	 */
	@Override
	public DiscussionBoardBean setviewCount(DiscussionBoardBean db, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		if (db.getViewCount() == null) {
			db.setViewCount("0");
		}
		return db;
	}
	
	
}
