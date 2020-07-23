package showEDU.com.web.member.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import showEDU.com.web.member.model.LoginMember;

public class ForgetPasswdvalidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginMember.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "","Email不能是空白");
	
		
	}

}
