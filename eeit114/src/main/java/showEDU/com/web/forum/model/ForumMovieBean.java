package showEDU.com.web.forum.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ForumMovie")
public class ForumMovieBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer movieId;
	String name;
	@JsonIgnore
	Blob image;
	String filename;
	
	
	
	public ForumMovieBean() {
		super();
	}
	public ForumMovieBean(Integer movieId, String name, Blob image, String filename) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.image = image;
		this.filename = filename;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
	
	
}
