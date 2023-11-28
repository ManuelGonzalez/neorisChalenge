package com.neoris.challenge.validatior;

import com.neoris.challenge.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements Validator {

    @Value("${app.password.regex}")
    private String passwordPattern;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        String password = ((UserDTO) obj).getPassword();

        if (!password.matches(passwordPattern)) {
            errors.rejectValue("password", "password.invalidFormat");
        }
    }
}


