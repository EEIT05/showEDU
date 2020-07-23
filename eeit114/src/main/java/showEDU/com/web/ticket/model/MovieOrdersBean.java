package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.sql.Date;
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

import showEDU.com.web.member.model.MemberBean;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="MovieOrders")
public class MovieOrdersBean implements Serializable {
	static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	movieOrdersId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="memberId")
	MemberBean  memberBean;
	
	Date  		ordersDate;
	Integer  	totalAccount;
	
	
	@OneToMany(mappedBy = "movieOrdersBean")
	 Set<MovieOrderDetailBean> movieOrderDetail = new HashSet<>();
	
	

	public MovieOrdersBean() {
	}

	public Integer getMovieOrdersId() {
		return movieOrdersId;
	}

	public void setMovieOrdersId(Integer movieOrdersId) {
		this.movieOrdersId = movieOrdersId;
	}

	public MemberBean getShowMemberBean() {
		return memberBean;
	}

	public void setShowMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Date getOrdersDate() {
		return ordersDate;
	}

	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}

	public Integer getTotalAccount() {
		return totalAccount;
	}

	public void setTotalAccount(Integer totalAccount) {
		this.totalAccount = totalAccount;
	}

	public Set<MovieOrderDetailBean> getMovieOrderDetail() {
		return movieOrderDetail;
	}

	public void setMovieOrderDetail(Set<MovieOrderDetailBean> movieOrderDetail) {
		this.movieOrderDetail = movieOrderDetail;
	}

	public MovieOrdersBean(Integer movieOrdersId, MemberBean memberBean, Date ordersDate, Integer totalAccount,
			Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.movieOrdersId = movieOrdersId;
		this.memberBean = memberBean;
		this.ordersDate = ordersDate;
		this.totalAccount = totalAccount;
		this.movieOrderDetail = movieOrderDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
