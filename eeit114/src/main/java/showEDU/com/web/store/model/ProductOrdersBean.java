package showEDU.com.web.store.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ProductOrders")
public class ProductOrdersBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer formNumber;
//	
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="FK_MovieMemberbean_Id") 
//	MemberBean memberBean;
	Integer 	memberId;
	
	Double totalAmount;
	
	Date ordersDate;
	String payStatus;
	String sendStatus;
    @JsonManagedReference
	@OneToMany(mappedBy="productOrdersBean", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	Set<ProductOrdersItemsBean> items = new LinkedHashSet<>();
	
	



	public ProductOrdersBean(Integer formNumber, Integer memberId, Double totalAmount, Date ordersDate,
			String payStatus, String sendStatus, Set<ProductOrdersItemsBean> items) {
		super();
		this.formNumber = formNumber;
		this.memberId = memberId;
		this.totalAmount = totalAmount;
		this.ordersDate = ordersDate;
		this.payStatus = payStatus;
		this.sendStatus = sendStatus;
		this.items = items;
	}


	public String getPayStatus() {
		return payStatus;
	}


	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}


	public String getSendStatus() {
		return sendStatus;
	}


	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}


	public ProductOrdersBean() {
		// TODO Auto-generated constructor stub
	}


	public Integer getFormNumber() {
		return formNumber;
	}


	public void setFormNumber(Integer formNumber) {
		this.formNumber = formNumber;
	}





//	public MemberBean getMemberBean() {
//		return memberBean;
//	}
//
//
//	public void setMemberBean(MemberBean memberBean) {
//		this.memberBean = memberBean;
//	}


	public Double getTotalAmount() {
		return totalAmount;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Date getOrdersDate() {
		return ordersDate;
	}


	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}


	public Set<ProductOrdersItemsBean> getItems() {
		return items;
	}


	public void setItems(Set<ProductOrdersItemsBean> items) {
		this.items = items;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
