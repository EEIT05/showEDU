package showEDU.com.web.store.dao;

import java.util.List;

import showEDU.com.web.store.model.CategoryBean;
import showEDU.com.web.store.model.PeripheralProductBean;
import showEDU.com.web.store.model.ProductCategoryBean;
import showEDU. com.web.store.model.ProductImageBean;

public interface PeripheralDao {
	List<PeripheralProductBean> getAllProducts();
	
	List<ProductCategoryBean> getAllCategories();
	
	List<PeripheralProductBean> getProductsByCategory(Integer productCategoryBean);
	
	PeripheralProductBean getProductById(Integer productId);
	
	List<ProductImageBean> getProductImage(Integer productId);
	
	void addproduct(PeripheralProductBean product);
	
	CategoryBean getCategoryById(Integer categoryId);
	
	List<CategoryBean> getCategoryList();
	
	ProductCategoryBean getProductCategoryById(Integer productcategoryId);
	
	List<ProductCategoryBean> geProducttCategoryList();

	ProductImageBean getItemImg(Integer productId);

	List<PeripheralProductBean> getAllProductSelect();

	void updateproduct(PeripheralProductBean product);

	void delete(Integer id);

	

	
}
