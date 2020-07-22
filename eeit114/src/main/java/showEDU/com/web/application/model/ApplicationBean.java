package showEDU.com.web.application.model;


import java.io.Serializable;



import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.ticket.model.TheaterBean;


@Entity
@Table(name="Application")
public class ApplicationBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer aplcId;
	
	@ManyToOne
	@JoinColumn(name="memberId_fk")
	MemberBean memberBean;
	@Transient
	Integer memberId;
	
	@ManyToOne
	@JoinColumn(name="actClassId_fk")
	ActClassBean actClassBean;
	@Transient
	Integer actClassId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="theaterId_fk")
	TheaterBean theaterBean;
	@Transient
	Integer theaterId;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT 8")
	Date date;
	String time;
	@Column(length = 1000 )
	String intro;
    Integer totalAmount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone="GMT 8")
	Timestamp aplcTime;
	
	@ManyToOne
	@JoinColumn(name="statusId_fk")
	StatusBean statusBean;
	@Transient
	Integer statusId;
	
	Integer payStatus;
	

	public ApplicationBean() {
		super();
	}


	public Integer getAplcId() {
		return aplcId;
	}


	public void setAplcId(Integer aplcId) {
		this.aplcId = aplcId;
	}


	

	public MemberBean getMemberBean() {
		return memberBean;
	}


	public void setMemberBean(MemberBean mb) {
		this.memberBean = mb;
	}


	public ActClassBean getActClassBean() {
		return actClassBean;
	}


	public void setActClassBean(ActClassBean actClassBean) {
		this.actClassBean = actClassBean;
	}

	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
	


	public TheaterBean getTheaterBean() {
		return theaterBean;
	}


	public void setTheaterBean(TheaterBean theaterBean) {
		this.theaterBean = theaterBean;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	


	


	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}


	


	
	


	public Integer getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Timestamp getAplcTime() {
		return aplcTime;
	}


	public void setAplcTime(Timestamp aplcTime) {
		this.aplcTime = aplcTime;
	}


	public StatusBean getStatusBean() {
		return statusBean;
	}


	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}


	public Integer getPayStatus() {
		return payStatus;
	}


	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public Integer getActClassId() {
		return actClassId;
	}


	public void setActClassId(Integer actClassId) {
		this.actClassId = actClassId;
	}


	public Integer getTheaterId() {
		return theaterId;
	}


	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}


	public Integer getStatusId() {
		return statusId;
	}


	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	

	
	
	
}
