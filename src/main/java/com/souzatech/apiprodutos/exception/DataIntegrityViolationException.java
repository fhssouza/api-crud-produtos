package com.souzatech.apiprodutos.exception;

public class DataIntegrityViolationException extends RuntimeException{
    private static final Long serialVersionUID = 1L;
    public DataIntegrityViolationException(String message){
        super(message);
    }
}
