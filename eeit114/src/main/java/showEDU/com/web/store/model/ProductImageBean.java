package showEDU.com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ProductImage")
public class ProductImageBean implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer ProductImageId;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_PeripheralProduct_Id")
	 PeripheralProductBean peripheralProductBean;
	 @JsonIgnore
	 Blob images;
	 
	 String  	fileName;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_category_Id")
	 CategoryBean categoryBean;
	

	private static final long serialVersionUID = 1L;

	public ProductImageBean() {
		// TODO Auto-generated constructor stub
	}



	public Integer getProductImageId() {
		return ProductImageId;
	}



	public void setProductImageId(Integer productImageId) {
		ProductImageId = productImageId;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public ProductImageBean(Integer productImageId, PeripheralProductBean peripheralProductBean, Blob images,
			String fileName, CategoryBean categoryBean) {
		super();
		ProductImageId = productImageId;
		this.peripheralProductBean = peripheralProductBean;
		this.images = images;
		this.fileName = fileName;
		this.categoryBean = categoryBean;
	}



	public PeripheralProductBean getPeripheralProductBean() {
		return peripheralProductBean;
	}

	public void setPeripheralProductBean(PeripheralProductBean peripheralProductBean) {
		this.peripheralProductBean = peripheralProductBean;
	}

	public Blob getImages() {
		return images;
	}

	public void setImages(Blob images) {
		this.images = images;
	}

	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

}
