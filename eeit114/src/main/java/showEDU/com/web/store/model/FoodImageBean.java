package showEDU.com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FoodImage")
public class FoodImageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer ProductImageId;
	@ManyToOne
	@JoinColumn(name = "FK_foodProduct_Id")
	 FoodProductBean foodProductBean;
	@JsonIgnore
	 Blob images;
	 String  	fileName;
	 @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_category_Id")
	 CategoryBean categoryBean;


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

	public FoodImageBean(Integer productImageId, FoodProductBean foodProductBean, Blob images, String fileName,
			CategoryBean categoryBean) {
		super();
		ProductImageId = productImageId;
		this.foodProductBean = foodProductBean;
		this.images = images;
		this.fileName = fileName;
		this.categoryBean = categoryBean;
	}

	public FoodProductBean getFoodProductBean() {
		return foodProductBean;
	}

	public void setFoodProductBean(FoodProductBean foodProductBean) {
		this.foodProductBean = foodProductBean;
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
