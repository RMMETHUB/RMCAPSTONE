package com.capstoneproject.ms3authenticationservicev1.user.exception;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String str)
    {
        super(str);
    }
}
