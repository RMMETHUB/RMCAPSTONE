package com.capstoneproject.ms7matchservicev1.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long>{


    Optional<MatchEntity> findByIdAndIsActive(Long id,boolean isActive);
    @Query(value = "SELECT u FROM MatchEntity u WHERE u.tournamentId = :id AND u.teamAId = :teamAId AND u.teamBId = :teamBId AND u.dateTime = :matchDate AND u.isActive = :isActive")
    List<MatchEntity> findMatchIfExist(Long id, int teamAId, int teamBId, Date matchDate, boolean isActive);

    Optional<List<MatchEntity>> findByIsActive(boolean isActive);

}
