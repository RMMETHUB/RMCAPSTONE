package com.capstoneproject.ms6tournamentservicev1.tournament;



import java.util.List;

public interface TournamentService {

    boolean addTournament(TournamentDTO tournament);
    boolean updateTournament(TournamentDTO tournament);
    boolean deleteTournamentById(Long id);
    TournamentDTO getTournamentById(Long id);
    boolean validateTournamentById(Long id);
    List<TournamentDTO> getAllTournaments();

}
