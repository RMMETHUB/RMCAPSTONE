package com.capstoneproject.ms7matchservicev1.match;


import com.capstoneproject.ms7matchservicev1.common.APIMapping;
import com.capstoneproject.ms7matchservicev1.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_MATCH+APIMapping.VERSION)
public class MatchController {

    @Autowired
    MatchService matchService;
    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_MATCH)
    public ResponseEntity<HashMap<String,String>> saveMatch(@RequestBody MatchRequestModel request){
        HashMap<String,String> message = new HashMap<>();
        matchService.addMatch(request);
        message.put(Constants.MESSAGE,Constants.ADD_MATCH_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping(APIMapping.PUT_MAPPING_ENDPOINT_UPDATE_MATCH)
    public ResponseEntity<HashMap<String,String>> updateTournament(@RequestBody MatchRequestModel requestModel) {
        HashMap<String,String> message = new HashMap<>();
        matchService.updateMatch(requestModel);
        message.put(Constants.MESSAGE,Constants.UPDATE_MATCH_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @DeleteMapping(APIMapping.DELETE_MAPPING_ENDPOINT_DELETE_MATCH)
    public ResponseEntity<HashMap<String,String>> deleteTeam(@RequestParam Long id) {
        HashMap<String,String> message = new HashMap<>();
        matchService.deleteMatchById(id);
        message.put(Constants.MESSAGE,Constants.DELETE_MATCH_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_MATCH_BY_ID)
    public ResponseEntity<Boolean> validateById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(matchService.validateMatchById(id));
    }
    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_MATCH)
    public ResponseEntity<List<MatchResponseModel>> getMatch() {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.getAllMatch());
    }
    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_MATCH_REQUEST_BY_ID)
    public ResponseEntity<MatchResponseForTicketRequestModel> getMatchRequestById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.getMatchResponseForTicketRequestModel(id));
    }

}
