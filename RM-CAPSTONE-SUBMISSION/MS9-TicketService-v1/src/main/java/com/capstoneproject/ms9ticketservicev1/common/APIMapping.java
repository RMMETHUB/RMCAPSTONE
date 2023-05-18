package com.capstoneproject.ms9ticketservicev1.common;

public class APIMapping {
    public static final String VERSION = "/v1";
    public static final String REQUEST_MAPPING_ENDPOINT_TICKET_SERVICE = "ticketservice";


    public static final String REQUEST_MAPPING_ENDPOINT_FIELD = "field";
    public static final String POST_MAPPING_ENDPOINT_ADD_FIELD = "/addField";
    public static final String GET_MAPPING_ENDPOINT_GET_FIELD = "/getField";
    public static final String PUT_MAPPING_ENDPOINT_UPDATE_FIELD  = "/updateField";
    public static final String DELETE_MAPPING_ENDPOINT_DELETE_FIELD = "/deleteField";



    public static final String REQUEST_MAPPING_ENDPOINT_TOURNAMENT = "tournament";
    public static final String POST_MAPPING_ENDPOINT_ADD_TOURNAMENT = "/addTournament";
    public static final String PUT_MAPPING_ENDPOINT_UPDATE_TOURNAMENT = "/updateTournament";
    public static final String DELETE_MAPPING_ENDPOINT_DELETE_TOURNAMENT = "/deleteTournament";
    public static final String GET_MAPPING_ENDPOINT_GET_TOURNAMENT = "/getTournament";



    public static final String REQUEST_MAPPING_ENDPOINT_MATCH = "match";
    public static final String POST_MAPPING_ENDPOINT_ADD_MATCH = "/addMatch";
    public static final String PUT_MAPPING_ENDPOINT_UPDATE_MATCH = "/updateMatch";
    public static final String DELETE_MAPPING_ENDPOINT_DELETE_MATCH = "/deleteMatch";
    public static final String GET_MAPPING_ENDPOINT_GET_MATCH = "/getMatch";

    public static final String REQUEST_MAPPING_ENDPOINT_TICKET = "/ticket";
    public static final String POST_MAPPING_ENDPOINT_ADD_TICKET= "/addTicket";
    public static final String GET_MAPPING_ENDPOINT_GET_TICKET = "/getTicket";
    public static final String PUT_MAPPING_ENDPOINT_UPDATE_TICKET = "/updateTicket";
    public static final String DELETE_MAPPING_ENDPOINT_DELETE_TICKET = "/deleteTicket";

    public static final String GET_MAPPING_ENDPOINT_GET_MATCH_REQUEST_BY_ID = "match/v1/getMatchRequestById";

    public static final String GET_MAPPING_ENDPOINT_VALIDATE_MATCH_BY_ID = "match/v1/validateMatchById";

    private APIMapping() {}

}
