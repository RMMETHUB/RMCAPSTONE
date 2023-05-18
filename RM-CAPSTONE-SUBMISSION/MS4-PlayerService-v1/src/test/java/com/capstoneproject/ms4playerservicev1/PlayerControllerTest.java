package com.capstoneproject.ms4playerservicev1;

import com.capstoneproject.ms4playerservicev1.Player.PlayerController;
import com.capstoneproject.ms4playerservicev1.Player.PlayerRequestModel;
import com.capstoneproject.ms4playerservicev1.Player.PlayerService;
import com.capstoneproject.ms4playerservicev1.Player.PlayerServiceImpl;



import com.capstoneproject.ms4playerservicev1.common.exception.PlayerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PlayerControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private PlayerController playerController;
    @Mock
    private PlayerServiceImpl playerService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController).setCustomArgumentResolvers().build();
    }
    @Test
    public void savePlayerTest() throws Exception{
        when(playerService.addPlayer(any(PlayerRequestModel.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/player/v1/addPlayer")
                        .contentType("application/json")
                        .content(asJsonString(mockPlayerRequest())))
                .andExpect(status().is(201));

    }

    @Test
    public void updatePlayerTest() throws Exception{
        when(playerService.updatePlayer(any(PlayerRequestModel.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8081/player/v1/updatePlayer")
                        .contentType("application/json")
                        .content(asJsonString(mockPlayerRequest())))
                .andExpect(status().is(200));
    }
    @Test
    public void deletePlayerTest() throws Exception{
        when(playerService.deletePlayerById(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8081/player/v1/deletePlayer?id="+1l)
                        .contentType("application/json")
                        .content(asJsonString(mockPlayerRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void getAllPlayerByTeamIdTest() throws Exception{
        when(playerService.getPlayerById(anyLong())).thenReturn(mockPlayerRequest());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/player/v1/getPlayerByTeamId?id="+1)
                        .contentType("application/json")
                        .content(asJsonString(mockPlayerRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void getAllPlayerTest() throws Exception{
        when(playerService.getPlayer()).thenReturn(getMockPlayers());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/player/v1/getPlayer"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPlayerByIdTest() throws Exception{
        when(playerService.getPlayerById(anyLong())).thenReturn(mockPlayerRequest());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/player/v1/getPlayerById?id="+1l)
                        .contentType("application/json")
                        .content(asJsonString(mockPlayerRequest())))
                .andExpect(status().is(200));
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


    public PlayerRequestModel mockPlayerRequestWithError(){
        PlayerRequestModel playerRequestModel = new PlayerRequestModel();
        playerRequestModel.setFirstName("t123estFn12");
        playerRequestModel.setLastName("tes123tLn12");
        playerRequestModel.setCountry("testC123ountry12");
        playerRequestModel.setTeamId(1);
        playerRequestModel.setActive(true);
        return playerRequestModel;
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
    private static String  asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
