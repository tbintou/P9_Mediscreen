package com.bintou.mediscreen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "L'API des Patients",
                "Cette API utilise la méthode CRUD pour traiter les données des patients",
                "1.0",
                "http://www.mediscreen.com/tos",
                new Contact("Bintou", "www.mediscreen.com", "myaddress@mediscreen.com"),
                "License de API", "http://www.mediscreen.com/license", Collections.emptyList());
    }
}
