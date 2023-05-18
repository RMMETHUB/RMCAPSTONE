package com.capstoneproject.ms4playerservicev1.common.exception;

public class PlayerException extends RuntimeException{
    public PlayerException(String str)
    {
        super(str);
    }

    public PlayerException() {
    }
}
