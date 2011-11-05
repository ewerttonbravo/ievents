package br.com.ievents.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Documented
//@Constraint(validatedBy=RoleValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
	String message() default "Role invalida";
	Class<?>[] groups() default {};
	//Class<? extends ConstraintPayload>[] payload() default {};
}
