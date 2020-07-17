package showEDU.com.web.forum.model;

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


@Entity
@Table(name = "ArtType")
public class ArtTypeBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer typeId;
	String type;
	@JsonIgnore
	Blob image;
	String filename;
	
	@OneToMany(mappedBy = "artTypeBean")
	@JsonIgnore
	Set<ArticleBean> articles = new HashSet<>();

	public ArtTypeBean() {
		super();
	}

	public ArtTypeBean(Integer typeId, String type, Blob image, String filename, Set<ArticleBean> articles) {
		super();
		this.typeId = typeId;
		this.type = type;
		this.image = image;
		this.filename = filename;
		this.articles = articles;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Set<ArticleBean> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleBean> articles) {
		this.articles = articles;
	}

	
	
}
