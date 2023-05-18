package com.capstoneproject.ms6tournamentservicev1.tournament;


import com.capstoneproject.ms6tournamentservicev1.common.Constants;
import com.capstoneproject.ms6tournamentservicev1.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class TournamentServiceImpl implements TournamentService{
    @Autowired
    TournamentRepository tournamentRepository;
    @Override
    public boolean addTournament(TournamentDTO tournament){
        Optional<Tournament> findByTournamentName = tournamentRepository.findByTournamentName(tournament.getTournamentName());
        if(findByTournamentName.isPresent()){
            throw new TournamentException(Constants.TOURNAMENT_ALREADY_EXIST_EXCEPTION);
        }else if (tournament.getTournamentName().isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_NAME_MANDATORY_EXCEPTION);
        }else if (tournament.getSportsCategory().isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION);
        }else if (tournament.getTournamentStyle().isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_STYLE_MANDATORY_EXCEPTION);
        }else{
            tournamentRepository.save(new Tournament(tournament.getTournamentName(),tournament.getSportsCategory(),tournament.getTournamentStyle(),true));
            return true;
        }
    }

    @Override
    public boolean updateTournament(TournamentDTO requestModel){
        Optional<Tournament> tournament = tournamentRepository.findByIdAndIsActive(requestModel.getId(),true);
        if(tournament.isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION);
        }else if (requestModel.getTournamentName().isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_NAME_MANDATORY_EXCEPTION);
        }else if (requestModel.getSportsCategory().isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_CATEGORY_MANDATORY_EXCEPTION);
        }else if (requestModel.getTournamentStyle().isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_STYLE_MANDATORY_EXCEPTION);
        }else{
            tournament.get().setTournamentName(requestModel.getTournamentName());
            tournament.get().setSportsCategory(requestModel.getSportsCategory());
            tournament.get().setTournamentStyle(requestModel.getTournamentStyle());
            tournamentRepository.save(tournament.get());
            return true;
        }
    }

    @Override
    public boolean deleteTournamentById(Long id){
        Optional<Tournament> findById = tournamentRepository.findByIdAndIsActive(id,true);
        if(findById.isEmpty()){
            throw new TournamentException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION);
        }else{
            findById.get().setActive(false);
            tournamentRepository.save(findById.get());
            return true;
        }
    }

    @Override
    public TournamentDTO getTournamentById(Long id){
        Optional<Tournament> findTournamentById = tournamentRepository.findByIdAndIsActive(id,true);
        if (findTournamentById.isPresent()){
            TournamentDTO tournamentDTO = new TournamentDTO();
            tournamentDTO.setId(findTournamentById.get().getId());
            tournamentDTO.setTournamentName(findTournamentById.get().getTournamentName());
            tournamentDTO.setTournamentStyle(findTournamentById.get().getTournamentStyle());
            tournamentDTO.setSportsCategory(findTournamentById.get().getSportsCategory());
            tournamentDTO.setActive(findTournamentById.get().isActive());
            return tournamentDTO;
        }else {
           throw new TournamentException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public boolean validateTournamentById(Long id){
        Optional<Tournament> findTournamentById = tournamentRepository.findByIdAndIsActive(id,true);
        return findTournamentById.isPresent();
    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        Optional<List<Tournament>> getAllTournaments = tournamentRepository.findByIsActive(true);
        List<TournamentDTO> tournamentDTOS = new ArrayList<>();
        if (getAllTournaments.isPresent()){
            for (Tournament tournament: getAllTournaments.get()) {
                TournamentDTO tournamentDTO = new TournamentDTO();
                tournamentDTO.setId(tournament.getId());
                tournamentDTO.setTournamentName(tournament.getTournamentName());
                tournamentDTO.setTournamentStyle(tournament.getTournamentStyle());
                tournamentDTO.setSportsCategory(tournament.getSportsCategory());
                tournamentDTO.setActive(tournament.isActive());
                tournamentDTOS.add(tournamentDTO);
            }
            return tournamentDTOS;
        }
        return Collections.emptyList();
    }
}
