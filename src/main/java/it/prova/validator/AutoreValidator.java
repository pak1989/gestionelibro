package it.prova.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.prova.dto.AutoreDTO;

public class AutoreValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AutoreDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required", "Campo obbligatorio.");

	}
}
