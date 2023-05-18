package com.capstoneproject.ms4playerservicev1.Player;




import javax.persistence.*;

@Entity
@Table(name= "players",schema = "player_schema")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")

    private String lastName;
    @Column(name = "country")
    private String country;
    @Column(name = "isActive")
    private boolean isActive;
    @Column(name = "team_id")
    private int teamId;

    public PlayerEntity() {
    }

    public PlayerEntity(long id, String firstName, String lastName, String country, boolean isActive, int teamId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.isActive = isActive;
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

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setFirstName(String firstName) {
        if(firstName == null){
            throw new NullPointerException("The First Name cannot be null!");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName == null){
            throw new NullPointerException("The Last Name cannot be null!");
        }
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        if(country == null){
            throw new NullPointerException("The Country cannot be null!");
        }
        this.country = country;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", isActive=" + isActive +
                ", teamId=" + teamId +
                '}';
    }
}
