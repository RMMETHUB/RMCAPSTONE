package com.capstoneproject.ms7matchservicev1.match;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TeamClientService teamClientService;

    @Autowired
    private FieldClientService fieldClientService;

    @Autowired
    private TournamentClientService tournamentClientService;

    @Autowired
    private PlayerClientService playerClientService;

    @Override
    public boolean addMatch(MatchRequestModel matchEntity) {
        try {
            Date date = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT_PATTERN).parse(matchEntity.getDateTime());
            List<MatchEntity> matchEnt = matchRepository.findMatchIfExist(matchEntity.getTournamentId(),matchEntity.getTeamsAId(),matchEntity.getTeamsBId(),date,true);
            boolean isFieldExist = fieldClientService.validateFieldBYId(matchEntity.getFieldId());
            boolean isTournamentExist = tournamentClientService.validateTournamentById(matchEntity.getTournamentId());
            if (!isFieldExist){
                throw new MatchException(Constants.FIELD_NOT_FOUND_EXCEPTION);
            } else if (!isTournamentExist) {
                throw new MatchException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION);
            }else if (!matchEnt.isEmpty()) {
                throw new MatchException(Constants.MATCH_ALREADY_EXIST_EXCEPTION);
            }
            matchRepository.save(new MatchEntity(matchEntity.getFieldId(),date,matchEntity.getTournamentId(),matchEntity.getTeamsAId(),matchEntity.getTeamsBId(),true));
            return true;
        }catch (ParseException parseException){
            throw new MatchException(Constants.INVALID_DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean updateMatch(MatchRequestModel matchEntity) {
        try {
            Date date = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT_PATTERN).parse(matchEntity.getDateTime());
            Optional<MatchEntity> updateMatchEntity = matchRepository.findByIdAndIsActive(matchEntity.getId(),true);
            List<MatchEntity> matchEnt = matchRepository.findMatchIfExist(matchEntity.getTournamentId(),matchEntity.getTeamsAId(),matchEntity.getTeamsBId(),date,true);
            boolean isFieldExist = fieldClientService.validateFieldBYId(matchEntity.getFieldId());
            boolean isTournamentExist = tournamentClientService.validateTournamentById(matchEntity.getTournamentId());
            if (updateMatchEntity.isEmpty()){
                throw new MatchException(Constants.MATCH_NOT_FOUND_EXCEPTION);
            }else if (!isFieldExist){
                throw new MatchException(Constants.FIELD_NOT_FOUND_EXCEPTION);
            } else if (!isTournamentExist) {
                throw new MatchException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION);
            }else if (!matchEnt.isEmpty()) {
                throw new MatchException(Constants.MATCH_ALREADY_EXIST_EXCEPTION);
            }
            updateMatchEntity.get().setFieldId(matchEntity.getFieldId());
            updateMatchEntity.get().setDateTime(date);
            updateMatchEntity.get().setTournamentId(matchEntity.getTournamentId());
            updateMatchEntity.get().setTeamAId(matchEntity.getTeamsAId());
            updateMatchEntity.get().setTeamBId(matchEntity.getTeamsBId());
            matchRepository.save(updateMatchEntity.get());
            return true;
        }catch (ParseException exception){
            throw new MatchException(Constants.INVALID_DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean deleteMatchById(Long id) {
        Optional<MatchEntity> matchEntity = matchRepository.findByIdAndIsActive(id,true);
        if(matchEntity.isEmpty()){
            throw new MatchException(Constants.MATCH_NOT_FOUND_EXCEPTION);
        }else{
            matchEntity.get().setActive(false);
            matchRepository.save(matchEntity.get());
            return true;
        }
    }
    @Override
    public boolean validateMatchById(Long id) {
        Optional<MatchEntity> findById = matchRepository.findByIdAndIsActive(id,true);
        return findById.isPresent();
    }

    @Override
    public MatchResponseModel getMatchById(Long id){
        Optional<MatchEntity> matchEntities = matchRepository.findByIdAndIsActive(id,true);
        MatchResponseModel responseModel = new MatchResponseModel();
        if (matchEntities.isPresent()){
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT_PATTERN);
            String formattedDate = formatter.format(matchEntities.get().getDateTime());
            FieldDTO fieldDTO = fieldClientService.getFieldById(matchEntities.get().getFieldId());
            TournamentDTO tournamentDTO = tournamentClientService.getTournament(matchEntities.get().getTournamentId());
            TeamResponseModel teamResponseModelA = teamClientService.getTeamById(matchEntities.get().getTeamAId());
            TeamResponseModel teamResponseModelB = teamClientService.getTeamById(matchEntities.get().getTeamBId());
            responseModel.setId(matchEntities.get().getId());
            responseModel.setActive(matchEntities.get().isActive());
            responseModel.setDateTime(formattedDate);
            responseModel.setField(fieldDTO);
            responseModel.setTournament(tournamentDTO);
            responseModel.setTeamsA(teamResponseModelA);
            responseModel.setTeamB(teamResponseModelB);
            return responseModel;
        }else {
            throw new MatchException(Constants.MATCH_NOT_FOUND_EXCEPTION);
        }

    }
    @Override
    public MatchResponseForTicketRequestModel getMatchResponseForTicketRequestModel(Long id){
        Optional<MatchEntity> matchEntities = matchRepository.findByIdAndIsActive(id,true);
        MatchResponseForTicketRequestModel responseModel = new MatchResponseForTicketRequestModel();
        if (matchEntities.isPresent()){
            FieldDTO fieldDTO = fieldClientService.getFieldById(matchEntities.get().getFieldId());
            TournamentDTO tournamentDTO = tournamentClientService.getTournament(matchEntities.get().getTournamentId());
            TeamResponseModel teamResponseModelA = teamClientService.getTeamById(matchEntities.get().getTeamAId());
            TeamResponseModel teamResponseModelB = teamClientService.getTeamById(matchEntities.get().getTeamBId());
            responseModel.setFieldName(fieldDTO.getFieldName());
            responseModel.setMatchId(matchEntities.get().getId());
            responseModel.setDateTime(matchEntities.get().getDateTime());
            responseModel.setTournamentName(tournamentDTO.getTournamentName());
            responseModel.setMatchName(teamResponseModelA.getTeamName()+" Vs "+teamResponseModelB.getTeamName());
        }
        return responseModel;
    }

    @Override
    public List<MatchResponseModel> getAllMatch() {
        Optional<List<MatchEntity>> matchEntities = matchRepository.findByIsActive(true);
        List<MatchResponseModel> responseModelList = new ArrayList<>();
        if (matchEntities.isPresent()){
            for (MatchEntity listOfMatches: matchEntities.get()) {
                SimpleDateFormat formatter = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT_PATTERN);
                String formattedDate = formatter.format(listOfMatches.getDateTime());
                MatchResponseModel responseModel = new MatchResponseModel();
                FieldDTO fieldDTO = fieldClientService.getFieldById(listOfMatches.getFieldId());
                TournamentDTO tournamentDTO = tournamentClientService.getTournament(listOfMatches.getTournamentId());
                TeamResponseModel teamResponseModelA = teamClientService.getTeamById(listOfMatches.getTeamAId());
                TeamResponseModel teamResponseModelB = teamClientService.getTeamById(listOfMatches.getTeamBId());
                List<PlayerRequestModel> listOfPlayerA = playerClientService.getPlayerByTeamId(listOfMatches.getTeamAId());
                List<PlayerRequestModel> listOfPlayerB = playerClientService.getPlayerByTeamId(listOfMatches.getTeamBId());
                teamResponseModelA.setPlayers(listOfPlayerA);
                teamResponseModelB.setPlayers(listOfPlayerB);
                responseModel.setId(listOfMatches.getId());
                responseModel.setActive(listOfMatches.isActive());
                responseModel.setDateTime(formattedDate);
                responseModel.setField(fieldDTO);
                responseModel.setTournament(tournamentDTO);
                responseModel.setTeamsA(teamResponseModelA);
                responseModel.setTeamB(teamResponseModelB);
                responseModelList.add(responseModel);
            }
        }

        return responseModelList;
    }


}
