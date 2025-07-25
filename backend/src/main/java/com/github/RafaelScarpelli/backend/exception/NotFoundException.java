package com.github.RafaelScarpelli.backend.exception;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(String mensagem){
        super(mensagem);
    }
}
