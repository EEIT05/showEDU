package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="MovieTicket")
public class MovieTicketBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieTicketId;
	
	String		name;
	String		info;
	
	@JsonIgnore
	Blob  		image;
	String		imageName;
	Integer		price;
	
	@OneToMany(mappedBy = "movieTicketBean")
	 Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();
	
	

	public MovieTicketBean() {
	}

	public MovieTicketBean(Integer movieTicketId, String name, String info, Blob image, String imageName, Integer price,
			Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.movieTicketId = movieTicketId;
		this.name = name;
		this.info = info;
		this.image = image;
		this.imageName = imageName;
		this.price = price;
		this.movieOrderDetail = movieOrderDetail;
	}

	public Integer getMovieTicketId() {
		return movieTicketId;
	}

	public void setMovieTicketId(Integer movieTicketId) {
		this.movieTicketId = movieTicketId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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
