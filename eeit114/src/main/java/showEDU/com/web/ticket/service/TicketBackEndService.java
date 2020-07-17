package showEDU.com.web.ticket.service;

import java.util.List;

import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieStatusBean;

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

}