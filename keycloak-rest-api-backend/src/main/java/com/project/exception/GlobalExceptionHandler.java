package com.project.exception;

import com.project.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // resource not found exception handling
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException (ResourceNotFoundException exception, WebRequest webRequest) {
        ExceptionDto exceptionDto = new ExceptionDto(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    // API call exceptions
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ExceptionDto> handleApiException (APIException apiException, WebRequest webRequest) {
        ExceptionDto exceptionDto = new ExceptionDto(new Date(), apiException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    // global exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGlobalException (Exception exception, WebRequest webRequest) {
        ExceptionDto exceptionDto = new ExceptionDto(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
