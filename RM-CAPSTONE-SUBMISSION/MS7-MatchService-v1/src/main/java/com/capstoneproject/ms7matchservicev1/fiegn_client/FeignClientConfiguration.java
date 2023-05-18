package com.capstoneproject.ms7matchservicev1.fiegn_client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate ->
          requestTemplate.header("X-Forwarded-Host","localhost:8081");
    }
}
