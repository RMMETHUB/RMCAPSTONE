package com.capstoneproject.ms7matchservicev1.match;


public class MatchRequestModel {

    private long id;
    private int fieldId;
    private long tournamentId;
    private int teamsAId;
    private int teamsBId;
    private String dateTime;
    private boolean isActive;


    public MatchRequestModel() {
    }

    public MatchRequestModel(int fieldId, long tournamentId, int teamsAId, int teamsBId, String dateTime) {
        this.fieldId = fieldId;
        this.tournamentId = tournamentId;
        this.teamsAId = teamsAId;
        this.teamsBId = teamsBId;
        this.dateTime = dateTime;

    }

    public MatchRequestModel(long id, int fieldId, long tournamentId, int teamsAId, int teamsBId, String dateTime) {
        this.id = id;
        this.fieldId = fieldId;
        this.tournamentId = tournamentId;
        this.teamsAId = teamsAId;
        this.teamsBId = teamsBId;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamsAId() {
        return teamsAId;
    }

    public void setTeamsAId(int teamsAId) {
        this.teamsAId = teamsAId;
    }

    public int getTeamsBId() {
        return teamsBId;
    }

    public void setTeamsBId(int teamsBId) {
        this.teamsBId = teamsBId;
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
