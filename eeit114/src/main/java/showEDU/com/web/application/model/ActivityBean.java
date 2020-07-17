package showEDU.com.web.application.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Activity")
public class ActivityBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer actId;
	String actTitle;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT 8")
	Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT 8")
	Date endDate;
	@Column(length = 2000 )
	String descr;
	@JsonIgnore
	String fileName;
	@JsonIgnore
	Blob actImg;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT 8")
	Date postDate;
	
	@JsonIgnore
	@Transient
	private MultipartFile activityImage;  
	
	
	public ActivityBean() {
	
	}


	public Integer getActId() {
		return actId;
	}


	public void setActId(Integer actId) {
		this.actId = actId;
	}


	public String getActTitle() {
		return actTitle;
	}


	public void setActTitle(String actTitle) {
		this.actTitle = actTitle;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Blob getActImg() {
		return actImg;
	}


	public void setActImg(Blob actImg) {
		this.actImg = actImg;
	}


	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	public MultipartFile getActivityImage() {
		return activityImage;
	}


	public void setActivityImage(MultipartFile activityImage) {
		this.activityImage = activityImage;
	}



	
	
}
