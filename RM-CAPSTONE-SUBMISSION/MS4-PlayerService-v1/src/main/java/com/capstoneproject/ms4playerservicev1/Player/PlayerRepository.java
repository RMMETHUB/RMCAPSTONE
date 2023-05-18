package com.capstoneproject.ms4playerservicev1.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {

    PlayerEntity findByFirstNameAndLastName(String firstName,String lastName);

    Optional<PlayerEntity> findByIdAndIsActive(long id, boolean active);

    @Query(value = "SELECT u FROM PlayerEntity u WHERE u.teamId = :id AND u.isActive = true")
    Optional<List<PlayerEntity>> findByTeamId(int id);

}
