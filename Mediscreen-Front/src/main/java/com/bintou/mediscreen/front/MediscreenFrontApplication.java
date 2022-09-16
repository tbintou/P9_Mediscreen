package com.bintou.mediscreen.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MediscreenFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenFrontApplication.class, args);
	}

}
