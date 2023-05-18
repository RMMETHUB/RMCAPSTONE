package com.example.apigateway.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class RequestFilter extends AbstractGatewayFilterFactory<RequestFilter.Config> {
    @Autowired
    private RouteValidator validator;

    public RequestFilter(){
        super(Config.class);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            HttpHeaders headers = new HttpHeaders();

            if (validator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing authorization header");
                }
                headers.addAll(exchange.getRequest().getHeaders());
                try {
                    HttpEntity<String> entity = new HttpEntity<>("body", headers);
                    Boolean valid = restTemplate.postForObject("http://MS3-AUTHENTICATION-SERVICE-v1/user/v1/validateJwtToken", entity, Boolean.class);
                    Optional<Boolean> validOptional = Optional.ofNullable(valid);
                    if(validOptional.isEmpty()){
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Unauthorized request");
                    }
                }catch (RuntimeException e){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Unauthorized request");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }

}