package showEDU.com.web.member.model;


import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="SHOW114_Member")
public class MemberBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer memberId;

	private String name;
	private String account;
	private String pswd;
	private String phone;
	@Transient
	private String pswd2;
	private String userType;
	private Timestamp registerTime; 
	@JsonIgnore
	private Blob image;
	private String address;
	private String fileName;
	private Double unpaidAmount;
	@Transient
	private MultipartFile  memImage;
	
	private String login;
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getPswd2() {
		return pswd2;
	}
	public void setPswd2(String pswd2) {
		this.pswd2 = pswd2;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public java.sql.Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	public Blob getImage() {
		return image;
	}
	public void image(Blob photo) {
		this.image = photo;
	}
	public Double getUnpaidAmount() {
		return unpaidAmount;
	}
	public void setUnpaidAmount(Double unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public MultipartFile getMemImage() {
		return memImage;
	}
	public void setMemImage(MultipartFile memImage) {
		this.memImage = memImage;
	}
	
	public MemberBean(Integer memberId, String name, String account, String pswd, String phone, String pswd2,
			String userType, Timestamp registerTime, Blob image, String address, String fileName, Double unpaidAmount,
			MultipartFile memImage, String login) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.account = account;
		this.pswd = pswd;
		this.phone = phone;
		this.pswd2 = pswd2;
		this.userType = userType;
		this.registerTime = registerTime;
		this.image = image;
		this.address = address;
		this.fileName = fileName;
		this.unpaidAmount = unpaidAmount;
		this.memImage = memImage;
		this.login = login;
		
	}

	public MemberBean() {
		super();
	}



	
	

}
