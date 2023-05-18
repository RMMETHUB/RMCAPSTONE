package com.capstoneproject.ms3authenticationservicev1.authentication.service;

import java.util.ArrayList;

import com.capstoneproject.ms3authenticationservicev1.user.entity.UserEntity;
import com.capstoneproject.ms3authenticationservicev1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userDetails = userRepository.findUserByUserNameAndAdmin(username);
        if (userDetails != null){
            if (userDetails.getUserName().equals(username)) {
                return new User(userDetails.getUserName(),
                        userDetails.getPassword(),
                        new ArrayList<>());
            }else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
