package showEDU.com.web.forum.dao;

import java.util.List;

import showEDU.com.web.forum.model.ArtTypeBean;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.ArticleBeanWithImageData;
import showEDU.com.web.forum.model.DiscussionBoardBean;

public interface ArticleDao {
	List<ArticleBean> getAllArticle();
	
	List<ArticleBean> getArticlesByBoardId(int boardId);
	
	Integer getMovieIdByBoardId(int boardId);
	
	ArtTypeBean getArtTypeByTypeId(int typeId);
	
	ArticleBean getArticleByArtId(int artId);
	
	List<ArticleBean> getArticleBeansByTypeId(int typeId);
	
	List<ArtTypeBean> getAllArtTypeBean();
	
	public List<ArticleBeanWithImageData> getArtBeansImageDataByTypeId(int typeId);
	
	void addArticle(ArticleBean articleBean, int boardId, int memberId);
	
	void deleteArticle(int artId);
}
