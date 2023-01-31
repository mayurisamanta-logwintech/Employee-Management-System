package com.security.SpringSecurity.Exception;

public class UserException extends Exception{

    public UserException(){

    }

    public UserException(String message){
        super(message);
    }
}
