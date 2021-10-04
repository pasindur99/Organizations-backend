package com.exception;

import jakarta.servlet.http.HttpServletResponse;

public class UnotherizeException extends Exception {

    public UnotherizeException(String message){
        super(message);
    }
}


