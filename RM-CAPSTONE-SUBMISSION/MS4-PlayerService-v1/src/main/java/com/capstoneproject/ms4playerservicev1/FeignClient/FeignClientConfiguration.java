package com.capstoneproject.ms4playerservicev1.FeignClient;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate ->
          requestTemplate.header("X-Forwarded-Host","localhost:8081");
    }

}
