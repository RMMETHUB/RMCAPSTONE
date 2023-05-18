package com.capstoneproject.ms4playerservicev1;

import com.capstoneproject.ms4playerservicev1.FeignClient.TeamClientService;
import com.capstoneproject.ms4playerservicev1.Player.PlayerEntity;
import com.capstoneproject.ms4playerservicev1.Player.PlayerRepository;
import com.capstoneproject.ms4playerservicev1.Player.PlayerRequestModel;
import com.capstoneproject.ms4playerservicev1.Player.PlayerServiceImpl;

import com.capstoneproject.ms4playerservicev1.common.Constants;
import com.capstoneproject.ms4playerservicev1.common.exception.PlayerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PlayerServiceTest {
    @InjectMocks
    private PlayerServiceImpl playerService;
    @Mock
    RestTemplate restTemplate;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    TeamClientService teamClientService;



    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void addPlayerServiceTest(){
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerService.addPlayer(mockPlayerRequest)).thenReturn(true);
        boolean isSaved =  playerService.addPlayer(mockPlayerRequest());
        verify(playerRepository).saveAndFlush(any(PlayerEntity.class));
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void addPlayerServiceWithTeamNotFoundExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setTeamId(100);
        when(teamClientService.validateById(1)).thenReturn(true);
        doThrow(new PlayerException(Constants.TEAM_NOT_FOUND_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.TEAM_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addPlayerServiceWithEmptyPlayerFirstNameExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setFirstName("");
        when(teamClientService.validateById(1)).thenReturn(true);
        doThrow(new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addPlayerServiceWithEmptyPlayerLastNameExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setLastName("");
        when(teamClientService.validateById(1)).thenReturn(true);
        doThrow(new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addPlayerServiceWithPlayerAlreadyExistExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByFirstNameAndLastName(anyString(),anyString())).thenReturn(mockPlayerEntity());
        doThrow(new PlayerException(Constants.PLAYER_ALREADY_EXIST_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addPlayerServiceWitPlayerFirstNameExceptiohTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setFirstName("testFn12");
        when(teamClientService.validateById(1)).thenReturn(true);
        doThrow(new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (RuntimeException ex) {
            assertEquals(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addPlayerServiceWithPlayerLastNameExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setLastName("testLn12");
        when(teamClientService.validateById(1)).thenReturn(true);
        doThrow(new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addPlayerServiceWithPlayerCountryFormatExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setCountry("country1234");
        when(teamClientService.validateById(1)).thenReturn(true);
        doThrow(new PlayerException(Constants.PLAYER_COUNTRY_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.addPlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_COUNTRY_FORMAT_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updatePlayerServiceTest(){
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        boolean isSaved =  playerService.updatePlayer(mockPlayerRequest());
        verify(playerRepository).saveAndFlush(any(PlayerEntity.class));
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void updatePlayerServiceWithTeamNotFoundExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setTeamId(1000);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.TEAM_NOT_FOUND_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.TEAM_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updatePlayerServiceWithPlayerAlreadyExistExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setFirstName("testFn");
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_ALREADY_EXIST_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void updatePlayerServiceWitPlayerFirstNameExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setFirstName("testFn12");
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void updatePlayerServiceWitPlayerFirstNameExceptionEmptyTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setFirstName("");
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_FIRST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void updatePlayerServiceWithPlayerLastNameExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setLastName("testLn12");
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void updatePlayerServiceWithPlayerEmptyLastNameExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setLastName("");
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_LAST_NAME_FORMAT_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void updatePlayerServiceWithPlayerCountryFormatExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = mockPlayerRequest();
        mockPlayerRequest.setCountry("country1234");
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_COUNTRY_FORMAT_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_COUNTRY_FORMAT_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updatePlayerServiceWithPlayerNotFoundExceptionTest() throws Exception{
        PlayerRequestModel mockPlayerRequest = new PlayerRequestModel();
        mockPlayerRequest.setTeamId(1);
        mockPlayerRequest.setId(111);
        when(teamClientService.validateById(1)).thenReturn(true);
        when(playerRepository.findByIdAndIsActive(1,true)).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.updatePlayer(mockPlayerRequest);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }


    @Test
    public void getAllPlayerTest(){
        when(playerRepository.findAll()).thenReturn(getMockPlayersList());
        List<PlayerRequestModel> list = playerService.getPlayer();
        for (PlayerRequestModel responseList: list) {
            Assert.assertNotNull(responseList.getFirstName());
            Assert.assertNotNull(responseList.getFirstName());
            Assert.assertNotNull(responseList.getCountry());
        }
    }
    @Test
    public void getPlayerByIDTest(){
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        PlayerRequestModel response = playerService.getPlayerById(1l);
        Assert.assertNotNull(response);
    }

    @Test
    public void getPlayerByTeamIdTest(){
        when(playerRepository.findByTeamId(anyInt())).thenReturn(optionalMockPlayerList());
        List<PlayerRequestModel> response = playerService.getPlayerByTeamId(1);
        Assert.assertNotNull(response);
    }

    @Test
    public void getPlayerByTeamIdTesttWithException(){
        doThrow(new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.getPlayerByTeamId(1000);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void getPlayerByIDTestWithException(){
        when(playerRepository.findByIdAndIsActive(100l,false)).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.getPlayerById(1000l);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void deletePlayerTest(){
        when(playerRepository.findByIdAndIsActive(any(Long.class),any(Boolean.class))).thenReturn(optionalMockPlayerRequest());
        boolean isDeleted =  playerService.deletePlayerById(1);
        verify(playerRepository).saveAndFlush(any(PlayerEntity.class));
        Assert.assertEquals(true,isDeleted);
    }
    @Test
    public void deletePlayerTestWithException(){
        when(playerRepository.findByIdAndIsActive(100l,false)).thenReturn(optionalMockPlayerRequest());
        doThrow(new PlayerException(Constants.PLAYER_NOT_FOUND_EXCEPTION)).when(playerRepository).saveAndFlush(any(PlayerEntity.class));
        try {
            playerService.deletePlayerById(1000l);
        } catch (PlayerException ex) {
            assertEquals(Constants.PLAYER_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
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

    private Optional<List<PlayerEntity>> optionalMockPlayerList(){
        List<PlayerEntity> newList = new ArrayList<>();
        PlayerEntity playerRequestModel = new PlayerEntity();
        playerRequestModel.setFirstName("testFn");
        playerRequestModel.setLastName("testLn");
        playerRequestModel.setCountry("testCountry");
        playerRequestModel.setTeamId(1);
        playerRequestModel.setActive(true);
        newList.add(playerRequestModel);
        Optional<List<PlayerEntity>> optionalPlayer = Optional.of(newList);
        return optionalPlayer;
    }

    private Optional<PlayerEntity> optionalMockPlayerRequest(){
        PlayerEntity playerRequestModel = new PlayerEntity();
        playerRequestModel.setFirstName("testFn");
        playerRequestModel.setLastName("testLn");
        playerRequestModel.setCountry("testCountry");
        playerRequestModel.setTeamId(1);
        playerRequestModel.setActive(true);
        Optional<PlayerEntity> optionalPlayer = Optional.of(playerRequestModel);
        return optionalPlayer;
    }
    private PlayerRequestModel mockPlayerRequest(){
        PlayerRequestModel playerRequestModel = new PlayerRequestModel();
        playerRequestModel.setId(1);
        playerRequestModel.setFirstName("testFn");
        playerRequestModel.setLastName("testLn");
        playerRequestModel.setCountry("testCountry");
        playerRequestModel.setTeamId(1);
        playerRequestModel.setActive(true);
        return playerRequestModel;
    }
    private PlayerRequestModel mockPlayerRequestExpected(){
        PlayerRequestModel playerRequestModel = new PlayerRequestModel();
        playerRequestModel.setFirstName("testFn");
        playerRequestModel.setLastName("testLn");
        playerRequestModel.setCountry("testCountry");
        playerRequestModel.setTeamId(1);
        playerRequestModel.setActive(true);
        return playerRequestModel;
    }
    private PlayerEntity mockPlayerEntity(){
        PlayerEntity mockPlayerEntity = new PlayerEntity();
        mockPlayerEntity.setFirstName("testFn");
        mockPlayerEntity.setLastName("testLn");
        mockPlayerEntity.setCountry("testCountry");
        mockPlayerEntity.setTeamId(1);
        mockPlayerEntity.setActive(true);
        return mockPlayerEntity;
    }

    private List<PlayerEntity> getMockPlayersList(){
        List<PlayerEntity> playerRequestModelList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            PlayerEntity playerRequestModel = new PlayerEntity();
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




}
