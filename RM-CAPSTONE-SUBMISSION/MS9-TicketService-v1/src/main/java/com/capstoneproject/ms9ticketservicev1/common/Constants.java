package com.capstoneproject.ms9ticketservicev1.common;

public class Constants {

    public static final String MESSAGE = "message";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "fail";
    public static final String STATUS = "status";

    public static final String ADD_FIELD_SUCCESS_MESSAGE = "Field successfully added";
    public static final String FIELD_ALREADY_EXIST_EXCEPTION = "Field Already Exist";
    public static final String FIELD_NOT_FOUND_EXCEPTION = "Field Not Found";

    public static final String FIELD_NAME_MANDATORY_EXCEPTION = "Field name should not be empty";
    public static final String FIELD_ADDRESS_MANDATORY_EXCEPTION = "Field address should not be empty";
    public static final String FIELD_CAPACITY_MANDATORY_EXCEPTION = "Field capacity should not be empty";
    public static final String UPDATE_FIELD_SUCCESS_MESSAGE = "Field successfully updated";
    public static final String DELETE_FIELD_SUCCESS_MESSAGE = "Field successfully deleted";

    public static final String TOURNAMENT_ALREADY_EXIST_EXCEPTION = "Tournament Already Exist";
    public static final String TOURNAMENT_NAME_MANDATORY_EXCEPTION = "Tournament name should not be empty";
    public static final String TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION = "Tournament category should not be empty";
    public static final String TOURNAMENT_STYLE_MANDATORY_EXCEPTION = "Tournament style should not be empty";
    public static final String TOURNAMENT_NOT_FOUND_EXCEPTION = "Tournament Not Found";
    public static final String ADD_TOURNAMENT_SUCCESS_MESSAGE = "Tournament successfully added";
    public static final String UPDATE_TOURNAMENT_SUCCESS_MESSAGE = "Tournament successfully updated";
    public static final String DELETE_TOURNAMENT_SUCCESS_MESSAGE = "Tournament successfully deleted";


    public static final String MATCH_ALREADY_EXIST_EXCEPTION = "Match Already Exist";
    public static final String MATCH_NOT_FOUND_EXCEPTION = "Match Not Found";
    public static final String ADD_MATCH_SUCCESS_MESSAGE = "Match successfully added";
    public static final String UPDATE_MATCH_SUCCESS_MESSAGE = "Match successfully updated";
    public static final String DELETE_MATCH_SUCCESS_MESSAGE = "Match successfully deleted";
    public static final String INVALID_DATE_FORMAT_EXCEPTION_MESSAGE = "Invalid date format please follow dd/MM/yyyy format";


    public static final String TICKET_ALREADY_EXIST_EXCEPTION = "Ticket Already Exist";
    public static final String TICKET_CUSTOMER_NAME_MANDATORY_EXCEPTION = "Customer name should not be empty";
    public static final String TICKET_PRICE_MANDATORY_EXCEPTION = "Ticket Price should not be empty";
    public static final String TICKET_NOT_FOUND_EXCEPTION = "Ticket Not Found";
    public static final String ADD_TICKET_SUCCESS_MESSAGE = "Ticket successfully added";
    public static final String UPDATE_TICKET_SUCCESS_MESSAGE = "Ticket successfully updated";
    public static final String DELETE_TICKET_SUCCESS_MESSAGE = "Ticket successfully deleted";


    public static final String REST_TEMPLATE_URL = "http://playerservice/playerservice/team/v1/getTeamById?id=";

    private Constants() {}

}
