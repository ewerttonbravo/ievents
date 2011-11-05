package br.com.ievents.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="emailValidator")
public class EmailValidator implements javax.faces.validator.Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value)
			throws ValidatorException {
		String email = (String) value;
		if (email.indexOf("@") == -1) {
			FacesMessage message = new FacesMessage("Email invalido. Exemplo: user@server.com");
			throw new ValidatorException(message);
		}
	}

}
