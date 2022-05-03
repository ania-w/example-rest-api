package com.example.restapi.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request)
    {
        ExceptionResponse response=new ExceptionResponse(new Date(), e.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception e, WebRequest request)
    {
        ExceptionResponse response=new ExceptionResponse(new Date(), "Invalid id",request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status,
            WebRequest request)
    {
        ExceptionResponse response=
                new ExceptionResponse(new Date(), "Validation failed",e.getBindingResult().toString());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
