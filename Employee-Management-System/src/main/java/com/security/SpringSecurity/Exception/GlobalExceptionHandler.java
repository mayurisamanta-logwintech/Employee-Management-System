package com.security.SpringSecurity.Exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.el.MethodNotFoundException;
import java.time.LocalDateTime;

/**
 * Global Exception Handler to handle the exceptions globally
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MyErrorDetails> resourseNotFoundExceptionHandler (ResourceNotFoundException ex, WebRequest req){
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler (UserException ex, WebRequest req){
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorityException.class)
    public ResponseEntity<MyErrorDetails> authorityExceptionHandler (AuthorityException ex, WebRequest req){
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PrivilegeException.class)
    public ResponseEntity<MyErrorDetails> privilegeExceptionHandler (PrivilegeException ex, WebRequest req){
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me) {
        MyErrorDetails err= new MyErrorDetails(LocalDateTime.now(), "Validation Error", me.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MyErrorDetails> notFoundExceptionHandler(NotFoundException ex, WebRequest req) {
        MyErrorDetails err= new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodNotFoundException.class)
    public ResponseEntity<MyErrorDetails> methodNotFoundExceptionHandler(MethodNotFoundException ex, WebRequest req) {
        MyErrorDetails err= new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> exceptionHandler (Exception ex, WebRequest req){
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
