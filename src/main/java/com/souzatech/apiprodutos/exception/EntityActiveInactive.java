package com.souzatech.apiprodutos.exception;

public class EntityActiveInactive extends RuntimeException{
    private static final Long serialVersionUID = 1L;
    public EntityActiveInactive(String message){
        super(message);
    }
}
