package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.LinkedHashSet;
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
@Table(name="MovieLevel")
public class MovieLevelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer 	movieLevelId;
	String  	level;
	@JsonIgnore
	Blob  		levelPhoto;
	String		photoName;
	
	@OneToMany(mappedBy="movieLevelBean")
	Set<MovieBean> movie = new LinkedHashSet<>();
	
	
	public MovieLevelBean() {
	}
	
	public Integer getMovieLevelId() {
		return movieLevelId;
	}

	public void setMovieLevelId(Integer movieLevelId) {
		this.movieLevelId = movieLevelId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Blob getLevelPhoto() {
		return levelPhoto;
	}

	public void setLevelPhoto(Blob levelPhoto) {
		this.levelPhoto = levelPhoto;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public Set<MovieBean> getMovies() {
		return movie;
	}

	public void setMovies(Set<MovieBean> movie) {
		this.movie = movie;
	}

	public MovieLevelBean(Integer movieLevelId, String level, Blob levelPhoto, String photoName,
			Set<MovieBean> movie) {
		super();
		this.movieLevelId = movieLevelId;
		this.level = level;
		this.levelPhoto = levelPhoto;
		this.photoName = photoName;
		this.movie = movie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
