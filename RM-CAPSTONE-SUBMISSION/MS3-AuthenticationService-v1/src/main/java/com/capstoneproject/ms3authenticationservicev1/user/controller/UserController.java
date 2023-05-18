package com.capstoneproject.ms3authenticationservicev1.user.controller;

import com.capstoneproject.ms3authenticationservicev1.common.APIMapping;
import com.capstoneproject.ms3authenticationservicev1.common.Constants;
import com.capstoneproject.ms3authenticationservicev1.user.entity.UserEntity;
import com.capstoneproject.ms3authenticationservicev1.user.entity.UserRequestModel;
import com.capstoneproject.ms3authenticationservicev1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_USER+ APIMapping.VERSION)
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping(APIMapping.POST_MAPPING_ENDPOINT_ADD_USER)
    public ResponseEntity<HashMap<String,String>> saveUser(@RequestBody UserRequestModel request) {
        HashMap<String,String> message = new HashMap<>();
        try {
            userService.addUser(request);
            message.put(Constants.MESSAGE,Constants.ADD_USER_SUCCESS_MESSAGE);
            message.put(Constants.STATUS, Constants.SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message.put(Constants.MESSAGE,e.getMessage());
            message.put(Constants.STATUS, Constants.FAILURE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_USER)
    public ResponseEntity<List<UserEntity>> getUser() {
        List<UserEntity> list = userService.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
