package com.capstoneproject.ms5teamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Ms5TeamServiceV1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms5TeamServiceV1Application.class, args);
	}

}
