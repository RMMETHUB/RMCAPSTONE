package com.capstoneproject.ms6tournamentservicev1;

import com.capstoneproject.ms6tournamentservicev1.common.Constants;
import com.capstoneproject.ms6tournamentservicev1.exception.TournamentException;
import com.capstoneproject.ms6tournamentservicev1.tournament.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TournamentServiceTest {


    @InjectMocks
    TournamentServiceImpl tournamentService;

    @Mock
    TournamentRepository tournamentRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTournamentServiceTest(){
        TournamentDTO tournamentDTO = mockTournamentRequest();
        when(tournamentService.addTournament(tournamentDTO)).thenReturn(true);
        boolean isSaved = tournamentService.addTournament(tournamentDTO);
        Assert.assertEquals(true,isSaved);
    }


    @Test
    public void addTournamentServiceTestWithTournamentAlreadyExistExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        when(tournamentRepository.findByTournamentName(anyString())).thenReturn(optionalMockTournamentRequest());
        doThrow(new TournamentException(Constants.TOURNAMENT_ALREADY_EXIST_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.addTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addTournamentServiceTestWithTournamentNameShouldNotBeEmptyExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        tournamentDTO.setTournamentName("");
        doThrow(new TournamentException(Constants.TOURNAMENT_NAME_MANDATORY_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.addTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_NAME_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addTournamentServiceTestWithTournamentCategoryShouldNotBeEmptyExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        tournamentDTO.setSportsCategory("");
        doThrow(new TournamentException(Constants.TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.addTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addTournamentServiceTestWithTournamentStyleShouldNotBeEmptyExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        tournamentDTO.setTournamentStyle("");
        doThrow(new TournamentException(Constants.TOURNAMENT_STYLE_MANDATORY_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.addTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_STYLE_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }


    @Test
    public void updateTournamentServiceTest(){
        TournamentDTO tournamentDTO = mockTournamentRequest();
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        boolean isSaved = tournamentService.updateTournament(tournamentDTO);
        Assert.assertEquals(true,isSaved);
    }


    @Test
    public void updateTournamentServiceTestWithTournamentNotFoundExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        when(tournamentRepository.findByTournamentName(anyString())).thenReturn(optionalMockTournamentRequest());
        doThrow(new TournamentException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.updateTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateTournamentServiceTestWithTournamentNameShouldNotBeEmptyExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        tournamentDTO.setTournamentName("");
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        doThrow(new TournamentException(Constants.TOURNAMENT_NAME_MANDATORY_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.updateTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_NAME_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateTournamentServiceTestWithTournamentCategoryShouldNotBeEmptyExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        tournamentDTO.setSportsCategory("");
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        doThrow(new TournamentException(Constants.TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.updateTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateTournamentServiceTestWithTournamentStyleShouldNotBeEmptyExceptionTest() throws Exception{
        TournamentDTO tournamentDTO = mockTournamentRequest();
        tournamentDTO.setTournamentStyle("");
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        doThrow(new TournamentException(Constants.TOURNAMENT_STYLE_MANDATORY_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.updateTournament(tournamentDTO);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_STYLE_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void deleteFieldTest(){
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        boolean isDeleted =  tournamentService.deleteTournamentById(1l);
        Assert.assertEquals(true,isDeleted);
    }

    @Test
    public void deleteFieldWithExceptionTest(){
        doThrow(new TournamentException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION)).when(tournamentRepository).save(any(Tournament.class));
        try {
            tournamentService.deleteTournamentById(1l);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void getAllTournamentTest(){
        when(tournamentRepository.findByIsActive(true)).thenReturn(optionalMockTournamentRequestList());
        List<TournamentDTO> list = tournamentService.getAllTournaments();
        for (TournamentDTO tournamentDTO: list) {
            Assert.assertNotNull(tournamentDTO.getTournamentName());
            Assert.assertNotNull(tournamentDTO.getTournamentStyle());
            Assert.assertNotNull(tournamentDTO.getSportsCategory());
        }
    }

    @Test
    public void getEmptyTournamentTest(){
        List<TournamentDTO> list = tournamentService.getAllTournaments();
        Assert.assertEquals(0,list.size());
    }

    @Test
    public void getTournamentByIdTest(){
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        TournamentDTO response = tournamentService.getTournamentById(1l);
        Assert.assertNotNull(response);
    }

    @Test
    public void getTournamentByIdWithExceptionTest(){
        doThrow(new TournamentException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION)).when(tournamentRepository).saveAndFlush(any(Tournament.class));
        try {
            tournamentService.getTournamentById(1l);
        } catch (TournamentException ex) {
            assertEquals(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void verifyTournamentTest(){
        when(tournamentRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockTournamentRequest());
        boolean isVerified =  tournamentService.validateTournamentById(1l);
        Assert.assertEquals(true,isVerified);
    }


    private Optional<List<Tournament>> optionalMockTournamentRequestList(){
        List<Tournament> tournaments = new ArrayList<>();
        tournaments.add(mockTournamentEntity());
        Optional<List<Tournament>> optionalFieldDTO = Optional.of(tournaments);
        return optionalFieldDTO;
    }

    private Optional<Tournament> optionalMockTournamentRequest(){
        Optional<Tournament> optionalFieldDTO = Optional.of(mockTournamentEntity());
        return optionalFieldDTO;
    }

    private Tournament mockTournamentEntity(){
        Tournament tournament = new Tournament();
        tournament.setId(1);
        tournament.setTournamentName("testTournamentName");
        tournament.setTournamentStyle("testTournamentStyle");
        tournament.setSportsCategory("testCategory");
        tournament.setActive(true);
        tournament.setActive(true);
        return tournament;
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

}
