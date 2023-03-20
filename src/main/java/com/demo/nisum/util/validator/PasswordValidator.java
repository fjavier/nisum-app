package com.demo.nisum.util.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Value("${password.validator.pattern}")
    private String pattern;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        log.info(pattern);
        return passwordField != null && passwordField.matches(pattern);
    }
}
