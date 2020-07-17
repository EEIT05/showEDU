package showEDU.com.web.ticket.dao.impl;

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

import showEDU.com.web.ticket.dao.TicketBackEndDao;
import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
@Repository
public class TicketBackEndDaoImpl implements TicketBackEndDao {
	
	@Autowired
	SessionFactory  factory;
	
	@Autowired
	ServletContext ctx;


	@SuppressWarnings("unchecked")
	@Override
	public List<MovieBean> getAllMovies() {
		String hql = "FROM MovieBean Where movieStatusId = 1";
		Session session = factory.getCurrentSession();
		List<MovieBean> beans = session.createQuery(hql).getResultList();
		return beans;
	}
	
	@Override
	public void addMovie(MovieBean movie) {
	    Session session = factory.getCurrentSession();
	    MovieLevelBean mlb = getMovieLevelById(movie.getMovieLevelId());
	    movie.setMovieLevelBean(mlb);
	    MovieStatusBean msb = getMovieStatusById(movie.getMovieStatusId());
	    movie.setMovieStatusBean(msb);
//	    String d = movie.getOffDate().toString();
//	    System.out.println(d);
	    
	    session.save(movie);
	}
	
	@Override
	public MovieLevelBean getMovieLevelById(Integer movieLevelId) {
		MovieLevelBean mlb = null;
	    Session session = factory.getCurrentSession();
	    mlb = session.get(MovieLevelBean.class, movieLevelId);
	    return mlb;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieLevelBean> getMovieLevelList() {
	    String hql = "FROM MovieLevelBean";
	    Session session = factory.getCurrentSession();
	    List<MovieLevelBean> list = session.createQuery(hql).getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieStatusBean> getMovieStatusList() {
		String hql = "FROM MovieStatusBean";
    Session session = factory.getCurrentSession();
    List<MovieStatusBean> list = session.createQuery(hql).getResultList();
    return list;
	}

	@Override
	public MovieStatusBean getMovieStatusById(Integer movieStatusId) {
		MovieStatusBean msb = null;
	    Session session = factory.getCurrentSession();
	    msb = session.get(MovieStatusBean.class, movieStatusId);
	    return msb;
	}

//	@Override  //非Ajax版
//	public MovieBean getMovieById(Integer movieId) {
//		Session session = factory.getCurrentSession();
//		MovieBean mb = session.get(MovieBean.class, movieId);
//		if (mb == null) {
//			throw new MovieNotFoundException("電影	：" + movieId + "找不到");
//		}else {
//		return mb;
//		}
//	}
	@Override
	public MovieBean getMovieById(Integer movieId)  {
		MovieBean mb = null;
		String hql = "FROM MovieBean WHERE movieId = :id";
		Session session = factory.getCurrentSession(); 
		try {
			mb = (MovieBean) session.createQuery(hql)
								   .setParameter("id", movieId)
								   .getSingleResult();
		} catch(NoResultException e) {
			;
		} catch(Exception e) {
			;
		}		
		return mb;
	}
	

	
	@Override
	public MovieBean getMovieDetailById(Integer movieId) {
		
		
		Session session = factory.getCurrentSession();
		return session.get(MovieBean.class, movieId);
	}
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MovieBean> getMovieDetailById(Integer movieId) {
//		String hql = "FROM MovieBean mb WHERE mb.movieId = :movie";
//		Session session = factory.getCurrentSession();
//		return session.createQuery(hql)
//				.setParameter("movie", movieId)
//				.getResultList();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMovieIdForImageName() {
		String hql = "Select m.movieId FROM MovieBean m";
	    Session session = factory.getCurrentSession();
	    List<Integer> list = session.createQuery(hql).getResultList();
	    Integer newId = list.size() + 1;   //取得既有的陣列個數 + 1 = 新增的圖片排序號碼

	    return newId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieBean> getMovieList() {
		String hql = "FROM MovieBean Where movieStatusId = 1";
    Session session = factory.getCurrentSession();
    List<MovieBean> list = session.createQuery(hql).getResultList();
    return list;
	}

	@Override
	public MovieBean getMovieTextOnly(Integer movieId) {
		return getMovieById(movieId);
	}
//===========================Ajax========================================
	@Override
	public List<MovieBeanWithImageData> getAllMoviesWithImageData() {
		List<MovieBean> listSource = getAllMovieBeanJson();
		List<MovieBeanWithImageData> listTarget = new ArrayList<>();
		for(MovieBean bean : listSource) {
			listTarget.add(addImageData(bean));
		}
		return listTarget;
	}
	@SuppressWarnings("unchecked")
	public List<MovieBean> getAllMovieBeanJson()  {
		List<MovieBean> list = new ArrayList<>();
		String hql = "FROM MovieBean";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	public MovieBeanWithImageData addImageData(MovieBean mbean) {
		MovieBeanWithImageData mbwid = null;
		try {
			StringBuffer sb = new StringBuffer();
			String fileName = mbean.getImageName();
			String mimeType = ctx.getMimeType(fileName);
			Blob blob = mbean.getImage();
			byte[] bytes = blob.getBytes(1, (int) blob.length());
			String prefix = "data:" + mimeType + ";base64,";
			sb.append(prefix);
			Base64.Encoder be = Base64.getEncoder();
			String str = new String(be.encode(bytes));
			sb.append(str);
			String imageData = sb.toString();
			mbwid = new MovieBeanWithImageData(mbean, imageData);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return mbwid;		
	}
	

}
