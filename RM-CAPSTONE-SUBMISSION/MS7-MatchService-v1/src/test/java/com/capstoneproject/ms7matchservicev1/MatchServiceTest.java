package com.capstoneproject.ms7matchservicev1;

import com.capstoneproject.ms7matchservicev1.common.Constants;
import com.capstoneproject.ms7matchservicev1.exception.MatchException;
import com.capstoneproject.ms7matchservicev1.fiegn_client.field.FieldClientService;
import com.capstoneproject.ms7matchservicev1.fiegn_client.field.FieldDTO;
import com.capstoneproject.ms7matchservicev1.fiegn_client.player.PlayerClientService;
import com.capstoneproject.ms7matchservicev1.fiegn_client.player.PlayerRequestModel;
import com.capstoneproject.ms7matchservicev1.fiegn_client.team.TeamClientService;
import com.capstoneproject.ms7matchservicev1.fiegn_client.team.TeamResponseModel;
import com.capstoneproject.ms7matchservicev1.fiegn_client.tournament.TournamentClientService;
import com.capstoneproject.ms7matchservicev1.fiegn_client.tournament.TournamentDTO;
import com.capstoneproject.ms7matchservicev1.match.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
@SpringBootTest
public class MatchServiceTest {
    @InjectMocks
    MatchServiceImpl matchService;
    @Mock
    MatchRepository matchRepository;
    @Mock
    private FieldClientService fieldClientService;
    @Mock
    private TournamentClientService tournamentClientService;
    @Mock
    private PlayerClientService playerClientService;
    @Mock
    private TeamClientService teamClientService;
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void addMatchServiceTest(){
        MatchRequestModel mockMatchRequestModel = matchRequest();
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        when(matchService.addMatch(mockMatchRequestModel)).thenReturn(true);
        boolean isSaved = matchService.addMatch(mockMatchRequestModel);
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void addMatchServiceTestWithFieldNotFoundExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        Mockito.doThrow(new MatchException(Constants.FIELD_NOT_FOUND_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.addMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.FIELD_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addMatchServiceTestWithTournamentNotFoundExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        Mockito.doThrow(new MatchException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.addMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addMatchServiceTestWithMatchAlreadyExistExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(matchRepository.findMatchIfExist(anyLong(),anyInt(),anyInt(),any(),anyBoolean())).thenReturn(matchEntityList());
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        Mockito.doThrow(new MatchException(Constants.MATCH_ALREADY_EXIST_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.addMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.MATCH_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }

    @Test(expected = MatchException.class)
    public void addMatchServiceTestWithDateParseExceptionExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        mockMatchRequest.setDateTime("2021-15-40");
        when(matchService.addMatch(mockMatchRequest)).thenThrow(new MatchException("Invalid date format please follow dd/MM/yyyy format"));
        matchService.addMatch(mockMatchRequest);
    }

    @Test
    public void updateMatchServiceTest(){
        MatchRequestModel mockMatchRequestModel = matchRequest();
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        boolean isSaved = matchService.updateMatch(mockMatchRequestModel);
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void updateMatchServiceTestWithMatchNotFoundExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        Mockito.doThrow(new MatchException(Constants.MATCH_NOT_FOUND_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.updateMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.MATCH_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateMatchServiceTestWithFieldNotFoundExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        Mockito.doThrow(new MatchException(Constants.FIELD_NOT_FOUND_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.updateMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.FIELD_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateMatchServiceTestWithTournamentNotFoundExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        Mockito.doThrow(new MatchException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.updateMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateMatchServiceTestWithMatchAlreadyExistExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(fieldClientService.validateFieldBYId(anyInt())).thenReturn(true);
        when(tournamentClientService.validateTournamentById(anyLong())).thenReturn(true);
        when(matchRepository.findMatchIfExist(anyLong(),anyInt(),anyInt(),any(),anyBoolean())).thenReturn(matchEntityList());
        Mockito.doThrow(new MatchException(Constants.MATCH_ALREADY_EXIST_EXCEPTION)).when(matchRepository).saveAndFlush(any(MatchEntity.class));
        try {
            matchService.updateMatch(mockMatchRequest);
        } catch (MatchException ex) {
            assertEquals(Constants.MATCH_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }

    @Test(expected = MatchException.class)
    public void updateMatchServiceTestWithDateParseExceptionExceptionTest() throws Exception{
        MatchRequestModel mockMatchRequest = matchRequest();
        mockMatchRequest.setDateTime("2021-15-40");
        when(matchService.updateMatch(mockMatchRequest)).thenThrow(new MatchException("Invalid date format please follow dd/MM/yyyy format"));
        matchService.updateMatch(mockMatchRequest);
    }

    @Test
    public void deleteMatchTest(){
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        boolean isDeleted =  matchService.deleteMatchById(1l);
        Assert.assertEquals(true,isDeleted);
    }

    @Test
    public void deleteFieldWithExceptionTest(){
        doThrow(new MatchException(Constants.MATCH_NOT_FOUND_EXCEPTION)).when(matchRepository).save(any(MatchEntity.class));
        try {
            matchService.deleteMatchById(1l);
        } catch (MatchException ex) {
            assertEquals(Constants.MATCH_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void validateMatchByIdTest(){
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        boolean isExisting = matchService.validateMatchById(1l);
        Assert.assertEquals(true,isExisting);
    }

    @Test
    public void getAllMatchTest(){
        when(matchRepository.findByIsActive(anyBoolean())).thenReturn(listOptional());
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(fieldClientService.getFieldById(anyInt())).thenReturn(mockFieldRequest());
        when(tournamentClientService.getTournament(anyLong())).thenReturn(mockTournamentRequest());
        when(teamClientService.getTeamById(anyInt())).thenReturn(mockTeamRequestA(),mockTeamRequestB());
        when(playerClientService.getPlayerByTeamId(anyInt())).thenReturn(getMockPlayers());
        List<MatchResponseModel> actual = matchService.getAllMatch();
        for (MatchResponseModel matchResponseModel: actual) {
            MatchResponseModel model = matchRequestModel1();
            Assert.assertEquals(model.getId(),matchResponseModel.getId());
            Assert.assertEquals(model.getTeamsA().getTeamName(),matchResponseModel.getTeamsA().getTeamName());
            Assert.assertEquals(model.getTeamB().getTeamName(),matchResponseModel.getTeamB().getTeamName());
            Assert.assertEquals(model.getField().getFieldName(),matchResponseModel.getField().getFieldName());
            Assert.assertEquals(model.getTournament().getTournamentName(),matchResponseModel.getTournament().getTournamentName());
            Assert.assertEquals(model.getDateTime(),matchResponseModel.getDateTime());
        }

    }

    public Optional<List<MatchEntity>> listOptional(){
        Optional<List<MatchEntity>> optionalPlayer = Optional.of(matchEntityList());
        return optionalPlayer;
    }

    private MatchResponseModel matchRequestModel1(){
        MatchResponseModel matchRequestModel = new MatchResponseModel();
        matchRequestModel.setId(1);
        matchRequestModel.setTeamsA(mockTeamRequestA());
        matchRequestModel.setTeamB(mockTeamRequestB());
        matchRequestModel.setDateTime("18/05/2023");
        matchRequestModel.setField(mockFieldRequest());
        matchRequestModel.setTournament(mockTournamentRequest());
        matchRequestModel.setActive(true);
        return matchRequestModel;
    }

    private MatchResponseModel matchRequestModel(){
        MatchResponseModel matchRequestModel = new MatchResponseModel();
        matchRequestModel.setId(1);
        matchRequestModel.setTeamsA(mockTeamRequestA());
        matchRequestModel.setTeamB(mockTeamRequestB());
        matchRequestModel.setDateTime("17/05/2023");
        matchRequestModel.setField(mockFieldRequest());
        matchRequestModel.setTournament(mockTournamentRequest());
        matchRequestModel.setActive(true);
        return matchRequestModel;
    }

    @Test
    public void getAllMatchByIdTest(){
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(fieldClientService.getFieldById(anyInt())).thenReturn(mockFieldRequest());
        when(tournamentClientService.getTournament(anyLong())).thenReturn(mockTournamentRequest());
        when(teamClientService.getTeamById(anyInt())).thenReturn(mockTeamRequestA());
        when(playerClientService.getPlayerByTeamId(1)).thenReturn(getMockPlayers());
        MatchResponseModel list = matchService.getMatchById(1l);
        Assert.assertNotNull(list.getDateTime());
        Assert.assertNotNull(list.getField());
        Assert.assertNotNull(list.getTournament());
        Assert.assertNotNull(list.getTeamsA());
        Assert.assertNotNull(list.getTeamB());
    }

    @Test
    public void getAllMatchByIdWithExceptionTest(){
        doThrow(new MatchException(Constants.MATCH_NOT_FOUND_EXCEPTION)).when(matchRepository).save(any(MatchEntity.class));
        try {
            matchService.getMatchById(1l);
        } catch (MatchException ex) {
            assertEquals(Constants.MATCH_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void getMatchResponseForTicketRequestModelTest(){
        when(matchRepository.findByIdAndIsActive(anyLong(),anyBoolean())).thenReturn(optionalMockMatchRequest());
        when(fieldClientService.getFieldById(anyInt())).thenReturn(mockFieldRequest());
        when(tournamentClientService.getTournament(anyLong())).thenReturn(mockTournamentRequest());
        when(teamClientService.getTeamById(anyInt())).thenReturn(mockTeamRequestA(),mockTeamRequestB());
        when(playerClientService.getPlayerByTeamId(anyInt())).thenReturn(getMockPlayers());
        MatchResponseForTicketRequestModel actual = matchService.getMatchResponseForTicketRequestModel(1l);
        MatchResponseForTicketRequestModel expected = expected();
        Assert.assertEquals(expected.getMatchId(),actual.getMatchId());
        Assert.assertEquals(expected.getMatchName(),actual.getMatchName());
        Assert.assertEquals(expected.getTournamentName(),actual.getTournamentName());
        Assert.assertEquals(expected.getFieldName(),actual.getFieldName());
        Assert.assertEquals(expected.getDateTime(),actual.getDateTime());
    }
    public MatchResponseForTicketRequestModel getMatchResponseForTicketRequestModel(){
        MatchResponseForTicketRequestModel responseModel = new MatchResponseForTicketRequestModel();
        responseModel.setFieldName("testFieldName");
        responseModel.setMatchId(1);
        responseModel.setDateTime(new Date(123123l));
        responseModel.setTournamentName("testTournamentName");
        responseModel.setMatchName("team A Vs team B");
        return responseModel;
    }

    public MatchResponseForTicketRequestModel expected(){
        MatchResponseForTicketRequestModel responseModel = new MatchResponseForTicketRequestModel();
        responseModel.setFieldName("testFieldName");
        responseModel.setMatchId(1);
        responseModel.setDateTime(new Date(123123l));
        responseModel.setTournamentName("testTournamentName");
        responseModel.setMatchName("team A Vs team B");
        return responseModel;
    }

    private TeamResponseModel mockTeamRequestA(){
        TeamResponseModel teamRequestModel = new TeamResponseModel();
        teamRequestModel.setTeamName("team A");
        teamRequestModel.setActive(true);
        return teamRequestModel;
    }

    private TeamResponseModel mockTeamRequestB(){
        TeamResponseModel teamRequestModel = new TeamResponseModel();
        teamRequestModel.setTeamName("team B");
        teamRequestModel.setActive(true);
        return teamRequestModel;
    }

    private List<PlayerRequestModel> getMockPlayers(){
        List<PlayerRequestModel> playerRequestModelList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            PlayerRequestModel playerRequestModel = new PlayerRequestModel();
            playerRequestModel.setFirstName("testFn");
            playerRequestModel.setLastName("testLn");
            playerRequestModel.setCountry("testCountry");
            playerRequestModel.setTeamId(1);
            playerRequestModel.setActive(true);
            playerRequestModelList.add(playerRequestModel);
        }
        return playerRequestModelList;
    }

    private TournamentDTO mockTournamentRequest(){
        TournamentDTO tournamentDTO = new TournamentDTO();
        tournamentDTO.setTournamentName("testTournamentName");
        tournamentDTO.setTournamentStyle("testTournamentName");
        tournamentDTO.setSportsCategory("testCategory");
        tournamentDTO.setActive(true);
        tournamentDTO.setActive(true);
        return tournamentDTO;
    }



    private Optional<MatchEntity> optionalMockMatchRequest(){
        Optional<MatchEntity> mockFieldEntity = Optional.of(matchEntity());
        mockFieldEntity.get().setDateTime(new Date(123123l));
        return mockFieldEntity;
    }

    private FieldDTO mockFieldRequest(){
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldAddress("testAddress");
        fieldDTO.setFieldName("testFieldName");
        fieldDTO.setFieldCapacity(1);
        fieldDTO.setActive(true);
        return fieldDTO;
    }


    private MatchEntity matchEntity(){
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(1);
        matchEntity.setTeamAId(1);
        matchEntity.setTeamBId(2);
        matchEntity.setDateTime(new Date());
        matchEntity.setFieldId(1);
        matchEntity.setTournamentId(1l);
        matchEntity.setActive(true);
        return matchEntity;
    }

    private List<MatchEntity> matchEntityList(){
        List<MatchEntity> matchEntityList = new ArrayList<>();
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(1);
        matchEntity.setTeamAId(1);
        matchEntity.setTeamBId(2);
        matchEntity.setDateTime(new Date());
        matchEntity.setFieldId(1);
        matchEntity.setTournamentId(1l);
        matchEntity.setActive(true);
        matchEntityList.add(matchEntity);
        return matchEntityList;
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




}
