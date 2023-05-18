package com.capstoneproject.ms5teamservice.Team;


import java.util.List;

public interface TeamService {
    boolean addTeam(TeamRequestModel teamRequestModel);
    boolean updateTeam(TeamRequestModel teamRequestModel);
    boolean deleteTeamById(Integer id);
    TeamResponseModel findById(Integer id);
    Boolean valiteById(Integer id);
    List<TeamRequestModel> getTeam();


}
