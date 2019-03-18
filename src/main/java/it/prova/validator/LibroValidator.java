package it.prova.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.prova.dto.LibroDTO;

public class LibroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LibroDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genere", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroPagine", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataPubblicazione", "required", "Campo obbligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "required", "Campo obbligatorio.");
		
		LibroDTO libro = (LibroDTO) arg0;
		
		if (libro.getAutore().getId() == null) {
			errors.rejectValue("autore.id", "Campo obbligatorio.");
		}
		
		if ((libro.getIsbn() + "").matches("/^(97(8|9))?\\d{9}(\\d|X)$/")) {

			errors.rejectValue("isbn", "ISBN non valido!");
		}

	}

}
