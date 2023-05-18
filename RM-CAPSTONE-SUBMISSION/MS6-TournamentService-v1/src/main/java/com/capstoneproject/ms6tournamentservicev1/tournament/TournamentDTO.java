package com.capstoneproject.ms6tournamentservicev1.tournament;

public class TournamentDTO {
    private long id;
    private String tournamentName;
    private String sportsCategory;
    private String tournamentStyle;
    private boolean isActive;
    public TournamentDTO() {
    }

    public TournamentDTO(long id, String tournamentName, String sportsCategory, String tournamentStyle) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.sportsCategory = sportsCategory;
        this.tournamentStyle = tournamentStyle;
    }

    public TournamentDTO(String tournamentName, String sportsCategory, String tournamentStyle) {
        this.tournamentName = tournamentName;
        this.sportsCategory = sportsCategory;
        this.tournamentStyle = tournamentStyle;
    }

    public long getId() {
        return id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public String getSportsCategory() {
        return sportsCategory;
    }

    public String getTournamentStyle() {
        return tournamentStyle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setSportsCategory(String sportsCategory) {
        this.sportsCategory = sportsCategory;
    }

    public void setTournamentStyle(String tournamentStyle) {
        this.tournamentStyle = tournamentStyle;
    }
}
