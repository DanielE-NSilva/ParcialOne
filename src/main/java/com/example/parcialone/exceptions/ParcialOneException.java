package com.example.parcialone.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ParcialOneException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public ParcialOneException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }


}