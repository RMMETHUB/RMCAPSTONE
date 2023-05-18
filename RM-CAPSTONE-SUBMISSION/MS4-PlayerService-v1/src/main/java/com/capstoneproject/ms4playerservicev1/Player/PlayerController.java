package com.capstoneproject.ms4playerservicev1.Player;


import com.capstoneproject.ms4playerservicev1.common.APIMapping;
import com.capstoneproject.ms4playerservicev1.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_PLAYER+APIMapping.VERSION)
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_PLAYER)
    public ResponseEntity<HashMap<String,String>> savePlayer(@RequestBody PlayerRequestModel request){
        HashMap<String,String> message = new HashMap<>();
        playerService.addPlayer(request);
        message.put(Constants.MESSAGE,Constants.ADD_PLAYER_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping(APIMapping.PUT_MAPPING_ENDPOINT_UPDATE_PLAYER)
    public ResponseEntity<HashMap<String,String>> updatePlayer(@RequestBody PlayerRequestModel requestModel) {
        HashMap<String,String> message = new HashMap<>();
        playerService.updatePlayer(requestModel);
        message.put(Constants.MESSAGE,Constants.UPDATE_PLAYER_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping(APIMapping.DELETE_MAPPING_ENDPOINT_DELETE_PLAYER)
    public ResponseEntity<HashMap<String,String>> deletePlayer(@RequestParam Long id) {
        HashMap<String,String> message = new HashMap<>();
        playerService.deletePlayerById(id);
        message.put(Constants.MESSAGE,Constants.DELETE_PLAYER_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_PLAYE_BY_ID)
    public ResponseEntity<PlayerRequestModel> getPlayerById(@RequestParam Long id) {
        PlayerRequestModel responseModel = playerService.getPlayerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseModel);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_PLAYE_BY_TEAM_ID)
    public ResponseEntity<List<PlayerRequestModel>> getPlayerByTeamId(@RequestParam Integer id) {
        List<PlayerRequestModel> responseModel = playerService.getPlayerByTeamId(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseModel);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_PLAYER)
    public ResponseEntity<List<PlayerRequestModel>> getPlayer() {
        List<PlayerRequestModel> list = playerService.getPlayer();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
