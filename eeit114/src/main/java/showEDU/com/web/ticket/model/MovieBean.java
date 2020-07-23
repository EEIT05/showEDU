package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="Movie")
public class MovieBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieId;
	String  	chName;
	String  	enName;
	@JsonIgnore
	Blob  		image;
	String		imageName;
	@JsonIgnore
	@Transient
	private MultipartFile	movieImage;  	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="movieLevelId")
	MovieLevelBean  movieLevelBean;
	@JsonIgnore
	@Transient
	Integer		movieLevelId;
	
	Integer  	length;
	String    	director;
	String  	actors;
	String  	trailer;
	@Column(length = 1000)
	String  	synopsis;
//	@DateTimeFormat(pattern="yyyy/MM/dd")  //開啟會讓Date資料無法新增至資料庫  待測試
	Date  		premierDate;
//	@DateTimeFormat(pattern="yyyy/MM/dd")  //開啟會讓Date資料無法新增至資料庫  待測試
	Date  		offDate;
	@JsonIgnore //
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="movieStatusId")
	MovieStatusBean  movieStatusBean;
	@JsonIgnore
	@Transient
	Integer		movieStatusId;
	
//	@JsonIgnore //
	@OneToMany(mappedBy = "movieBean")
	 Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();

	public MovieBean() {
	}

	public MovieBean(Integer movieId, String chName, String enName, Blob image, String imageName,
			MultipartFile movieImage, MovieLevelBean movieLevelBean, Integer movieLevelId, Integer length,
			String director, String actors, String trailer, String synopsis, Date premierDate, Date offDate,
			MovieStatusBean movieStatusBean, Integer movieStatusId, Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.movieId = movieId;
		this.chName = chName;
		this.enName = enName;
		this.image = image;
		this.imageName = imageName;
		this.movieImage = movieImage;
		this.movieLevelBean = movieLevelBean;
		this.movieLevelId = movieLevelId;
		this.length = length;
		this.director = director;
		this.actors = actors;
		this.trailer = trailer;
		this.synopsis = synopsis;
		this.premierDate = premierDate;
		this.offDate = offDate;
		this.movieStatusBean = movieStatusBean;
		this.movieStatusId = movieStatusId;
		this.movieOrderDetail = movieOrderDetail;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
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

	public MultipartFile getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(MultipartFile movieImage) {
		this.movieImage = movieImage;
	}

	public MovieLevelBean getMovieLevelBean() {
		return movieLevelBean;
	}

	public void setMovieLevelBean(MovieLevelBean movieLevelBean) {
		this.movieLevelBean = movieLevelBean;
	}

	public Integer getMovieLevelId() {
		return movieLevelId;
	}

	public void setMovieLevelId(Integer movieLevelId) {
		this.movieLevelId = movieLevelId;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Date getPremierDate() {
		return premierDate;
	}

	public void setPremierDate(Date premierDate) {
		this.premierDate = premierDate;
	}

	public Date getOffDate() {
		return offDate;
	}

	public void setOffDate(Date offDate) {
		this.offDate = offDate;
	}

	public MovieStatusBean getMovieStatusBean() {
		return movieStatusBean;
	}

	public void setMovieStatusBean(MovieStatusBean movieStatusBean) {
		this.movieStatusBean = movieStatusBean;
	}

	public Integer getMovieStatusId() {
		return movieStatusId;
	}

	public void setMovieStatusId(Integer movieStatusId) {
		this.movieStatusId = movieStatusId;
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
