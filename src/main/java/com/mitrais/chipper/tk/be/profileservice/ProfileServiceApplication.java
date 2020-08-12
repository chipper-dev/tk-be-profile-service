package com.mitrais.chipper.tk.be.profileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableFeignClients
@EnableEurekaClient
//@EnableCircuitBreaker
//@EnableHystrixDashboard
//@EnableHystrix
@SpringBootApplication
@EnableAutoConfiguration
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

}
