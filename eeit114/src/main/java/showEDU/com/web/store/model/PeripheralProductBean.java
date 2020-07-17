package showEDU.com.web.store.model;


import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PeripheralProduct")
@Proxy(lazy = false)
public class PeripheralProductBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productId;
	
	
	@ManyToOne
	@JoinColumn(name = "FK_CategoryBean_Id")
	CategoryBean categoryBean;
	
	
	@ManyToOne
	@JoinColumn(name = "FK_ProductCategoryBean_Id")
	ProductCategoryBean productCategoryBean;
	
	String name;
	@Column(length = 1000)
	String detail;
	String company;
	Integer stock;
	Integer price;
	Date createdate;
	String status;
	@JsonIgnore 
	Blob images;
	 String fileName;
	
//	@OneToMany(mappedBy = "peripheralProductBean")
//	Set<ProductImageBean>ProductImageBean = new HashSet<>();

	@Transient
	private MultipartFile	productImage;  
	
	@Transient
	private Integer  	categoryId;
	
	@Transient
	private Integer  	productCategoryId;
	
	
	

	public PeripheralProductBean() {
		super();
	}

	public PeripheralProductBean(Integer productId, CategoryBean categoryBean, ProductCategoryBean productCategoryBean,
			String name, String detail, String company, Integer stock, Integer price, Date createdate, String status,
			Blob images, String fileName, MultipartFile productImage, Integer categoryId, Integer productCategoryId) {
		super();
		this.productId = productId;
		this.categoryBean = categoryBean;
		this.productCategoryBean = productCategoryBean;
		this.name = name;
		this.detail = detail;
		this.company = company;
		this.stock = stock;
		this.price = price;
		this.createdate = createdate;
		this.status = status;
		this.images = images;
		this.fileName = fileName;
		this.productImage = productImage;
		this.categoryId = categoryId;
		this.productCategoryId = productCategoryId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public ProductCategoryBean getProductCategoryBean() {
		return productCategoryBean;
	}

	public void setProductCategoryBean(ProductCategoryBean productCategoryBean) {
		this.productCategoryBean = productCategoryBean;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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

	public Blob getImages() {
		return images;
	}

	public void setImages(Blob images) {
		this.images = images;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	
}