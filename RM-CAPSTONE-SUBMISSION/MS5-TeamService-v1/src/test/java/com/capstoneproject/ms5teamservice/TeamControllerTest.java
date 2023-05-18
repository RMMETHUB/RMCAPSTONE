package com.capstoneproject.ms5teamservice;

import com.capstoneproject.ms5teamservice.Team.TeamController;
import com.capstoneproject.ms5teamservice.Team.TeamRequestModel;
import com.capstoneproject.ms5teamservice.Team.TeamResponseModel;
import com.capstoneproject.ms5teamservice.Team.TeamServiceImpl;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TeamControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    TeamController teamController;

    @Mock
    TeamServiceImpl teamService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(teamController).setCustomArgumentResolvers().build();
    }

    @Test
    public void saveTeamTest() throws Exception{
        when(teamService.addTeam(any(TeamRequestModel.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/team/v1/addTeam")
                        .contentType("application/json")
                        .content(asJsonString(mockTeamRequest())))
                .andExpect(status().is(201));
    }
    @Test
    public void updateTeamTest() throws Exception{
        when(teamService.updateTeam(any(TeamRequestModel.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8081/team/v1/updateTeam")
                        .contentType("application/json")
                        .content(asJsonString(mockTeamRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteTeamTest() throws Exception{
        when(teamService.deleteTeamById(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8081/team/v1/deleteTeam?id="+1l)
                        .contentType("application/json")
                        .content(asJsonString(mockTeamRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void getAllTeamTest() throws Exception{
        when(teamService.getTeam()).thenReturn(mockTeamsList());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/team/v1/getTeam"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTeamByIdTest()throws Exception{
        when(teamService.findById(anyInt())).thenReturn(mockTeamResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/team/v1/getTeamById?id="+1)
                        .contentType("application/json")
                        .content(asJsonString(mockTeamRequest())))
                .andExpect(status().is(200));
    }
    @Test
    public void validateByIdTeamService()throws Exception{
        when(teamService.valiteById(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/team/v1/validateTeamById?id="+1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    private TeamResponseModel mockTeamResponse(){
        TeamResponseModel teamResponseModel = new TeamResponseModel();
        teamResponseModel.setTeamName("teamA");
        teamResponseModel.setActive(true);
        return teamResponseModel;
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



    private TeamRequestModel mockTeamRequest(){
        TeamRequestModel teamRequestModel = new TeamRequestModel();
        teamRequestModel.setId(1);
        teamRequestModel.setTeamName("teamA");
        teamRequestModel.setActive(true);
        return teamRequestModel;
    }

    private static String  asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
