package br.com.rstephano.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.rstephano.constraints.validators.EachFuncaoValidator;

@Constraint(validatedBy = EachFuncaoValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EachFuncao {
	String message() default "{br.com.rstephano.constraints.EachFuncao." + "message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
