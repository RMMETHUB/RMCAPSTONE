package com.capstoneproject.ms6tournamentservicev1;

import com.capstoneproject.ms6tournamentservicev1.tournament.Tournament;
import com.capstoneproject.ms6tournamentservicev1.tournament.TournamentController;
import com.capstoneproject.ms6tournamentservicev1.tournament.TournamentDTO;
import com.capstoneproject.ms6tournamentservicev1.tournament.TournamentServiceImpl;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TournamentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    TournamentController tournamentController;

    @Mock
    TournamentServiceImpl tournamentService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tournamentController).setCustomArgumentResolvers().build();
    }
    @Test
    public void savePlayerTest() throws Exception{
        when(tournamentService.addTournament(any(TournamentDTO.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/tournament/v1/addTournament")
                        .contentType("application/json")
                        .content(asJsonString(mockTournamentRequest())))
                .andExpect(status().is(201));
    }

    @Test
    public void updatePlayerTest() throws Exception{
        when(tournamentService.updateTournament(any(TournamentDTO.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8082/tournament/v1/updateTournament")
                        .contentType("application/json")
                        .content(asJsonString(mockTournamentRequest())))
                .andExpect(status().is(200));
    }
    @Test
    public void deleteTournamentTest() throws Exception{
        when(tournamentService.deleteTournamentById(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8082/tournament/v1/deleteTournament?id="+1l)
                        .contentType("application/json")
                        .content(asJsonString(mockTournamentRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void getAllTournamentTest() throws Exception{
        when(tournamentService.getAllTournaments()).thenReturn(getAllTournaments());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/tournament/v1/getTournament"))
                .andExpect(status().isOk());
    }

    @Test
    public void valiteByIdTest() throws Exception{
        when(tournamentService.validateTournamentById(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/tournament/v1/validateTournamentById?id="+1l))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    public List<TournamentDTO> getAllTournaments() {
        List<TournamentDTO> tournamentDTOS = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            TournamentDTO tournamentDTO = new TournamentDTO();
            tournamentDTO.setId(1);
            tournamentDTO.setTournamentName("testTournamentName");
            tournamentDTO.setTournamentStyle("testTournamentName");
            tournamentDTO.setSportsCategory("testCategory");
            tournamentDTO.setActive(true);
            tournamentDTOS.add(tournamentDTO);
        }
        return tournamentDTOS;
    }



    private TournamentDTO mockTournamentRequest(){
        TournamentDTO tournamentDTO = new TournamentDTO();
        tournamentDTO.setId(1);
        tournamentDTO.setTournamentName("testTournamentName");
        tournamentDTO.setTournamentStyle("testTournamentName");
        tournamentDTO.setSportsCategory("testCategory");
        tournamentDTO.setActive(true);
        tournamentDTO.setActive(true);
        return tournamentDTO;
    }


    private static String  asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
