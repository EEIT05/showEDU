package showEDU.com.web.member.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import showEDU.com.web.member.model.LoginMember;



public class LoginMemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginMember.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "", "帳號欄不能為空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "密碼欄不能為空白");
	}

}
