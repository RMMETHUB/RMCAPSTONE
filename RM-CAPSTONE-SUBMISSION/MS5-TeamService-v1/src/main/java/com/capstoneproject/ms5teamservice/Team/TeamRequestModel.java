package com.capstoneproject.ms5teamservice.Team;

import com.capstoneproject.ms5teamservice.FeignClient.PlayerRequestModel;

import java.util.List;

public class TeamRequestModel {

    private int id;

    private String teamName;

    private boolean isActive;

    private List<PlayerRequestModel> players;


    public TeamRequestModel() {
    }

    public TeamRequestModel(String teamName, boolean isActive) {
        this.teamName = teamName;
        this.isActive = isActive;
    }

    public TeamRequestModel(int id, String teamName, boolean isActive) {
        this.id = id;
        this.teamName = teamName;
        this.isActive = isActive;
    }

    public TeamRequestModel(int id, String teamName, boolean isActive, List<PlayerRequestModel> players) {
        this.id = id;
        this.teamName = teamName;
        this.isActive = isActive;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<PlayerRequestModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerRequestModel> players) {
        this.players = players;
    }
}
