package com.capstoneproject.ms5teamservice.Team;

import com.capstoneproject.ms5teamservice.common.APIMapping;
import com.capstoneproject.ms5teamservice.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_TEAM+APIMapping.VERSION)
public class TeamController {

    @Autowired
    TeamService teamService;
    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_TEAM)
    public ResponseEntity<HashMap<String,String>> saveTeam(@RequestBody TeamRequestModel teamRequestModel) {
        HashMap<String,String> message = new HashMap<>();
        teamService.addTeam(teamRequestModel);
        message.put(Constants.MESSAGE,Constants.ADD_TEAM_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);

    }

    @PutMapping(APIMapping.POST_MAPPING_ENDPOINT_UPDATE_TEAM)
    public ResponseEntity<HashMap<String,String>> updateTeam(@RequestBody TeamRequestModel requestModel) {
        HashMap<String,String> message = new HashMap<>();
        teamService.updateTeam(requestModel);
        message.put(Constants.MESSAGE,Constants.UPDATE_TEAM_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping(APIMapping.POST_MAPPING_ENDPOINT_DELETE_TEAM)
    public ResponseEntity<HashMap<String,String>> deleteTeam(@RequestParam Integer id) {
        HashMap<String,String> message = new HashMap<>();
        teamService.deleteTeamById(id);
        message.put(Constants.MESSAGE,Constants.DELETE_TEAM_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TEAM_BY_ID)
    public ResponseEntity<TeamResponseModel> getTeamById(@RequestParam Integer id) {
        TeamResponseModel list = teamService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_TEAM_BY_ID)
    public ResponseEntity<Boolean> valiteById(@RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.valiteById(id));
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TEAM)
    public ResponseEntity<List<TeamRequestModel>> getTeam() {
        List<TeamRequestModel> list = teamService.getTeam();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
