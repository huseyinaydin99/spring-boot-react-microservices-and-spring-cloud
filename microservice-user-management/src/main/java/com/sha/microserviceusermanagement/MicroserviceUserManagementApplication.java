package com.sha.microserviceusermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserManagementApplication.class, args);
	}

}
