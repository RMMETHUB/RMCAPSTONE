package com.capstoneproject.ms3authenticationservicev1.user.service;

import com.capstoneproject.ms3authenticationservicev1.user.entity.UserRequestModel;
import com.capstoneproject.ms3authenticationservicev1.user.exception.UserAlreadyExistException;
import com.capstoneproject.ms3authenticationservicev1.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    public void addUser(UserRequestModel userEntity) throws UserAlreadyExistException;
    public UserEntity getUserById(UserEntity userEntity);
    public List<UserEntity> getUser();
    public boolean validateJwtToken(HttpServletRequest request) throws IllegalArgumentException;



}
