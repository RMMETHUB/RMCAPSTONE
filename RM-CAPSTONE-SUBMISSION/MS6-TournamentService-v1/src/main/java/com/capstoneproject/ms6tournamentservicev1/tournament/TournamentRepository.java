package com.capstoneproject.ms6tournamentservicev1.tournament;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament,Long>{

    Optional<Tournament> findByTournamentName(String tournamentName);

    Optional<Tournament> findByIdAndIsActive(Long id,boolean isActive);

    Optional<List<Tournament>> findByIsActive(boolean isActive);




}
