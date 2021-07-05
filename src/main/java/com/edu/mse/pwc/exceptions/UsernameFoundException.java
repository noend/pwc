package com.edu.mse.pwc.exceptions;

@SuppressWarnings("unused")
public class UsernameFoundException extends RuntimeException{

    public UsernameFoundException(String message) {
        super(message);
    }

    public UsernameFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
