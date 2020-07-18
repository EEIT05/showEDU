package showEDU.com.web.member.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import showEDU.com.web.member.model.MemberBean;



@Component
public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
//		System.out.println(clazz.getName());
		boolean b = MemberBean.class.isAssignableFrom(clazz);
		return b;
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberBean member = (MemberBean)target;
		ValidationUtils.rejectIfEmptyOrWhitespace
			(errors, "account", "member.account.not.empty","帳號欄不能空白(預設值)");
		ValidationUtils.rejectIfEmptyOrWhitespace
			(errors, "name", "member.name.not.empty","姓名欄不能空白(預設值)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pswd", "", "密碼欄不能空白(MemberValidator)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "", "電話欄不能空白(MemberValidator)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "地址欄不能空白(MemberValidator)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pswd2", "", "密碼欄不能空白(MemberValidator)");
		
	
		if (member.getAccount().length()<5) {
			errors.rejectValue("account","", "帳號欄不能小於五個字元");
		}


	}

}
