package com.capstoneproject.ms4playerservicev1.Player;

import com.capstoneproject.ms4playerservicev1.FeignClient.TeamClientService;
import com.capstoneproject.ms4playerservicev1.common.Constants;
import com.capstoneproject.ms4playerservicev1.common.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TeamClientService teamClientService;

    public PlayerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean addPlayer(PlayerRequestModel playerRequestModel){
            boolean isTeamExiting = teamClientService.validateById(playerRequestModel.getTeamId());
            PlayerEntity playerExist = playerRepository.findByFirstNameAndLastName(playerRequestModel.getFirstName(), playerRequestModel.getLastName());
            if(!isTeamExiting){
                throw new PlayerException(Constants.TEAM_NOT_FOUND_EXCEPTION);
            }else if (playerExist != null){
                throw new PlayerException(Constants.PLAYER_ALREADY_EXIST_EXCEPTION);
            }else if (playerRequestModel.getFirstName().equals("")) {
                throw new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION);
            } else if(containsDigit(playerRequestModel.getFirstName())){
                throw new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION);
            }else if (playerRequestModel.getLastName().equals("")) {
                throw new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION);
            }else if(containsDigit(playerRequestModel.getLastName())){
                throw new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION);
            }else if(containsDigit(playerRequestModel.getCountry())){
                throw new PlayerException(Constants.PLAYER_COUNTRY_FORMAT_EXCEPTION);
            }else {
                PlayerEntity newPlayer = new PlayerEntity();
                newPlayer.setFirstName(playerRequestModel.getFirstName());
                newPlayer.setLastName(playerRequestModel.getLastName());
                newPlayer.setCountry(playerRequestModel.getCountry());
                newPlayer.setActive(playerRequestModel.isActive());
                newPlayer.setTeamId(playerRequestModel.getTeamId());
                playerRepository.saveAndFlush(newPlayer);
                return true;
            }
    }

    public static boolean containsDigit(String input){
        return input.matches(".*\\d+.*");
    }

    @Override
    public boolean updatePlayer(PlayerRequestModel playerEntity){
            boolean isTeamExiting = teamClientService.validateById(playerEntity.getTeamId());
            Optional<PlayerEntity> playerExist = playerRepository.findByIdAndIsActive(playerEntity.getId(), true);
            if(!isTeamExiting){
                throw new PlayerException(Constants.TEAM_NOT_FOUND_EXCEPTION);
            }else if (playerExist.isPresent()) {
                if (playerEntity.getFirstName().equals("")) {
                    throw new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION);
                } else if (containsDigit(playerEntity.getFirstName())) {
                    throw new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION);
                } else if (playerEntity.getLastName().equals("")) {
                    throw new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION);
                }else if (containsDigit(playerEntity.getLastName())) {
                    throw new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION);
                } else if (containsDigit(playerEntity.getCountry())) {
                    throw new PlayerException(Constants.PLAYER_COUNTRY_FORMAT_EXCEPTION);
                }
                playerExist.get().setFirstName(playerEntity.getFirstName());
                playerExist.get().setLastName(playerEntity.getLastName());
                playerExist.get().setCountry(playerEntity.getCountry());
                playerExist.get().setActive(playerEntity.isActive());
                playerExist.get().setTeamId(playerEntity.getTeamId());
                playerRepository.saveAndFlush(playerExist.get());
                return true;
            }
            else{
                throw new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION);
            }
    }

    @Override
    public boolean deletePlayerById(long id){
        Optional<PlayerEntity> playerEntity = playerRepository.findByIdAndIsActive(id,true);
        if (playerEntity.isPresent()){
            playerEntity.get().setActive(false);
            playerRepository.saveAndFlush(playerEntity.get());
            return true;
        }else {
            throw new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION);
        }
    }


    @Override
    public PlayerRequestModel getPlayerById(long id) {
        Optional<PlayerEntity> entity = playerRepository.findByIdAndIsActive(id,true);
        PlayerRequestModel responseModel = new PlayerRequestModel();
        if (entity.isPresent()){
            responseModel.setId(entity.get().getId());
            responseModel.setFirstName(entity.get().getFirstName());
            responseModel.setLastName(entity.get().getLastName());
            responseModel.setCountry(entity.get().getCountry());
            responseModel.setActive(entity.get().isActive());
            responseModel.setTeamId(entity.get().getTeamId());
            return responseModel;
        }else {
            throw new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public List<PlayerRequestModel> getPlayerByTeamId(int id) {
        Optional<List<PlayerEntity>> entity = playerRepository.findByTeamId(id);
        List<PlayerRequestModel> playerRequestModelList = new ArrayList<>();
        if (entity.isPresent()){
            for (PlayerEntity entityList: entity.get()) {
                PlayerRequestModel responseModel = new PlayerRequestModel();
                responseModel.setId(entityList.getId());
                responseModel.setFirstName(entityList.getFirstName());
                responseModel.setLastName(entityList.getLastName());
                responseModel.setCountry(entityList.getCountry());
                responseModel.setActive(entityList.isActive());
                responseModel.setTeamId(entityList.getTeamId());
                playerRequestModelList.add(responseModel);
            }
            return playerRequestModelList;
        }else {
            throw new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public List<PlayerRequestModel> getPlayer() {
        List<PlayerEntity> getAllPlayers = playerRepository.findAll();
        List<PlayerRequestModel> playerRequestModelList = new ArrayList<>();
        for (PlayerEntity playerEntity: getAllPlayers) {
            PlayerRequestModel requestModel = new PlayerRequestModel();
            requestModel.setId(playerEntity.getId());
            requestModel.setFirstName(playerEntity.getFirstName());
            requestModel.setLastName(playerEntity.getLastName());
            requestModel.setCountry(playerEntity.getCountry());
            requestModel.setTeamId(playerEntity.getTeamId());
            requestModel.setActive(playerEntity.isActive());
            playerRequestModelList.add(requestModel);
        }
        return playerRequestModelList;
    }

    public boolean validaTeamById(int id){
        return Objects.equals(Boolean.TRUE, restTemplate.getForObject("http://MS5-TEAM-SERVICE-V1/team/v1/validateTeamById?id=" + id, Boolean.class));
    }

}
