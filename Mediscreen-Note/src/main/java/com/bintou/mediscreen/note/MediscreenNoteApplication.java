package com.bintou.mediscreen.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableFeignClients
public class MediscreenNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenNoteApplication.class, args);
	}

}
