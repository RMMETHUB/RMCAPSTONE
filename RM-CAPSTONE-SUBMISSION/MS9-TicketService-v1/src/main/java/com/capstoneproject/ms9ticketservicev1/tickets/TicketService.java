package com.capstoneproject.ms9ticketservicev1.tickets;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TicketService {
    boolean addTicket(TicketRequestModel ticketEntity);
    boolean updateTicket(TicketRequestModel ticketEntity);
    boolean deleteTicketById(Long id);
    TicketResponseModel getTicketById(Long id);
    List<TicketResponseModel> getAllTicket(HttpServletRequest request);

}
