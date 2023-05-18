package com.capstoneproject.ms9ticketservicev1.feign_client;



import java.util.Date;

public class MatchResponseForTicketRequestModel {

    private String tournamentName;
    private String matchName;
    private Date dateTime;

    public MatchResponseForTicketRequestModel() {}

    public MatchResponseForTicketRequestModel(String tournamentName, String matchName, Date dateTime) {
        this.tournamentName = tournamentName;
        this.matchName = matchName;
        this.dateTime = dateTime;
    }


    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}


