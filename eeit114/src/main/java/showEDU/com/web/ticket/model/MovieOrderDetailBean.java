package showEDU.com.web.ticket.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="MovieOrderDetail")
public class MovieOrderDetailBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieordersDetailId;
	
	@ManyToOne
	@JoinColumn(name="movieOrdersId")
	MovieOrdersBean  movieOrdersBean;
	
	@ManyToOne
	@JoinColumn(name="movieId")
	MovieBean  movieBean;
	
	@ManyToOne
	@JoinColumn(name="movieShowtimeId")
	MovieShowtimeBean  movieShowtimeBean;
	
	@ManyToOne
	@JoinColumn(name="theaterId")
	TheaterBean  theaterBean;
	
	@ManyToOne
	@JoinColumn(name="seatsId")
	SeatsBean  seatsBean;
	
	@ManyToOne
	@JoinColumn(name="movieTicketId")
	MovieTicketBean  movieTicketBean;
	
	String  	ticketStatus;
	

	public MovieOrderDetailBean() {
	}

	public MovieOrderDetailBean(Integer movieordersDetailId, MovieOrdersBean movieOrdersBean, MovieBean movieBean,
			MovieShowtimeBean movieShowtimeBean, TheaterBean theaterBean, SeatsBean seatsBean,
			MovieTicketBean movieTicketBean, String ticketStatus) {
		super();
		this.movieordersDetailId = movieordersDetailId;
		this.movieOrdersBean = movieOrdersBean;
		this.movieBean = movieBean;
		this.movieShowtimeBean = movieShowtimeBean;
		this.theaterBean = theaterBean;
		this.seatsBean = seatsBean;
		this.movieTicketBean = movieTicketBean;
		this.ticketStatus = ticketStatus;
	}

	public Integer getMovieordersDetailId() {
		return movieordersDetailId;
	}

	public void setMovieordersDetailId(Integer movieordersDetailId) {
		this.movieordersDetailId = movieordersDetailId;
	}

	public MovieOrdersBean getMovieOrdersBean() {
		return movieOrdersBean;
	}

	public void setMovieOrdersBean(MovieOrdersBean movieOrdersBean) {
		this.movieOrdersBean = movieOrdersBean;
	}

	public MovieBean getMovieBean() {
		return movieBean;
	}

	public void setMovieBean(MovieBean movieBean) {
		this.movieBean = movieBean;
	}

	public MovieShowtimeBean getMovieShowtimeBean() {
		return movieShowtimeBean;
	}

	public void setMovieShowtimeBean(MovieShowtimeBean movieShowtimeBean) {
		this.movieShowtimeBean = movieShowtimeBean;
	}

	public TheaterBean getTheaterBean() {
		return theaterBean;
	}

	public void setTheaterBean(TheaterBean theaterBean) {
		this.theaterBean = theaterBean;
	}

	public SeatsBean getSeatsBean() {
		return seatsBean;
	}

	public void setSeatsBean(SeatsBean seatsBean) {
		this.seatsBean = seatsBean;
	}

	public MovieTicketBean getMovieTicketBean() {
		return movieTicketBean;
	}

	public void setMovieTicketBean(MovieTicketBean movieTicketBean) {
		this.movieTicketBean = movieTicketBean;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
