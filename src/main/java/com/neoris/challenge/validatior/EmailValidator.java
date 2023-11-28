package com.neoris.challenge.validatior;

import com.neoris.challenge.dto.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements Validator {

    private static final String EMAIL_REGEX = "^[a-zA-Z]+@dominio\\.cl$";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        String email = ((UserDTO) obj).getEmail();

        if (!email.matches(EMAIL_REGEX)) {
            errors.rejectValue("email", "email.invalidFormat");
        }
    }
}


