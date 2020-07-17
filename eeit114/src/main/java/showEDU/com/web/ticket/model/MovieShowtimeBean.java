package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="MovieShowtime")
public class MovieShowtimeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieShowtimeId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="movieId")
	MovieBean  movieBean;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="theaterId")
	TheaterBean  theaterBean;
	
	Date date;
	String time;
	String seatsStatus;
	Integer remainingSeats;

	@OneToMany(mappedBy = "movieShowtimeBean")
	Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();
	
	
	
	
	
	

	public MovieShowtimeBean() {
	}

	public MovieShowtimeBean(Integer movieShowtimeId, MovieBean movieBean, TheaterBean theaterBean, Date date,
			String time, String seatsStatus, Integer remainingSeats, Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.movieShowtimeId = movieShowtimeId;
		this.movieBean = movieBean;
		this.theaterBean = theaterBean;
		this.date = date;
		this.time = time;
		this.seatsStatus = seatsStatus;
		this.remainingSeats = remainingSeats;
		this.movieOrderDetail = movieOrderDetail;
	}

	public Integer getMovieShowtimeId() {
		return movieShowtimeId;
	}

	public void setMovieShowtimeId(Integer movieShowtimeId) {
		this.movieShowtimeId = movieShowtimeId;
	}

	public MovieBean getMovieBean() {
		return movieBean;
	}

	public void setMovieBean(MovieBean movieBean) {
		this.movieBean = movieBean;
	}

	public TheaterBean getTheaterBean() {
		return theaterBean;
	}

	public void setTheaterBean(TheaterBean theaterBean) {
		this.theaterBean = theaterBean;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSeatsStatus() {
		return seatsStatus;
	}

	public void setSeatsStatus(String seatsStatus) {
		this.seatsStatus = seatsStatus;
	}

	public Integer getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(Integer remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	public Set<MovieOrderDetailBean> getMovieOrderDetail() {
		return movieOrderDetail;
	}

	public void setMovieOrderDetail(Set<MovieOrderDetailBean> movieOrderDetail) {
		this.movieOrderDetail = movieOrderDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
