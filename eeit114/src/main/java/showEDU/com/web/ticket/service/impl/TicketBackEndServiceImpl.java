package showEDU.com.web.ticket.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.ticket.dao.TicketBackEndDao;
import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieShowtimeBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.model.MovieTicketBean;
import showEDU.com.web.ticket.model.SeatsBean;
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

	@Transactional
	@Override
	public List<MovieShowtimeBean> getMovieShowTimeById(Integer movieId) {
		return ticketBackEndDao.getMovieShowTimeById(movieId);
	}

	@Transactional
	@Override
	public List<Date> getMovieShowTimeDateByMovieID(Integer movieid) {
		return ticketBackEndDao.getMovieShowTimeDateByMovieID(movieid);
	}
	@Transactional
	@Override
	public List<String> getMovieShowTimeTimeByMovieID(Integer movieid) {
		return ticketBackEndDao.getMovieShowTimeTimeByMovieID(movieid);
	}

	@Transactional
	@Override
	public List<MovieTicketBean> getMovieTicketPrice() {
		return ticketBackEndDao.getMovieTicketPrice();
	}

	@Transactional
	@Override
	public MovieTicketBean getTicketById(Integer movieTicketId) {
		return ticketBackEndDao.getTicketById(movieTicketId);
	}


	@Transactional
	@Override
	public List<Integer> getSeatsByOrderDetail(Integer movieId, Date date, String time) {
		return ticketBackEndDao.getSeatsByOrderDetail( movieId,  date,  time);
	}

	@Transactional
	@Override
	public List<Integer> getSeatsBeanrowNumber() {
		return ticketBackEndDao.getSeatsBeanrowNumber();
	}

	@Transactional
	@Override
	public List<String> getSeatsBeanlineLetters() {
		return ticketBackEndDao.getSeatsBeanlineLetters();
	}

	@Transactional
	@Override
	public Integer getRemainingSeatsByUserSelected(Integer mm, Date dd, String time) {
		return ticketBackEndDao.getRemainingSeatsByUserSelected( mm,  dd,  time);
	}


}
