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
@Table(name="MovieTicket")
public class MovieTicketBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieTicketId;
	
	@OneToMany(mappedBy = "movieTicketBean")
	 Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();
	
	

	public MovieTicketBean() {
	}

	public MovieTicketBean(Integer movieTicketId, Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.movieTicketId = movieTicketId;
		this.movieOrderDetail = movieOrderDetail;
	}

	public Integer getMovieTicketId() {
		return movieTicketId;
	}

	public void setMovieTicketId(Integer movieTicketId) {
		this.movieTicketId = movieTicketId;
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
