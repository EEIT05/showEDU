package showEDU.com.web.ticket.dao;

import java.util.List;

import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieStatusBean;

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





}