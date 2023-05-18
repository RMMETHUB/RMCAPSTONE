package com.capstoneproject.ms7matchservicev1.match;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name= "match",schema = "match_schema")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private long id;
    @Column(name = "field_id")
    private int fieldId;

    @Column(name = "date_time")
    private Date dateTime;
    @Column(name = "tournament_id")
    private long tournamentId;
    @Column(name = "team_a_id")
    private int teamAId;

    @Column(name = "team_b_id")
    private int teamBId;

    @Column(name = "isActive")
    private boolean isActive;

    public MatchEntity() {
    }

    public MatchEntity(int fieldEntity, Date dateTime, long tournament, int teamAId, int teamBId, boolean isActive) {
        this.fieldId = fieldEntity;
        this.dateTime = dateTime;
        this.tournamentId = tournament;
        this.teamAId = teamAId;
        this.teamBId = teamBId;
        this.isActive = isActive;
    }

    public MatchEntity(long id, int fieldId, Date dateTime, long tournamentId, int teamAId, int teamBId, boolean isActive) {
        this.id = id;
        this.fieldId = fieldId;
        this.dateTime = dateTime;
        this.tournamentId = tournamentId;
        this.teamAId = teamAId;
        this.teamBId = teamBId;
        this.isActive = isActive;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamAId() {
        return teamAId;
    }

    public void setTeamAId(int teamAId) {
        this.teamAId = teamAId;
    }

    public int getTeamBId() {
        return teamBId;
    }

    public void setTeamBId(int teamBId) {
        this.teamBId = teamBId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "MatchEntity{" +
                "id=" + id +
                ", fieldId=" + fieldId +
                ", dateTime=" + dateTime +
                ", tournamentId=" + tournamentId +
                ", teamAId=" + teamAId +
                ", teamBId=" + teamBId +
                ", isActive=" + isActive +
                '}';
    }
}


