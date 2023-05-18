package com.capstoneproject.ms3authenticationservicev1.user.service;

import com.capstoneproject.ms3authenticationservicev1.authentication.manager.TokenManager;
import com.capstoneproject.ms3authenticationservicev1.authentication.service.CustomUserDetailsService;
import com.capstoneproject.ms3authenticationservicev1.user.entity.UserEntity;
import com.capstoneproject.ms3authenticationservicev1.user.entity.UserRequestModel;
import com.capstoneproject.ms3authenticationservicev1.user.exception.AuthenticationException;
import com.capstoneproject.ms3authenticationservicev1.user.exception.UserAlreadyExistException;
import com.capstoneproject.ms3authenticationservicev1.user.exception.UserException;
import com.capstoneproject.ms3authenticationservicev1.user.repository.UserRepository;
import com.capstoneproject.ms3authenticationservicev1.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    TokenManager tokenManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Override
    public void addUser(UserRequestModel userEntity) throws UserAlreadyExistException {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).+$";
        if (userEntity.getUserName().equals("") || userEntity.getPassword().equals("")){
            throw new UserException(Constants.USER_MANDATORY_FIELD_EXCEPTION);
        }
        UserEntity userEntityList = userRepository.findUserByUserName(userEntity.getUserName());
        if(userEntityList == null){
            if (userEntity.getPassword().matches(pattern)){
                final String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
                UserEntity newUser = new UserEntity();
                newUser.setUserName(userEntity.getUserName());
                newUser.setPassword(encodedPassword);
                newUser.setEmail(userEntity.getEmail());
                newUser.setRole(userEntity.getRole());
                userRepository.save(newUser);
            }else {
                throw new UserException(Constants.INVALID_PASSWORD);
            }
        }else {
            throw new UserAlreadyExistException(Constants.USER_ALREADY_EXIST_EXCEPTION);
        }
    }



    @Override
    public UserEntity getUserById(UserEntity userEntity) {
        return null;
    }

    @Override
    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean validateJwtToken(HttpServletRequest request)  throws IllegalArgumentException{
        try {
            String tokenHeader = request.getHeader("Authorization");
            String username = null;
            String token = null;
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                token = tokenHeader.substring(7);
                username = tokenManager.getUsernameFromToken(token);
            } else {
                throw new AuthenticationException("Bearer String not found in token");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return tokenManager.validateJwtToken(token,userDetails);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

    }
}
