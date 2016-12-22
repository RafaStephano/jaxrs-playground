package br.com.rstephano.constraints.validators;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rstephano.Funcao;
import br.com.rstephano.constraints.EachFuncao;

public class EachFuncaoValidator implements ConstraintValidator<EachFuncao, List<String>> {
	private Pattern pattern = Pattern.compile(Funcao.ADMINISTRADOR + "|" + Funcao.USUARIO);

	@Override
	public void initialize(EachFuncao constraintAnnotation) {

	}

	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) {
		boolean isValid = true;
		for (int i = 0; i < value.size(); i++) {
			Matcher m = pattern.matcher(value.get(i));
			if (!m.matches()) {
				isValid = false;
			}
		}
		return isValid;
	}

}
