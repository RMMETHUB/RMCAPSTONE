package com.capstoneproject.ms5teamservice.common;

public class APIMapping {


    public static final String VERSION = "/v1";

    public static final String REQUEST_MAPPING_ENDPOINT_TEAM = "team";

    public static final String POST_MAPPING_ENDPOINT_ADD_TEAM = "/addTeam";
    public static final String POST_MAPPING_ENDPOINT_UPDATE_TEAM = "/updateTeam";

    public static final String POST_MAPPING_ENDPOINT_DELETE_TEAM = "/deleteTeam";

    public static final String GET_MAPPING_ENDPOINT_GET_TEAM = "/getTeam";

    public static final String GET_MAPPING_ENDPOINT_GET_TEAM_BY_ID = "/getTeamById";

    public static final String GET_MAPPING_ENDPOINT_VALIDATE_TEAM_BY_ID = "/validateTeamById";




    public static final String GET_MAPPING_ENDPOINT_GET_PLAYE_BY_TEAM_ID = "player/v1/getPlayerByTeamId?id=";


    private APIMapping(){

    }
}
