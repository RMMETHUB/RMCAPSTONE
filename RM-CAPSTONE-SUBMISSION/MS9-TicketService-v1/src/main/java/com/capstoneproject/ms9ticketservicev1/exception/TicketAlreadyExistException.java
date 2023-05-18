package com.capstoneproject.ms9ticketservicev1.exception;

public class TicketAlreadyExistException extends Exception{
    public TicketAlreadyExistException(String str)
    {
        super(str);
    }
}