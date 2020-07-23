package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="Theater")
public class TheaterBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	theaterId;
	String  	name;
	Integer  	totalSeats;
	
	@OneToMany(mappedBy = "theaterBean")
	 Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();
	
	@OneToMany(mappedBy = "theaterBean")
	 Set<MovieShowtimeBean> movieShowtime = new HashSet<>();
	
	@OneToMany(mappedBy = "theaterBean")
	 Set<SeatsBean> Seats = new HashSet<>();

	public TheaterBean() {
	}

	public TheaterBean(Integer theaterId, String name, Integer totalSeats, Set<MovieOrderDetailBean> movieOrderDetail,
			Set<MovieShowtimeBean> movieShowtime, Set<SeatsBean> seats) {
		super();
		this.theaterId = theaterId;
		this.name = name;
		this.totalSeats = totalSeats;
		this.movieOrderDetail = movieOrderDetail;
		this.movieShowtime = movieShowtime;
		Seats = seats;
	}

	public Integer getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Set<MovieOrderDetailBean> getMovieOrderDetail() {
		return movieOrderDetail;
	}

	public void setMovieOrderDetail(Set<MovieOrderDetailBean> movieOrderDetail) {
		this.movieOrderDetail = movieOrderDetail;
	}

	public Set<MovieShowtimeBean> getMovieShowtime() {
		return movieShowtime;
	}

	public void setMovieShowtime(Set<MovieShowtimeBean> movieShowtime) {
		this.movieShowtime = movieShowtime;
	}

	public Set<SeatsBean> getSeats() {
		return Seats;
	}

	public void setSeats(Set<SeatsBean> seats) {
		Seats = seats;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
