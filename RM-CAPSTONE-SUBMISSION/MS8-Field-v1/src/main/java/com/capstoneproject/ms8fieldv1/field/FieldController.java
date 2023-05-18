package com.capstoneproject.ms8fieldv1.field;


import com.capstoneproject.ms8fieldv1.common.APIMapping;
import com.capstoneproject.ms8fieldv1.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_FIELD+APIMapping.VERSION)
public class FieldController {
    @Autowired
    FieldService fieldService;

    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_FIELD)
    public ResponseEntity<HashMap<String,String>> savePlayer(@RequestBody FieldDTO request){
        HashMap<String,String> message = new HashMap<>();
        fieldService.addField(request);
        message.put(Constants.MESSAGE,Constants.ADD_FIELD_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping(APIMapping.PUT_MAPPING_ENDPOINT_UPDATE_FIELD)
    public ResponseEntity<HashMap<String,String>> updateTournament(@RequestBody FieldDTO requestModel) {
        HashMap<String,String> message = new HashMap<>();
        fieldService.updateField(requestModel);
        message.put(Constants.MESSAGE,Constants.UPDATE_FIELD_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping(APIMapping.DELETE_MAPPING_ENDPOINT_DELETE_FIELD)
    public ResponseEntity<HashMap<String,String>> deleteTeam(@RequestParam Integer id) {
        HashMap<String,String> message = new HashMap<>();
        fieldService.deleteFieldById(id);
        message.put(Constants.MESSAGE,Constants.DELETE_FIELD_SUCCESS_MESSAGE);
        message.put(Constants.STATUS, Constants.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_FIELD_BY_ID)
    public ResponseEntity<Boolean> validateFieldBYId(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(fieldService.validateFieldById(id));

    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_FIELD_BY_ID)
    public ResponseEntity<FieldDTO> getFieldById(@RequestParam Integer id) {
        FieldDTO list = fieldService.getFieldById(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_FIELD)
    public ResponseEntity<List<FieldDTO>> getField() {
        List<FieldDTO> list = fieldService.getAllField();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
