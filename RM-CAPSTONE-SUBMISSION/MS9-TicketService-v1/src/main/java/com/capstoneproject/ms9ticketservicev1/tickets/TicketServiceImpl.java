package com.capstoneproject.ms9ticketservicev1.tickets;
import com.capstoneproject.ms9ticketservicev1.common.Constants;
import com.capstoneproject.ms9ticketservicev1.exception.TicketException;
import com.capstoneproject.ms9ticketservicev1.feign_client.MatchClientService;
import com.capstoneproject.ms9ticketservicev1.feign_client.MatchResponseForTicketRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MatchClientService matchClientService;

    @Override
    public boolean addTicket(TicketRequestModel ticketModel){
        boolean isMatchExist = matchClientService.validateById(ticketModel.getMatchId());
        Optional<TicketEntity> ticketEntity = ticketRepository.findByIdAndIsActive(ticketModel.getId(),true);
        if(!isMatchExist){
            throw new TicketException(Constants.MATCH_NOT_FOUND_EXCEPTION);
        }else if (ticketEntity.isPresent()){
            throw new TicketException(Constants.TICKET_ALREADY_EXIST_EXCEPTION);
        }else if (ticketModel.getCustomerName().isEmpty()){
            throw new TicketException(Constants.TICKET_CUSTOMER_NAME_MANDATORY_EXCEPTION);
        }else if (ticketModel.getTicketPrice() == 0){
            throw new TicketException(Constants.TICKET_PRICE_MANDATORY_EXCEPTION);
        } else{
            ticketRepository.save(new TicketEntity(ticketModel.getCustomerName(), ticketModel.getMatchId(), ticketModel.getTicketPrice(),true));
            return true;
        }
    }

    @Override
    public boolean updateTicket(TicketRequestModel ticketModel){
        boolean isMatchExist = matchClientService.validateById(ticketModel.getMatchId());
        Optional<TicketEntity> ticketEntity = ticketRepository.findByIdAndIsActive(ticketModel.getId(),true);
        if(!isMatchExist){
            throw new TicketException(Constants.MATCH_NOT_FOUND_EXCEPTION);
        }else if (ticketEntity.isEmpty()){
            throw new TicketException(Constants.TICKET_NOT_FOUND_EXCEPTION);
        }else if (ticketModel.getCustomerName().isEmpty()){
            throw new TicketException(Constants.TICKET_CUSTOMER_NAME_MANDATORY_EXCEPTION);
        }else if (ticketModel.getTicketPrice() == 0){
            throw new TicketException(Constants.TICKET_PRICE_MANDATORY_EXCEPTION);
        } else{
            ticketEntity.get().setCustomerName(ticketModel.getCustomerName());
            ticketEntity.get().setMatchEntityId(ticketModel.getMatchId());
            ticketEntity.get().setTicketPrice(ticketModel.getTicketPrice());
            ticketEntity.get().setActive(true);
            ticketRepository.save(ticketEntity.get());
            return true;
        }
    }

    @Override
    public boolean deleteTicketById(Long id){
        Optional<TicketEntity> findById = ticketRepository.findByIdAndIsActive(id,true);
        if(findById.isEmpty()){
            throw new TicketException(Constants.TOURNAMENT_NOT_FOUND_EXCEPTION);
        }else{
            findById.get().setActive(false);
            ticketRepository.save(findById.get());
            return true;
        }
    }

    @Override
    public TicketResponseModel getTicketById(Long id) {
        Optional<TicketEntity> ticketEntity = ticketRepository.findByIdAndIsActive(id,true);
        TicketResponseModel ticketResponseModel = new TicketResponseModel();
        if (ticketEntity.isPresent()) {
            MatchResponseForTicketRequestModel response = matchClientService.getMatchRequestById(ticketEntity.get().getMatchEntityId());
            ticketResponseModel.setTicketId(ticketEntity.get().getId());
            ticketResponseModel.setCustomerName(ticketEntity.get().getCustomerName());
            ticketResponseModel.setTicketPrice(ticketEntity.get().getTicketPrice());
            ticketResponseModel.setTicketId(ticketEntity.get().getMatchEntityId());
            ticketResponseModel.setActive(ticketEntity.get().isActive());
            ticketResponseModel.setMatch(response);
            return ticketResponseModel;
        }else{
            throw new TicketException(Constants.TICKET_NOT_FOUND_EXCEPTION);
        }

    }

    @Override
    public List<TicketResponseModel> getAllTicket(HttpServletRequest request){
        Optional<List<TicketEntity>> ticketEntityList = ticketRepository.findByIsActive(true);
        List<TicketResponseModel> ticketResponseModelList = new ArrayList<>();
        if (ticketEntityList.isPresent()) {
            for (TicketEntity listOfTickets : ticketEntityList.get()) {
                MatchResponseForTicketRequestModel response = matchClientService.getMatchRequestById(listOfTickets.getMatchEntityId());
                TicketResponseModel ticketResponseModel = new TicketResponseModel();
                ticketResponseModel.setTicketId(listOfTickets.getId());
                ticketResponseModel.setCustomerName(listOfTickets.getCustomerName());
                ticketResponseModel.setTicketPrice(listOfTickets.getTicketPrice());
                ticketResponseModel.setTicketId(listOfTickets.getId());
                ticketResponseModel.setActive(listOfTickets.isActive());
                ticketResponseModel.setMatch(response);
                ticketResponseModelList.add(ticketResponseModel);
            }
        }
        return ticketResponseModelList;
    }


}
