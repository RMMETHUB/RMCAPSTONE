package com.capstoneproject.ms7matchservicev1;

import com.capstoneproject.ms7matchservicev1.fiegn_client.field.FieldDTO;
import com.capstoneproject.ms7matchservicev1.fiegn_client.team.TeamResponseModel;
import com.capstoneproject.ms7matchservicev1.fiegn_client.tournament.TournamentDTO;
import com.capstoneproject.ms7matchservicev1.match.*;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class MatchControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    MatchController matchController;

    @Mock
    MatchServiceImpl matchService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matchController).setCustomArgumentResolvers().build();
    }


    @Test
    public void saveMatchTest() throws Exception{
        when(matchService.addMatch(any(MatchRequestModel.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/match/v1/addMatch")
                        .contentType("application/json")
                        .content(asJsonString(matchRequest())))
                .andExpect(status().is(201));
    }

    @Test
    public void updateMatchTest() throws Exception{
        when(matchService.updateMatch(any(MatchRequestModel.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8081/match/v1/updateMatch")
                        .contentType("application/json")
                        .content(asJsonString(matchRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteMatchTest() throws Exception{
        when(matchService.deleteMatchById(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8082/match/v1/deleteMatch?id="+1l)
                        .contentType("application/json")
                        .content(asJsonString(matchRequest())))
                .andExpect(status().is(200));
    }
    @Test
    public void getAllMatchesTest() throws Exception{
        when(matchService.getAllMatch()).thenReturn(matchResponseModelsRequest());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/match/v1/getMatch"))
                .andExpect(status().isOk());
    }
    @Test
    public void validateById() throws Exception{
        when(matchService.validateMatchById(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/match/v1/validateMatchById?id="+1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void getMatchRequestById() throws Exception{
        when(matchService.getMatchResponseForTicketRequestModel(anyLong())).thenReturn(matchResponseForTicketRequestModel());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/match/v1/getMatchRequestById?id="+1l))
                .andExpect(status().isOk());
    }



    private MatchResponseForTicketRequestModel matchResponseForTicketRequestModel(){
        MatchResponseForTicketRequestModel matchRequestModel = new MatchResponseForTicketRequestModel();
        matchRequestModel.setMatchId(1);
        matchRequestModel.setMatchName("test");
        matchRequestModel.setTournamentName("test");
        matchRequestModel.setDateTime(new Date());
        return matchRequestModel;
    }

    private MatchRequestModel matchRequest(){
        MatchRequestModel matchRequestModel = new MatchRequestModel();
        matchRequestModel.setId(1);
        matchRequestModel.setTeamsAId(1);
        matchRequestModel.setTeamsBId(2);
        matchRequestModel.setDateTime("05/28/2023");
        matchRequestModel.setFieldId(1);
        matchRequestModel.setTournamentId(1l);
        matchRequestModel.setActive(true);
        return matchRequestModel;
    }

    private List<MatchResponseModel> matchResponseModelsRequest(){
        List<MatchResponseModel> list = new ArrayList<>();
        MatchResponseModel matchResponseModel = new MatchResponseModel();
        matchResponseModel.setId(1);
        matchResponseModel.setTeamsA(new TeamResponseModel());
        matchResponseModel.setTeamB(new TeamResponseModel());
        matchResponseModel.setDateTime("05/28/2023");
        matchResponseModel.setField(new FieldDTO());
        matchResponseModel.setTournament(new TournamentDTO());
        matchResponseModel.setActive(true);
        list.add(matchResponseModel);
        return list;
    }

    private static String  asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
