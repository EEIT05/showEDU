package showEDU.com.web.store.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.store.dao.PeripheralDao;
import showEDU.com.web.store.model.CategoryBean;
import showEDU.com.web.store.model.PeripheralProductBean;
import showEDU.com.web.store.model.ProductCategoryBean;
import showEDU.com.web.store.model.ProductImageBean;
import showEDU.com.web.store.service.PeripheralService;
@Service
public class PeripheralServiceImpl implements PeripheralService {

	@Autowired
	PeripheralDao peripheralDao	;
	
	@Transactional
	@Override
	public List<PeripheralProductBean> getAllProducts() {
		
		return peripheralDao.getAllProducts();
	}
	
	@Transactional
	@Override
	public List<ProductCategoryBean> getAllCategories() {
		
		return peripheralDao.getAllCategories();
	}

	@Transactional
	@Override
	public List<PeripheralProductBean> getProductsByCategory(Integer productCategoryBean) {
		
		return peripheralDao.getProductsByCategory(productCategoryBean);
	}

	@Transactional
	@Override
	public PeripheralProductBean getProductById(Integer productId) {
		return peripheralDao.getProductById(productId);
	}

	@Transactional
	@Override
	public List<ProductImageBean> getProductImage(Integer productImageBean) {
		
		return peripheralDao.getProductImage(productImageBean);
	}

	@Transactional
	@Override
	public void addproduct(PeripheralProductBean product) {
		
		peripheralDao.addproduct(product);
		
	}

	@Transactional
	@Override
	public CategoryBean getCategoryById(Integer categoryId) {
		
		return peripheralDao.getCategoryById(categoryId);
	}

	@Transactional
	@Override
	public List<CategoryBean> getCategoryList() {
		
		return peripheralDao.getCategoryList();
	}

	@Transactional
	@Override
	public ProductCategoryBean getProductCategoryById(Integer productcategoryId) {
		
		return peripheralDao.getProductCategoryById(productcategoryId);
	}

	@Transactional
	@Override
	public List<ProductCategoryBean> geProducttCategoryList() {
		
		return peripheralDao.geProducttCategoryList();
	}

	@Transactional
	@Override
	public ProductImageBean getItemImg(Integer productId) {
		
		return peripheralDao.getItemImg(productId);
	}

	@Transactional
	@Override
	public List<PeripheralProductBean> getAllProductSelect() {
		
		return peripheralDao.getAllProductSelect();
	}

	@Transactional
	@Override
	public void updateproduct(PeripheralProductBean product) {
		 peripheralDao.updateproduct(product);
		
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		peripheralDao.delete(id);
		
	}


}
