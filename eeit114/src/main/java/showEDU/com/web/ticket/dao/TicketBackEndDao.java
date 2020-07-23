package showEDU.com.web.ticket.dao;

import java.sql.Date;
import java.util.List;

import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieShowtimeBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.model.MovieTicketBean;
import showEDU.com.web.ticket.model.SeatsBean;

public interface TicketBackEndDao {

	List<MovieBean> getAllMovies();

	void addMovie(MovieBean movie);
	MovieLevelBean getMovieLevelById(Integer movieLevelId);
	List<MovieLevelBean> getMovieLevelList();
	MovieStatusBean getMovieStatusById(Integer movieStatusId);
	List<MovieStatusBean> getMovieStatusList();


	MovieBean getMovieById(Integer movieId);

	Integer getMovieIdForImageName();

	public MovieBean getMovieDetailById(Integer movieId);

	List<MovieBean> getMovieList();

	MovieBean getMovieTextOnly(Integer movieId);

	List<MovieBeanWithImageData> getAllMoviesWithImageData();

	List<MovieShowtimeBean> getMovieShowTimeById(Integer movieId);

	List<Date> getMovieShowTimeDateByMovieID(Integer movieid);

	List<String> getMovieShowTimeTimeByMovieID(Integer movieid);

	List<MovieTicketBean> getMovieTicketPrice();

	MovieTicketBean getTicketById(Integer movieTicketId);

	List<SeatsBean> getSeatsByOrderDetail(Integer movieId, Date date, String time);





}