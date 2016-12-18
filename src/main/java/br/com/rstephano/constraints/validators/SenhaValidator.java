package br.com.rstephano.constraints.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rstephano.constraints.Senha;

public class SenhaValidator implements ConstraintValidator<Senha, String> {
	// TODO Criar padrão
	private Pattern pattern = Pattern.compile("");

	@Override
	public void initialize(Senha constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
