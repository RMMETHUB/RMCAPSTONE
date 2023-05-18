package com.capstoneproject.ms5teamservice.Team;




import javax.persistence.*;

@Entity
@Table(name= "team",schema = "team_schema")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "isActive")
    private boolean isActive;


    public TeamEntity() {
    }

    public TeamEntity(String teamName, boolean isActive) {
        this.teamName = teamName;
        this.isActive = isActive;
    }

    public TeamEntity(int id, String teamName, boolean isActive) {
        this.id = id;
        this.teamName = teamName;
        this.isActive = isActive;
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

    @Override
    public String toString() {
        return "TeamEntity{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
