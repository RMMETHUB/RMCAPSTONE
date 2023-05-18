package com.capstoneproject.ms5teamservice;


import com.capstoneproject.ms5teamservice.FeignClient.PlayeClientService;
import com.capstoneproject.ms5teamservice.FeignClient.PlayerRequestModel;
import com.capstoneproject.ms5teamservice.Team.*;
import com.capstoneproject.ms5teamservice.common.Constants;
import com.capstoneproject.ms5teamservice.common.exception.TeamException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamServiceTest {
    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayeClientService playeClientService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTeamServiceTest(){
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        when(teamService.addTeam(mockTeamRequest)).thenReturn(true);
        boolean isSaved = teamService.addTeam(mockTeamRequest());
        Assert.assertEquals(true,isSaved);
    }
    @Test
    public void addTeamServiceWithTeamAlreadyExistExceptionTest() throws Exception{
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        when(teamRepository.findByTeamNameAndIsActive(anyString(),anyBoolean())).thenReturn(optionalMockTeamRequest());
        doThrow(new TeamException(Constants.TEAM_ALREADY_EXIST_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.addTeam(mockTeamRequest);
        } catch (TeamException ex) {
            assertEquals(Constants.TEAM_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addTeamServiceWithInvalidTeamNameFormatExceptionTest() throws Exception{
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        mockTeamRequest.setTeamName("test123");
        doThrow(new TeamException(Constants.TEAM_NAME_FORMAT_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.addTeam(mockTeamRequest);
        } catch (TeamException ex) {
            assertEquals(Constants.TEAM_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addTeamServiceWithEmptyTeamNameFormatExceptionTest() throws Exception{
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        mockTeamRequest.setTeamName("");
        doThrow(new TeamException(Constants.EMPTY_TEAM_NAME_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.addTeam(mockTeamRequest);
        } catch (TeamException ex) {
            assertEquals(Constants.EMPTY_TEAM_NAME_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updatePlayerServiceTest(){
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        when(teamRepository.findByIdAndIsActive(mockTeamRequest.getId(),mockTeamRequest.isActive())).thenReturn(mockTeamEntity());
        boolean isSaved =  teamService.updateTeam(mockTeamRequest());
        verify(teamRepository).saveAndFlush(any(TeamEntity.class));
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void updateTeamServiceWithTeamNameShouldNotBeEmptyExceptionTest() throws Exception{
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        mockTeamRequest.setTeamName("");
        when(teamRepository.findByIdAndIsActive(mockTeamRequest.getId(),mockTeamRequest.isActive())).thenReturn(mockTeamEntity());
        doThrow(new TeamException(Constants.EMPTY_TEAM_NAME_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.updateTeam(mockTeamRequest);
        } catch (TeamException ex) {
            assertEquals(Constants.EMPTY_TEAM_NAME_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateTeamServiceWithTeamNotFoundExceptionTest() throws Exception{
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        doThrow(new TeamException(Constants.TEAM_NOT_FOUND_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.updateTeam(mockTeamRequest);
        } catch (TeamException ex) {
            assertEquals(Constants.TEAM_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void deletePlayerTest(){
        TeamRequestModel mockTeamRequest = mockTeamRequest();
        when(teamRepository.findByIdAndIsActive(mockTeamRequest.getId(),mockTeamRequest.isActive())).thenReturn(mockTeamEntity());
        boolean isDeleted =  teamService.deleteTeamById(1);
        verify(teamRepository).saveAndFlush(any(TeamEntity.class));
        Assert.assertEquals(true,isDeleted);
    }

    @Test
    public void deletePlayerTestWithException(){
        doThrow(new TeamException(Constants.TEAM_NOT_FOUND_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.deleteTeamById(1000);
        } catch (TeamException ex) {
            assertEquals(Constants.TEAM_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void findByIdTeamServiceTest(){
        when(teamRepository.findByIdAndIsActive(anyInt(),anyBoolean())).thenReturn(mockTeamEntity());
        when(playeClientService.getPlayerByTeamId(anyInt())).thenReturn(getMockPlayers());
        TeamResponseModel list = teamService.findById(1);
        Assert.assertNotNull(list);
    }

    @Test
    public void findByIdTeamServiceTestWithExceptionTest(){
        when(teamRepository.findByIsActive(anyBoolean())).thenReturn(mockTeamEntityList());
        doThrow(new TeamException(Constants.TEAM_NOT_FOUND_EXCEPTION)).when(teamRepository).saveAndFlush(any(TeamEntity.class));
        try {
            teamService.findById(1000);
        } catch (TeamException ex) {
            assertEquals(Constants.TEAM_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void getAllTeamTeamServiceTest(){
        when(teamRepository.findByIsActive(anyBoolean())).thenReturn(mockTeamEntityList());
        List<TeamRequestModel> list = teamService.getTeam();
        for (TeamRequestModel teamRequestList :list) {
            Assert.assertNotNull(teamRequestList.getTeamName());
            Assert.assertNotNull(teamRequestList.getPlayers());
        }
    }

    @Test
    public void validateByIdTeamServiceTest(){
        when(teamRepository.findById(anyInt())).thenReturn(optionalMockTeamRequest());
        boolean isExisting = teamService.valiteById(1);
        Assert.assertEquals(true,isExisting);
    }

    private List<PlayerRequestModel> getMockPlayers(){
        List<PlayerRequestModel> playerRequestModelList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            PlayerRequestModel playerRequestModel = new PlayerRequestModel();
            playerRequestModel.setId(i);
            playerRequestModel.setFirstName("testFn");
            playerRequestModel.setLastName("testLn");
            playerRequestModel.setCountry("testCountry");
            playerRequestModel.setTeamId(1);
            playerRequestModel.setActive(true);
            playerRequestModelList.add(playerRequestModel);
        }
        return playerRequestModelList;
    }

    private List<TeamRequestModel> mockTeamsList(){
        List<TeamRequestModel> teamRequestModelArrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            TeamRequestModel teamRequestModel = new TeamRequestModel();
            teamRequestModel.setId(1);
            teamRequestModel.setTeamName("teamA");
            teamRequestModel.setActive(true);
            teamRequestModelArrayList.add(teamRequestModel);
        }
        return teamRequestModelArrayList;
    }

    private List<TeamEntity> mockTeamEntityList(){
        List<TeamEntity> teamRequestModelArrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            TeamEntity teamRequestModel = new TeamEntity();
            teamRequestModel.setId(1);
            teamRequestModel.setTeamName("teamA");
            teamRequestModel.setActive(true);
            teamRequestModelArrayList.add(teamRequestModel);
        }
        return teamRequestModelArrayList;
    }
    private Optional<TeamEntity> optionalMockTeamRequest(){
        TeamEntity teamRequestModel = new TeamEntity();
        teamRequestModel.setId(1);
        teamRequestModel.setTeamName("teamA");
        teamRequestModel.setActive(true);
        Optional<TeamEntity> optionalTeamRequestModel = Optional.of(teamRequestModel);
        return optionalTeamRequestModel;
    }

    private Optional<TeamEntity> optionalMockTeamRequestInvalidName(){
        TeamEntity teamRequestModel = new TeamEntity();
        teamRequestModel.setId(1);
        teamRequestModel.setTeamName("team12");
        teamRequestModel.setActive(true);
        Optional<TeamEntity> optionalTeamRequestModel = Optional.of(teamRequestModel);
        return optionalTeamRequestModel;
    }

    private TeamEntity mockTeamEntity(){
        TeamEntity teamRequestModel = new TeamEntity();
        teamRequestModel.setId(1);
        teamRequestModel.setTeamName("teamA");
        teamRequestModel.setActive(true);
        return teamRequestModel;
    }

    private TeamRequestModel mockTeamRequest(){
        TeamRequestModel teamRequestModel = new TeamRequestModel();
        teamRequestModel.setId(1);
        teamRequestModel.setTeamName("teamA");
        teamRequestModel.setActive(true);
        return teamRequestModel;
    }

}
