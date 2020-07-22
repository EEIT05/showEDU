package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany(mappedBy = "theaterBean",fetch=FetchType.EAGER)
	 Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();
	

	public TheaterBean() {
	}

	public TheaterBean(Integer theaterId, String name, Integer totalSeats, Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.theaterId = theaterId;
		this.name = name;
		this.totalSeats = totalSeats;
		this.movieOrderDetail = movieOrderDetail;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
