package showEDU.com.web.member.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import showEDU.com.web.member.model.AdministratorBean;




@Component
public class AdministratorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
//		System.out.println(clazz.getName());
		boolean b = AdministratorBean.class.isAssignableFrom(clazz);
		return b;
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdministratorBean administrator = (AdministratorBean)target;
		ValidationUtils.rejectIfEmptyOrWhitespace
			(errors, "adaccount", "administrator.adaccount.not.empty","帳號欄不能空白(預設值)");
		ValidationUtils.rejectIfEmptyOrWhitespace
			(errors, "adname", "administrator.adname.not.empty","姓名欄不能空白(預設值)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adpswd", "", "密碼欄不能空白(AdministratorValidator)");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "", "電話欄不能空白(MemberValidator)");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "地址欄不能空白(MemberValidator)");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pswd2", "", "密碼欄不能空白(MemberValidator)");
	
		if (administrator.getAdaccount().length()<5) {
			errors.rejectValue("adaccount","", "帳號欄不能小於五個字元");
		}


	}

}
