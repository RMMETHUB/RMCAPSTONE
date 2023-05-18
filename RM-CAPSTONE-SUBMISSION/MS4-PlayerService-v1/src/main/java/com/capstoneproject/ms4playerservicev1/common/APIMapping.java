package com.capstoneproject.ms4playerservicev1.common;

public class APIMapping {


    public static final String VERSION = "/v1";

    public static final String REQUEST_MAPPING_ENDPOINT_PLAYER_SERVICE = "playerservice/";


    public static final String GET_MAPPING_ENDPOINT_GET_PLAYER = "/getPlayer";

    public static final String GET_MAPPING_ENDPOINT_GET_PLAYE_BY_ID = "/getPlayerById";

    public static final String GET_MAPPING_ENDPOINT_GET_PLAYE_BY_TEAM_ID = "/getPlayerByTeamId";

    public static final String REQUEST_MAPPING_ENDPOINT_PLAYER = "player";

    public static final String PUT_MAPPING_ENDPOINT_UPDATE_PLAYER = "/updatePlayer";

    public static final String POST_MAPPING_ENDPOINT_ADD_PLAYER = "/addPlayer";
    public static final String DELETE_MAPPING_ENDPOINT_DELETE_PLAYER = "/deletePlayer";

    public static final String GET_MAPPING_ENDPOINT_VALIDATE_TEAM_BY_ID = "team/v1/validateTeamById?id=";

    private APIMapping(){

    }
}
