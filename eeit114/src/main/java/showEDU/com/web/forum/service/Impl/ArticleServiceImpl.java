package showEDU.com.web.forum.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.forum.dao.ArticleDao;
import showEDU.com.web.forum.model.ArtTypeBean;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.ArticleBeanWithImageData;
import showEDU.com.web.forum.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao articleDao;
	
	@Transactional
	@Override
	public List<ArticleBean> getAllArticle() {
		return articleDao.getAllArticle();
	}
	@Transactional
	@Override
	public List<ArticleBean> getSortedArticles(int boardId) {
		return articleDao.getSortedArticles(boardId);
	}
	@Transactional
	@Override
	public List<ArticleBean> getArticlesByBoardId(int boardId) {
		return articleDao.getArticlesByBoardId(boardId);
	}
	@Transactional
	@Override
	public Integer getMovieIdByBoardId(int boardId) {
		return articleDao.getMovieIdByBoardId(boardId);
	}
	@Transactional
	@Override
	public ArtTypeBean getArtTypeByTypeId(int typeId) {
		return articleDao.getArtTypeByTypeId(typeId);
	}
	@Transactional
	@Override
	public ArticleBean getArticleByArtId(int artId) {
		return articleDao.getArticleByArtId(artId);
	}
	@Transactional
	@Override
	public List<ArticleBean> getArticleBeansByTypeId(int typeId) {
		return articleDao.getArticleBeansByTypeId(typeId);
	}
	@Transactional
	@Override
	public List<ArtTypeBean> getAllArtTypeBean() {
		return articleDao.getAllArtTypeBean();
	}
	
	
	
	@Transactional
	@Override
	public List<ArticleBeanWithImageData> getArtBeansImageDataByTypeId(int typeId) {
		return articleDao.getArtBeansImageDataByTypeId(typeId);
	}
	@Transactional
	@Override
	public void addArticle(ArticleBean articleBean, int boardId, int memberId) {
		articleDao.addArticle(articleBean, boardId, memberId);
	}
	@Transactional
	@Override
	public void deleteArticle(int artId) {
		articleDao.deleteArticle(artId);
	}
	@Transactional
	@Override
	public List<ArticleBean> getArticlesByName(String name) {
		return articleDao.getArticlesByName(name);
	}
	
	
	
	
}
