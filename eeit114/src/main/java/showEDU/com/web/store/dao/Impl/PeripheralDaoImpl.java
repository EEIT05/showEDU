package showEDU.com.web.store.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import showEDU.com.web.store.dao.PeripheralDao;
import showEDU.com.web.store.model.CategoryBean;
import showEDU.com.web.store.model.PeripheralProductBean;
import showEDU.com.web.store.model.ProductCategoryBean;
import showEDU.com.web.store.model.ProductImageBean;
@Repository
public class PeripheralDaoImpl implements PeripheralDao {

	SessionFactory factory;
	
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	//抓所有的商品
	@Override
	public List<PeripheralProductBean> getAllProducts() {
		String hql = "From PeripheralProductBean pp where pp.status = 'A'";
		Session session = factory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<PeripheralProductBean> beans = session.createQuery(hql).getResultList();
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategoryBean> getAllCategories() {
		String hql ="From  ProductCategoryBean";
		Session session = factory.getCurrentSession();
		
		List<ProductCategoryBean> beans = session.createQuery(hql).getResultList();
		
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PeripheralProductBean> getProductsByCategory(Integer CategoryId) {
			String hql ="From PeripheralProductBean pp where pp.productCategoryBean.productCategoryId =:productcategory";
			Session session = factory.getCurrentSession();

			session.createQuery(hql).setParameter("productcategory", CategoryId).getResultList();
		return session.createQuery(hql).setParameter("productcategory", CategoryId).getResultList();
	}

	@Override
	public PeripheralProductBean getProductById(Integer productId) {
		Session session = factory.getCurrentSession();
		return session.get(PeripheralProductBean.class, productId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductImageBean> getProductImage(Integer productId) {
		String hql = "Select ProductImageBean From  PeripheralProductBean where productId=:id";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("id", productId).getResultList();
	}

	//新增商品
	@Override
	public void addproduct(PeripheralProductBean product) {
		Session session = factory.getCurrentSession();
		CategoryBean cb = getCategoryById(product.getCategoryId());
		product.setCategoryBean(cb);
		ProductCategoryBean pcb = getProductCategoryById(product.getProductCategoryId());
		product.setProductCategoryBean(pcb);
		session.save(product);

	}

	@Override
	public CategoryBean getCategoryById(Integer categoryId) {
		Session session = factory.getCurrentSession();
		return session.get(CategoryBean.class, categoryId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryBean> getCategoryList() {
		String hql = "FROM CategoryBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public ProductCategoryBean getProductCategoryById(Integer productcategoryId) {
		Session session = factory.getCurrentSession();
		return session.get(ProductCategoryBean.class, productcategoryId);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategoryBean> geProducttCategoryList() {
		String hql = "FROM ProductCategoryBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public ProductImageBean getItemImg(Integer productId) {
		Session session = factory.getCurrentSession();
		
		return  session.get(ProductImageBean.class, productId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PeripheralProductBean> getAllProductSelect() {
		String hql = "From PeripheralProductBean";
		Session session = factory.getCurrentSession();
		
		return session.createQuery(hql).getResultList();
	}

	@Override
	public void updateproduct(PeripheralProductBean product) {
		if (product != null & product.getProductId() != null) {
			Session session = factory.getCurrentSession();
			CategoryBean cb = getCategoryById(product.getCategoryId());
			product.setCategoryBean(cb);
			ProductCategoryBean pcb = getProductCategoryById(product.getProductCategoryId());
			product.setProductCategoryBean(pcb);
			session.update(product);
		}
		
	}

	@Override
	public void delete(Integer id) {
		Session session = factory.getCurrentSession();
		PeripheralProductBean product = getProductById(id);
		if (id != null) {
			product.setCategoryBean(null);
			product.setProductCategoryBean(null);
			session.delete(product);
		}
		
	}


	
}
