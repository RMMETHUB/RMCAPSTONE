package com.capstoneproject.ms9ticketservicev1.feign_client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate ->
          requestTemplate.header("X-Forwarded-Host","localhost:8081");
    }

}
