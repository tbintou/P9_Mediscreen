package com.bintou.mediscreen.rapport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MediscreenRapportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenRapportApplication.class, args);
	}

}
