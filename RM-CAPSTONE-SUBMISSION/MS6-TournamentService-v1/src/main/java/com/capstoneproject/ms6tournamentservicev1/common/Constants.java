package com.capstoneproject.ms6tournamentservicev1.common;

public class Constants {

    public static final String MESSAGE = "message";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "fail";
    public static final String STATUS = "status";


    public static final String TOURNAMENT_ALREADY_EXIST_EXCEPTION = "Tournament Already Exist";
    public static final String TOURNAMENT_NAME_MANDATORY_EXCEPTION = "Tournament name should not be empty";
    public static final String TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION = "Tournament category should not be empty";
    public static final String TOURNAMENT_STYLE_MANDATORY_EXCEPTION = "Tournament style should not be empty";
    public static final String TOURNAMENT_NOT_FOUND_EXCEPTION = "Tournament Not Found";
    public static final String ADD_TOURNAMENT_SUCCESS_MESSAGE = "Tournament successfully added";
    public static final String UPDATE_TOURNAMENT_SUCCESS_MESSAGE = "Tournament successfully updated";
    public static final String DELETE_TOURNAMENT_SUCCESS_MESSAGE = "Tournament successfully deleted";


    public static final String REST_TEMPLATE_URL = "http://playerservice/playerservice/team/v1/getTeamById?id=";

    private Constants() {}

}
