package com.example.uni.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{

    HttpStatus httpStatus;
    public UserException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
