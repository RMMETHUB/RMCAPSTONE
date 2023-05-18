package com.capstoneproject.ms7matchservicev1.fiegn_client.player;

import java.io.Serializable;

public class PlayerRequestModel implements Serializable {

    private String firstName;
    private String lastName;
    private String country;
    private boolean active;
    private int teamId;

    public PlayerRequestModel() {
    }

    public PlayerRequestModel(String firstName, String lastName, String country, boolean active, int teamId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.active = active;
        this.teamId = teamId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
