package showEDU.com.web.ticket.model;

import java.io.Serializable;
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
@Table(name="Seats")
public class SeatsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	seatsId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="theaterId")
	TheaterBean  theaterBean;
	
	String  	lineLetters;
	Integer  	rowNumber;
	
	
	@OneToMany(mappedBy = "seatsBean")
	 Set<MovieOrderDetailBean>movieOrderDetail = new HashSet<>();
	

	public SeatsBean() {
	}

	public SeatsBean(Integer seatsId, TheaterBean theaterBean, String lineLetters, Integer rowNumber,
			Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.seatsId = seatsId;
		this.theaterBean = theaterBean;
		this.lineLetters = lineLetters;
		this.rowNumber = rowNumber;
		this.movieOrderDetail = movieOrderDetail;
	}

	public Integer getSeatsId() {
		return seatsId;
	}

	public void setSeatsId(Integer seatsId) {
		this.seatsId = seatsId;
	}

	public TheaterBean getTheaterBean() {
		return theaterBean;
	}

	public void setTheaterBean(TheaterBean theaterBean) {
		this.theaterBean = theaterBean;
	}

	public String getLineLetters() {
		return lineLetters;
	}

	public void setLineLetters(String lineLetters) {
		this.lineLetters = lineLetters;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
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
