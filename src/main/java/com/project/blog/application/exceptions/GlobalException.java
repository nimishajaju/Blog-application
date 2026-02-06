package com.project.blog.application.exceptions;

import com.project.blog.application.payloads.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseApi> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
       String message= ex.getMessage();
       return new ResponseEntity<>(new ResponseApi(message,false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>  handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName= ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST );

        }

        @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseApi> ioExceptionHandler(IOException ex){
        String message= ex.getMessage();
        return  new ResponseEntity<>(new ResponseApi(message,false),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

