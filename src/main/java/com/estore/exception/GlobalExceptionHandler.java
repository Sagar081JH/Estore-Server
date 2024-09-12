package com.estore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {LoginFailedException.class})
    public ResponseEntity<ErrorResponse> loginFailedException(LoginFailedException ex) {
        ErrorResponse message = ErrorResponse.builder()
                .statusCode(502)
                .timestamp(new Date())
                .message(ex.getMessage())
                .description("LoginController/login() : Login failed due to incorrect credentials!")
                .build();
        return new ResponseEntity<ErrorResponse>(message, HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException ex) {
        ErrorResponse message = ErrorResponse.builder()
                .statusCode(404)
                .timestamp(new Date())
                .message(ex.getMessage())
                .description("UserController/getProfile() : User not found !")
                .build();
        return new ResponseEntity<ErrorResponse>(message, HttpStatus.NOT_FOUND);
    }
}
