package com.capstoneproject.ms7matchservicev1.fiegn_client.team;




import com.capstoneproject.ms7matchservicev1.fiegn_client.player.PlayerRequestModel;

import java.util.List;

public class TeamResponseModel {



    private String teamName;

    private boolean isActive;
    private List<PlayerRequestModel> players;

    public TeamResponseModel() {
    }

    public TeamResponseModel(String teamName, boolean isActive) {
        this.teamName = teamName;
        this.isActive = isActive;
    }

    public TeamResponseModel(String teamName, boolean isActive, List<PlayerRequestModel> players) {
        this.teamName = teamName;
        this.isActive = isActive;
        this.players = players;
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
