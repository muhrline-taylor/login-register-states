package com.taylormuhrline.loginregisterstates.validators;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.taylormuhrline.loginregisterstates.models.User;
import com.taylormuhrline.loginregisterstates.services.MainService;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private MainService mainService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		// CHECK IF PW AND CONFIRM PW MATCH
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		
		// CHECK TO SEE IF EMAIL IS UNIQUE
		if(mainService.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("duplicate", "Dupe");
		}
	}
	
}
