package showEDU.com.web.store.model;

import java.io.Serializable;

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
@Table(name = "FoodCategory")
public class FoodCategoryBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer foodCategoryId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_category_Id")
	 CategoryBean categoryBean;
	 String items;
	 String status;

	public FoodCategoryBean(Integer foodCategoryId, CategoryBean categoryBean, String items, String status) {
		super();
		this.foodCategoryId = foodCategoryId;
		this.categoryBean = categoryBean;
		this.items = items;
		this.status = status;
	}

	public FoodCategoryBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFoodCategoryId() {
		return foodCategoryId;
	}

	public void setFoodCategoryId(Integer foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}

	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
