package com.bintou.mediscreen.front.proximity;


import com.bintou.mediscreen.front.model.Rapport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mediscreen-rapport", url = "${rapport.serviceUrl:http://localhost:8083}")
public interface RapportProximity {

    @GetMapping("/api/rapport/{id}")
    Rapport getPatientRapport(@PathVariable("id") final Long patientId);
}