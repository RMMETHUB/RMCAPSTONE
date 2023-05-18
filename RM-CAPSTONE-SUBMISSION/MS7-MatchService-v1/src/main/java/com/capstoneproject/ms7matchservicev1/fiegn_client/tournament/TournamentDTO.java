package com.capstoneproject.ms7matchservicev1.fiegn_client.tournament;

public class TournamentDTO {

    private String tournamentName;
    private String sportsCategory;
    private String tournamentStyle;
    private boolean isActive;
    public TournamentDTO() {
    }


    public TournamentDTO(String tournamentName, String sportsCategory, String tournamentStyle) {
        this.tournamentName = tournamentName;
        this.sportsCategory = sportsCategory;
        this.tournamentStyle = tournamentStyle;
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
