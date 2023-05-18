package com.capstoneproject.ms3authenticationservicev1.authentication.controller;
import com.capstoneproject.ms3authenticationservicev1.authentication.manager.TokenManager;
import com.capstoneproject.ms3authenticationservicev1.authentication.model.JwtRequestModel;
import com.capstoneproject.ms3authenticationservicev1.common.APIMapping;
import com.capstoneproject.ms3authenticationservicev1.authentication.model.JwtResponseModel;
import com.capstoneproject.ms3authenticationservicev1.authentication.service.CustomUserDetailsService;
import com.capstoneproject.ms3authenticationservicev1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = APIMapping.REQUEST_MAPPING_ENDPOINT_USER+ APIMapping.VERSION)
public class JWTController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    UserService userService;

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping(APIMapping.LOGIN)
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel
                                                request) throws DisabledException,BadCredentialsException {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
    @PostMapping(APIMapping.VALIDATE_JWT_TOKEN)
    public ResponseEntity<Boolean> validateJwtToken(HttpServletRequest request){
        return ResponseEntity.ok(userService.validateJwtToken(request));
    }
}