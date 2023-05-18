package com.capstoneproject.ms7matchservicev1.common;

public class Constants {

    public static final String MESSAGE = "message";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "fail";
    public static final String STATUS = "status";
    public static final String FIELD_NOT_FOUND_EXCEPTION = "Field Not Found";
    public static final String TOURNAMENT_NOT_FOUND_EXCEPTION = "Tournament Not Found";


    public static final String MATCH_ALREADY_EXIST_EXCEPTION = "Match Already Exist";
    public static final String MATCH_NOT_FOUND_EXCEPTION = "Match Not Found";
    public static final String ADD_MATCH_SUCCESS_MESSAGE = "Match successfully added";
    public static final String UPDATE_MATCH_SUCCESS_MESSAGE = "Match successfully updated";
    public static final String DELETE_MATCH_SUCCESS_MESSAGE = "Match successfully deleted";
    public static final String INVALID_DATE_FORMAT_EXCEPTION_MESSAGE = "Invalid date format please follow dd/MM/yyyy format";

    public static final String SIMPLE_DATE_FORMAT_PATTERN = "dd/MM/yyyy";

    public static final String REST_TEMPLATE_URL = "http://playerservice/playerservice/team/v1/getTeamById?id=";


    private Constants() {}

}
