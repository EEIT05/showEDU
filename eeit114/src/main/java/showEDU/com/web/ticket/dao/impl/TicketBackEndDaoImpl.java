package showEDU.com.web.ticket.dao.impl;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
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
import showEDU.com.web.ticket.model.MovieShowtimeBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.model.MovieTicketBean;
import showEDU.com.web.ticket.model.SeatsBean;

@Repository
public class TicketBackEndDaoImpl implements TicketBackEndDao {

	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext ctx;

	// 依照上映狀態尋找取出電影Bean
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieBean> getAllMovies() {
		String hql = "FROM MovieBean Where movieStatusId = 1";
		Session session = factory.getCurrentSession();
		List<MovieBean> beans = session.createQuery(hql).getResultList();
		return beans;
	}

	// 新增電影
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

	// 依照電影級別ID取得電影級別BEAN
	@Override
	public MovieLevelBean getMovieLevelById(Integer movieLevelId) {
		MovieLevelBean mlb = null;
		Session session = factory.getCurrentSession();
		mlb = session.get(MovieLevelBean.class, movieLevelId);
		return mlb;
	}

	// 取得電影級別Bean
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieLevelBean> getMovieLevelList() {
		String hql = "FROM MovieLevelBean";
		Session session = factory.getCurrentSession();
		List<MovieLevelBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	// 取得電影狀態BEAN
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieStatusBean> getMovieStatusList() {
		String hql = "FROM MovieStatusBean";
		Session session = factory.getCurrentSession();
		List<MovieStatusBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	// 依照電影狀態ID取得電影狀態BEAN
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

	// 依照電影ID取得電影BEAN
	@Override
	public MovieBean getMovieById(Integer movieId) {
		MovieBean mb = null;
		String hql = "FROM MovieBean WHERE movieId = :id";
		Session session = factory.getCurrentSession();
		try {
			mb = (MovieBean) session.createQuery(hql).setParameter("id", movieId).getSingleResult();
		} catch (NoResultException e) {
			;
		} catch (Exception e) {
			;
		}
		return mb;
	}

	// 依照電影ID取得電影時刻BEAN
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShowtimeBean> getMovieShowTimeById(Integer movieId) {
		List<MovieShowtimeBean> mstb = null;
		String hql = "FROM MovieShowtimeBean mstb WHERE movieId = :id";
		Session session = factory.getCurrentSession();
		try {
			System.out.println("===============================");
			mstb = session.createQuery(hql).setParameter("id", movieId).getResultList();
		} catch (NoResultException e) {
			System.out.println("找不到資料");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到資料");
		}
		return mstb;
	}

	// 依照電影ID取得電影時刻日期
	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getMovieShowTimeDateByMovieID(Integer movieid) {
		List<Date> mstb = null;
		String hql = "SELECT distinct date FROM MovieShowtimeBean WHERE movieId = :id";
		Session session = factory.getCurrentSession();
		try {
			mstb = session.createQuery(hql).setParameter("id", movieid).getResultList();
		} catch (NoResultException e) {
			System.out.println("找不到資料");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到資料");
		}
		return mstb;
	}

	// 依照電影ID取得電影時刻時間
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMovieShowTimeTimeByMovieID(Integer movieid) {
		List<String> mstb = null;
		String hql = "SELECT distinct time FROM MovieShowtimeBean WHERE movieId = :id";
		Session session = factory.getCurrentSession();
		try {
			mstb = session.createQuery(hql).setParameter("id", movieid).getResultList();
		} catch (NoResultException e) {
			System.out.println("找不到資料");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到資料");
		}
		return mstb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieTicketBean> getMovieTicketPrice() {
		List<MovieTicketBean> mtb = null;
		String hql = "FROM MovieTicketBean";
		Session session = factory.getCurrentSession();
		try {
			mtb = session.createQuery(hql).getResultList();
		} catch (NoResultException e) {
			System.out.println("找不到資料");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到資料");
		}

		return mtb;

	}

	// 取得電影ID取得電影BEAN給movieDeatil.jsp用
	@Override
	public MovieBean getMovieDetailById(Integer movieId) {
		Session session = factory.getCurrentSession();
		return session.get(MovieBean.class, movieId);
	}

	// 取得電影ID列表給圖片名稱用
	@SuppressWarnings("unchecked")
	@Override
	public Integer getMovieIdForImageName() {
		String hql = "Select m.movieId FROM MovieBean m";
		Session session = factory.getCurrentSession();
		List<Integer> list = session.createQuery(hql).getResultList();
		Integer newId = list.size() + 1; // 取得既有的陣列個數 + 1 = 新增的圖片排序號碼

		return newId;
	}

	// 依照上映中電影狀態取得電影BEAN
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieBean> getMovieList() {
		String hql = "FROM MovieBean Where movieStatusId = 1";
		Session session = factory.getCurrentSession();
		List<MovieBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	// 取得座位table
	@SuppressWarnings("unchecked")
	@Override
	public List<SeatsBean> getSeatsByOrderDetail(Integer movieId, Date date, String time) {
		//依使用者選擇的日期&時間取得movieshowtimeBean的 movieShowtimeId  & theaterId
		String hql0 = "SELECT m.movieShowtimeId FROM MovieShowtimeBean m "
				+ "WHERE m.movieBean.movieId = :movieId and m.date =:date and m.time = :time";
		Session session = factory.getCurrentSession();
		Integer Dtest = (Integer) session.createQuery(hql0)
				 .setParameter("movieId", movieId)
				 .setParameter("date", date)
				 .setParameter("time", time)
				 .getSingleResult();
//		for (Object[] objects : Dtest) {
//			System.out.println(objects);
//		}
		Integer msId = (Integer) session.createQuery(hql0)
								 .setParameter("movieId", movieId)
								 .setParameter("date", date)
								 .setParameter("time", time)
								 .getSingleResult();
		System.out.println(msId);
		String hql1 = "SELECT m.movieShowtimeId FROM MovieShowtimeBean m "
				+ "WHERE m.movieBean.movieId = :movieId and m.date =:date and m.time = :time";
		Integer tId = (Integer) session.createQuery(hql1)
				.setParameter("movieId", movieId)
				 .setParameter("date", date)
				 .setParameter("time", time)
				.getSingleResult();

		String hql = "SELECT m.seatsBean FROM MovieOrderDetailBean m WHERE "
				+ "m.movieBean.movieId = :movieId and "
				+ "m.movieShowtimeBean.movieShowtimeId = :msId and "
				+ "m.theaterBean.theaterId = :tId";
			List<SeatsBean> mb = session.createQuery(hql)
					.setParameter("movieId", movieId)
					.setParameter("msId", msId)
					.setParameter("tId", tId)
					.getResultList();
		
		System.out.println(mb.size());
		return mb;
	}
	
	//取得座位的排LIST
	@Override
	public List<String> getSeatsBeanlineLetters() {
		String hql = "SELECT distinct lineLetters FROM SeatsBean order by lineLetters";
		Session session = factory.getCurrentSession();
		
		List<String> Line = session.createQuery(hql).getResultList();
		System.out.println(Line);
		return Line;
	}
	//取得座位的列LIST
	@Override
	public List<String> getSeatsBeanlineLetters() {
		String hql = "SELECT distinct rowNumber FROM SeatsBean order by rowNumber";
		Session session = factory.getCurrentSession();
		
		List<String> row = session.createQuery(hql).getResultList();
		System.out.println(row);
		return row;
	}

	

	@Override
	public MovieBean getMovieTextOnly(Integer movieId) {
		return getMovieById(movieId);
	}

	@Override
	public List<MovieBeanWithImageData> getAllMoviesWithImageData() {
		List<MovieBean> listSource = getAllMovieBeanJson();
		List<MovieBeanWithImageData> listTarget = new ArrayList<>();
		for (MovieBean bean : listSource) {
			listTarget.add(addImageData(bean));
		}
		return listTarget;
	}

	@SuppressWarnings("unchecked")
	public List<MovieBean> getAllMovieBeanJson() {
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

	@Override
	public MovieTicketBean getTicketById(Integer movieTicketId) {
		MovieTicketBean mb = null;
		String hql = "FROM MovieTicketBean WHERE movieTicketId = :id";
		Session session = factory.getCurrentSession();
		try {
			mb = (MovieTicketBean) session.createQuery(hql).setParameter("id", movieTicketId).getSingleResult();
		} catch (NoResultException e) {
			;
		} catch (Exception e) {
			;
		}
		return mb;
	}

	// 範例
	@SuppressWarnings("unchecked")
	public List<MovieBean> getAllMoeBeanJson() {
		List<MovieBean> list = new ArrayList<>();
		String hql = "FROM MovieBean";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}
