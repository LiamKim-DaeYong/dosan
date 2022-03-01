package com.samin.dosan.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity duplicateKey(DuplicateKeyException exception) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("global", exception.getMessage());
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
//    }
//

    @ExceptionHandler
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("fieldErrors", exception.getFieldErrors());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
}
