package com.bookers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    //book exception handler
    @ExceptionHandler(BookException.class)
    public ResponseEntity<MyErrorDetails> bookExceptionHandler(BookException be, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),be.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    //user exception handler
    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException ue, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),ue.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    //author exception handler
    @ExceptionHandler(AuthorException.class)
    public ResponseEntity<MyErrorDetails> authorExceptionHandler(AuthorException ae, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),ae.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    //access exception
    @ExceptionHandler(AccessDenied.class)
    public ResponseEntity<MyErrorDetails> authorExceptionHandler(AccessDenied ad, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),ad.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    //generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> genericExceptionHandler(Exception e, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),e.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    //login exception handler
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<MyErrorDetails> logInExceptionHandler(LoginException le, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),le.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    //no handler exception handler
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ne, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),ne.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    //validation exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),me.getBindingResult().getFieldError().getDefaultMessage(),req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
