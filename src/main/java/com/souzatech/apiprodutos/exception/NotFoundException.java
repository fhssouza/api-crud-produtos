package com.souzatech.apiprodutos.exception;

public class NotFoundException extends RuntimeException{
    private static final Long serialVersionUID = 1L;
    public NotFoundException(String message){
        super(message);
    }
}
