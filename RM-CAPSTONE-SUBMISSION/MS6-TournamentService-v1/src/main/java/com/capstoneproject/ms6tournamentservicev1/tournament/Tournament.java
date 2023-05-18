package com.capstoneproject.ms6tournamentservicev1.tournament;

import javax.persistence.*;

@Entity
@Table(name= "tournament",schema = "tournament_schema")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private long id;
    @Column(name = "tournament_name")
    private String tournamentName;
    @Column(name = "sports_category")
    private String sportsCategory;
    @Column(name = "tournament_style")
    private String tournamentStyle;

    @Column(name = "isActive")
    private boolean isActive;

    public Tournament() {
    }

    public Tournament(String tournamentName, String sportsCategory, String tournamentStyle, boolean isActive) {
        this.tournamentName = tournamentName;
        this.sportsCategory = sportsCategory;
        this.tournamentStyle = tournamentStyle;
        this.isActive = isActive;
    }

    public Tournament(long id, String tournamentName, String sportsCategory, String tournamentStyle, boolean isActive) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.sportsCategory = sportsCategory;
        this.tournamentStyle = tournamentStyle;
        this.isActive = isActive;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getSportsCategory() {
        return sportsCategory;
    }

    public void setSportsCategory(String sportsCategory) {
        this.sportsCategory = sportsCategory;
    }

    public String getTournamentStyle() {
        return tournamentStyle;
    }

    public void setTournamentStyle(String tournamentStyle) {
        this.tournamentStyle = tournamentStyle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", tournamentName='" + tournamentName + '\'' +
                ", sportsCategory='" + sportsCategory + '\'' +
                ", tournamentStyle='" + tournamentStyle + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
