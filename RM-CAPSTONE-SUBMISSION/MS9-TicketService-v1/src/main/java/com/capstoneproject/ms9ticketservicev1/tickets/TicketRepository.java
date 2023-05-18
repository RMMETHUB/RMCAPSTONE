package com.capstoneproject.ms9ticketservicev1.tickets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {

    Optional<TicketEntity> findByIdAndIsActive(Long id, boolean isActive);

    Optional<List<TicketEntity>> findByIsActive(boolean isActive);

}
