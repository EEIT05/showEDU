package showEDU.com.web.ticket.service;

import java.sql.Date;
import java.util.List;

import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieShowtimeBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.model.MovieTicketBean;
import showEDU.com.web.ticket.model.SeatsBean;

public interface TicketBackEndService {

	List<MovieBean> getAllMovies();

	void addMovie(MovieBean movie);

	List<MovieLevelBean> getMovieLevelList();

	List<MovieStatusBean> getMovieStatusList();

	MovieLevelBean getMovieLevelById(Integer movieLevelId);

	MovieStatusBean getMovieStatusById(Integer movieStatusId);

	public MovieBean getMovieById(Integer id);
	
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


	List<Integer> getSeatsByOrderDetail(Integer movieId, Date date, String time);
	
	List<Integer> getSeatsBeanrowNumber();

	List<String> getSeatsBeanlineLetters();
	
	Integer getRemainingSeatsByUserSelected(Integer mm, Date dd, String time);

}