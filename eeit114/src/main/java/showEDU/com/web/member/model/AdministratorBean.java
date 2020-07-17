package showEDU.com.web.member.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SHOW114_Administrator")
public class AdministratorBean {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Integer admId;
 private String adname;
 private String adaccount;
 private String adpswd;
 private String admType;
 private Timestamp registerTime; 
 private String login;
 private String userType;
 
 
 
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public Timestamp getRegisterTime() {
	return registerTime;
}
public void setRegisterTime(Timestamp registerTime) {
	this.registerTime = registerTime;
}
public Integer getAdmId() {
	return admId;
}
public void setAdmId(Integer admId) {
	this.admId = admId;
}
public String getAdname() {
	return adname;
}
public void setAdname(String adname) {
	this.adname = adname;
}
public String getAdaccount() {
	return adaccount;
}
public void setAdaccount(String adaccount) {
	this.adaccount = adaccount;
}
public String getAdpswd() {
	return adpswd;
}
public void setAdpswd(String adpswd) {
	this.adpswd = adpswd;
}
public String getAdmType() {
	return admType;
}
public void setAdmType(String admType) {
	this.admType = admType;
}


public AdministratorBean(Integer admId, String adname, String adaccount, String adpswd, String admType,
		Timestamp registerTime, String login, String userType) {
	super();
	this.admId = admId;
	this.adname = adname;
	this.adaccount = adaccount;
	this.adpswd = adpswd;
	this.admType = admType;
	this.registerTime = registerTime;
	this.login = login;
	this.userType = userType;
}
@Override
public String toString() {
	return "AdministratorBean [admId=" + admId + ", adname=" + adname + ", adaccount=" + adaccount + ", adpswd="
			+ adpswd + ", admType=" + admType + ", registerTime=" + registerTime + ", login=" + login + ", userType="
			+ userType + "]";
}
public AdministratorBean() {
	super();
}
 	
}
