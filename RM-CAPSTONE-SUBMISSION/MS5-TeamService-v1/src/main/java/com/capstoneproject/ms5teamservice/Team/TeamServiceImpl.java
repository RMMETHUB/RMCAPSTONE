package com.capstoneproject.ms5teamservice.Team;


import com.capstoneproject.ms5teamservice.FeignClient.PlayeClientService;
import com.capstoneproject.ms5teamservice.FeignClient.PlayerRequestModel;
import com.capstoneproject.ms5teamservice.common.Constants;
import com.capstoneproject.ms5teamservice.common.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayeClientService playeClientService;

    @Override
    public boolean addTeam(TeamRequestModel teamRequestModel){
        Optional<TeamEntity> team = teamRepository.findByTeamNameAndIsActive(teamRequestModel.getTeamName(), true);
        if(team.isPresent()){
            throw new TeamException(Constants.TEAM_ALREADY_EXIST_EXCEPTION);
        }else if (teamRequestModel.getTeamName().equals("")){
            throw new TeamException(Constants.EMPTY_TEAM_NAME_EXCEPTION);
        }else if(containsDigit(teamRequestModel.getTeamName())){
            throw new TeamException(Constants.TEAM_NAME_FORMAT_EXCEPTION);
        }else{
            teamRepository.save(new TeamEntity(teamRequestModel.getTeamName(), teamRequestModel.isActive()));
            return true;
        }
    }

    @Override
    public boolean updateTeam(TeamRequestModel teamRequestModel){
        TeamEntity updateEmployee = teamRepository.findByIdAndIsActive(teamRequestModel.getId(),true);
        if (updateEmployee != null){
            if (teamRequestModel.getTeamName().equals("")){
                throw new TeamException(Constants.EMPTY_TEAM_NAME_EXCEPTION);
            }
            updateEmployee.setTeamName(teamRequestModel.getTeamName());
            teamRepository.saveAndFlush(updateEmployee);
            return true;
        }else{
            throw new TeamException(Constants.TEAM_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public boolean deleteTeamById(Integer id){
        TeamEntity entityToBeDeleted = teamRepository.findByIdAndIsActive(id,true);
        if (entityToBeDeleted != null){
            entityToBeDeleted.setActive(false);
            teamRepository.saveAndFlush(entityToBeDeleted);
            return true;
        }else {
            throw new TeamException(Constants.TEAM_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public TeamResponseModel findById(Integer id) {
        TeamEntity teamEntity = teamRepository.findByIdAndIsActive(id,true);
        TeamResponseModel teamResponseModel = new TeamResponseModel();
        List<PlayerRequestModel> listOfPlayer = playeClientService.getPlayerByTeamId(teamResponseModel.getTeamId());
        if (teamEntity != null){
            teamResponseModel.setTeamId(teamEntity.getId());
            teamResponseModel.setTeamName(teamEntity.getTeamName());
            teamResponseModel.setActive(teamEntity.isActive());

            teamResponseModel.setPlayers(listOfPlayer);
            return teamResponseModel;
        }else {
            throw new TeamException(Constants.TEAM_NOT_FOUND_EXCEPTION);
        }


    }

    @Override
    public Boolean valiteById(Integer id) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(id);
        return teamEntity.isPresent();
    }

    @Override
    public List<TeamRequestModel> getTeam() {
        List<TeamEntity> getAllTeam = teamRepository.findByIsActive(true);
        List<TeamRequestModel> teamRequestModelArrayList = new ArrayList<>();
        for (TeamEntity teamEntity: getAllTeam) {
            List<PlayerRequestModel> listOfPlayer = playeClientService.getPlayerByTeamId(teamEntity.getId());
            TeamRequestModel teamRequestModel = new TeamRequestModel();
            teamRequestModel.setId(teamEntity.getId());
            teamRequestModel.setTeamName(teamEntity.getTeamName());
            teamRequestModel.setActive(teamEntity.isActive());
            teamRequestModel.setPlayers(listOfPlayer);
            teamRequestModelArrayList.add(teamRequestModel);

        }
        return teamRequestModelArrayList;

    }
    public static boolean containsDigit(String input){
        return input.matches(".*\\d+.*");
    }
}
