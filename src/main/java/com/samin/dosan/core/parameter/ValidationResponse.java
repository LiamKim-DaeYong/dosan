package com.samin.dosan.core.parameter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter @Setter
public class ValidationResponse {
    private List<ValidError> fieldErrors;

    public ValidationResponse(Errors errors, MessageSource messageSource, Locale locale) {
        this.fieldErrors = errors.getFieldErrors().stream().map(error -> new ValidError(error, messageSource, locale))
                .collect(Collectors.toList());
    }

    @Getter @Setter
    static class ValidError {
        private String field;

        private String code;

        private Object rejectedValue;

        private String message;

        public ValidError(FieldError fieldError, MessageSource messageSource, Locale locale) {
            this.field = fieldError.getField();
            this.code = fieldError.getCode();
            this.rejectedValue = fieldError.getRejectedValue();
            this.message = messageSource.getMessage(fieldError, locale);
        }
    }
}

