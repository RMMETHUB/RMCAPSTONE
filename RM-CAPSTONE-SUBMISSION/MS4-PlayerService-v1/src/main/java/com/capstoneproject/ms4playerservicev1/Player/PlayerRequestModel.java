package com.capstoneproject.ms4playerservicev1.Player;

import java.io.Serializable;

public class PlayerRequestModel implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String country;
    private boolean active;
    private int teamId;

    public PlayerRequestModel() {
    }

    public PlayerRequestModel(long id, String firstName, String lastName, String country, boolean active, int teamId) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.active = active;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
