package com.learning.post.exception;

import com.learning.post.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> resourceNotFoundHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ExceptionDto exceptionDto = new ExceptionDto(message, false);
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgNotFoundException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String fieldMessage = error.getDefaultMessage();
            errorMap.put(fieldName, fieldMessage);
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyPresentException.class)
    public ResponseEntity<ExceptionDto> emailAlreadyPresentException(EmailAlreadyPresentException ex) {
        String message = ex.getMessage();
        ExceptionDto exceptionDto = new ExceptionDto(message, false);
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
