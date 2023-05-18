package com.capstoneproject.ms3authenticationservicev1.user.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String str)
    {
        super(str);
    }
}
