package showEDU.com.web.store.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import showEDU.com.web.store.model.PeripheralProductBean;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		boolean p = PeripheralProductBean.class.isAssignableFrom(clazz);
		return p;
	}

	@Override
	public void validate(Object target, Errors errors) {
		PeripheralProductBean product = (PeripheralProductBean)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","", "商品名稱不得空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price","", "商品價格不得空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock","", "商品庫存不得空白");
		if(product.getPrice() < 1 ) {
			errors.rejectValue("price","", "金額不得小於0");
		}else if (product.getPrice() == null ) {
			errors.rejectValue("price","", "金額不得空白");
		}
		
		if(product.getCategoryId() == -1) {
			errors.rejectValue("categoryId","" ,"必須挑選類別");
		}
		if(product.getProductCategoryId() == -1) {
			errors.rejectValue("productCategoryId","" ,"必須挑選商品類別");		
		}
	}

}
