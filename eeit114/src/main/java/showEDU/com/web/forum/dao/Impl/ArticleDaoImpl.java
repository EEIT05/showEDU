package showEDU.com.web.forum.dao.Impl;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.forum.dao.ArticleDao;
import showEDU.com.web.forum.model.ArtTypeBean;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.ArticleBeanWithImageData;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.member.model.MemberBean;


@Repository
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext ctx;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleBean> getAllArticle() { // 得到所有文章
		String hql = "From ArticleBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleBean> getArticlesByBoardId(int boardId) { // 傳入討論版ID的值   找到對應討論版ID內所有的文章
		String hql = "From ArticleBean a Where a.discussionBoardBean.boardId = :bid"; 
		Session session = factory.getCurrentSession();
		List<ArticleBean> articleBeans = null;
		try {
			articleBeans = session.createQuery(hql).setParameter("bid", boardId).getResultList(); 
		} catch (NoResultException e) {
			if (articleBeans == null) {
				;
			}
		}
		return  articleBeans;
	}

	@Override
	public Integer getMovieIdByBoardId(int boardId) { // 傳入討論版ID的值  找到對應的MovieId
		String hql = "Select d.movieBean.movieId From DiscussionBoardBean d Where d.boardId = :bid";
		Session session = factory.getCurrentSession();
		return (Integer) session.createQuery(hql).setParameter("bid", boardId).getSingleResult();
	}

	@Override
	public ArtTypeBean getArtTypeByTypeId(int typeId) { // 傳入類型ID回傳對應的類型
		String hql = "From ArtTypeBean at Where at.typeId = :atid";
		Session session = factory.getCurrentSession();
		return (ArtTypeBean) session.createQuery(hql).setParameter("atid", typeId).getSingleResult();
	}

	@Override
	public ArticleBean getArticleByArtId(int artId) { // 傳入文章ID回傳對應的文章
		String hql = "From ArticleBean a Where a.artId = :aid";
		Session session = factory.getCurrentSession();
		return (ArticleBean) session.createQuery(hql).setParameter("aid", artId).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleBean> getArticleBeansByTypeId(int typeId) { // 傳入類型Id回傳對應的全部文章
		String hql = "From ArticleBean a Where a.artTypeBean.typeId = :atid";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("atid", typeId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArtTypeBean> getAllArtTypeBean() {
		String hql = "From ArtTypeBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	
	@Override
	public List<ArticleBeanWithImageData> getArtBeansImageDataByTypeId(int typeId) { 
		List<ArticleBean> articleBeans = getArticleBeansByTypeId(typeId);
		List<ArticleBeanWithImageData> listTarget = new ArrayList<>();
		for (ArticleBean bean : articleBeans) {
			listTarget.add(addImageData(bean));
		}
		return listTarget;
	}
	
	
	@Override
	public void addArticle(ArticleBean articleBean, int boardId, int memberId) {
		Session session = factory.getCurrentSession();
		DiscussionBoardBean boardBean = session.get(DiscussionBoardBean.class, boardId);
		MemberBean memberBean = session.get(MemberBean.class, memberId);
		ArtTypeBean artTypeBean = getArtTypeByTypeId(articleBean.getTypeId());
		articleBean.setDiscussionBoardBean(boardBean);
		articleBean.setMemberBean(memberBean);
		articleBean.setArtTypeBean(artTypeBean);
		session.save(articleBean);
	}
	
	@Override
	public void deleteArticle(int artId) {
		String hql = "Delete ArticleBean a Where a.artId = :aid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("aid", artId).executeUpdate();
	}
	
	
	
	
	
	
	
	
	
	
	
	public ArticleBeanWithImageData addImageData(ArticleBean bean) {
		ArticleBeanWithImageData abwid = null;
		try {
			StringBuffer sb = new StringBuffer();
			String fileNameString = bean.getArtTypeBean().getFilename();
			String mimeType = ctx.getMimeType(fileNameString);
			Blob blob = bean.getArtTypeBean().getImage();
			byte[] bytes = blob.getBytes(1, (int)blob.length());
			sb.append("data:");
			sb.append(mimeType);
			sb.append(";base64");
			Base64.Encoder be = Base64.getEncoder();
			String str = new String(be.encode(bytes));
			sb.append(str);
			String iamgeData = sb.toString();
			abwid = new ArticleBeanWithImageData(bean, iamgeData);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("存取ArtTypeImage時錯誤!!!");
		}
		return abwid;
	}

	

	

	

	


	



}
