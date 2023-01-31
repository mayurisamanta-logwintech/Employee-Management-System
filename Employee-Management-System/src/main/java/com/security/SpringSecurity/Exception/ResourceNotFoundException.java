package com.security.SpringSecurity.Exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
