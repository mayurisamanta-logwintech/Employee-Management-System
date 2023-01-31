package com.security.SpringSecurity.Exception;

public class PrivilegeException extends Exception{

    public PrivilegeException (){

    }

    public PrivilegeException (String message){
        super(message);
    }
}
