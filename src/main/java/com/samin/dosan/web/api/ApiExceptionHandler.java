package com.samin.dosan.web.api;

import com.samin.dosan.core.parameter.ValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;

//    @ExceptionHandler
//    public ResponseEntity duplicateKey(DuplicateKeyException exception) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("global", exception.getMessage());
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationResponse methodArgumentNotValid(MethodArgumentNotValidException exception, Locale locale) {
        return new ValidationResponse(exception, messageSource, locale);
    }
}
