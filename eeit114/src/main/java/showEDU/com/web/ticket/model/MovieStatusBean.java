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
@Table(name="MovieStatus")
public class MovieStatusBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieStatusId;
	String  	status;
	
	@OneToMany(mappedBy = "movieStatusBean")
	 Set<MovieBean> movie = new HashSet<>();
	

	public MovieStatusBean() {
	}

	public MovieStatusBean(Integer movieStatusId, String status) {
		super();
		this.movieStatusId = movieStatusId;
		this.status = status;
	}

	public Integer getMovieStatusId() {
		return movieStatusId;
	}

	public void setMovieStatusId(Integer movieStatusId) {
		this.movieStatusId = movieStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<MovieBean> getMovie() {
		return movie;
	}

	public void setMovie(Set<MovieBean> movie) {
		this.movie = movie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
