package br.com.ievents.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<Role, String> {

	@Override
	public void initialize(Role parameters) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctxt) {
		boolean result = false;
		String[] validRoles = {"user", "admin"};
		
		for (String role : validRoles) {
			if (role.equals(value)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
