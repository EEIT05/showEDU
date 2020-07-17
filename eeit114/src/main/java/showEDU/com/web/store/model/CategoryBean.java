package showEDU.com.web.store.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Category")
public class CategoryBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer categoryId;
	 String category;
	 String status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoryBean",fetch = FetchType.EAGER)
	 Set<ProductCategoryBean>ProductCategory = new HashSet<>();
	 
	@JsonIgnore
	@OneToMany(mappedBy = "categoryBean",fetch = FetchType.EAGER)
	 Set<FoodCategoryBean>FoodCategory = new HashSet<>();

	public CategoryBean() {
		
	}

	public CategoryBean(Integer categoryId, String category, String status, Set<ProductCategoryBean> productCategory,
			Set<FoodCategoryBean> foodCategory) {
		super();
		this.categoryId = categoryId;
		this.category = category;
		this.status = status;
		ProductCategory = productCategory;
		FoodCategory = foodCategory;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<ProductCategoryBean> getProductCategory() {
		return ProductCategory;
	}

	public void setProductCategory(Set<ProductCategoryBean> productCategory) {
		ProductCategory = productCategory;
	}

	public Set<FoodCategoryBean> getFoodCategory() {
		return FoodCategory;
	}

	public void setFoodCategory(Set<FoodCategoryBean> foodCategory) {
		FoodCategory = foodCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
