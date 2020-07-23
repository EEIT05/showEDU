package showEDU.com.web.store.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import showEDU.com.web.ticket.model.MovieOrderDetailBean;

@Entity
@Table(name = "FoodProduct")
public class FoodProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productId;
	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "FK_categoryBean_Id")
//	CategoryBean categoryBean;
//	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "FK_foodCategoryBean_Id")
//	FoodCategoryBean foodCategoryBean;
	
	String name;
	String detail;
	Integer price;
	Date createdate;
	String status;
//	@JsonIgnore
//	@OneToMany(mappedBy = "foodProductBean")
//	Set<FoodImageBean>FoodImageBean = new HashSet<>();
	

	
	


	public FoodProductBean() {
		// TODO Auto-generated constructor stub
	}






	public Integer getProductId() {
		return productId;
	}






	public void setProductId(Integer productId) {
		this.productId = productId;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}






	public String getDetail() {
		return detail;
	}






	public void setDetail(String detail) {
		this.detail = detail;
	}






	public Integer getPrice() {
		return price;
	}






	public void setPrice(Integer price) {
		this.price = price;
	}






	public Date getCreatedate() {
		return createdate;
	}






	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}






	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	public FoodProductBean(Integer productId, String name, String detail, Integer price, Date createdate,
			String status) {
		super();
		this.productId = productId;
		this.name = name;
		this.detail = detail;
		this.price = price;
		this.createdate = createdate;
		this.status = status;
	}







	
}
