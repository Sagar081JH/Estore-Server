package com.estore.exception;

public class LoginFailedException extends RuntimeException {
    private String message;
    public LoginFailedException() {}
    public LoginFailedException(String msg) {
        super(msg);
        this.message = msg;
    }
}
