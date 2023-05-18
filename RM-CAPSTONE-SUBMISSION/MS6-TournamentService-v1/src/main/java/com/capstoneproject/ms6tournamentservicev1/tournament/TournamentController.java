package com.capstoneproject.ms6tournamentservicev1.tournament;


import com.capstoneproject.ms6tournamentservicev1.common.APIMapping;
import com.capstoneproject.ms6tournamentservicev1.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_TOURNAMENT+APIMapping.VERSION)
public class TournamentController {
    @Autowired
    TournamentService tournamentService;

    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_TOURNAMENT)
    public ResponseEntity<HashMap<String,String>> savePlayer(@RequestBody TournamentDTO
                                                      request){
        HashMap<String,String> message = new HashMap<>();
        tournamentService.addTournament(request);
        message.put(Constants.MESSAGE,Constants.ADD_TOURNAMENT_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping(APIMapping.PUT_MAPPING_ENDPOINT_UPDATE_TOURNAMENT)
    public ResponseEntity<HashMap<String,String>> updateTournament(@RequestBody TournamentDTO requestModel) {
        HashMap<String,String> message = new HashMap<>();
        tournamentService.updateTournament(requestModel);
        message.put(Constants.MESSAGE,Constants.UPDATE_TOURNAMENT_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping(APIMapping.DELETE_MAPPING_ENDPOINT_DELETE_TOURNAMENT)
    public ResponseEntity<HashMap<String,String>> deleteTeam(@RequestParam Long id) {
        HashMap<String,String> message = new HashMap<>();
        tournamentService.deleteTournamentById(id);
        message.put(Constants.MESSAGE,Constants.DELETE_TOURNAMENT_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_TOURNAMENT_BY_ID)
    public ResponseEntity<Boolean> validateTournamentById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tournamentService.validateTournamentById(id));

    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TOURNAMENT)
    public ResponseEntity<List<TournamentDTO>> getTournament() {
        List<TournamentDTO> list = tournamentService.getAllTournaments();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TOURNAMENT_BY_ID)
    public ResponseEntity<TournamentDTO> getTournament(@RequestParam Long id) {
        TournamentDTO tournamentDTO = tournamentService.getTournamentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tournamentDTO);
    }

}
