package com.capstoneproject.ms6tournamentservicev1.common;

public class APIMapping {
    public static final String VERSION = "/v1";
    public static final String GET_MAPPING_ENDPOINT_VALIDATE_TOURNAMENT_BY_ID = "/validateTournamentById";

    public static final String REQUEST_MAPPING_ENDPOINT_TOURNAMENT = "tournament";
    public static final String POST_MAPPING_ENDPOINT_ADD_TOURNAMENT = "/addTournament";
    public static final String PUT_MAPPING_ENDPOINT_UPDATE_TOURNAMENT = "/updateTournament";
    public static final String DELETE_MAPPING_ENDPOINT_DELETE_TOURNAMENT = "/deleteTournament";
    public static final String GET_MAPPING_ENDPOINT_GET_TOURNAMENT = "/getTournament";

    public static final String GET_MAPPING_ENDPOINT_GET_TOURNAMENT_BY_ID = "/getTournamentById";
    private APIMapping() {}

}
