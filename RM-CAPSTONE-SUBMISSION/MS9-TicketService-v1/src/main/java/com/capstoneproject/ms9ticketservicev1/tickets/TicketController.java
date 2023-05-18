package com.capstoneproject.ms9ticketservicev1.tickets;


import com.capstoneproject.ms9ticketservicev1.common.APIMapping;
import com.capstoneproject.ms9ticketservicev1.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_TICKET+APIMapping.VERSION)
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_TICKET)
    public ResponseEntity<HashMap<String,String>> saveTicket(@RequestBody TicketRequestModel
                                                      request){
        HashMap<String,String> message = new HashMap<>();
        try {
            ticketService.addTicket(request);
            message.put(Constants.MESSAGE,Constants.ADD_TICKET_SUCCESS_MESSAGE);
            message.put(Constants.STATUS, Constants.SUCCESS);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }catch (Exception e){
            message.put(Constants.MESSAGE,e.getMessage());
            message.put(Constants.STATUS, Constants.FAILURE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping(APIMapping.PUT_MAPPING_ENDPOINT_UPDATE_TICKET)
    public ResponseEntity<HashMap<String,String>> updateTournament(@RequestBody TicketRequestModel requestModel) {
        HashMap<String,String> message = new HashMap<>();
        ticketService.updateTicket(requestModel);
        message.put(Constants.MESSAGE,Constants.UPDATE_TICKET_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping(APIMapping.DELETE_MAPPING_ENDPOINT_DELETE_TICKET)
    public ResponseEntity<HashMap<String,String>> deleteTeam(@RequestParam Long id) {
        HashMap<String,String> message = new HashMap<>();
        ticketService.deleteTicketById(id);
        message.put(Constants.MESSAGE,Constants.DELETE_TICKET_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TICKET)
    public ResponseEntity<List<TicketResponseModel>> getTicket(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getAllTicket(request));

    }



}
