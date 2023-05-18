package com.capstoneproject.ms7matchservicev1.match;



import com.capstoneproject.ms7matchservicev1.fiegn_client.field.FieldDTO;
import com.capstoneproject.ms7matchservicev1.fiegn_client.team.TeamResponseModel;
import com.capstoneproject.ms7matchservicev1.fiegn_client.tournament.TournamentDTO;

public class MatchResponseModel {

    private long id;
    private FieldDTO field;
    private TournamentDTO tournament;
    private TeamResponseModel teamsA;
    private TeamResponseModel teamB;
    private String dateTime;
    private boolean isActive;

    public MatchResponseModel() {
    }

    public MatchResponseModel(long id, FieldDTO field, TournamentDTO tournament, TeamResponseModel teamsA, TeamResponseModel teamB, String dateTime, boolean isActive) {
        this.id = id;
        this.field = field;
        this.tournament = tournament;
        this.teamsA = teamsA;
        this.teamB = teamB;
        this.dateTime = dateTime;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FieldDTO getField() {
        return field;
    }

    public void setField(FieldDTO field) {
        this.field = field;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public TeamResponseModel getTeamsA() {
        return teamsA;
    }

    public void setTeamsA(TeamResponseModel teamsA) {
        this.teamsA = teamsA;
    }

    public TeamResponseModel getTeamB() {
        return teamB;
    }

    public void setTeamB(TeamResponseModel teamB) {
        this.teamB = teamB;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}


