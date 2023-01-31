package com.security.SpringSecurity.Exception;

public class AuthorityException extends Exception{
    public AuthorityException(){
    }

    public AuthorityException(String message){
        super(message);
    }
}
