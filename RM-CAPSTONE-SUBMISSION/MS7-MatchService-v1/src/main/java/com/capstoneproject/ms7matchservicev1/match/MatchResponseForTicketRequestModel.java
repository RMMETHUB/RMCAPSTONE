package com.capstoneproject.ms7matchservicev1.match;



import java.util.Date;

public class MatchResponseForTicketRequestModel {

    private long matchId;

    private String fieldName;
    private String tournamentName;
    private String matchName;
    private Date dateTime;

    public MatchResponseForTicketRequestModel() {}

    public MatchResponseForTicketRequestModel(long matchId, String fieldName, String tournamentName, String matchName, Date dateTime) {
        this.matchId = matchId;
        this.fieldName = fieldName;
        this.tournamentName = tournamentName;
        this.matchName = matchName;
        this.dateTime = dateTime;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
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


