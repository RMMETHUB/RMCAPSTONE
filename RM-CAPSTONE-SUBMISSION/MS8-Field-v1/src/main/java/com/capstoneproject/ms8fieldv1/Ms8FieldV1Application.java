package com.capstoneproject.ms8fieldv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Ms8FieldV1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms8FieldV1Application.class, args);
	}

}
