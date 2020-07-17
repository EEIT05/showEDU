package showEDU.com.web.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.ticket.dao.TicketBackEndDao;
import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.service.TicketBackEndService;

@Service
public class TicketBackEndServiceImpl implements TicketBackEndService{

	@Autowired
	TicketBackEndDao ticketBackEndDao;

	@Transactional
	@Override
	public List<MovieBean> getAllMovies() {
		return ticketBackEndDao.getAllMovies();
		
	}

	@Transactional
	@Override
	public void addMovie(MovieBean movie) {
		ticketBackEndDao.addMovie(movie);
		
	}

	@Transactional
	@Override
	public List<MovieLevelBean> getMovieLevelList() {
		return ticketBackEndDao.getMovieLevelList();
	}

	@Transactional
	@Override
	public List<MovieStatusBean> getMovieStatusList() {
		return ticketBackEndDao.getMovieStatusList();
	}
	
	@Transactional
	@Override
	public MovieLevelBean getMovieLevelById(Integer movieLevelId) {
		return ticketBackEndDao.getMovieLevelById(movieLevelId);
	}
	
	@Transactional
	@Override
	public MovieStatusBean getMovieStatusById(Integer movieStatusId) {
		return ticketBackEndDao.getMovieStatusById(movieStatusId);
	}

	
	@Transactional
	@Override
	public MovieBean getMovieById(Integer id) {
		return ticketBackEndDao.getMovieById(id);
	}

	@Transactional
	@Override
	public Integer getMovieIdForImageName() {
		return ticketBackEndDao.getMovieIdForImageName();
	}

	@Transactional
	@Override
	public MovieBean getMovieDetailById(Integer movieId) {
		
		return ticketBackEndDao.getMovieDetailById(movieId);
	}

	@Transactional
	@Override
	public List<MovieBean> getMovieList() {
		return ticketBackEndDao.getMovieList();
	}

	@Transactional
	@Override
	public MovieBean getMovieTextOnly(Integer movieId) {
		return ticketBackEndDao.getMovieTextOnly(movieId);
	}

	@Transactional
	@Override
	public List<MovieBeanWithImageData> getAllMoviesWithImageData() {
		return ticketBackEndDao.getAllMoviesWithImageData();
	}


}
